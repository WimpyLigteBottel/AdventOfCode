package nel.marco.v1

import nel.marco.ReadUtil
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class Day8Test {

    var dayNumber = 8


    @Test
    fun answerOne_exampleOnly() {
        val readInputExample = ReadUtil.readInputAsList(dayNumber, true, true)
        assertEquals("2", Day8(readInputExample).answerOne())

    }

    @Test
    fun answerOne_exampleOnly_modified() {
        val readInputExample = """
            LLR

            AAA = (BBB, BBB)
            BBB = (AAA, ZZZ)
            ZZZ = (ZZZ, ZZZ)
        """.trimIndent().split("\n")

        assertEquals("6", Day8(readInputExample).answerOne())

    }


    @Test
    fun answerOne() {
        val readInputExample = ReadUtil.readInputAsList(dayNumber, true, true)
        assertEquals("2", Day8(readInputExample).answerOne())
        val real = ReadUtil.readInputAsList(dayNumber, false, true)
        assertEquals("19637", Day8(real).answerOne())

    }

    @Test
    fun answerTwo_example() {
        val real = """
            LR
            
            11A = (11B, XXX)
            11B = (XXX, 11Z)
            11Z = (11B, XXX)
            22A = (22B, XXX)
            22B = (22C, 22C)
            22C = (22Z, 22Z)
            22Z = (22B, 22B)
            XXX = (XXX, XXX)""".trimIndent().split("\n")


        assertEquals("6", Day8(real).answerTwo())
    }


    @Test
    fun answerTwo_exampleCheat() {
        val real = ReadUtil.readInputAsList(dayNumber, false, true)

        assertEquals("8811050362409", Day8(real).answerTwo())
    }


    @Test
    fun answerTwo() {
        val real = ReadUtil.readInputAsList(dayNumber, false, true)
        val answerTwo = Day8(real).answerTwo()
        assertEquals("8811050362409", answerTwo)

    }


}