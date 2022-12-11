package nel.marco

import nel.marco.v1.Day4
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day4Test {
    val day4Example = Day4(ReadUtil.readInputAsList(4, exampleInput = true))
    val day4 = Day4(ReadUtil.readInputAsList(4, exampleInput = false))


    @ParameterizedTest
    @CsvSource(
        value = [
            "6-6,4-6#1",
            "2-3,4-5#0",
            "5-7,7-9#0",
            "2-8,3-7#1",
            "6-6,4-6#1",
            "2-6,4-8#0",
        ],
        delimiter = '#'
    )
    fun testingContains(text: String, matchingAmount: String) {
        assertEquals(matchingAmount, Day4(listOf(text)).answerOne())
    }

    @Test
    fun answerOne() {
        assertEquals("2", day4Example.answerOne())
        assertEquals("305", day4.answerOne())
    }

    @Test
    fun answerTwo() {
        assertEquals("4", day4Example.answerTwo())
        assertEquals("811", day4.answerTwo())
    }

}