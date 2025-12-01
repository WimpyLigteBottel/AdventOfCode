package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day1Test {

    val day = Day1(useExample = false, useMac = true)
    val example = Day1(useExample = true, useMac = true)

    @Test
    fun answerOne() {
        assertEquals("3", example.answerOne())
        assertEquals("1086", day.answerOne())
    }


    @Test
    fun answerTwo() {
        assertEquals("6", example.answerTwo())
        assertEquals("X", day.answerTwo())
    }

}
