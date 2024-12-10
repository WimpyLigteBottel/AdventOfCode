package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class Day10Test {

    val day = Day10(useExample = false, useMac = true)
    val example = Day10(useExample = true, useMac = true)

    @Test
    fun answerOne() {
        assertEquals("X", example.answerOne())
        assertEquals("X", day.answerOne()) // not 6273684533700
    }

    @Test
    fun answerTwo() {
        assertEquals("X", example.answerTwo())
        assertEquals("X", day.answerTwo()) // cheated this one :(
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

