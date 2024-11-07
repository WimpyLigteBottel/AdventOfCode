package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.nio.file.Files
import java.nio.file.Path

class Day3Test {


    val input = Files.readAllLines(Path.of("C:\\code\\AdventOfCode\\2019\\src\\main\\resources\\Day3"))
    val day = Day3()

    @Test
    fun examples() {
        assertEquals("6", Day3("example1").part1(listOf("R8,U5,L5,D3", "U7,R6,D4,L4")))
        assertEquals("159", Day3("example2").part1(listOf("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83")))
        assertEquals("135", Day3("example3").part1(listOf("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51","U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")))
    }

    @Test
    fun examples_Part2() {
        assertEquals("30", Day3("example1").part2(listOf("R8,U5,L5,D3", "U7,R6,D4,L4")))
        assertEquals("610", Day3("example2").part2(listOf("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83")))
        assertEquals("410", Day3("example3").part2(listOf("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51","U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")))
    }

    @Test
    fun part1() {
        assertEquals("855", day.part1(input))
    }

    @Test
    fun part2() {
        assertEquals("11238", day.part2(input))
    }
}