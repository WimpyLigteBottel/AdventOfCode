package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class Day10Test {

    val example = Day10(useExample = true, useMac = false)
    val day = Day10(useExample = false, useMac = false)

    @Test
    fun answerOne() {
        assertEquals("36", example.answerOne())
        assertEquals("514", day.answerOne()) // not 6273684533700
    }

    @Test
    fun answerTwo() {
        assertEquals("81", example.answerTwo())
        assertEquals("1162", day.answerTwo()) // cheated this one :(
    }

    @Test
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

