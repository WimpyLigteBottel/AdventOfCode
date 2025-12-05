package nel.marco

import java.math.BigInteger

private fun BigInteger.isBetween(it: Day5.Range): Boolean {
    return it.start <= this && it.end >= this
}

data class Day5(
    val useExample: Boolean = false,
    val useMac: Boolean = true,
) : Day(dayNumber = 5, useExample = useExample, macBook = useMac) {


    override fun answerOne(): String {
        var ranges = readInput
            .filter { it.contains("-") }
            .map { it.split("-") }
            .map { (a, b) -> Range(a.toBigInteger(), b.toBigInteger()) }
            .sortedBy { it.start }

        ranges = mergeRanges(ranges)

        val ids: List<BigInteger> = readInput.filter { !it.contains("-") && it.isNotBlank() }.map { it.toBigInteger() }

        val isFresh = ids.count { id -> ranges.any { id.isBetween(it) } }

        return isFresh.toString()
    }

    data class Range(val start: BigInteger, val end: BigInteger)

    override fun answerTwo(): String {
        val ranges = readInput
            .filter { it.contains("-") }
            .map { it.split("-") }
            .map { (a, b) -> Range(a.toBigInteger(), b.toBigInteger()) }
            .sortedBy { it.start }

        val merged = mergeRanges(ranges)

        // need to +1 because otherwise we dont count it correctly
        val total = merged.sumOf { it.end - it.start + BigInteger.ONE }

        return total.toString()
    }

    /*
    Merges the ranges so that i can correctly count freshness
     */
    private fun mergeRanges(ranges: List<Range>): ArrayDeque<Range> {
        val merged = ArrayDeque<Range>()
        merged.add(ranges.first())

        for (currentRange in ranges) {
            var last = merged.last()
            if (currentRange.start > last.end + BigInteger.ONE) {
                // no overlap → add new interval
                merged.add(currentRange)
                continue
            }

            // remove the last value and we are going to replace it
            last = merged.removeLast()

            val newRange = Range(
                start = last.start,
                end = maxOf(last.end, currentRange.end)
            )

            merged.add(newRange)
        }
        return merged
    }

}
