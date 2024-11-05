package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path

class Day1Test {

    val input = Files.readAllLines(Path.of("C:\\code\\AdventOfCode\\2019\\src\\main\\resources\\Day1"))
    val day = Day1()

    @Test
    fun part1() {
        assertEquals("3474920", day.part1(input))
    }

    @Test
    fun part2() {
        assertEquals("5209504", day.part2(input))
    }
}