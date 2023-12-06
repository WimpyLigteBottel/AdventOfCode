package nel.marco.v1

import nel.marco.ReadUtil
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day5Test {


    var dayNumber = 5

    @Test
    fun tesing() {
    }

    @Test
    fun part1_example() {
        val readInputExample = ReadUtil.readInputAsList(dayNumber, true, true)
        assertEquals("35", Day5(readInputExample).answerOne())
    }

    @Test
    fun part1_real() {
        val readInputReal = ReadUtil.readInputAsList(dayNumber, false, true)
        assertEquals("379811651", Day5(readInputReal).answerOne())
    }

    @Test
    fun part2_example() {
        val readInputReal = ReadUtil.readInputAsList(dayNumber, true, true)
        assertEquals("46", Day5(readInputReal).answerTwo())
    }

    @Test
    fun part2_real() {
        val readInputReal = ReadUtil.readInputAsList(dayNumber, false, true)
        assertEquals("27992443", Day5(readInputReal).answerTwo())
    }


}