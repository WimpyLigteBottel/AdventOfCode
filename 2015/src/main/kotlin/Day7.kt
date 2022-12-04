import java.lang.NumberFormatException

class Day7(private var input: MutableList<String>) {

    val mapResults = mutableMapOf<String, String>()
    val wires = mutableSetOf<String>()


    fun part1(): Int {
        input
            .map {
                val text = it.split("->")
                "${text[1]} -> ${text[0]}"
            }
            .forEach {
                val key = it.split("->")[0].trim()
                var value = it.split("->")[1]

                mapResults[key] = value
            }

        tryToFillAsMuchAsPossible()

        return mapResults["a"]!!.trim().toInt()
    }

    fun part2(): Int {
        input
            .map {
                val text = it.split("->")
                "${text[1]} -> ${text[0]}"
            }
            .forEach {
                val key = it.split("->")[0].trim()
                var value = it.split("->")[1]

                if (key == "b")
                    value = "956"

                mapResults[key] = value
            }

        tryToFillAsMuchAsPossible()

        return mapResults["a"]!!.trim().toInt()
    }

    private fun tryEquations() {
        mapResults.forEach {
            if (it.value.contains("RSHIFT")) {
                val split = it.value.trim().split("RSHIFT")

                if (split[0].isDigit() && split[1].isDigit()) {
                    mapResults[it.key] = split[0].trim().toInt().shr(split[1].trim().toInt()).toString()
                    tryToFillAsMuchAsPossible()
                }
            }

            if (it.value.contains("LSHIFT")) {
                val split = it.value.trim().split("LSHIFT")
                if (split[0].isDigit() && split[1].isDigit()) {
                    mapResults[it.key] = split[0].trim().toInt().shl(split[1].trim().toInt()).toString()
                    tryToFillAsMuchAsPossible()
                }
            }

            if (it.value.contains(" OR ")) {
                val split = it.value.trim().split(" OR ")
                if (split[0].isDigit() && split[1].isDigit()) {
                    mapResults[it.key] = split[0].trim().toInt().or(split[1].trim().toInt()).toString()
                    tryToFillAsMuchAsPossible()
                }
            }

            if (it.value.contains(" AND ")) {
                val split = it.value.trim().split(" AND ")
                if (split[0].isDigit() && split[1].isDigit()) {
                    mapResults[it.key] = split[0].trim().toInt().and(split[1].trim().toInt()).toString()
                    tryToFillAsMuchAsPossible()
                }
            }
        }

        solveNOT()
    }

    private fun solveNOT(){
        val regex = "(NOT.+)\\d{1,10}".toRegex()
        mapResults.forEach { outer ->

            val find = regex.find(mapResults[outer.key.trim()]!!)

            find?.let {
                if (outer.value.contains(" NOT ")) {

                    val split = outer.value.split(" NOT ")
                    if (split.size == 2 && split[1].isDigit()) {
                        mapResults[outer.key] = (-split[1].trim().toInt() - 1).toString()
                        tryToFillAsMuchAsPossible()
                    }
                }
            }

        }

    }

    private fun tryToFillAsMuchAsPossible() {
        mapResults.forEach { outer ->
            if (mapResults[outer.key.trim()]!!.isDigit()) {
                mapResults.forEach { inner ->
                    val value = mapResults[inner.key]!!
                    if (value.contains(" ${outer.key} ")) {
                        mapResults[inner.key] = mapResults[inner.key]!!.replace(outer.key, outer.value)
                        tryEquations()
                    }
                }
            }
        }
    }


}

private fun String.isDigit(): Boolean {
    try {
        Integer.parseInt(this.trim())
    } catch (e: NumberFormatException) {
        return false
    }

    return true
}


fun main(args: Array<String>) {

    var readAllLines = MarcoUtil.readInput(7, false) as MutableList<String>

    val part1: () -> Unit = {
        val day = Day7(readAllLines)
        println("part1 = ${day.part1()}")
    }
    val part2: () -> Unit = {
        val day = Day7(readAllLines)
        println("part2 = ${day.part2()}")
    }
    MarcoUtil.time("part1", part1)
    MarcoUtil.time("part2", part2)
}
