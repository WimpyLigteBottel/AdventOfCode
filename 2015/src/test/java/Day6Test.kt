import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@Execution(ExecutionMode.CONCURRENT)
internal class Day6Test {

    @Test
    fun `turn on 1 row == lights on`() {

        val toList = """
            turn on 0,0 through 999,0
        """.trimIndent()
            .split("\n")
            .toList()

        assertEquals(1000, Day6(toList).part1())
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
    fun `part1_toggle twice == stays off`() {

        val toList = """
            toggle 0,0 through 2,2
            toggle 0,0 through 2,2
        """.trimIndent()
            .split("\n")
            .toList()

        assertEquals(0, Day6(toList).part1())
    }

    @Test
    fun `turn on should turn lights on`() {

        val toList = """
            turn on 0,0 through 2,2
        """.trimIndent()
            .split("\n")
            .toList()

        assertEquals(9, Day6(toList).part1())
    }

    @Test
    fun `turn off should turn lights off`() {

        val toList = """
            turn on 0,0 through 2,2
            turn off 0,0 through 2,2
        """.trimIndent()
            .split("\n")
            .toList()

        assertEquals(0, Day6(toList).part1())
    }

    @Test
    fun `turn on and lights is already on == nothing changes`() {

        val toList = """
            turn on 0,0 through 2,2
            turn on 0,0 through 2,2
        """.trimIndent()
            .split("\n")
            .toList()

        assertEquals(9, Day6(toList).part1())
    }

    @Test
    fun `can handle large amount of lights`() {

        val toList = """
            turn on 0,0 through 999,999
        """.trimIndent()
            .split("\n")
            .toList()

        assertEquals(1_000_000, Day6(toList).part1())
    }

    @Test
    fun `can turn off 1 million lights`() {

        val toList = """
            turn on 0,0 through 999,999
            turn off 0,0 through 999,999
        """.trimIndent()
            .split("\n")
            .toList()

        assertEquals(0, Day6(toList).part1())
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