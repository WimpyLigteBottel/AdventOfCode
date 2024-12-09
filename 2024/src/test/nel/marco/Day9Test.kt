package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class Day9Test {

    val day = Day9(useExample = false, useMac = false)
    val example = Day9(useExample = true, useMac = false)

    @Test
    fun answerOne() {
        assertEquals("1928", example.answerOne())
        assertEquals("X", day.answerOne()) // not 6273684533700
    }

    @Test
    fun answerTwo() {
        assertEquals("X", example.answerTwo())
        assertEquals("X", day.answerTwo())
    }

    fun avg() {
        val day = day
        MarcoUtil.avgTime("part1", 5) {
            assertEquals("X", day.answerOne())
        }
        MarcoUtil.avgTime("part2", 5) {
            assertEquals("X", day.answerTwo())
        }
    }
}

