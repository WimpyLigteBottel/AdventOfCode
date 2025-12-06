package nel.marco

import java.math.BigInteger

data class Day6(
    val useExample: Boolean = false,
    val useMac: Boolean = true,
) : Day(dayNumber = 6, useExample = useExample, macBook = useMac) {


    override fun answerOne(): String {

        val isExample = readInput[3].contains("*")

        val numbersRow1 = mutableListOf<String>()
        val numbersRow2 = mutableListOf<String>()
        val numbersRow3 = mutableListOf<String>()
        val numbersRow4 = mutableListOf<String>()
        val operator = mutableListOf<Pair<String, Int>>()

        for (x in 0..readInput[0].length - 1) {

            val ch = if (isExample)
                readInput[3][x].toString()
            else
                readInput[4][x].toString()

            if (ch != " ")
                operator.add(ch to x)
        }

        for (x in 1..operator.size - 1) {
            val current = operator[x].second - 1
            val previous = operator[x - 1].second

            numbersRow1.add(readInput[0].substring(previous, current))
            numbersRow2.add(readInput[1].substring(previous, current))
            numbersRow3.add(readInput[2].substring(previous, current))
            numbersRow4.add(readInput[3].substring(previous, current))

        }

        var total = BigInteger.ZERO

        for (x in 0..operator.size - 2) {

            var newList = mutableListOf(numbersRow1[x], numbersRow2[x], numbersRow3[x], numbersRow4[x])
            if(isExample){
                newList = mutableListOf(numbersRow1[x], numbersRow2[x], numbersRow3[x])
            }
            newList = newList
                .filter { it.isNotBlank() }
                .map {it.replace(" ","") }
                .toMutableList()


            var answer = BigInteger.ZERO
            if (operator[x].first == "*") {
                answer = newList.map { BigInteger(it) }.fold(BigInteger.ONE) { acc, i -> acc.times(i) }
            }

            if (operator[x].first == "+") {
                answer = newList.map { BigInteger(it) }.fold(BigInteger.ZERO) { acc, i -> acc.plus(i) }
            }

            total += answer
        }


        return total.toString()
    }


    override fun answerTwo(): String {

        val isExample = readInput[3].contains("*")

        val numbersRow1 = mutableListOf<String>()
        val numbersRow2 = mutableListOf<String>()
        val numbersRow3 = mutableListOf<String>()
        val numbersRow4 = mutableListOf<String>()
        val operator = mutableListOf<Pair<String, Int>>()

        for (x in 0..readInput[0].length - 1) {

            val ch = if (isExample)
                readInput[3][x].toString()
            else
                readInput[4][x].toString()

            if (ch != " ")
                operator.add(ch to x)
        }

        for (x in 1..operator.size - 1) {
            val current = operator[x].second - 1
            val previous = operator[x - 1].second

            numbersRow1.add(readInput[0].substring(previous, current))
            numbersRow2.add(readInput[1].substring(previous, current))
            numbersRow3.add(readInput[2].substring(previous, current))
            numbersRow4.add(readInput[3].substring(previous, current))

        }

        var total = BigInteger.ZERO

        for (x in 0..operator.size - 2) {

            var newList = mutableListOf<String>()


            for (y in 0..numbersRow1[x].length - 1) {
                val one = numbersRow1[x]
                val two = numbersRow2[x]
                val three = numbersRow3[x]

                if (!isExample) {
                    val four = numbersRow4[x]
                    newList.add(("" + one[y] + two[y] + three[y] + four[y]).trim())
                } else {
                    newList.add(("" + one[y] + two[y] + three[y]).trim())
                }

            }
            newList = newList.filter { it.isNotBlank() }.toMutableList()


            var answer = BigInteger.ZERO
            if (operator[x].first == "*") {
                answer = newList.map { BigInteger(it) }.fold(BigInteger.ONE) { acc, i -> acc.times(i) }
            }

            if (operator[x].first == "+") {
                answer = newList.map { BigInteger(it) }.fold(BigInteger.ZERO) { acc, i -> acc.plus(i) }
            }

            total += answer
        }


        return total.toString()
    }


}
