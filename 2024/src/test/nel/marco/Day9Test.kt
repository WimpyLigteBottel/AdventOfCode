package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class Day9Test {

    val day = Day9(useExample = false, useMac = true)
    val example = Day9(useExample = true, useMac = true)

    @Test
    fun answerOne() {
        assertEquals("1928", example.answerOne())
        assertEquals("6288707484810", day.answerOne()) // not 6273684533700
    }

    @Test
    fun answerTwo() {
        assertEquals("2858", example.answerTwo())
        assertEquals("6311837662089", day.answerTwo()) // cheated this one :(
    }

    fun avg() {
        val day = day
        MarcoUtil.avgTime("part1", 5) {
            assertEquals("6288707484810", day.answerOne())
        }
        MarcoUtil.avgTime("part2", 5) {
            assertEquals("X", day.answerTwo())
        }
    }
}

