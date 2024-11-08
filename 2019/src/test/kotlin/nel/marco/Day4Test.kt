package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import java.nio.file.Files
import java.nio.file.Path

class Day4Test {

    val input = Files.readAllLines(Path.of("C:\\code\\AdventOfCode\\2019\\src\\main\\resources\\Day4"))
    val day = Day4()

    @RepeatedTest(500)
    fun part1() {
        assertEquals("2814", day.part1(input))
    }

    @RepeatedTest(500)
    fun part2() {
        assertEquals("1991", day.part2(input))
    }
}