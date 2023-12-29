package nel.marco.v1

import java.math.BigInteger

class Day9(readInput: List<String>) : Day(readInput) {


    override fun answerOne(): String {

        return readInput.map { it ->
            var rows = createRows(it)

            rows.reverse()
            runCatching {
                for ((index, _) in rows.withIndex()) {
                    val nextValue = rows[index + 1].last + rows[index].last
                    rows[index + 1].add(nextValue)
                }
            }
            rows.reverse()
            rows
        }
            .sumOf { it[0].last() } // sum all the last digits
            .toString()
    }



    override fun answerTwo(): String {
        return readInput.map {
            var rows = createRows(it)

            rows.reverse()
            runCatching {
                for ((index, bigIntegers) in rows.withIndex()) {
                    val nextValue = rows[index + 1].first - rows[index].first
                    rows[index + 1].add(0, nextValue)
                }
            }

            rows.reverse()
            rows
        }
            .sumOf { it[0].first() } // sum all the last digits
            .toString()
    }

    private fun createRows(
        it: String,
    ): MutableList<MutableList<BigInteger>> {
        var rows = mutableListOf<MutableList<BigInteger>>()

        rows.add(it.split(" ").map { it.toBigInteger() }.toMutableList())

        while (rows.last.size > 0) {
            val row = getDiff(rows.last)
            rows.add(row)
        }

        rows = rows
            .filter { row -> row.size > 0L }
            .toMutableList()

        return rows;
    }

    private fun getDiff(numbers: List<BigInteger>) =
        (1 until numbers.size step 1)
            .map { x -> numbers[x] - numbers[x - 1] }.toMutableList()


}