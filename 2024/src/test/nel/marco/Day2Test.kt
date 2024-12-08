package nel.marco

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class Day2Test {

    val day = Day2(useExample = false, useMac = false)
    val example = Day2(useExample = true, useMac = false)

    @Test
    fun answerOne() {
        assertEquals("2", example.answerOne())
        assertEquals("213",day.answerOne())
    }


    @Test
    fun answerTwo() {
        assertEquals("4", example.answerTwo())
        assertEquals("285", day.answerTwo())
    }


    fun avg() {
        val input = day
        MarcoUtil.avgTime("part1") {
            assertEquals("213", input.answerOne())
        }
        MarcoUtil.avgTime("part2") {
            assertEquals("285", input.answerTwo())
        }
    }
}