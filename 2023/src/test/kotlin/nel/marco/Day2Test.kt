package nel.marco

import nel.marco.v1.Day2
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class Day2Test {

    @Test
    fun answerOne() {
        assertEquals("8", Day2(ReadUtil.readInputAsList(2, exampleInput = true)).answerOne())
        assertEquals("2505", Day2(ReadUtil.readInputAsList(2, exampleInput = false)).answerOne())
    }


    @Test
    fun answerTwo_part2() {
        assertEquals("2286", Day2(ReadUtil.readInputAsList(2, exampleInput = true)).answerTwo())
        assertEquals("2505", Day2(ReadUtil.readInputAsList(2, exampleInput = false)).answerTwo())
    }


}
