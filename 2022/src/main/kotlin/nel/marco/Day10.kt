package nel.marco

import java.util.*


class Day10(var readInput: List<String>) {

    companion object {
        val day: Int = 10
    }

    var map = """
    ${".".repeat(40)}
    ${".".repeat(40)}
    ${".".repeat(40)}
    ${".".repeat(40)}
    ${".".repeat(40)}
    ${".".repeat(40)}
    """.trimIndent().split("\n").toMutableList()


    var cycle = LinkedList<Command>()
    var str = 1
    var currentCycle = 0

    private fun inCycleList(cycle: Int): Boolean {
        if (cycle > 50 && (cycle - 20) % 40 == 0) {
            return true
        }

        return cycle == 20
    }

    private fun inWhatCycleList(cycle: Int): Int {
        if ((1..40).contains(cycle)) {
            return 0
        } else if ((41..80).contains(cycle)) {
            return 1
        } else if ((81..120).contains(cycle)) {
            return 2
        } else if ((121..160).contains(cycle)) {
            return 3
        } else if ((161..200).contains(cycle)) {
            return 4
        } else if ((201..240).contains(cycle)) {
            return 5
        }

        return -1
    }


    fun answerOne(): Int {

        readInput.forEach {
            cycle.add(Command(it))
        }

        var total = 0;

        while (cycle.isNotEmpty()) {
            val command = cycle.get(0)
            if (command.isNoop()) {
                total = process(total)
                cycle.remove()
            } else if (command.isAdding()) {
                total = process(total)
                total = process(total)
                str += command.amount
                cycle.remove()
            }
        }

        return total
    }

    private fun process(total: Int): Int {
        var total1 = total
        currentCycle++
        if (inCycleList(currentCycle)) {
            total1 += str * currentCycle
        }
        return total1
    }

    fun answerTwo(): Int {
        readInput.forEach {
            cycle.add(Command(it))
        }

        while (cycle.isNotEmpty()) {
            val command = cycle.get(0)
            if (command.isNoop()) {
                processPart2()
                cycle.remove()
            } else if (command.isAdding()) {
                processPart2()
                processPart2()
                str += command.amount
                cycle.remove()
            }
        }

        map.forEach { println(it.substring(0,40)) }

        return -1
    }

    private fun processPart2() {
        currentCycle++
        var line = StringBuilder(map[inWhatCycleList(currentCycle)])
        var screenSelect = currentCycle - 1
        while (screenSelect > 39) {
            screenSelect -= 40
        }


        if ((str - 1..str + 1).contains(screenSelect)) {
            line.replace(screenSelect, screenSelect, "#")
            map.set(inWhatCycleList(currentCycle), line.toString())
        }
    }


    data class Command(var command: String, var amount: Int) {

        fun isAdding(): Boolean {
            return command == "addx"
        }

        fun isNoop(): Boolean {
            return command == "noop"
        }

        constructor(input: String) : this("", 0) {
            command = input.split(" ")[0]
            when (command) {
                "addx" -> {
                    amount = input.split(" ")[1].toInt()
                }

                "noop" -> {
                    amount = 0
                }
            }
        }

    }
}


fun main(args: Array<String>) {
//    var readAllLines = MarcoUtil.readInput(Day10.day, true) as MutableList<String>
    var readAllLines = MarcoUtil.readInput(Day9.day, false) as MutableList<String>

    val part1: () -> Unit = {
        val day = day10(readAllLines)
        println("part1 = ${day.answerOne()}")
    }
    val part2: () -> Unit = {
        val day = day10(readAllLines)
        println("part2 = ${day.answerTwo()}")
    }

    MarcoUtil.time("part1", part1)
    MarcoUtil.time("part2", part2)

}

private fun day10(readAllLines: MutableList<String>) = Day10(readAllLines)