package nel.marco

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day6Test {


    private val example = ReadUtil.readInputAsList(6, exampleInput = true)
    private val actual = ReadUtil.readInputAsList(6, exampleInput = false)

    val dayExample = Day6(example)
    val day = Day6(actual)


    @ParameterizedTest (name = "{0} == after character == {1}")
    @CsvSource(
        value = ["mjqjpqmgbljsphdztnvjfqwrcgsmlb,7",
            "bvwbjplbgvbhsrlpgdmjqwftvncz,5",
            "nppdvjthqldpwncqszvftbrmjlhg,6",
            "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg,10",
            "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw,11"]
    )
    fun process(input: String, expect: Int) {
        assertEquals(expect, day.process(input))
    }


    @Test
    fun answerOne() {
        assertEquals("7", dayExample.answerOne())
        assertEquals("1779", day.answerOne())
    }

    @Test
    fun answerTwo() {

        assertEquals("19", dayExample.answerTwo())
        assertEquals("2635", day.answerTwo())
    }
}