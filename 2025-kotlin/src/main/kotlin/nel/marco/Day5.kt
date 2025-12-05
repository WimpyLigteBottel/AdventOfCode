package nel.marco

import java.math.BigInteger
import java.util.concurrent.ConcurrentLinkedDeque

private fun BigInteger.isBetween(it: Pair<BigInteger, BigInteger>): Boolean {
    return it.first <= this && it.second >= this
}

data class Day5(
    val useExample: Boolean = false,
    val useMac: Boolean = true,
) : Day(dayNumber = 5, useExample = useExample, macBook = useMac) {


    override fun answerOne(): String {
        val rangesA: List<Pair<BigInteger, BigInteger>> = readInput
            .filter { it.contains("-") }
            .map { it.split("-") }
            .map { (a, b) -> a.toBigInteger() to b.toBigInteger() }

        val ids: List<BigInteger> = readInput.filter { !it.contains("-") && it.isNotBlank() }.map { it.toBigInteger() }


        val isFresh = ids.count { id -> rangesA.any { id.isBetween(it) } }

        return isFresh.toString()
    }

    data class Range(val start: BigInteger, val end: BigInteger)

    override fun answerTwo(): String {
        val ranges = readInput
            .filter { it.contains("-") }
            .map { it.split("-") }
            .map { (a, b) -> Range(a.toBigInteger(), b.toBigInteger()) }
            .sortedBy { it.start }


        val merged = ConcurrentLinkedDeque<Range>()
        merged.add(ranges.first())

        for (r in ranges) {
            if (r.start > merged.last().end + BigInteger.ONE) {
                // no overlap → add new interval
                merged.add(r)
            } else {
                // overlap → merge into last
                val last = merged.removeLast()
                merged.add(
                    Range(
                        last.start,
                        maxOf(last.end, r.end)
                    )
                )
            }
        }

        val total = merged.sumOf { it.end - it.start + BigInteger.ONE }

        return total.toString()
    }

}
