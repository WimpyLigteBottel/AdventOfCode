package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class Day10Test {
    val dayNumber = 10

    private val example = ReadUtil.readInputAsList(dayNumber, exampleInput = true)
    private val actual = ReadUtil.readInputAsList(dayNumber, exampleInput = false)

    var dayExample = Day10(example)
    var day = Day10(actual)


    @Test
    fun answerOne() {
        assertEquals(13140, dayExample.answerOne())
        assertEquals(6367, day.answerOne())
    }

    @Test
    fun answerTwo() {
        assertEquals(-1, dayExample.answerTwo())
        println()
        assertEquals(2536, day.answerTwo())
    }
}