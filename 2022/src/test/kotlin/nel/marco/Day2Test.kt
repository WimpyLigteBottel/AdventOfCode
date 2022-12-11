package nel.marco

import nel.marco.v1.Day2
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day2Test {

    @Test
    fun answerOne() {
        assertEquals("15", Day2(ReadUtil.readInputAsList(2, exampleInput = true)).answerOne())
        assertEquals("12772", Day2(ReadUtil.readInputAsList(2)).answerOne())
    }

    @Test
    fun answerTwo() {
        assertEquals("12", Day2(ReadUtil.readInputAsList(2, exampleInput = true)).answerTwo())
        assertEquals("11618", Day2(ReadUtil.readInputAsList(2)).answerTwo())
    }
}