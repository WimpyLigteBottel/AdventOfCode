package nel.marco.v1

import nel.marco.MarcoUtil
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
        assertEquals("345015", Day6(real).answerOne())

    }

    @Test
    fun answerTwo() {

        val readInputExample = ReadUtil.readInputAsList(dayNumber, true, true)
        assertEquals("71503", Day6(readInputExample).answerTwo())

        val real = ReadUtil.readInputAsList(dayNumber, false, true)
        assertEquals("42588603", Day6(real).answerTwo())
    }


    @Test
    fun answerTwo_benchmark() {
        val real = ReadUtil.readInputAsList(dayNumber, false, true)

        MarcoUtil.avgTime("answer 2", 10){
            assertEquals("42588603", Day6(real).answerTwo())
        }
    }
}