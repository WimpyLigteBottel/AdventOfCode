package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test

class Day6Test {

    val input = ReadUtil.readInputAsList(6, exampleInput = false, macBook = false)
    val exampleInput = ReadUtil.readInputAsList(6, exampleInput = true, macBook = false)


    @Test
    fun answerOne() {
        assertEquals("41", Day6(exampleInput).answerOne())
        assertEquals("4433", Day6(input).answerOne())
    }


    @Test
    fun answerTwo() {
        assertEquals("6", Day6(exampleInput).answerTwo())
        assertEquals("1516", Day6(input).answerTwo())
    }

    @Test
    fun avg() {
        val day = Day6(input)
        MarcoUtil.avgTime("part1",5) {
            assertEquals("4433", day.answerOne())
        }
        MarcoUtil.avgTime("part2",30) {
            assertEquals("1516", day.answerTwo())
        }
    }
}