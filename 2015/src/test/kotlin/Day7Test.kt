import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day7Test {

    @Test
    fun part1() {

        val day7 = Day7(MarcoUtil.readInput(7) as MutableList<String>)
        kotlin.test.assertEquals(956, day7.part1())
    }

    @Test
    fun part2() {
        val day7 = Day7(MarcoUtil.readInput(7) as MutableList<String>)
        kotlin.test.assertEquals(40149, day7.part2())
    }
}