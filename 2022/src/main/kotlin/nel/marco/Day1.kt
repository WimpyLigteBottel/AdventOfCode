import nel.marco.ReadUtil

fun main(args: Array<String>) {

    val input = ReadUtil.readInput(1)
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