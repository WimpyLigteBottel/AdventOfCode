package nel.marco.v1

import java.math.BigInteger
import kotlin.collections.Map

class Day8(readInput: List<String>) : Day(readInput) {


    override fun answerOne(): String {
        var map = getMap()

        return howManyStepsToGetToEnd(map["AAA"]!!, map["ZZZ"]!!, map).toString()
    }

    private fun howManyStepsToGetToEnd(
        starting: Pair<String, String>,
        endingNode: Pair<String, String>,
        map: Map<String, Pair<String, String>>,
    ): Long {
        var current = starting.copy()
        var counter = 0L
        while (current != endingNode) {
            readInput[0].forEach { direction ->
                if (current == endingNode) {
                    return@forEach
                }
                counter++

                if (direction == 'R') {
                    current = map[current.second]!!
                } else if (direction == 'L') {
                    current = map[current.first]!!
                }
            }

        }
        return counter
    }

    private fun takeOneStep(
        current: Pair<String, String>,
        direction: Char,
    ): String {
        if (direction == 'R') {
            return current.second
        }

        return current.first
    }


    override fun answerTwo(): String {
        var map = getMap()

        var listOfStartingPoints = map.keys.filter { it.endsWith("A") }
            .map { it to map[it]!! }
        var instructions = readInput[0]

        return listOfStartingPoints
            .map { continueTillNextZ(it, instructions, map) } // find when the first "Z" is found at the end
            .map { it.toBigInteger() } // so that i can handle big numbers
            .reduce { total, bigInteger -> // reducing the number by finding the common deviding number  and then times it with iself
                total
                    .times(bigInteger)
                    .divide(BigInteger(instructions.length.toString())) // reduce the big number down
            }.toString()
    }

    private fun continueTillNextZ(
        starting: Pair<String, Pair<String, String>>,
        instructions: String,
        map: Map<String, Pair<String, String>>,
    ): Int {
        var selected = starting.copy()
        var internalCounter = 0
        var notSolved = true
        while (notSolved) {
            for (direction in instructions) {
                val step = takeOneStep(selected.second, direction)
                selected = step to map[step]!!
                internalCounter++
                if (selected.first.last() == 'Z') {
                    notSolved = false
                    break
                }
            }
        }

        return internalCounter
    }

    private fun getMap() = readInput.subList(2, readInput.size)
        .map {
            val key = it.substring(0, 3)
            val value = getLeft(it) to getRight(it)
            key to value
        }.toMap()


    private fun getRight(it: String): String {
        val rightStart = it.indexOf(")")
        val pathR = it.substring(rightStart - 3, rightStart)
        return pathR
    }

    private fun getLeft(it: String): String {
        val leftStart = it.indexOf("(")
        val pathL = it.substring(leftStart + 1, leftStart + 4)
        return pathL
    }


}