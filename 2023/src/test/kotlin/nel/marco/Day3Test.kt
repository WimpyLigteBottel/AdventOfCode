package nel.marco

import nel.marco.v1.Day3
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day3Test {

    var dayNumber = 3


    @Test
    fun answerOne_AllDigrections() {
        var input = """
            100.100...
            100*100...
            100.100...
            ..........
        """.trimIndent().split("\n")


        assertEquals("600", Day3(input).answerOne())
    }

    @Test
    fun answerOne_acrossMultiple() {
        var input = """
            ...100....
            ....$.....
            ..........
            ..........
        """.trimIndent().split("\n")


        assertEquals("100", Day3(input).answerOne())
    }


    @Test
    fun answerOne() {
        assertEquals("4361", Day3(ReadUtil.readInputAsList(dayNumber, exampleInput = true)).answerOne())
        assertEquals("537732", Day3(ReadUtil.readInputAsList(dayNumber, exampleInput = false)).answerOne())
    }


    @Test
    fun answerTwo_part2() {
        assertEquals("467835", Day3(ReadUtil.readInputAsList(dayNumber, exampleInput = true)).answerTwo())
        assertEquals("84883664", Day3(ReadUtil.readInputAsList(dayNumber, exampleInput = false)).answerTwo())
    }


}
