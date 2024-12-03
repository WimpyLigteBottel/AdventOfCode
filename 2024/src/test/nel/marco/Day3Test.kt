package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

/*
As you scan through the corrupted memory, you notice that some of the conditional statements are also still intact.
If you handle some of the uncorrupted conditional statements in the program,
you might be able to get an even more accurate result.

There are two new instructions you'll need to handle:

The do() instruction enables future mul instructions.
The don't() instruction disables future mul instructions.

Only the most recent do() or don't() instruction applies. At the beginning of the program, mul instructions are enabled.

For example:

xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
This corrupted memory is similar to the example from before, but this time the mul(5,5) and mul(11,8) instructions are disabled because there is a don't() instruction before them. The other mul instructions function normally, including the one at the end that gets re-enabled by a do() instruction.

This time, the sum of the results is 48 (2*4 + 8*5).

Handle the new instructions; what do you get if you add up all of the results of just the enabled multiplications?
 */
class Day3Test {

    val input = ReadUtil.readInputAsList(3, exampleInput = false, macBook = true)
    val exampleInput = ReadUtil.readInputAsList(3, exampleInput = true, macBook = true)


    @Test
    fun answerOne() {
        assertEquals("161", Day3(exampleInput).answerOne())
        assertEquals("188192787", Day3(input).answerOne())
    }


    @Test
    fun answerTwo() {
        assertEquals("48", Day3(exampleInput).answerTwo())
        assertNotEquals("172367763", Day3(input).answerTwo())
        assertNotEquals("186619028", Day3(input).answerTwo())
        assertNotEquals("8757489", Day3(input).answerTwo())
        assertEquals("X", Day3(input).answerTwo())
    }


    @Test
    fun avg() {
        val day = Day3(input)
        MarcoUtil.avgTime("part1") {
            assertEquals("213", day.answerOne())
        }
        MarcoUtil.avgTime("part2") {
            assertEquals("285", day.answerTwo())
        }
    }
}