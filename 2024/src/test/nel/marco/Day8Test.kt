package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day8Test {


    val day = Day8(useExample = false, useMac = false)
    val example = Day8(useExample = true, useMac = false)

    @Test
    fun answerOne() {
        assertEquals("14", example.answerOne())
        assertEquals("244", day.answerOne())
    }

    @Test
    fun answerTwo() {
        assertEquals("34", example.answerTwo())
        assertEquals("912", day.answerTwo())
    }



    fun avg() {
        val day = day
        MarcoUtil.avgTime("part1", 5) {
            assertEquals("244", day.answerOne())
        }
        MarcoUtil.avgTime("part2", 5) {
            assertEquals("912", day.answerTwo())
        }
    }
}