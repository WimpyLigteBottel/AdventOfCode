package nel.marco.v1

import nel.marco.MarcoUtil


fun main(args: Array<String>) {
    var readAllLines = MarcoUtil.readInput(4, true) as MutableList<String>
    val day = Day4(readAllLines)
    executeTimes("ANSWER 1") {
        day.answerOne()
    }
    executeTimes("ANSWER 2") {
        day.answerTwo()
    }
}


class Day4(var readInput: List<String>) {

    fun answerOne(): String {

        return readInput
            .map { it.split(":")[1] } // Get everything after ":"
            .map { it.extractWinningAndActualNumberPair() } // get winning and actual number pair
            .map { it.intersect().count() } // intersect and count the amount of winning numbers
            .filter { it > 0 } // only wining numbers
            .sumOf { Math.pow(2.0, it.toDouble() - 1).toInt() } // calculate points and sum it
            .toString()
    }

    private fun extractWinningAndActualNumberPair(afterGame: String, side: Int = 0) =
        "\\d+".toRegex()
            .findAll(afterGame.split("|")[side])
            .map { it.value }
            .toList()

    private fun String.extractWinningAndActualNumberPair(): Pair<List<String>, List<String>> {
        val winningNumbers = extractWinningAndActualNumberPair(this, 0)
        val actualNumbers = extractWinningAndActualNumberPair(this, 1)
        return Pair(winningNumbers, actualNumbers)
    }

    private fun Pair<List<String>, List<String>>.intersect() = this.second.intersect(this.first)

    fun answerTwo(): String {
        var gameTickets = readInput.toMutableList()
        var mapCounter = mutableMapOf<String, Long>()
        var uniqueSet = gameTickets.toList()
        while (gameTickets.isNotEmpty()) {
            var currentGameTicket = gameTickets[0]
            val timesWon = currentGameTicket.split(":")[1]
                .extractWinningAndActualNumberPair()
                .intersect()
                .count()

            val copiesOfGameTicket = gameTickets.count { x -> x == currentGameTicket }

            // Add the number of times current ticket won
            val gameNumber = currentGameTicket.split(":")[0]
            mapCounter.putIfAbsent(gameNumber, 0)
            mapCounter[gameNumber] = mapCounter[gameNumber]!! + copiesOfGameTicket

            // There could be error one (there are not more tickets after winning)
            runCatching {
                for (nextTicketIndex in 1..timesWon) {
                    for (repeat in 1..copiesOfGameTicket) {
                        gameTickets.add(uniqueSet[nextTicketIndex])
                    }
                }
                // remove all the duplicates because they have been processed and speed up the process
                gameTickets.removeIf { it == currentGameTicket }
            }
            // remove the first element in the unique set
            uniqueSet = uniqueSet.subList(1, uniqueSet.size)
        }

        return mapCounter.values.sum().toString()
    }

}