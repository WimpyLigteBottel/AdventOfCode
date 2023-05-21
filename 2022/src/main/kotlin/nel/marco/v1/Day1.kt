package nel.marco.v1

import nel.marco.MarcoUtil
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    var readAllLines = MarcoUtil.readInput(1, true) as MutableList<String>


    val day1 = Day1(readAllLines)
    executeTimes ("ANSWER 1") {
        day1.answerOne()
    }
    executeTimes("ANSWER 2") {
        day1.answerTwo()
    }


}

fun execute(name: String = "", block: () -> Unit) {
    println("$name -> " + measureTimeMillis(block) + "ms")
}

fun executeTimes(name: String = "", block: () -> Unit) {
    var total = 0L
    for (x in 0..10000000)
        total += measureTimeMillis(block)

    println("$name -> " + total + "ms")

}

class Day1(var readInput: List<String>) {

    fun answerOne(): String {
        val maxCalories = mutableListOf<Int>()
        var total = 0

        readInput.forEach { x ->
            if (x.isNotEmpty()) {
                total += x.toInt()
            } else {
                maxCalories.add(total)
                total = 0
            }
        }
        maxCalories.add(total)

        return "${maxCalories.maxOrNull()}"
    }

    fun answerTwo(): String {
        val maxCalories = mutableListOf<Int>()
        var total = 0
        for (x in readInput) {
            if (x != "") {
                total += x.toInt()
            } else {
                maxCalories.add(total)
                total = 0
            }
        }
        maxCalories.add(total)

        return "${maxCalories.sortedDescending().take( 3).sum()}"
    }
}