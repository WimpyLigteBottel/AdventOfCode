package nel.marco

import kotlin.math.absoluteValue


class Day2(readInput: List<String>) : Day(readInput) {

    override fun answerOne(): String {
        return readInput
            .map { it.split(" ") }
            .map {
                val toNumbers = it.map { it.toInt() }

                (toNumbers.isIncreasing() || toNumbers.isDecreasing()) && isCorrectAppart(toNumbers)
            }
            .count { it }
            .toString()

    }


    override fun answerTwo(): String {
        return readInput
            .map { it.split(" ") }
            .map {
                val toNumbers = it.map { it.toInt() }
                toNumbers.permutateWithOneNumberLess()
            }
            .map {
                it.any { toNumbers ->
                    (toNumbers.isIncreasing() || toNumbers.isDecreasing()) && isCorrectAppart(toNumbers)
                }
            }
            .count { it }
            .toString()
    }

    private fun List<Int>.permutateWithOneNumberLess(): MutableList<MutableList<Int>> {
        var newList = mutableListOf<MutableList<Int>>()


        for (x in 0..this.size - 1) {
            val temp = mutableListOf<Int>()
            temp.addAll(this)
            temp.removeAt(x)
            newList.add(temp)
        }

        return newList
    }

    private fun isCorrectAppart(toNumbers: List<Int>): Boolean {
        var isCorrectApart = true
        for (x in 0..toNumbers.size - 2) {
            var apart = (toNumbers[x] - toNumbers[x + 1]).absoluteValue

            if (apart !in 1..3) {
                return false
            }
        }
        return isCorrectApart
    }

    private fun List<Int>.isIncreasing(): Boolean {
        return this.sorted() == this
    }

    private fun List<Int>.isDecreasing(): Boolean {
        return this.sortedDescending() == this
    }
}
