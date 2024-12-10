package nel.marco

import java.math.BigInteger
import java.util.*


class Day9(useExample: Boolean = false, useMac: Boolean = false) : Day(9, useExample = useExample, macBook = useMac) {

    override fun answerOne(): String {

        val mapList = 10000
        val map: MutableMap<Int, String> =
            (0..mapList).mapIndexed { index, c -> index to c.toString() }.associate { it.first to it.second }
                .toMutableMap()

        val reverse: MutableMap<String, Int> =
            (0..mapList).mapIndexed { index, c -> index to c.toString() }.associate { it.second to it.first }
                .toMutableMap()


        var newList = expandList(map)
        compact(newList)


        var total = newList
            .mapIndexed { index: Int, indexedValue: String ->
                BigInteger(index.toString()).times(BigInteger((reverse[indexedValue] ?: 0).toString()))
            }.fold(BigInteger.ZERO) { acc, bigInteger -> acc.add(bigInteger) }

        return "$total"
    }

    override fun answerTwo(): String {
        val mapList = 10000
        val map: MutableMap<Int, String> =
            (0..mapList).mapIndexed { index, c -> index to c.toString() }.associate { it.first to it.second }
                .toMutableMap()

        val reverse: MutableMap<String, Int> =
            (0..mapList).mapIndexed { index, c -> index to c.toString() }.associate { it.second to it.first }
                .toMutableMap()


        var newList = expandList(map)
        compactV2(newList)


        var total = newList.mapIndexed { index: Int, indexedValue: String ->
            val index = BigInteger("$index")
            val other = BigInteger("${reverse[indexedValue] ?: 0}")

            index.times(other)
        }.fold(BigInteger.ZERO) { acc, bigInteger -> acc.add(bigInteger) }

        return "$total"
    }


    private fun expandList(map: MutableMap<Int, String>): MutableList<String> {
        val line = readInput.first().map { it }

        var newList = ArrayList<String>(94570)


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


    private fun compact(packedInto: MutableList<String>) {
        var counter = 0

        for ((index, s) in packedInto.withIndex()) {
            if (index + counter == packedInto.size) break
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


    private fun compactV2(packedInto: MutableList<String>) {
        var workBackwards: String = packedInto[packedInto.size - 1]
        var toProcess = mutableListOf<Pair<String, Pair<Int, Int>>>()

        while (workBackwards != "0") {
            toProcess.add(digitsFromBack(packedInto, workBackwards))
            workBackwards = (workBackwards.toInt() - 1).toString()
        }

        toProcess.forEach { a ->
            val size = a.second.second - a.second.first + 1

            val spot = getOpenSpots(packedInto, size, a.second.first)

            spot?.let { firstOpenSlot ->
                for (x in firstOpenSlot.first until firstOpenSlot.first + size) {
                    packedInto[x] = a.first
                }
                for (x in a.second.first..a.second.second) {
                    packedInto[x] = "."
                }
            }
        }
    }

    private fun getOpenSpots(
        packedInto: MutableList<String>,
        needToHandleSize: Int,
        dontGoFurtherThan: Int
    ): Pair<Int, Int>? {
        var firstOpenSpot = packedInto.indexOf(".")
        var from = firstOpenSpot

        while (firstOpenSpot != -1) {
            if (from > dontGoFurtherThan) {
                return null
            }

            if (packedInto[from] != ".") {
                // multi jump check
                if (packedInto[from + 1] != ".") {
                    from += 2
                    continue
                }
                from++
                continue
            }
            var to = from

            while (packedInto[to] == ".") {
                to++
            }

            val avail = to - from

            val pair = Pair(from, to)
            if (avail >= needToHandleSize) {
                return pair
            }

            from += avail
        }
        return null
    }

    private fun digitsFromBack(
        list: MutableList<String>,
        workBackwords: String
    ): Pair<String, Pair<Int, Int>> {

        var start = list.indexOf(workBackwords)
        var end = list.lastIndexOf(workBackwords)

        return workBackwords to (start to end)
    }

}
