package nel.marco

import java.math.BigInteger

class Day2(
    useExample: Boolean = false,
    useMac: Boolean = true,
) : Day(dayNumber = 2, useExample = useExample, macBook = useMac) {
    override fun answerOne(): String {
        val lines = readInput.first().split(",")

        val digits = expandDigits(lines)

        val count =
            digits
                .filter { it ->
                    if (it.length % 2 == 0) {
                        val left = it.substring(0, it.length / 2)
                        val right = it.substring(it.length / 2)

                        if (right.startsWith("0") && left == right) {
                            return@filter false
                        }

                        return@filter left == right
                    }

                    return@filter false
                }.map { it.toBigInteger() }
                .fold(BigInteger.ZERO) { a, b -> a + b }

        return count.toString()
    }

    private fun expandDigits(lines: List<String>): MutableList<String> {
        val digits = mutableSetOf<String>()

        lines.forEach { line ->
            val first = line.split("-")[0].toBigInteger()
            val until = line.split("-")[1].toBigInteger()
            var current = first
            while (current <= until) {
                digits.add(current.toString())
                current = current.add(BigInteger.ONE)
            }
        }
        return digits.toMutableList()
    }

    override fun answerTwo(): String {
        val lines = readInput.first().split(",")

        val digits = expandDigits(lines)

        val invalidDigits =
            digits
                .filter {
                    // if more than half the does not work exit out
                    for (end in 0 until it.length / 2 + 1) {
                        val temp = it.substring(0, end)
                        if (it.replace(temp, "").isBlank()) {
                            return@filter true
                        }
                    }
                    return@filter false
                }.map { it.toBigInteger() }


        return invalidDigits.fold(BigInteger.ZERO) { a, b -> a + b }.toString()
    }
}
