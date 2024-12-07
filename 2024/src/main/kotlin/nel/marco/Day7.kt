package nel.marco

import java.math.BigInteger


class Day7(readInput: List<String>) : Day(readInput) {
    val possibleOperations = listOf("+", "*", "|")

    val operators = possibleOperations.associateWith { operator ->
        when (operator) {
            "+" -> { a: BigInteger, b: BigInteger -> a + b }
            "*" -> { a: BigInteger, b: BigInteger -> a * b }
            "|" -> { a: BigInteger, b: BigInteger -> "$a$b".toBigInteger() }
            else -> throw IllegalArgumentException("Unsupported operator: $operator")
        }
    }

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
            val orderOfSymbols = it.replace("\\d+".toRegex(), "").map {
                it.toString()
            }

            val parsed = parseEquation(it, orderOfSymbols)
            if (parsed == answer) {
                return true
            }
        }

        return false
    }


    private fun parseEquation(mutation: String, orderOfSymbols: List<String>): String {
        // get digits in order
        var digitsInOrder: MutableList<BigInteger> = mutation
            .replace("|", " ")
            .replace("*", " ")
            .replace("+", " ")
            .split(" ")
            .map { it.toBigInteger() }
            .toMutableList()

        orderOfSymbols.forEach {
            var result =  operators[it]!!(digitsInOrder[0], digitsInOrder[1])

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
        fun generate(current: String, index: Int) {
            if (index == numbersList.size) {
                result.add(current)
                return
            }

            for (symbol in possibleEquations) {
                generate("$current$symbol${numbersList[index]}", index + 1)
            }
        }

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

                if (findPossibleAnswer(numbersList, answer, possibleOperations)) {
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
