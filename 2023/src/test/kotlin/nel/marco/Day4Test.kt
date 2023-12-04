package nel.marco

import nel.marco.v1.Day4
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day4Test {

    var dayNumber = 4


    @Test
    fun answerOne_1Card() {
        var input = """
            Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
        """.trimIndent().split("\n")


        assertEquals("8", Day4(input).answerOne())
    }

    @Test
    fun answerOne_exampleInput() {
        var input = """
            Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
            Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
            Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
            Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
            Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
            Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
        """.trimIndent().split("\n")


        assertEquals("13", Day4(input).answerOne())
    }

    @Test
    fun answerOne() {
        assertEquals("13", Day4(ReadUtil.readInputAsList(dayNumber, exampleInput = true)).answerOne())
        assertEquals("20829", Day4(ReadUtil.readInputAsList(dayNumber, exampleInput = false)).answerOne())
    }

    @Test
    fun answerTwo() {
        assertEquals("30", Day4(ReadUtil.readInputAsList(dayNumber, exampleInput = true)).answerTwo())
        assertEquals("12648035", Day4(ReadUtil.readInputAsList(dayNumber, exampleInput = false)).answerTwo())
    }


    @Test
    fun answerTwo_benchmark() {
        val day4 = Day4(ReadUtil.readInputAsList(dayNumber, exampleInput = false))


        MarcoUtil.avgTime("part 2") { day4.answerTwo() }

    }


}
