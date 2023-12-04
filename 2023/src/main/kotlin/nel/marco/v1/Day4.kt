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
        var copies = readInput.toMutableList()
        var mapCounter = mutableMapOf<String, Long>()
        var uniqueSet = copies.toList()
        while (copies.isNotEmpty()) {

            val timesWon = copies[0].split(":")[1]
                .extractWinningAndActualNumberPair()
                .intersect()
                .count()

            val countOfCopies = copies.count { x -> x.startsWith(copies[0]) }

            val gameNumber = copies[0].split(":")[0]
            mapCounter.putIfAbsent(gameNumber, 0)
            mapCounter[gameNumber] = mapCounter[gameNumber]!! + countOfCopies


            runCatching {
                var tempList = mutableListOf<String>()
                for (x in 1..timesWon) {
                    tempList.add(uniqueSet[x])
                }
                for (x in 1..countOfCopies) {
                    copies.addAll(tempList)
                }
                // remove all the duplicates because they have been processed
                copies.removeIf { it == copies[0] }

                // sort the remaining once's
                copies.sort()
            }
            // remove the first element in the unique set
            uniqueSet = uniqueSet.subList(1, uniqueSet.size)
        }

        return mapCounter.values.sum().toString()
    }

}