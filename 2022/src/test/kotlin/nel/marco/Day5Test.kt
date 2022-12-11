package nel.marco

import nel.marco.v1.Day5
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day5Test{

    val example = ReadUtil.readInputAsList(5, exampleInput = true)
    val actual = ReadUtil.readInputAsList(5, exampleInput = false)

    val day5Example = Day5(example)
    val day5 = Day5(actual)


    @Test
    fun answerOne() {
        assertEquals("CMZ", day5Example.answerOne())
        assertEquals("TLFGBZHCN", day5.answerOne())
    }

    @Test
    fun answerTwo() {
        assertEquals("MCD", day5Example.answerTwo())
        assertEquals("QRQFHFWCL", day5.answerTwo())
    }
}

