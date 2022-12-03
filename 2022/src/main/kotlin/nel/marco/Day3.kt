package nel.marco

import java.lang.RuntimeException

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

        var total = readInput.map {
            val length = it.length
            val bagOne = it.substring(0, length / 2)
            val bagTwo = it.substring(length / 2)

            val setUnique = mutableSetOf<String>()

            bagOne.forEach {
                if (bagTwo.contains(it)) {
                    setUnique.add(it.toString())
                }
            }

            if (setUnique.size != 1) {
                println("BROKEN")
            }

            mapOfValues.get(setUnique.first()).toString()
        }.sumOf { Integer.parseInt(it) }

        return "$total"
    }

    fun answerTwo(): String {
        setupValue()

        var counter = 0
        var totalValue = 0

        while (readInput.size > counter) {
            val subList = readInput.subList(counter, counter + 3)

            val setUnique = mutableSetOf<String>()

            subList[0].forEach {
                if (subList[1].contains(it) && subList[2].contains(it)) {
                    setUnique.add(it.toString())
                }
            }

            totalValue += Integer.parseInt(mapOfValues.get(setUnique.first()).toString())
            counter += 3
        }


        return "$totalValue"
    }

}

fun main(args: Array<String>) {
    val input = ReadUtil.readInputAsList(3, false)
    println("answer 1=" + Day3(input).answerOne())
    println("answer 2=" + Day3(input).answerTwo())
}


