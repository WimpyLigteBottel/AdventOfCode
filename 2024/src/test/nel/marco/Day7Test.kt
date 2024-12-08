package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day7Test {

    val day = Day7(useExample = false, useMac = false)
    val example = Day7(useExample = true, useMac = false)


    @Test
    fun answerOne() {
        assertEquals("3749", example.answerOne())
        assertEquals("6083020304036", day.answerOne())
    }

    @Test
    fun answerTwo() {
        assertEquals("11387", example.answerTwo())
        assertEquals("59002246504791", day.answerTwo())
    }

    fun avg() {
        val day = day
        MarcoUtil.avgTime("part1", 5) {
            assertEquals("6083020304036", day.answerOne())
        }
        MarcoUtil.avgTime("part2", 5) {
            assertEquals("59002246504791", day.answerTwo())
        }
    }
}