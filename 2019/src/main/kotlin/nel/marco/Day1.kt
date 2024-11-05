package nel.marco

import java.nio.file.Files
import java.nio.file.Path

class Day1 {

    fun part1(input: List<String>): String {

        val answer = input
            .map {
                it.toLong().calculate()
            }
            .sum()

        return answer.toString()
    }

    private fun Long.calculate() = ((this.toDouble() / 3) - 2).toLong()

    private fun calculateRecursively(input: Long): Long {
        if (input <= 3) {
            return 0
        }
        val inputA = input.calculate()
        val InputB = calculateRecursively(inputA)
        return inputA + InputB
    }


    fun part2(input: List<String>): String {
        return input
            .sumOf { calculateRecursively(it.toLong()) }
            .toString()
    }
}

fun main() {
    val lines = Files.readAllLines(Path.of("C:\\code\\AdventOfCode\\2019\\src\\main\\resources\\Day1"))

    println("part 1 = ${Day1().part1(lines)}")
    println("part 2 = ${Day1().part2(lines)}")

}