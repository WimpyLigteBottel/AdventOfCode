package nel.marco

import java.math.BigInteger

class Day3(
    useExample: Boolean = false,
    useMac: Boolean = true,
) : Day(dayNumber = 3, useExample = useExample, macBook = useMac) {
    override fun answerOne(): String {
        return readInput
            .parallelStream()
            .map { greedSelect(it, 2) }
            .toList()
            .sumOf { it }
            .toString()
    }


    override fun answerTwo(): String {
        return readInput
            .parallelStream()
            .map { greedSelect(it, 12) }
            .toList()
            .sumOf { it }
            .toString()
    }

    /**
     * Try to select as greedy as possible from left to right to created the highest number.
     */
    fun greedSelect(input: String, target: Int): BigInteger {
        val stack = ArrayDeque<Char>()
        //  How many tries we have that is allowed to remove a digit
        var allowedToRemove = input.length - target

        for (c in input) {
            while (
                stack.isNotEmpty() && // prevent errors
                allowedToRemove > 0 && // check if i can still fix 'mistakes'
                stack.last() < c // latest is bigger than last
            ) {
                stack.removeLast()
                allowedToRemove--
            }
            stack.addLast(c)
        }

        // If we still have too many digits (all were non-increasing) OR i had no more removals let
        while (stack.size > target) stack.removeLast()

        return stack.joinToString("").toBigInteger()
    }


}
