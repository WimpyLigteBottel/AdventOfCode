package nel.marco.v1

import nel.marco.MarcoUtil


class Day6(var readInput: List<String>) {

    companion object {
        val day: Int = 6
    }

    fun process(input: String, distinct: Int = 4): Int {
        val maxLength = input.length - distinct
        for (x in 0..maxLength) {
            val substringUnique = input.substring(x, x + distinct).toSet()
            if (substringUnique.size == distinct) {
                return x + distinct
            }
        }
        return -1
    }

    fun answerOne(): String {
        val buffer = readInput.get(0)
        return process(buffer).toString()
    }

    fun answerTwo(): String {
        val buffer = readInput.get(0)
        return process(buffer, 14).toString()
    }

}

fun main(args: Array<String>) {
    var readAllLines = MarcoUtil.readInput(Day6.day, false) as MutableList<String>

    val part1: () -> Unit = {
        val day = Day6(readAllLines).process(readAllLines.get(0))
//        println("part1 = ${day.answerOne()}")
    }
    val part2: () -> Unit = {
        val day = Day6(readAllLines).process(readAllLines.get(0),14)
//        println("part2 = ${day.answerTwo()}")
    }

    for (x in 0..10) {
        MarcoUtil.avgTime("part1", part1)
        MarcoUtil.avgTime("part2", part2)
        println("XXXXXXXXXXXXX")
    }

}