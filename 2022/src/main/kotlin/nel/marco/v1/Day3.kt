package nel.marco.v1

import nel.marco.MarcoUtil

class Day3(var readInput: List<String>) {

    var mapOfValues = setupValue()

    fun setupValue(): MutableMap<String, Int> {
        var temp = mutableMapOf<String, Int>()

        var counter = 1
        for (x in 'a'..'z') {
            temp[x.toString()] = counter++
        }

        counter = 27
        for (x in 'A'..'Z') {
            temp[x.toString()] = counter++
        }

        return temp
    }

    fun answerOne(): String {
        setupValue()

        val total = readInput.map {
            val length = it.length
            val bagOne = it.substring(0, length / 2).toList()
            val bagTwo = it.substring(length / 2).toList()

            val intersectingCharacter = bagOne.intersect(bagTwo).first().toString()
            mapOfValues[intersectingCharacter].toString()
        }.sumOf { Integer.parseInt(it) }

        return "$total"
    }

    fun answerTwo(): String {
        setupValue()

        var counter = 0
        var totalValue = 0

        while (readInput.size > counter) {
            val subList = readInput.subList(counter, counter + 3)

            val intersectingCharacter = subList[0].toSet()
                .intersect(subList[1].toSet())
                .intersect(subList[2].toSet())
                .first().toString()

            totalValue += mapOfValues[intersectingCharacter]!!
            counter += 3
        }


        return "$totalValue"
    }

}

fun main(args: Array<String>) {
    var readAllLines = MarcoUtil.readInput(3, false) as MutableList<String>

    val part1: () -> Unit = {
        val day = Day3(readAllLines)
        println("part1 = ${day.answerOne()}")
    }
    val part2: () -> Unit = {
        val day = Day3(readAllLines)
        println("part2 = ${day.answerTwo()}")
    }
    MarcoUtil.time("part1", part1)
    MarcoUtil.time("part2", part2)
}


