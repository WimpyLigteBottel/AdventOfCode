package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day8Test {

    val input = ReadUtil.readInputAsList(8, exampleInput = false, macBook = false)
    val exampleInput = ReadUtil.readInputAsList(8, exampleInput = true, macBook = false)

    @Test
    fun answerOne() {
        assertEquals("14", Day8(exampleInput).answerOne())
        assertEquals("244", Day8(input).answerOne())
    }

    @Test
    fun answerTwo() {
        assertEquals("34", Day8(exampleInput).answerTwo())
        assertEquals("", Day8(input).answerTwo())
    }

    @Test
    fun avg() {
        val day = Day8(input)
        MarcoUtil.avgTime("part1", 5) {
            assertEquals("244", day.answerOne())
        }
//        MarcoUtil.avgTime("part2", 5) {
//            assertEquals("X", day.answerTwo())
//        }
    }
}