package nel.marco

import java.math.BigInteger

class Day3(
    useExample: Boolean = false,
    useMac: Boolean = true,
) : Day(dayNumber = 3, useExample = useExample, macBook = useMac) {
    override fun answerOne(): String {
        return readInput.sumOf { line ->

            var highest = 0L
            for (i in 0..line.length - 1) {
                for (x in i + 1..line.length - 1) {
                    val newNumber = "${line[i]}${line[x]}".toLong()
                    highest = Math.max(highest, newNumber)
                }
            }

            highest
        }.toString()
    }


    override fun answerTwo(): String {
        return readInput.sumOf { line ->
            greedSelect(line, 12)
        }.toString()
    }

    /*
    987654321111111
    811111111111119
    234234234234278
    818181911112111
     */

    fun greedSelect(input: String, k: Int): BigInteger {
        val stack = ArrayDeque<Char>()
        var toRemove = input.length - k

        for (c in input) {
            while (stack.isNotEmpty() && toRemove > 0 && stack.last() < c) {
                stack.removeLast()
                toRemove--
            }
            stack.addLast(c)
        }

        // If we still have too many digits (all were non-increasing)
        while (stack.size > k) stack.removeLast()

        return stack.joinToString("").toBigInteger()
    }


}
