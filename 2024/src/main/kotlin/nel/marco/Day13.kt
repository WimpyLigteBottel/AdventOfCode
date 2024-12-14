package nel.marco

import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode


class Day13(useExample: Boolean = false, useMac: Boolean = false) :
    Day(dayNumber = 13, useExample = useExample, macBook = useMac) {


    override fun answerTwo(): String {
        readInput.add("")

        var total = BigInteger.ZERO

        while (readInput.isNotEmpty() && readInput.size != 1) {

            runCatching {

                var buttonA = readInput.removeFirst().setupButton()
                var buttonB = readInput.removeFirst().setupButton()
                var prizeTarget = readInput.removeFirst().setupPrizeTarget(true)
                readInput.removeFirst()
                val rationPress = mathSolution(prizeTarget, buttonA, buttonB)
                total = total.add(rationPress.tokenCost())
            }.onFailure {
            }

        }


        return total.toString()
    }

    override fun answerOne(): String {
        readInput.add("")

        var total = BigInteger.ZERO

        while (readInput.isNotEmpty() && readInput.size != 1) {
            var buttonA = readInput.removeFirst().setupButton()
            var buttonB = readInput.removeFirst().setupButton()
            var prizeTarget = readInput.removeFirst().setupPrizeTarget()
            readInput.removeFirst()
            val rationPress = mathSolution(prizeTarget, buttonA, buttonB)
            total = total.add(rationPress.tokenCost())
        }


        return total.toString()
    }

    fun Pair<BigInteger, BigInteger>.tokenCost() = this.first.times(BigInteger("3")).add(this.second)

    fun mathSolution(
        target: Pair<BigInteger, BigInteger>,
        a: Pair<BigInteger, BigInteger>,
        b: Pair<BigInteger, BigInteger>
    ): Pair<BigInteger, BigInteger> {
        val first = target.second.times(b.first)
        val second = target.first.times(b.second)

        var top = first.minus(second)

        var bottom = ((b.first.times(a.second))
            .minus(
                a.first.times(b.second)
            ))

        if (!top.abs().mod(bottom.abs()).equals(BigInteger.ZERO)) {
            return BigInteger.ZERO to BigInteger.ZERO
        }

        var n: BigDecimal = top.toBigDecimal().divide(bottom.toBigDecimal())

        var m: BigDecimal =
            (target.first.toBigDecimal().setScale(2, RoundingMode.FLOOR).minus(n.times(a.first.toBigDecimal()))).divide(
                b.first.toBigDecimal()
            ).setScale(2)

        return n.toBigInteger() to m.toBigInteger()
    }


    fun String.setupButton(): Pair<BigInteger, BigInteger> {

        var x = this.substringAfter("X+").substringBefore(",").toBigInteger()
        var y = this.substringAfter("Y+").substringAfterLast(",").toBigInteger()

        return x to y
    }

    fun String.setupPrizeTarget(part2: Boolean = false): Pair<BigInteger, BigInteger> {

        var x = this.substringAfter("X=").substringBefore(",").toBigInteger()
        var y = this.substringAfter("Y=").substringAfterLast(",").toBigInteger()

        if (part2) {
            val offset = BigInteger("10000000000000")
            return x.add(offset) to y.add(offset)
        } else
            return x to y
    }


}
