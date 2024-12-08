package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test

class Day5Test {

    val day = Day5(useExample = false, useMac = false)
    val example = Day5(useExample = true, useMac = false)

    @Test
    fun answerOne() {
        assertEquals("143", example.answerOne())
        assertEquals("6260", day.answerOne())
    }


    @Test
    fun answerTwo() {
        assertEquals("123", example.answerTwo())
        assertEquals("5346", day.answerTwo())
    }

    fun avg() {
        val day = day
        MarcoUtil.avgTime("part1",5) {
            assertEquals("6260", day.answerOne())
        }
        MarcoUtil.avgTime("part2",5) {
            assertEquals("5346", day.answerTwo())
        }
    }
}