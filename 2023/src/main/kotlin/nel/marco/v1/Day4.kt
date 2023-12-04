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

        return readInput.map {
            val afterGame = it.split(":")[1]
            val winningNumbers = extractNumbers(afterGame, 0).toList()
            val firstSet = extractNumbers(afterGame, 1).toList()

            winningNumbers.intersect(firstSet).count()

        }
            .filter { it > 0 } // only winning counts
            .map { Math.pow(2.0, it.toDouble() - 1).toInt() } // calculate actual points
            .sum()
            .toString()
    }

    private fun extractNumbers(afterGame: String, side: Int = 0) =
        "\\d+".toRegex().findAll(afterGame.split("|")[side]).map {
            it.value
        }.toList()

    fun answerTwo(): String {

        var copies = readInput.toMutableList()
        var mapCounter = mutableMapOf<String, Long>()
        while (copies.isNotEmpty()) {
            var it = copies[0]
            val gameNumber = it.split(":")[0]
            mapCounter.putIfAbsent(gameNumber, 0)
            val winningNumbers = extractNumbers(it.split(":")[1], 0).toList()
            val firstSet = extractNumbers(it.split(":")[1], 1).toList()
            val matching = winningNumbers.intersect(firstSet).toList().count()

            val countOfCopies = copies.count { it == copies[0] }
            runCatching {
                var tempList = mutableListOf<String>()
                val uniqueSet = copies.toSet().toList()
                for (x in 1..matching) {
                    val element = uniqueSet[x]
                    tempList.add(element)
                }

                for (x in 1..countOfCopies) {
                    copies.addAll(tempList)
                }
                copies.sort()
            }


            mapCounter[gameNumber] = mapCounter[gameNumber]!! + countOfCopies

            copies.removeIf { it == copies[0] }
        }

        return mapCounter.values.sum().toString()
    }

}