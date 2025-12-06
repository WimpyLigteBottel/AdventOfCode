package nel.marco

import java.math.BigInteger

typealias OperatorAndIndex = Pair<String, Int>


data class Day6(
    val useExample: Boolean = false,
    val useMac: Boolean = true,
) : Day(dayNumber = 6, useExample = useExample, macBook = useMac) {

    /*
    I modified the example and input ot have 0 and + at the end for easier parsing
    xxx xxx  xx xx  0
     xx xx  xxx xx  0
      x xx  xxx xxx 0
    .   .   .   .   +
     */

    override fun answerOne(): String {

        val isExample = readInput[3].contains("*")

        val numbersRow1 = mutableListOf<String>()
        val numbersRow2 = mutableListOf<String>()
        val numbersRow3 = mutableListOf<String>()
        val numbersRow4 = mutableListOf<String>()
        val operator = getOperatorsAndStartingIndex(isExample)


        // get the numbers for the other rows
        populateRows(operator, numbersRow1, numbersRow2, numbersRow3, numbersRow4)

        // removed the extra operator to make my life easier
        operator.removeLast()

        var total = BigInteger.ZERO

        for (x in 0 until operator.size) {
            val newList = getNumbersAsList(x, numbersRow1, numbersRow2, numbersRow3, numbersRow4, isExample)
            total += calculateAnswer(operator[x].first, newList)
        }


        return total.toString()
    }

    private fun getNumbersAsList(
        x: Int,
        numbersRow1: MutableList<String>,
        numbersRow2: MutableList<String>,
        numbersRow3: MutableList<String>,
        numbersRow4: MutableList<String>,
        isExample: Boolean
    ): List<BigInteger> {
        var newList = mutableListOf(numbersRow1[x], numbersRow2[x], numbersRow3[x], numbersRow4[x])
        if (isExample) {
            newList = mutableListOf(numbersRow1[x], numbersRow2[x], numbersRow3[x])
        }
        return newList.map { it.replace(" ", "") }.map { it.toBigInteger() }
    }

    private fun populateRows(
        operator: MutableList<Pair<String, Int>>,
        numbersRow1: MutableList<String>,
        numbersRow2: MutableList<String>,
        numbersRow3: MutableList<String>,
        numbersRow4: MutableList<String>
    ) {
        for (x in 0 until operator.size - 1) {
            val current = operator[x].second
            val next = operator[x + 1].second - 1

            numbersRow1.add(readInput[0].substring(current, next))
            numbersRow2.add(readInput[1].substring(current, next))
            numbersRow3.add(readInput[2].substring(current, next))
            numbersRow4.add(readInput[3].substring(current, next))

        }
    }


    override fun answerTwo(): String {

        val isExample = readInput[3].contains("*")

        val numbersRow1 = mutableListOf<String>()
        val numbersRow2 = mutableListOf<String>()
        val numbersRow3 = mutableListOf<String>()
        val numbersRow4 = mutableListOf<String>()
        val operator = getOperatorsAndStartingIndex(isExample)

        populateRows(operator, numbersRow1, numbersRow2, numbersRow3, numbersRow4)

        // removed the extra operator i added in input
        operator.removeLast()

        var total = BigInteger.ZERO

        for (x in 0..operator.size - 1) {

            val newList = getNumbersAsListButReversed(x, numbersRow1, numbersRow2, numbersRow3, numbersRow4, isExample)
            val answer = calculateAnswer(operator[x].first, newList)

            total += answer
        }


        return total.toString()
    }

    private fun calculateAnswer(
        operator: String,
        newList: List<BigInteger>
    ): BigInteger {
        var answer = BigInteger.ZERO
        if (operator == "*") {
            answer = newList.fold(BigInteger.ONE) { acc, i -> acc.times(i) }
        }

        if (operator == "+") {
            answer = newList.fold(BigInteger.ZERO) { acc, i -> acc.plus(i) }
        }
        return answer
    }

    private fun getNumbersAsListButReversed(
        x: Int,
        numbersRow1: MutableList<String>,
        numbersRow2: MutableList<String>,
        numbersRow3: MutableList<String>,
        numbersRow4: MutableList<String>,
        isExample: Boolean,
    ): List<BigInteger> {
        val newList = mutableListOf<String>()

        for (y in 0..numbersRow1[x].length - 1) {
            val one = numbersRow1[x][y].toString()
            val two = numbersRow2[x][y].toString()
            val three = numbersRow3[x][y].toString()
            val four = if(isExample) "" else numbersRow4[x][y].toString()

            val joinedNumber = listOf(one, two, three, four).joinToString("").trim()
            newList.add(joinedNumber)
        }
        return newList.map { it.toBigInteger() }
    }


    /*
    Because example has only 3 rows and actual input has 4
    
    i need to do special check to grab the correct operator row
     */
    private fun getOperatorsAndStartingIndex(isExample: Boolean): MutableList<OperatorAndIndex> {
        val operators = mutableListOf<OperatorAndIndex>()

        for (index in 0..readInput[0].length - 1) {
            val operator = if (isExample)
                readInput[3][index].toString()
            else
                readInput[4][index].toString()

            if (operator != " ")
                operators.add(operator to index)
        }
        return operators
    }


}
