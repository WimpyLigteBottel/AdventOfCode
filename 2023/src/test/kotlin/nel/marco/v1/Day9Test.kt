package nel.marco.v1

import nel.marco.ReadUtil
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day9Test {

    var dayNumber = 9


    @Test
    fun answerOne_exampleOnly() {
        assertEquals("114", Day9(ReadUtil.readInputAsList(dayNumber, true, true)).answerOne())
    }


    @ParameterizedTest
    @CsvSource(
        "0 3 6 9 12 15 18 21 24 27 30,33",
        "0 1 2 3 4 5,6",
        "0 -1 -2 -3 -4 -5,-6",
        "10 13 16 21 30 45,68",
        "1 3 6 10 15 21,28"
    )
    fun answerOwnExample(csvSource: String, expected: String) {
        var input = listOf(csvSource)

        assertEquals(expected, Day9(input).answerOne())
    }

    @ParameterizedTest
    @CsvSource(
        "10 15 15 10 0 -15 -35 -60 -90 -125 -165 -210 -260 -315 -375 -440 -510 -585 -665 -750 -840,-935",
        "4 0 4 37 137 373 875 1885 3829 7404 13667 24105 40656 65641 101556 150661 214290 291792 378998 466093 534755,554405",
        "12 19 26 33 40 47 54 61 68 75 82 89 96 103 110 117 124 131 138 145 152,159",
    )
    fun answerOwnExample2(csvSource: String, expected: String) {
        var input = listOf(csvSource)

        assertEquals(expected, Day9(input).answerOne())
    }

    @Test
    fun answerOne() {
        assertNotEquals("1834108701", Day9(ReadUtil.readInputAsList(dayNumber, false, true)).answerOne()) // too high
    }


    @ParameterizedTest
    @CsvSource(
        "10 13 16 21 30 45,5"
    )
    fun answerTwoOwnExample2(csvSource: String, expected: String) {
        var input = listOf(csvSource)

        assertEquals(expected, Day9(input).answerTwo())
    }


    @Test
    fun answerTwo() {
        assertEquals("2", Day9(ReadUtil.readInputAsList(dayNumber, true,true)).answerTwo())
        assertEquals("2", Day9(ReadUtil.readInputAsList(dayNumber, false)).answerTwo())
    }


}