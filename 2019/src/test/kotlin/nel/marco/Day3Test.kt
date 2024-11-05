package nel.marco

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.nio.file.Files
import java.nio.file.Path

class Day3Test {


    val input = Files.readAllLines(Path.of("C:\\code\\AdventOfCode\\2019\\src\\main\\resources\\Day3"))
    val day = Day3()

    @Test
    fun part1() {
        assertEquals("X", day.part1(input))
    }

    @Test
    fun part2() {
        assertEquals("X", day.part2(input))
    }
}