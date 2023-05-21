package nel.marco

import nel.marco.v1.Day1
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day1Test {

    @Test
    fun answerOne() {
        assertEquals("24000", Day1(ReadUtil.readInputAsList(1, exampleInput = true)).answerOne())
        assertEquals("66616", Day1(ReadUtil.readInputAsList(1)).answerOne())
    }

    @Test
    fun answerTwo() {
        assertEquals("45000", Day1(ReadUtil.readInputAsList(1, exampleInput = true)).answerTwo())
        assertEquals("199172", Day1(ReadUtil.readInputAsList(1)).answerTwo())
    }
}