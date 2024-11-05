package nel.marco.nel

import java.nio.file.Files
import java.nio.file.Path

class Day2 {

    fun part1(input: List<String>): String {
        val line = input
            .first()
            .split(",")
            .map { it.toLong() }
            .toMutableList()

        var counter = 0
        while (counter < line.size) {
            counter = process(line, counter)
        }

        return line[0].toString()
    }


    fun part2(input: List<String>, lookingForValue: String): String {
        val line = input
            .first()
            .split(",")
            .map { it.toLong() }

        for (noun in 0..99) {
            for (verb in 0..99) {
                val copy = line
                    .map { it.toString() }
                    .toMutableList()

                copy[1] = noun.toString()
                copy[2] = verb.toString()

                val answer = part1(listOf(copy.joinToString(separator = ",")))
                if (answer == lookingForValue) {
                    return (100 * noun + verb).toString()
                }
            }
        }

        return "NOTHING"
    }


    fun process(line: MutableList<Long>, counter: Int): Int {
        val operation = Operation.UNKNOWN.toEnum(line[counter])
        val inputA = line[1 + counter]
        val inputB = line[2 + counter]
        val locationChange = line[3 + counter]

        when (operation) {
            Operation.ADD -> {
                line[locationChange.toInt()] = line[inputA.toInt()] + line[inputB.toInt()]
            }

            Operation.MULTIPLY -> {
                line[locationChange.toInt()] = line[inputA.toInt()] * line[inputB.toInt()]
            }

            Operation.UNKNOWN, Operation.BREAK -> {
                return line[0].toInt()
            }
        }

        return counter + 4
    }

    public enum class Operation(val value: Long) {
        ADD(1),
        MULTIPLY(2),
        BREAK(99),
        UNKNOWN(-1);

        fun toEnum(inputvalue: Long): Operation {
            return Operation.values().find { it.value == inputvalue }
                ?: throw IllegalArgumentException("Unknown value $inputvalue")
        }
    }
}

fun main() {
    val resource = Path.of("C:\\code\\AdventOfCode\\2019\\src\\main\\resources\\Day2")
    val lines = Files.readAllLines(resource)

    println("part 1 = ${Day2().part1(lines)}")
    println("part 2 = ${Day2().part2(lines,"19690720")}")

}