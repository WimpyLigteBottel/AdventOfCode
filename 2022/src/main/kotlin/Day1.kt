import java.nio.file.Files
import java.nio.file.Path

fun readInput(exampleInput: Boolean = false): String {
    if (exampleInput) {
        return Files.readAllLines(Path.of("D:\\coding repo\\AdventOfCode\\2022\\src\\main\\resources\\day1_example"))
            .joinToString("\n")
    }

    return Files.readAllLines(Path.of("D:\\coding repo\\AdventOfCode\\2022\\src\\main\\resources\\day1"))
        .joinToString("\n")
}

fun main(args: Array<String>) {

    val input = readInput()
    val elves = input.split("\n\n")

    println(answerOne(elves))
    println(answerTwo(elves))
}

fun answerOne(elves: List<String>): String {
    val listOfCalories = elves.map { s ->
        s.split("\n")
            .filter { it != "" }
            .sumOf {
                Integer.parseInt(it)
            }
    }.sortedDescending()

    return listOfCalories[0].toString()
}

fun answerTwo(elves: List<String>): String {
    val listOfCalories = elves.map { s ->
        s.split("\n")
            .filter { it != "" }
            .sumOf {
                Integer.parseInt(it)
            }
    }.sortedDescending()

    return listOfCalories.subList(0, 3).sum().toString()
}