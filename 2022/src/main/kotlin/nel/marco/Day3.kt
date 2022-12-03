package nel.marco

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

            val intersectingCharacter = subList[0].toList()
                .intersect(subList[1].toList())
                .intersect(subList[2].toList())
                .first().toString()

            totalValue += mapOfValues[intersectingCharacter]!!
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


