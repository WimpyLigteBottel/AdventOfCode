package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test

class Day4Test {


    val day = Day4(useExample = false, useMac = false)
    val example = Day4(useExample = true, useMac = false)


    @Test
    fun answerOne() {
        assertEquals("18", example.answerOne())
        assertEquals("2578", day.answerOne())
    }


    @Test
    fun answerTwo() {
        assertEquals("9", example.answerTwo())
        assertEquals("1972", day.answerTwo())
    }


    fun avg() {
        val day = day
        MarcoUtil.avgTime("part1",5) {
            assertEquals("2578", day.answerOne())
        }
        MarcoUtil.avgTime("part2",5) {
            assertEquals("1972", day.answerTwo())
        }
    }
}