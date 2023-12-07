package nel.marco.v1

class Day7(readInput: List<String>) : Day(readInput) {


    override fun answerOne(): String {
        var handsWithRanks: MutableMap<Player, Int>

        handsWithRanks = readInput
            .map { it.toPlayer(false) to 1 }
            .toMap() as MutableMap<Player, Int>

        rankHands(handsWithRanks)

        return "${calculateScore(handsWithRanks)}"
    }


    override fun answerTwo(): String {
        var handsWithRanks: MutableMap<Player, Int>

        handsWithRanks = readInput
            .map { it.toPlayer(useJokerRule = true) to 1 }
            .toMap() as MutableMap<Player, Int>

        rankHands(handsWithRanks)

        return "${calculateScore(handsWithRanks)}"
    }

    private fun calculateScore(handsWithRanks: MutableMap<Player, Int>) = handsWithRanks
        .map {
            val bid = it.key.bid
            val rank = it.value
            bid * rank
        }.sum()

    private fun rankHands(handsWithRanks: MutableMap<Player, Int>) {
        handsWithRanks.forEach { parent ->
            handsWithRanks.forEach { child ->
                if (parent.key.beats(child.key)) {
                    handsWithRanks.put(parent.key, parent.value + 1)
                }
            }
        }
    }
}

fun String.toPlayer(useJokerRule: Boolean = false): Player {
    val mapValue = mutableMapOf<String, Long>(
        "A" to 14,
        "K" to 13,
        "Q" to 12,
        "J" to 11,
        "T" to 10,
        "9" to 9,
        "8" to 8,
        "7" to 7,
        "6" to 6,
        "5" to 5,
        "4" to 4,
        "3" to 3,
        "2" to 2
    )

    if (useJokerRule) {
        mapValue["J"] = 1
    }

    var hand = this.split(" ")[0].split("")
        .filter { it.isNotBlank() }
        .map { Card(value = it, points = mapValue[it]!!) }
        .toList()

    return Player(
        cards = hand,
        jokerHand = hand.makeStrongestWithJokers(),
        bid = this.split(" ")[1].toLong(),
        useJokerRule = useJokerRule
    )
}

private fun List<Card>.makeStrongestWithJokers(): List<Card> {
    var handWithoutJoker = this.filter { it.value != "J" }

    var possibleHands = handWithoutJoker.map { possibleOption ->
        this.map {
            if (it.value == "J") return@map possibleOption
            return@map it
        }
    }

    if (possibleHands.isEmpty())
        return this

    // get the strongest hand possible
    val strongestHand = possibleHands.reduce { acc, cards ->
        if (Player(acc).beats(Player(cards))) {
            return@reduce acc // was stronger
        }
        return@reduce cards // was stronger
    }
    return strongestHand
}

data class Card(var value: String, var points: Long)


data class Rules(var cards: List<Card> = emptyList()) {

    fun ofKind(amount:Int): Boolean {
        return cards
            .groupBy { it.value }
            .values
            .map { it.size }
            .find { it == amount } != null
    }

    fun hasFullHouse() = ofKind(3) && ofKind(2)

    fun hasTwoPairs(): Boolean {
        return cards
            .groupBy { it.value }
            .values
            .map { it.size }
            .count { it == 2 } == 2
    }
}


data class Player(
    var cards: List<Card> = emptyList(),
    var jokerHand: List<Card> = emptyList(),
    var bid: Long = 0,
    var totalWinning: Long = 0,
    val useJokerRule: Boolean = false,
) {
    fun beats(cardHandB: Player): Boolean {
        if (getHandScore() == cardHandB.getHandScore()) {
            return doesABeatB(cards, cardHandB.cards)
        }

        return getHandScore() > cardHandB.getHandScore()
    }

    fun doesABeatB(a: List<Card>, b: List<Card>): Boolean {
        a.forEachIndexed { index, _ ->
            val cardA = a[index]
            val cardB = b[index]
            if (cardA != cardB) {
                return cardA.isCardBetterThan(cardB)
            }
        }

        return false
    }

    fun Card.isCardBetterThan(cardB: Card): Boolean {
        return this.points > cardB.points
    }

    fun getHandScore(): Int {
        var rules = Rules(cards)
        if (useJokerRule) {
            rules = Rules(jokerHand)
        }

        if (rules.ofKind(5)) {
            return 6
        } else if (rules.ofKind(4)) {
            return 5 // four kind
        } else if (rules.hasFullHouse()) {
            return 4 // full house
        } else if (rules.ofKind(3)) {
            return 3
        } else if (rules.hasTwoPairs()) {
            return 2
        } else if (rules.ofKind(2)) {
            return 1
        }

        return 0
    }

}