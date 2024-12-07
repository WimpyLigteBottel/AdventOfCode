package nel.marco

import java.math.BigInteger
import kotlin.streams.toList


class Day7(readInput: List<String>) : Day(readInput) {

    /*
        190: 10 19
        3267: 81 40 27
        83: 17 5
        156: 15 6
        7290: 6 8 6 15
        161011: 16 10 13
        192: 17 8 14
        21037: 9 7 18 13
        292: 11 6 16 20
     */
    override fun answerOne(): String {

        var listPossibleEquations = listOf("+", "*")

        return readInput
            .parallelStream()
            .map {
                var (answer, numbers) = it.split(":")

                var numbersList = numbers.split(" ").filter { it != "" }

                if (findPossibleAnswer(numbersList, answer, listPossibleEquations)) {
                    return@map answer.toBigInteger()
                }
                BigInteger.ZERO
            }
            .toList()
            .fold(BigInteger.ZERO) { acc, i -> acc.add(i) }
            .toString()
    }

    private fun findPossibleAnswer(
        numbersList: List<String>,
        answer: String,
        possibleEquations: List<String>
    ): Boolean {

        var allMutations = mutations(possibleEquations, numbersList)

        allMutations.forEach {
            val parsed = parseEquation(it, possibleEquations)
            if(parsed == answer){
                return true
            }
        }

        return false
    }


    private fun parseEquation(mutation: String, possibleEquations: List<String>): String {
        val operators = possibleEquations.associateWith { operator ->
            when (operator) {
                "+" -> { a: BigInteger, b: BigInteger -> a + b }
                "*" -> { a: BigInteger, b: BigInteger -> a * b }
                "|" -> { a: BigInteger, b: BigInteger -> "$a$b".toBigInteger() }
                else -> throw IllegalArgumentException("Unsupported operator: $operator")
            }
        }

        // get the symbols in order
        var orderOfSymbols = mutableListOf<String>()

        mutation.forEach {
            if (possibleEquations.contains(it.toString())) {
                orderOfSymbols.add(it.toString())
            }
        }

        // get digits in order
        var digitsInOrder: MutableList<BigInteger> =  mutation
            .replace("|"," ")
            .replace("*"," ")
            .replace("+"," ")
            .split(" ")
            .map { it.toBigInteger() }
            .toMutableList()

        while(digitsInOrder.size > 1){
            val function = operators[orderOfSymbols.removeFirst()] ?: continue
            var result = function(digitsInOrder[0],digitsInOrder[1])

            digitsInOrder.removeFirst()
            digitsInOrder.set(0, result)
        }


        return digitsInOrder.first().toString()
    }

    fun mutations(
        possibleEquations: List<String>,
        numbersList: List<String>
    ): MutableList<String> {
        val result = mutableListOf<String>()

        // Helper function to generate permutations recursively
        fun generate(current: String, index: Int) {
            if (index == numbersList.size) {
                result.add(current)
                return
            }

            for (symbol in possibleEquations) {
                generate("$current$symbol${numbersList[index]}", index + 1)
            }
        }

        // Initialize the recursion with the first number
        if (numbersList.isNotEmpty()) {
            generate(numbersList[0], 1)
        }

        return result
    }


    override fun answerTwo(): String {

        var concat = readInput
            .parallelStream()
            .map {
                var (answer, numbers) = it.split(":")
                var numbersList = numbers.split(" ").filter { it != "" }

                if (findPossibleAnswer(numbersList, answer, listOf("+", "*", "|"))) {
                    return@map answer.toBigInteger()
                }
                BigInteger.ZERO
            }
            .toList()
            .fold(BigInteger.ZERO) { acc, i -> acc.add(i) }
            .toString()

        return concat
    }


}
