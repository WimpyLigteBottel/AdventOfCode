package nel.marco

import java.math.BigInteger
import java.util.Collections


class Day9(useExample: Boolean = false, useMac: Boolean = false) : Day(9, useExample = useExample, macBook = useMac) {

    override fun answerOne(): String {

        val mapList = 10000
        val map: MutableMap<Int, String> = (0..mapList)
            .mapIndexed { index, c -> index to c.toString() }
            .associate { it.first to it.second }
            .toMutableMap()

        val reverse: MutableMap<String, Int> = (0..mapList)
            .mapIndexed { index, c -> index to c.toString() }
            .associate { it.second to it.first }
            .toMutableMap()


        var newList = expandList(map)
        compact(newList)


        var total = newList
            .filter { it != "." }
            .mapIndexed { index: Int, indexedValue: String ->
                val index = BigInteger("$index")
                val other = BigInteger("${reverse[indexedValue]!!}")

                index.times(other)
            }.fold(BigInteger.ZERO) { acc, bigInteger -> acc.add(bigInteger) }

        return "$total"
    }

    private fun expandList(map: MutableMap<Int, String>): MutableList<String> {
        val line = readInput.first().map { it }

        var newList = mutableListOf<String>()

        var index = 0
        var digit = 0
        while (index < line.size - 1) {
            var file = line[index].digitToInt()
            var space = line[index + 1].digitToInt()
            repeat(file) {
                newList.add(map[digit].toString())
            }
            repeat(space) {
                newList.add(".")
            }
            digit++
            index++
            index++
        }
        // last line
        repeat(line[index].digitToInt()) {
            newList.add(map[digit].toString())
        }
        return newList
    }


    fun compact(packedInto: MutableList<String>) {
        var counter = 0

        for ((index, s) in packedInto.withIndex()) {
            if (index + counter == packedInto.size)
                break
            if (s == ".") {
                counter++
                while (packedInto[packedInto.size - counter] == ".") {
                    counter++
                }
                Collections.swap(packedInto, index, packedInto.size - counter)
                packedInto[packedInto.size - counter] = "."
            }
        }

    }

    var map: MutableMap<String, String> = mutableMapOf()


    fun compactV2(packedInto: MutableList<String>) {
        var workBackwards = packedInto[packedInto.size - 1]

        var toProcess = mutableListOf<Pair<MutableList<String>, Pair<Int, Int>>>()

        while (workBackwards != "0") {
            var digits = digitsFromBack(packedInto, workBackwards)
            toProcess.add(digits)
            workBackwards = (workBackwards.toInt() - 1).toString()
        }

        var openSpots = getOpenSpots(packedInto)

        toProcess.forEach { a ->

            val size = a.first.size

            openSpots
                .filter { it.second - it.first >= size }
                .firstOrNull()?.let { firstOpenSlot ->
                    for (x in firstOpenSlot.first until firstOpenSlot.first + a.first.size) {
                        if (a.first.isNotEmpty()) {
                            packedInto[x] = a.first.first()
                        }
                    }

                    for (x in a.second.first..a.second.second) {
                        packedInto[x] = "."
                    }

                    val distance = firstOpenSlot.second - firstOpenSlot.first
                    if (distance > a.first.size) {
                        val used = distance - size
                        openSpots.add(Pair(firstOpenSlot.second- used, firstOpenSlot.second))
                        openSpots.sortBy { it.first }
                    }

                    openSpots.remove(firstOpenSlot)
                }

        }
    }

    private fun getOpenSpots(packedInto: MutableList<String>): MutableList<Pair<Int, Int>> {
        var openSpots = mutableListOf<Pair<Int, Int>>()

        for ((index, s) in packedInto.withIndex()) {
            if (s != ".") {
                continue
            }
            var from = index
            var to = index

            while (packedInto[to] == ".") {
                to++
            }
            if (openSpots.filter { it.second == to }.size > 0) {
                continue
            }

            openSpots.add(Pair(from, to))
        }
        return openSpots
    }

    fun digitsFromBack(
        list: MutableList<String>,
        workBackwords: String
    ): Pair<MutableList<String>, Pair<Int, Int>> {

        var start = list.indexOf(workBackwords)
        var end = list.lastIndexOf(workBackwords)

        if (start == -1 || end == -1)
            return mutableListOf<String>() to (start to end)

        val subList = list.subList(start, end + 1)
        return subList to (start to end)
    }

    override fun answerTwo(): String {
        val mapList = 10000
        val map: MutableMap<Int, String> = (0..mapList)
            .mapIndexed { index, c -> index to c.toString() }
            .associate { it.first to it.second }
            .toMutableMap()

        val reverse: MutableMap<String, Int> = (0..mapList)
            .mapIndexed { index, c -> index to c.toString() }
            .associate { it.second to it.first }
            .toMutableMap()


        var newList = expandList(map)
        println(newList)

        kotlin.runCatching {
            compactV2(newList)
        }
        println(newList)


        var total = newList
            .mapIndexed { index: Int, indexedValue: String ->
                val index = BigInteger("$index")
                val other = BigInteger("${reverse[indexedValue] ?: 0}")

                index.times(other)
            }.fold(BigInteger.ZERO) { acc, bigInteger -> acc.add(bigInteger) }

        return "$total"
    }
}
