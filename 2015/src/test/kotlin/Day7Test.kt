import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day7Test {

    @Test
    fun part1() {
        val toList = """
            123 -> x
            456 -> y
            x AND y -> d
            x OR y -> e
            x LSHIFT 2 -> f
            y RSHIFT 2 -> g
            NOT x -> h
            NOT y -> i
        """.trimIndent()
            .split("\n")
            .toList()


        val day7 = Day7(toList)
        kotlin.test.assertEquals(123, day7.part1())
    }

    @Test
    fun part2() {
    }
}