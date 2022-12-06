package nel.marco

import java.util.*


class Day6(var readInput: List<String>) {

    companion object {
        val day: Int = 6
    }

    fun process(input: String, distinct: Int = 4): Int {
        for (x in 0..input.length - distinct) {
            if (input.substring(x, x + distinct).toSet().size == distinct) {
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
        val day = Day5(readAllLines)
        println("part1 = ${day.answerOne()}")
    }
    val part2: () -> Unit = {
        val day = Day5(readAllLines)
        println("part2 = ${day.answerTwo()}")
    }
    MarcoUtil.time("part1", part1)
    MarcoUtil.time("part2", part2)
}