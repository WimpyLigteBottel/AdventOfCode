package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day13Test {

    val day = Day13(useExample = false, useMac = false)
    val example = Day13(useExample = true, useMac = false)

    @Test
    fun answerOne() {
        assertEquals("480", example.answerOne())
        assertEquals("40069", day.answerOne())
    }


    @Test
    fun answerTwo() {
        assertEquals("875318608908", example.answerTwo())
        assertEquals("71493195288102", day.answerTwo())
    }


    @Test
    @Disabled
    fun avgTime() {
        MarcoUtil.avgTime("part1") {
            assertEquals("40069", Day13(useExample = false, useMac = false).answerOne())
        }
        MarcoUtil.avgTime("part2") {
            assertEquals("71493195288102", Day13(useExample = false, useMac = false).answerTwo())
        }
    }
}
