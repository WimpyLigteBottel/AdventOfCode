package nel.marco

import kotlin.math.absoluteValue


class Day1(readInput: List<String>) : Day(readInput) {

    override fun answerOne(): String {

        val leftList = readInput.map { it.split("   ")[0] }.map { it.toInt() }.sorted()
        val rightList = readInput.map { it.split("   ")[1] }.map { it.toInt() }.sorted()

        return leftList
            .zip(rightList) { left, right -> right - left }
            .sumOf { it.absoluteValue }
            .toString()

    }

    override fun answerTwo(): String {
        var leftList = readInput.map { it.split("   ")[0] }.map { it.toInt() }.sorted()
        var rightList = readInput.map { it.split("   ")[1] }.map { it.toInt() }

        var map = mutableMapOf<Int, Int>()

        rightList.forEach {
            map.putIfAbsent(it, 0)
            map[it] = map[it]!! + 1
        }

        return leftList
            .sumOf { it * map.getOrDefault(it, 0) }
            .toString()

    }
}
