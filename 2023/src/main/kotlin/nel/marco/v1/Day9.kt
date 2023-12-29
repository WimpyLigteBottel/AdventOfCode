package nel.marco.v1

import java.math.BigInteger

class Day9(readInput: List<String>) : Day(readInput) {


    override fun answerOne(): String {

        return readInput.map {
            var rows = mutableListOf<MutableList<BigInteger>>()
            rows.add(it.split(" ").map { it.toBigInteger() }.toMutableList())

            while (rows.last.size > 0) {
                val row = getDiff(rows.last)
                rows.add(row)
            }
            rows = rows
                .filter { row -> row.size > 0L }
                .toMutableList()

            rows = rows.reversed().toMutableList()

            runCatching {
                for ((index, bigIntegers) in rows.withIndex()) {
                    val nextValue = rows[index + 1].last + rows[index].last
                    rows[index + 1].add(nextValue)
                }
            }

            rows = rows.reversed().toMutableList()

            rows[0].last
        }
            .sumOf { it }
            .toString()
    }

    private fun getDiff(numbers: List<BigInteger>) = (1 until numbers.size step 1).map { x ->
        numbers[x] - numbers[x - 1]
    }.toMutableList()

    override fun answerTwo(): String {
        return readInput.map {
            var rows = mutableListOf<MutableList<BigInteger>>()
            rows.add(it.split(" ").map { it.toBigInteger() }.toMutableList())

            while (rows.last.size > 0) {
                val row = getDiff(rows.last)
                rows.add(row)
            }

            rows = rows
                .filter { row -> row.size > 0L }
                .toMutableList()

            rows = rows.reversed().toMutableList()

            runCatching {
                for ((index, bigIntegers) in rows.withIndex()) {
                    val nextValue = rows[index + 1].first - rows[index].first
                    rows[index + 1].add(0, nextValue)
                }
            }

            rows = rows.reversed().toMutableList()

            rows[0].last
        }
            .sumOf { it }
            .toString()
    }


}