package nel.marco.v1

import nel.marco.MarcoUtil


fun main(args: Array<String>) {
    var readAllLines = MarcoUtil.readInput(1, false) as MutableList<String>

    val part1: () -> Unit = {
        val day = Day1(readAllLines)
        println("part1 = ${day.answerOne()}")
    }
    val part2: () -> Unit = {
        val day = Day1(readAllLines)
        println("part2 = ${day.answerTwo()}")
    }
    MarcoUtil.time("part1", part1)
    MarcoUtil.time("part2", part2)
}

class Day1(var readInput: List<String>) {

    fun answerOne(): String {
        val listOfCalories = readInput.map { s ->
            s.split("\n")
                .filter { it != "" }
                .sumOf {
                    Integer.parseInt(it)
                }
        }.sortedDescending()

        return listOfCalories[0].toString()
    }

    fun answerTwo(): String {
        val listOfCalories = readInput.map { s ->
            s.split("\n")
                .filter { it != "" }
                .sumOf {
                    Integer.parseInt(it)
                }
        }.sortedDescending()

        return listOfCalories.subList(0, 3).sum().toString()
    }
}
