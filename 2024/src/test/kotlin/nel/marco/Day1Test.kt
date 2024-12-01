package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day1Test {

    @Test
    fun answerOne() {
        assertEquals("11", Day1(ReadUtil.readInputAsList(1, exampleInput = true, macBook = false)).answerOne())
        assertEquals("2375403", Day1(ReadUtil.readInputAsList(1, macBook = false)).answerOne())
    }


    @Test
    fun answerTwo() {
        assertEquals("31", Day1(ReadUtil.readInputAsList(1, exampleInput = true, macBook = false)).answerTwo())
        assertEquals("23082277", Day1(ReadUtil.readInputAsList(1, macBook = false)).answerTwo())
    }

}
