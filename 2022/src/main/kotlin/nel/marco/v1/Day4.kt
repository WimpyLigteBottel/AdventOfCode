package nel.marco.v1

import nel.marco.MarcoUtil
import java.util.stream.IntStream

class Day4(var readInput: List<String>) {

    fun String.makeRange(): String {
        val numbers = this.split("-")

        if (numbers[0].toInt() == numbers[1].toInt()) {
            return "|${numbers[0]}|"
        }

        return "|" + IntStream.rangeClosed(numbers[0].toInt(), numbers[1].toInt())
            .boxed().toList().joinToString("|") + "|"
    }

    fun answerOne(): String {

        var counter = 0

        readInput.forEach {
            val elves = it.split(",")
            val cleaning1 = elves[0].makeRange()
            val cleaning2 = elves[1].makeRange()


            if (containEachOther(cleaning2, cleaning1)) {
                counter++
            }
        }

        return "$counter"
    }

    fun containEachOther(cleaning2: String, cleaning1: String): Boolean {
        return cleaning2.contains(cleaning1) || cleaning1.contains(cleaning2)
    }

    fun String.between(min: Int, max: Int): Boolean {
        return this.toInt() in (min..max)
    }


    fun answerTwo(): String {
        var counter = 0
        readInput.forEach {
            val elves = it.split(",")
            val cleaning1 = elves[0].split("-")
            val cleaning2 = elves[1].split("-")


            if (cleaning1[0].between(cleaning2[0].toInt(), cleaning2[1].toInt()))
                counter++
            else if (cleaning1[1].between(cleaning2[0].toInt(), cleaning2[1].toInt()))
                counter++
            else if (cleaning2[0].between(cleaning1[0].toInt(), cleaning1[1].toInt()))
                counter++
            else if (cleaning2[1].between(cleaning1[0].toInt(), cleaning1[1].toInt()))
                counter++
        }

        return "$counter"
    }

}

fun main(args: Array<String>) {
    var readAllLines = MarcoUtil.readInput(4, false) as MutableList<String>

    val part1: () -> Unit = {
        val day = Day4(readAllLines)
        println("part1 = ${day.answerOne()}")
    }
    val part2: () -> Unit = {
        val day = Day4(readAllLines)
        println("part2 = ${day.answerTwo()}")
    }
    MarcoUtil.time("part1", part1)
    MarcoUtil.time("part2", part2)
}


