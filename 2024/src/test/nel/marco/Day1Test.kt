package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day1Test {

    @Test
    fun answerOne() {
        assertEquals("11", Day1(ReadUtil.readInputAsList(1, exampleInput = true, macBook = true)).answerOne())
        assertEquals("2375403", Day1(ReadUtil.readInputAsList(1, macBook = true)).answerOne())
    }


    @Test
    fun answerTwo() {
        assertEquals("31", Day1(ReadUtil.readInputAsList(1, exampleInput = true, macBook = true)).answerTwo())
        assertEquals("23082277", Day1(ReadUtil.readInputAsList(1, macBook = true)).answerTwo())
    }


    fun avgTime() {
        val input = Day1(ReadUtil.readInputAsList(1, macBook = true))
        MarcoUtil.avgTime("part1") {
            assertEquals("2375403", input.answerOne())
        }
        MarcoUtil.avgTime("part2") {
            assertEquals("23082277", input.answerTwo())
        }
    }
}
