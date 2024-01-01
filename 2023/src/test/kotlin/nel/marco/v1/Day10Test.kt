package nel.marco.v1

import nel.marco.ReadUtil
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day10Test {

    val dayNumber = 10

    @Test
    fun answerOne_test() {
        assertEquals("8", Day10(ReadUtil.readInputAsList(dayNumber, exampleInput = true, macBook = false)).answerOne())
    }


    @Test
    fun answerOne_real() {
        assertEquals("6867", Day10(ReadUtil.readInputAsList(dayNumber, exampleInput = false, macBook = false)).answerOne())
    }



    @Test
    fun answerTwo_part2() {
        assertEquals("-1", Day10(ReadUtil.readInputAsList(dayNumber, macBook = false)).answerTwo())
    }


}
