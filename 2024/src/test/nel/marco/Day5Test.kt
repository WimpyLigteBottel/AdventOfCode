package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test

class Day5Test {

    val input = ReadUtil.readInputAsList(5, exampleInput = false, macBook = false)
    val exampleInput = ReadUtil.readInputAsList(5, exampleInput = true, macBook = false)


    @Test
    fun answerOne() {
        assertEquals("143", Day5(exampleInput).answerOne())
        assertEquals("6260", Day5(input).answerOne())
    }


    @Test
    fun answerTwo() {
        assertEquals("123", Day5(exampleInput).answerTwo())
        assertEquals("5346", Day5(input).answerTwo())
    }


    @Test
    fun avg() {
        val day = Day5(input)
        MarcoUtil.avgTime("part1",5) {
            assertEquals("6260", day.answerOne())
        }
        MarcoUtil.avgTime("part2",5) {
            assertEquals("5346", day.answerTwo())
        }
    }
}