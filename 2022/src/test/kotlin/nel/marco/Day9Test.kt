package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class Day9Test {
    val dayNumber = 9

    private val example = ReadUtil.readInputAsList(dayNumber, exampleInput = true)
    private val actual = ReadUtil.readInputAsList(dayNumber, exampleInput = false)

    var dayExample = Day9(example)
    var day = Day9(actual)


    @Test
    fun answerOne() {
        assertEquals(13, dayExample.answerOne())
        assertEquals(6367, day.answerOne())
    }

    @Test
    fun answerTwo() {

        assertEquals(8, dayExample.answerTwo())
        assertEquals(8, day.answerTwo())
    }
}