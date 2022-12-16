package nel.marco

import nel.marco.v1.Day8
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*


class Day8Test {
    val dayNumber = 8

    private val example = ReadUtil.readInputAsList(dayNumber, exampleInput = true)
    private val actual = ReadUtil.readInputAsList(dayNumber, exampleInput = false)

    val dayExample = Day8(example)
    val day = Day8(actual)

    @Test
    fun answerOne() {
        assertEquals(21, dayExample.answerOne())
        assertEquals(1776, day.answerOne())
    }

    @Test
    fun answerTwo() {

        assertEquals(8, dayExample.answerTwo())
        assertEquals(8, day.answerTwo())
    }
}