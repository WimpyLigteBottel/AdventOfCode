package nel.marco

import java.math.BigInteger


class Day11(useExample: Boolean = false, useMac: Boolean = false) : Day(11, useExample = useExample, macBook = useMac) {


    override fun answerOne(): String {
        val map = setupMap()

        for (x in 0 until 25) {
            rule(map)
        }

        return map.map { it.value }.sumOf { it.toLong() }.toString()
    }


    fun rule(map: MutableMap<BigInteger, Long>) {
        val secondMap: MutableMap<BigInteger, Long> = mutableMapOf()

        map.forEach { (key, value) ->
            val applyRule = applyRule(key)
            applyRule.first.let {
                secondMap.putIfAbsent(it, 0)
                secondMap[it] = secondMap[it]!! + value
            }
            applyRule.second?.let {
                secondMap.putIfAbsent(it, 0)
                secondMap[it] = secondMap[it]!! + value
            }

        }

        map.clear()
        map.putAll(secondMap)
    }

    fun applyRule(bigInteger: BigInteger): Pair<BigInteger, BigInteger?> {
        if (bigInteger == BigInteger.ZERO) {
            return BigInteger.ONE to null
        } else {
            val digit = bigInteger.toString()
            if (digit.length % 2 == 0) {
                val half = digit.length / 2

                var firstHalf = digit.substring(0, half).toBigInteger()
                var secondHalf = digit.substring(half).toBigInteger()

                return firstHalf to secondHalf
            }
        }
        return bigInteger.times(BigInteger.valueOf(2024)) to null
    }


    override fun answerTwo(): String {
        var map = setupMap()

        for (x in 0 until 75) {
            rule(map)
        }

        return map
            .map { it.value.toBigInteger() }
            .fold(BigInteger.ZERO){ acc, i -> acc.add(i) }
                .toString()

    }

    private fun setupMap(): MutableMap<BigInteger, Long> {
        var map = mutableMapOf<BigInteger, Long>()

        readInput.first().split(" ")
            .map { it.toBigInteger() }
            .forEachIndexed { index, bigInteger ->
                map[bigInteger] = 1
            }

        return map
    }

}