package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day1Test {

    val day = Day1(useExample = false, useMac = false)
    val example = Day1(useExample = true, useMac = false)

    @Test
    fun answerOne() {
        assertEquals("11", example.answerOne())
        assertEquals("2375403", day.answerOne())
    }


    @Test
    fun answerTwo() {
        assertEquals("31", example.answerTwo())
        assertEquals("23082277", day.answerTwo())
    }


    fun avgTime() {
        val input = Day1(useExample = true, useMac = false)
        MarcoUtil.avgTime("part1") {
            assertEquals("2375403", input.answerOne())
        }
        MarcoUtil.avgTime("part2") {
            assertEquals("23082277", input.answerTwo())
        }
    }
}
