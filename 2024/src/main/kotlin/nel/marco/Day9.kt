package nel.marco

import java.math.BigInteger
import java.util.Collections


class Day9(useExample: Boolean = false, useMac: Boolean = false) : Day(9, useExample = useExample, macBook = useMac) {

    override fun answerOne(): String {

        val mapList = 100000
        val map: MutableMap<Int, String> = (0..mapList)
            .mapIndexed { index, c -> index to c.toString() }
            .associate { it.first to it.second +"."}
            .toMutableMap()

        val reverse: MutableMap<String, Int> = (0..mapList)
            .mapIndexed { index, c -> index to c.toString() }
            .associate { it.second+"." to it.first }
            .toMutableMap()


        var newList = expandList(map)
//        println("before: " + newList)
        compact(newList)
//        println("after: " + newList)


        var total = newList
            .filter { it != "" }
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
        val withoutDots = packedInto.filter { it != "." }.asReversed().toMutableList()

        var counter = 0

        for ((index, s) in packedInto.withIndex()) {
            if (s == ".") {
                counter++
                while (packedInto[packedInto.size - counter] == ".") {
                    counter++
                }
                Collections.swap(packedInto, index, packedInto.size - counter)
                packedInto.set(packedInto.size - counter, "")
            }
        }

        packedInto.removeAll { it == "" }
    }

    override fun answerTwo(): String {
        return ""
    }
}
