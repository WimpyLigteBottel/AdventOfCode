import java.lang.NumberFormatException

class Day7(private var input: MutableList<String>) {

    val mapResults = mutableMapOf<String, String>()
    val wires = mutableSetOf<String>()


    fun String.isDigit(): Boolean {
        try {
            Integer.parseInt(this)
        } catch (e: NumberFormatException) {
            return false
        }
        return true

    }

    fun String.isNotDigit(): Boolean {
        return !this.isDigit()
    }

    fun part1(): Int {

        input = input.sorted() as MutableList<String>

        input.forEach {
            val pair = it.SPLIT()
            mapResults.put(pair.first, pair.second)
        }

        mapResults.entries.forEach {
            if (it.key.isDigit() && it.value.isNotDigit()) {

                //repalce all value with key
                mapResults.entries.forEach { next ->
                    if (next.key.contains(" ${it.value} ")) {
                        mapResults.remove(next.key)
                        mapResults.put(key = next.key.replace(" ${it.value} ", it.value), value = next.value)
                        println(next)
                    }
                }
            }
        }


//            val pair = s.SPLIT()
//            if (pair.first.isDigit() && pair.second.isNotDigit()) {
//                input.forEachIndexed { index, s ->
//                    input[index] = s.replace(pair.second, pair.first)
//                }
//            }


//ao OR an -> ap
        //SPLIT  ALL OR STATEMENTS
        input.forEach {
            if (it.contains(" OR ")) {
                val value1 = it.SPLIT().first.split(" OR ")[0]
                val value2 = it.SPLIT().first.split(" OR ")[1]
                wires.add(value1 + "-> ${it.SPLIT().second}")
                wires.add(value2 + "-> ${it.SPLIT().second}")
            }
        }

        println(wires)


        return -1
    }

    private fun findNext(text: String): String {
        return input.first {
            it.SPLIT().second == text
        }
    }

    private fun startingPoint(): String {
        return findNext("a")
    }


    fun String.SPLIT(): Pair<String, String> {
        val split = this.split("->")
        return Pair(split[0].trim(), split[1].trim())
    }


    fun part2(): Int {
        return -1
    }
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
    MarcoUtil.time("part1", part1);
    MarcoUtil.time("part2", part2);
}
