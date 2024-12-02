package nel.marco

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class Day2Test {

    @Test
    fun answerOne() {
        assertEquals("2", Day2(ReadUtil.readInputAsList(2, exampleInput = true, macBook = false)).answerOne())
        assertEquals("213", Day2(ReadUtil.readInputAsList(2, macBook = false)).answerOne())
    }


    @Test
    fun answerTwo() {
        assertEquals("4", Day2(ReadUtil.readInputAsList(2, exampleInput = true, macBook = false)).answerTwo())
        assertEquals("285", Day2(ReadUtil.readInputAsList(2, macBook = false)).answerTwo())
    }


    @Test
    fun avg() {
        val input = Day2(ReadUtil.readInputAsList(2, macBook = false))
        MarcoUtil.avgTime("part1") {
            assertEquals("213", input.answerOne())
        }
        MarcoUtil.avgTime("part2") {
            assertEquals("285", input.answerTwo())
        }
    }
}