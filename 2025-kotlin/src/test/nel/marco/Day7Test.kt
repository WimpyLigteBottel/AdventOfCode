package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day7Test {

    val day = Day7(useExample = false, useMac = false)
    val example = Day7(useExample = true, useMac = false)

    @Test
    fun answerOne() {
        assertEquals("21", example.answerOne())
        assertEquals("1537", day.answerOne())
    }


    @Test
    fun answerTwo() {
        assertEquals("41", example.answerTwo())
        assertEquals("x", day.answerTwo())

    }

    @Test
    fun avgPart1() {
        MarcoUtil.avgTime("part 1", 5) {
            assertEquals("x", day.answerOne())
        }

        MarcoUtil.avgTime("part 2", 5) {
            assertEquals("x", day.answerTwo())
        }
    }
}
