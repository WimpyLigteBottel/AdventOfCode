package nel.marco.v1

import nel.marco.MarcoUtil
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    var readAllLines = MarcoUtil.readInput(1, true) as MutableList<String>

    val day1 = Day1(readAllLines)
    executeTimes("ANSWER 1") {
        day1.answerOne()
    }
    executeTimes("ANSWER 2") {
        day1.answerTwo()
    }
}

fun executeTimes(name: String = "", block: () -> Unit) {
    var total = 0L
    for (x in 0..1000)
        total += measureTimeMillis(block)

    println("$name -> " + total + "ms")
}

class Day1(var readInput: List<String>) {
    var map = mutableMapOf<String, String>()
    var mapReversed = mutableMapOf<String, String>()

    init {
        map["one"] = "1"
        map["two"] = "2"
        map["three"] = "3"
        map["four"] = "4"
        map["five"] = "5"
        map["six"] = "6"
        map["seven"] = "7"
        map["eight"] = "8"
        map["nine"] = "9"
        mapReversed["one".reversed()] = "1"
        mapReversed["two".reversed()] = "2"
        mapReversed["three".reversed()] = "3"
        mapReversed["four".reversed()] = "4"
        mapReversed["five".reversed()] = "5"
        mapReversed["six".reversed()] = "6"
        mapReversed["seven".reversed()] = "7"
        mapReversed["eight".reversed()] = "8"
        mapReversed["nine".reversed()] = "9"
    }

    fun answerOne(): String {

        var letters = mutableListOf<String>()
        var mutableList = mutableListOf<String>()

        for (x in 'a'..'z') {
            letters.add(x.toString())
        }

        readInput.forEachIndexed { index, s ->
            var string = s + ""
            letters.forEach {
                string = string.replace(it, "")
            }
            mutableList.add(string)
        }

        var sum = 0
        mutableList.forEach {
            val first = it.trim().first().toString()
            val last = it.reversed().trim().first().toString()
            sum += (first + last).toInt()
        }

        return "$sum"
    }

    fun answerTwo(): String {
        var sum = 0L
        readInput.forEach {
            val together = it.findFirstAndLast()
            sum += together.toLong()
        }

        return "$sum"
    }

    private fun String.findFirstAndLast(): String {
        var first = findFirstDigit(this, map)
        var last = findFirstDigit(this.reversed(), mapReversed)

        return first + last;
    }

    private fun findFirstDigit(inputNotMutable: String, map: MutableMap<String, String>): String {

        var input = inputNotMutable
        outerloop@ for (start in 0..input.length) {//innerLoop
            for (end in start + 1..input.length) {
                var selection = inputNotMutable.substring(start, end)
                for ((key, value) in map) {
                    if (selection.contains(key)) {
                        input = input.replaceFirst(key, value)
                        break@outerloop
                    }
                }
            }
        }
        input = input.replaceRemainingLetters()

        return input.first().toString()
    }

    private fun String.replaceRemainingLetters(): String {
        var letters = mutableListOf<String>()
        for (x in 'a'..'z') {
            letters.add(x.toString())
        }

        var string = this
        letters.forEach {
            string = string.replace(it, "")
        }

        return string
    }
}
