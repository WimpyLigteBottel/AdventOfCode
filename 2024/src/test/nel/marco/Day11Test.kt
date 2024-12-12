package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class Day11Test {

    val example = Day11(useExample = true, useMac = false)
    val day = Day11(useExample = false, useMac = false)

    @Test
    fun answerOne() {
        assertEquals("55312", example.answerOne())
        assertEquals("188902", day.answerOne())
    }

    @Test
    fun answerTwo() {
        assertEquals("223894720281135", day.answerTwo())
    }

    @Test
    fun avg() {
        val day = day
        MarcoUtil.avgTime("part1", 5) {
            assertEquals("188902", day.answerOne())
        }
        MarcoUtil.avgTime("part2", 5) {
            assertEquals("223894720281135", day.answerTwo())
        }
    }
}

