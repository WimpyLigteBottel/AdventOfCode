/*
Because your neighbors keep defeating you in the holiday house decorating contest year after year,
you've decided to deploy one million lights in a 1000x1000 grid.

Furthermore, because you've been especially nice this year, Santa has mailed you instructions on how to display the
ideal lighting configuration.

Lights in your grid are numbered from 0 to 999 in each direction; the lights at each corner are at 0,0, 0,999, 999,999,
and 999,0. The instructions include whether to turn on, turn off, or toggle various inclusive ranges given as coordinate pairs.
Each coordinate pair represents opposite corners of a rectangle, inclusive; a coordinate pair like 0,0 through 2,2 therefore
refers to 9 lights in a 3x3 square. The lights all start turned off.

To defeat your neighbors this year, all you have to do is set up your lights by doing the instructions Santa sent you in order.

For example:

turn on 0,0 through 999,999 would turn on (or leave on) every light.
toggle 0,0 through 999,0 would toggle the first line of 1000 lights, turning off the ones that were on, and turning on the ones that were off.
turn off 499,499 through 500,500 would turn off (or leave off) the middle four lights.
After following the instructions, how many lights are lit?





--- Part Two ---
You just finish implementing your winning light pattern when you realize you mistranslated Santa's message from Ancient Nordic Elvish.

The light grid you bought actually has individual brightness controls; each light can have a brightness of zero or more. The lights all start at zero.

The phrase turn on actually means that you should increase the brightness of those lights by 1.

The phrase turn off actually means that you should decrease the brightness of those lights by 1, to a minimum of zero.

The phrase toggle actually means that you should increase the brightness of those lights by 2.

What is the total brightness of all lights combined after following Santa's instructions?

For example:

turn on 0,0 through 0,0 would increase the total brightness by 1.
toggle 0,0 through 999,999 would increase the total brightness by 2000000.
 */

class Day6(private var input: List<String>) {

    var size = 1000
    var map = Array(size) { Array(size) { 0 } }
    fun switch(lightTask: LightTask, turnTo: Int = 1) {

        for (x in 0 until size) {
            for (y in 0 until size) {
                if (lightTask.between(x, y)) {
                    map[y][x] = turnTo
                }
            }
        }
    }

    fun switchpart2(lightTask: LightTask, turnTo: Int = 1) {
        for (x in 0 until size) {
            for (y in 0 until size) {
                if (lightTask.between(x, y)) {
                    map[y][x] += turnTo

                    if (map[y][x] <= 0)
                        map[y][x] = 0
                }
            }
        }
    }

    fun toggle(lightTask: LightTask) {
        for (x in 0 until size) {
            for (y in 0 until size) {
                if (lightTask.between(x, y)) {
                    if (map[y][x] == 0)
                        map[y][x] = 1
                    else
                        map[y][x] = 0
                }
            }
        }
    }

    fun String.pair(): Pair<Int, Int> {
        val results = this.split(",")

        return Pair(results[0].toInt(), results[1].toInt())
    }

    fun part1(): Int {

        input.map {
            getAction(it)
        }.forEach {
            when (it.action) {
                "turn on" -> {
                    switch(it, 1);
                }

                "turn off" -> {
                    switch(it, 0);
                }

                "toggle" -> {
                    toggle(it)
                }
            }
        }

        return map.sumOf { it.sum() }
    }


    private fun getAction(line: String): LightTask {
        val digitRegex = "(\\d{1,3},\\d{1,3}).+ (\\d{1,3},\\d{1,3})".toRegex()
        val actionRegex = "(turn on|turn off|toggle)".toRegex()

        val action = actionRegex.find(line)!!.groupValues[0]
        val digits1 = digitRegex.find(line)!!.destructured.component1().pair()
        val digits2 = digitRegex.find(line)!!.destructured.component2().pair()

        return LightTask(
            action = action,
            fromPair = digits1,
            toPair = digits2
        )

    }

    fun part2(): Int {
        input.map {
            getAction(it)
        }.forEach {
            when (it.action) {
                "turn on" -> {
                    switchpart2(it, 1);
                }

                "turn off" -> {
                    switchpart2(it, -1);
                }

                "toggle" -> {
                    switchpart2(it, 2);
                }
            }
        }

        return map.sumOf { it.sum() }
    }
}


fun main(args: Array<String>) {

    var readAllLines = MarcoUtil.readInput(6, false)

    val part1: () -> Unit = {
        val day = Day6(readAllLines)
        println("part1 = ${day.part1()}")
    }
    val part2: () -> Unit = {
        val day = Day6(readAllLines)
        println("part2 = ${day.part2()}")
    }
    MarcoUtil.time("part1", part1);
    MarcoUtil.time("part2", part2);
}


data class LightTask(val action: String, val fromPair: Pair<Int, Int>, val toPair: Pair<Int, Int>) {

    fun between(x: Int, y: Int): Boolean {
        return betweenX(x) && betweenY(y)
    }

    private fun betweenX(value: Int): Boolean {
        return value in fromPair.first..toPair.first
    }

    private fun betweenY(value: Int): Boolean {
        return value in fromPair.second..toPair.second
    }
}
