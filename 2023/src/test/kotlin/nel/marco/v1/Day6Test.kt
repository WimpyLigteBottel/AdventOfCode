package nel.marco.v1

import nel.marco.ReadUtil
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day6Test {

    var dayNumber = 6

    @Test
    fun answerOne() {
        val readInputExample = ReadUtil.readInputAsList(dayNumber, true, true)
        assertEquals("288", Day6(readInputExample).answerOne())

        val real = ReadUtil.readInputAsList(dayNumber, false, true)
        assertEquals("288", Day6(real).answerOne())

    }

    @Test
    fun answerTwo() {

        val readInputExample = ReadUtil.readInputAsList(dayNumber, true, true)
        assertEquals("71503", Day6(readInputExample).answerTwo())

        val real = ReadUtil.readInputAsList(dayNumber, false, true)
        assertNotEquals("345015", Day6(real).answerTwo())
        assertEquals("42588603", Day6(real).answerTwo())
    }
}