package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day1Test {

    val day = Day1(useExample = false, useMac = false)
    val example = Day1(useExample = true, useMac = false)

    @Test
    fun answerOne() {
        assertEquals("X", example.answerOne())
        assertEquals("XX", day.answerOne())
    }


    @Test
    fun answerTwo() {
        assertEquals("X", example.answerTwo())
        assertEquals("X", day.answerTwo())
    }

}
