package nel.marco

import kotlin.math.absoluteValue


class Day1(useExample: Boolean = false, useMac: Boolean = false) :
    Day(dayNumber = 1, useExample = useExample, macBook = useMac) {

    override fun answerOne(): String {

        val (leftList, rightList) = readInput
            .map { it.split("   ") }
            .map { (a, b) -> a.toLong() to b.toLong() }
            .unzip()
            .let { (a, b) -> a.sorted() to b.sorted() }

        return leftList
            .zip(rightList) { left, right -> right - left }
            .sumOf { it.absoluteValue }
            .toString()

    }

    override fun answerTwo(): String {
        val (leftList, rightList) = readInput
            .map { it.split("   ") }
            .map { (a, b) -> a.toLong() to b.toLong() }
            .unzip()
            .let { (a, b) -> a.sorted() to b }

        val map = rightList.groupingBy { it }.eachCount()

        return leftList
            .sumOf { it * map.getOrDefault(it, 0) }
            .toString()

    }
}
