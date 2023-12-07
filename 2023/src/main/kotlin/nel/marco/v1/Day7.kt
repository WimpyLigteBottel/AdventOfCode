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
        .map { it.key.bid * it.value }
        .sum()

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


val jokerMap = mutableMapOf<String, Long>(
    "A" to 14,
    "K" to 13,
    "Q" to 12,
    "T" to 10,
    "9" to 9,
    "8" to 8,
    "7" to 7,
    "6" to 6,
    "5" to 5,
    "4" to 4,
    "3" to 3,
    "2" to 2,
    "J" to 1
)

fun String.toPlayer(useJokerRule: Boolean = false): Player {
    val hand = if (useJokerRule) {
        this.substring(0, 5)
            .map { Card(value = it.toString(), points = jokerMap[it.toString()]!!.toLong()) }
            .toList()
    } else {
        this.substring(0, 5)
            .map { Card(value = it.toString(), points = mapValue[it.toString()]!!.toLong()) }
            .toList()
    }

    return Player(
        cards = hand,
        jokerHand = hand.makeStrongestWithJokers(),
        bid = this.substring(6).toLong(),
        useJokerRule = useJokerRule
    )
}

private fun List<Card>.makeStrongestWithJokers(): List<Card> {
    val possibleHands = this.filter { it.value != "J" }
        .map { possibleOption ->
            this.map {
                if (it.value == "J") return@map possibleOption
                return@map it
            }
        }

    if (possibleHands.isEmpty())
        return this

    return getStrongestHand(possibleHands)
}

private fun getStrongestHand(possibleHands: List<List<Card>>): List<Card> {
    return possibleHands.reduce { acc, cards ->
        if (Player(acc).beats(Player(cards))) {
            return@reduce acc // was stronger
        }
        return@reduce cards // was stronger
    }
}

data class Card(var value: String, var points: Long)


data class Rules(var cards: List<Card> = emptyList()) {

    fun apply(): Int {
        if (ofKind(5)) {
            return 6
        } else if (ofKind(4)) {
            return 5
        } else if (hasFullHouse()) {
            return 4
        } else if (ofKind(3)) {
            return 3
        } else if (hasTwoPairs()) {
            return 2
        } else if (ofKind(2)) {
            return 1
        }
        return 0
    }

    private fun ofKind(amount: Int) = cards
        .groupBy { it.value }
        .values
        .map { it.size }
        .find { it == amount } != null

    private fun hasFullHouse() = ofKind(3) && ofKind(2)

    private fun hasTwoPairs(): Boolean {
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
    val useJokerRule: Boolean = false,
) {
    fun beats(cardHandB: Player): Boolean {
        val playerAScore = getScore()
        val playerBScore = cardHandB.getScore()

        if (playerAScore == playerBScore) {
            return doesABeatB(cards, cardHandB.cards)
        }

        return playerAScore > playerBScore
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

    private fun getScore(): Int {
        if (useJokerRule) {
            return Rules(jokerHand).apply()
        }
        return Rules(cards).apply()
    }

}