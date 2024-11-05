package nel.marco

import nel.marco.nel.Day2
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path

class Day2Test {

    val input = Files.readAllLines(Path.of("C:\\code\\AdventOfCode\\2019\\src\\main\\resources\\Day2"))
    val day = Day2()


    @Test
    fun part1() {
        assertEquals("3500", day.part1(listOf("1,9,10,3,2,3,11,0,99,30,40,50")))
        assertEquals("3101878", day.part1(input))

    }

    @Test
    fun part2() {
        assertEquals("8444", day.part2(input, "19690720"))
    }
}