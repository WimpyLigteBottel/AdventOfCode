package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test

class Day4Test {

    val input = ReadUtil.readInputAsList(4, exampleInput = false, macBook = false)
    val exampleInput = ReadUtil.readInputAsList(4, exampleInput = true, macBook = false)


    @Test
    fun answerOne() {
        assertEquals("18", Day4(exampleInput).answerOne())
        assertEquals("2578", Day4(input).answerOne())
    }


    @Test
    fun answerTwo() {
        assertEquals("9", Day4(exampleInput).answerTwo())
        assertEquals("1972", Day4(input).answerTwo())
    }


    @Test
    fun avg() {
        val day = Day4(input)
        MarcoUtil.avgTime("part1",5) {
            assertEquals("2578", day.answerOne())
        }
        MarcoUtil.avgTime("part2",5) {
            assertEquals("1972", day.answerTwo())
        }
    }
}