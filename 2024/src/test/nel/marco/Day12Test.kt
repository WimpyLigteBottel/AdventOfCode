package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class Day12Test {

    val example = Day12(useExample = true, useMac = false)
    val day = Day12(useExample = false, useMac = false)

    @Test
    fun answerOne() {
        assertEquals("1930", example.answerOne())
        assertEquals("1461806", day.answerOne())
    }

    @Test
    fun answerTwo() {
        assertEquals("1206", example.answerTwo())
        assertEquals("X", day.answerTwo())
    }

    @Test
    fun avg() {
        val day = day
        MarcoUtil.avgTime("part1", 5) {
            assertEquals("1461806", day.answerOne())
        }
//        MarcoUtil.avgTime("part2", 5) {
//            assertEquals("X", day.answerTwo())
//        }
    }
}

