package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day7Test {

    val input = ReadUtil.readInputAsList(7, exampleInput = false, macBook = false)
    val exampleInput = ReadUtil.readInputAsList(7, exampleInput = true, macBook = false)

    @Test
    fun answerOne() {
        assertEquals("3749", Day7(exampleInput).answerOne())
        assertEquals("6083020304036", Day7(input).answerOne())
    }

    @Test
    fun answerTwo() {
        assertEquals("11387", Day7(exampleInput).answerTwo())
        assertEquals("59002246504791", Day7(input).answerTwo())
    }

    @Test
    fun avg() {
        val day = Day7(input)
        MarcoUtil.avgTime("part1", 5) {
            assertEquals("6083020304036", day.answerOne())
        }
        MarcoUtil.avgTime("part2", 5) {
            assertEquals("59002246504791", day.answerTwo())
        }
    }
}