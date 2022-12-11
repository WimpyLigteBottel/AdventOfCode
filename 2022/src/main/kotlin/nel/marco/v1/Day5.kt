package nel.marco.v1

import nel.marco.MarcoUtil
import java.util.*


class Day5(var readInput: List<String>) {


    val boxRows = mutableMapOf<Int, Stack<String>>()


    /*

    [D]
[N] [C]
[Z] [M] [P]
 1   2   3
     */

    fun answerOne(): String {
        val length = Integer.parseInt(readInput.find { it.startsWith(" 1   2   3 ") }!!.trim().last().toString())
        val boxes = createBoxMap(readInput, length)

        for (x in 0 until length) {
            boxRows.putIfAbsent(x + 1, Stack())
        }

        moveLettersToBoxes(boxes)
        executeMoves()

        return grabTopOfEachBox()
    }

    fun answerTwo(): String {
        val length = Integer.parseInt(readInput.find { it.startsWith(" 1   2   3 ") }!!.trim().last().toString())
        val boxes = createBoxMap(readInput, length)

        for (x in 0 until length) {
            boxRows.putIfAbsent(x + 1, Stack())
        }

        moveLettersToBoxes(boxes)
        executeMoves(true)

        return grabTopOfEachBox()
    }

    private fun grabTopOfEachBox(): String {
        var message = ""
        for (x in 1 until boxRows.size + 1) {

            message += boxRows.get(x)!!.pop()

        }

        return message
    }

    private fun executeMoves(moveInStacks: Boolean = false) {
        val regex = "move (\\d{1,2}) from (\\d{1,2}) to (\\d{1,2})".toRegex()

        readInput.map {
            if (it.contains("move"))
                it
            else
                ""
        }.filter {
            it != ""
        }.forEach {
            val destructured = regex.find(it)!!.destructured

            val amount = destructured.component1().toInt()
            val fromBox = destructured.component2().toInt()
            val toBox = destructured.component3().toInt()

            if (moveInStacks) {

                val temp = Stack<String>()
                for (x in 1..amount) {
                    val pop = boxRows.get(fromBox)!!.pop()
                    temp.push(pop)
                }
                while (temp.isNotEmpty())
                    boxRows.get(toBox)!!.push(temp.pop())

            } else {
                for (x in 1..amount) {
                    val pop = boxRows.get(fromBox)!!.pop()
                    boxRows.get(toBox)!!.push(pop)
                }
            }


        }
    }

    private fun moveLettersToBoxes(boxes: MutableList<String>) {
        for (y in 0..boxes.size + 2) {
            val box = boxRows.get(y + 1)
            for (x in boxes.size - 1 downTo 0) {
                if (boxes[x] == "")
                    break

                val substring = boxes[x].substring(0, 3).substring(1, 2)

                if (substring != "0")
                    box?.add(substring)

                boxes[x] = boxes[x].substring(3).trim()
            }
        }
    }


}

fun createBoxMap(readInput: List<String>, length: Int) = readInput
    .filter {
        it.contains("[") && it.contains("]")
    }
    .map {
        it.replace("   ", " [0] ")
    }
    .map {
        it.replace("  ", " ").trim().replace("  ", " ")
    }
    .map { it ->
        var temp = it
        val amountOfBox = it.split("").filter { s -> s == "[" }.size + 1

        for (x in 0..(length - amountOfBox)) {
            temp += " [0]"
        }
        temp.trim()
    }
    .toMutableList()

fun main(args: Array<String>) {
    var readAllLines = MarcoUtil.readInput(5, false) as MutableList<String>

    val part1: () -> Unit = {
        val day = Day5(readAllLines)
        println("part1 = ${day.answerOne()}")
    }
    val part2: () -> Unit = {
        val day = Day5(readAllLines)
        println("part2 = ${day.answerTwo()}")
    }
    MarcoUtil.time("part1", part1)
    MarcoUtil.time("part2", part2)
}


