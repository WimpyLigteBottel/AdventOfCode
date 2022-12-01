import java.nio.file.Files
import java.nio.file.Path

fun readInput(exampleInput: Boolean = false): String {
    if (exampleInput) {
        return Files.readAllLines(Path.of("D:\\coding repo\\AdventOfCode\\2022\\src\\main\\resources\\day1"))
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
    var highestCalories = -1;
    elves.forEach { s ->
        val totalCalories = s.split("\n")
            .filter { it != "" }
            .sumOf {
                Integer.parseInt(it)
            }

        if (totalCalories >= highestCalories) {
            highestCalories = totalCalories
        }
    }

    return "$highestCalories"
}

fun answerTwo(elves: List<String>): String {
    var highestCalories = -1
    val listOfCalories = mutableListOf<Int>()
    elves.forEach { s ->
        val totalCalories = s.split("\n")
            .filter { it != "" }
            .sumOf {
                Integer.parseInt(it)
            }

        listOfCalories.add(totalCalories)
    }

    listOfCalories.sortDescending()


    return listOfCalories.subList(0, 3).sum().toString()
}