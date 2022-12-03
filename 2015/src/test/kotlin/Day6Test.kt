import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals

internal class Day6Test {

    @ParameterizedTest(name = "{0} == {1} are lit")
    @CsvSource(
        value = [
            "turn on 0,0 through 0,2#3",
            "turn on 0,0 through 2,0#3",
            "turn on 0,0 through 2,2#9",
            "turn on 0,0 through 999,999#1000000"
        ], delimiter = '#'
    )
    fun `combined test`(text: String, expect: Int) {
        val toList = """
            $text
        """.trimIndent()
            .split("\n")
            .toList()

        val day6 = Day6(toList)
        assertEquals(expect, day6.part1())
    }

    @ParameterizedTest(name = "{0} == {1} are lit")
    @CsvSource(
        value = [
            "turn on 0,0 through 0,2!!turn off 0,0 through 0,2#0",
            "turn on 0,0 through 2,0!!turn off 0,0 through 2,0#0",
            "turn on 0,0 through 2,2!!turn off 0,0 through 2,2#0"
        ], delimiter = '#'
    )
    fun `combined test turning off`(text: String, expect: Int) {
        val toList = text
            .split("!!")
            .toList()

        val day6 = Day6(toList)
        assertEquals(expect, day6.part1())
    }

    @ParameterizedTest(name = "{0} == {1} are lit")
    @CsvSource(
        value = [
            //toggle itself off
            "toggle 0,0 through 0,2!!toggle 0,0 through 0,2#0",
            "toggle 0,0 through 2,0!!toggle 0,0 through 2,0#0",
            "toggle 0,0 through 2,2!!toggle 0,0 through 2,2#0",

            //switching off on stuff
            "turn on 0,0 through 0,2!!toggle 0,0 through 0,2#0",
            "turn on 0,0 through 2,0!!toggle 0,0 through 2,0#0",
            "turn on 0,0 through 2,2!!toggle 0,0 through 2,2#0",

            //switching on stuff
            "turn off 0,0 through 0,2!!toggle 0,0 through 0,2#3",
            "turn off 0,0 through 2,0!!toggle 0,0 through 2,0#3",
            "turn off 0,0 through 2,2!!toggle 0,0 through 2,2#9",
        ], delimiter = '#'
    )
    fun `combined test toggle`(text: String, expect: Int) {
        val toList = text
            .split("!!")
            .toList()

        val day6 = Day6(toList)
        assertEquals(expect, day6.part1())
    }


    @Test
    fun `toggle 1 == lights on`() {

        val toList = """
            toggle 0,0 through 2,2
        """.trimIndent()
            .split("\n")
            .toList()

        assertEquals(9, Day6(toList).part1())
    }

    @Test
    fun `can turn on middle lights`() {

        val toList = """
           turn on 499,499 through 500,500
        """.trimIndent()
            .split("\n")
            .toList()

        assertEquals(4, Day6(toList).part1())
    }

    @Test
    fun `part1`() {
        assertEquals(377891,  Day6(MarcoUtil.readInput(6, false)).part1())
    }

    @Test
    fun `part2 sum brightness`() {
        val toList = """
            turn off 0,0 through 2,2
            turn off 0,0 through 2,2
            turn off 0,0 through 2,2
        """.trimIndent()
            .split("\n")
            .toList()

        assertEquals(0, Day6(toList).part2())
    }

    @Test
    fun `part2 sum brightness === 0`() {
        val toList = """
           toggle 0,0 through 999,999
        """.trimIndent()
            .split("\n")
            .toList()

        assertEquals(2000000, Day6(toList).part2())
    }

    @Test
    fun part2() {
        val actual = Day6(MarcoUtil.readInput(6, false)).part2()
        assertEquals(14110788, actual)
    }
}