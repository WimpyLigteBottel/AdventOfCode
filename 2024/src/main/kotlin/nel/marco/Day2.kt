package nel.marco

import kotlin.math.absoluteValue


class Day2(useExample: Boolean = false, useMac: Boolean = false) : Day(2, useExample = useExample, macBook = useMac) {

    override fun answerOne(): String {
        return readInput
            .parallelStream()
            .map { it.split(" ") }
            .map { it.map { it.toInt() } }
            .map { toNumbers ->
                (toNumbers.isIncreasing() || toNumbers.isDecreasing()) && isCorrectAppart(toNumbers)
            }
            .toList()
            .count { it }
            .toString()

    }


    override fun answerTwo(): String {
        return readInput
            .parallelStream() // not necessary strictly performance improvement
            .map { it.split(" ") }
            .map { it.map(String::toInt) }
            .map { it.permutationWithOneNumberLess() }
            .map {
                //find one valid sequence
                it.any { toNumbers ->
                    (toNumbers.isIncreasing() || toNumbers.isDecreasing()) && isCorrectAppart(toNumbers)
                }
            }
            .toList()
            .count { it }
            .toString()
    }

    private fun List<Int>.permutationWithOneNumberLess(): MutableList<MutableList<Int>> {
        val newList = mutableListOf<MutableList<Int>>()

        for (x in indices) {
            val temp = mutableListOf<Int>()
            temp.addAll(this)
            temp.removeAt(x)
            newList.add(temp)
        }

        return newList
    }

    private fun isCorrectAppart(toNumbers: List<Int>): Boolean {
        for (x in 0..toNumbers.size - 2) {
            if ((toNumbers[x] - toNumbers[x + 1]).absoluteValue !in 1..3) {
                return false
            }
        }
        return true
    }

    private fun List<Int>.isIncreasing(): Boolean {
        return this.sorted() == this
    }

    private fun List<Int>.isDecreasing(): Boolean {
        return this.sortedDescending() == this
    }
}
