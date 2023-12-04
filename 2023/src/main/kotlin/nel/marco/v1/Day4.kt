package nel.marco.v1

import kotlin.math.pow


class Day4(readInput: List<String>) : Day(readInput) {
    override fun answerOne(): String {

        return readInput
            .map { it.split(":")[1] } // Get everything after ":"
            .map { it.extractNumbers() } // get winning and actual number pair
            .map { it.intersect().count() } // intersect and count the amount of winning numbers
            .filter { it > 0 } // only wining numbers
            .sumOf { 2.0.pow(it.toDouble() - 1).toInt() } // calculate points and sum it
            .toString()
    }


    override fun answerTwo(): String {
        val gameTickets = readInput.toMutableList()
        val originalList = readInput.toMutableList()

        val mapCounter = mutableMapOf<String, Long>()

        while (gameTickets.isNotEmpty()) {
            val currentGameTicket = gameTickets[0]
            val split = currentGameTicket.split(":")
            val timesWon = split[1]
                .extractNumbers()
                .intersect()
                .count()

            val countOfGameTicket = gameTickets.count { x -> x == currentGameTicket }

            // Add the number of times current ticket won
            val gameNumber = split[0]
            mapCounter.putIfAbsent(gameNumber, 0)
            mapCounter[gameNumber] = mapCounter[gameNumber]!! + countOfGameTicket


            // Create the necessary copies of "tickets" i won
            for (nextTicketIndex in 1..timesWon) {
                for (repeat in 1..countOfGameTicket) {
                    gameTickets.add(originalList[nextTicketIndex])
                }
            }

            // cleanup tickets
            gameTickets.removeIf { it == currentGameTicket }
            originalList.removeAt(0)
        }

        return mapCounter.values.sum().toString()
    }


    private fun extractNumbers(input: String) =
        "\\d+".toRegex()
            .findAll(input)
            .map { it.value }
            .toList()

    private fun String.extractNumbers(): Pair<List<String>, List<String>> {
        val split = this.split("|")
        val winningNumbers = extractNumbers(split[0])
        val actualNumbers = extractNumbers(split[1])
        return Pair(winningNumbers, actualNumbers)
    }

    private fun Pair<List<String>, List<String>>.intersect() = with(this) {
        first.intersect(second)
    }

}