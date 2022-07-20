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


    fun part1(): Int {

        val mutableMapOf: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()

        input.forEach {
            var action = getAction(it);

            val substringAfter = it.after(action)

            var a1 = substringAfter.leftHalf().leftNumber()
            var a2 = substringAfter.leftHalf().rightNumber()

            var b1 = substringAfter.rightHalf().leftNumber()
            var b2 = substringAfter.rightHalf().rightNumber()


            when (action) {
                ACTION.TURN_ON -> {
                    turnOn(mutableMapOf, a1, a2, b1, b2)
                }
                ACTION.TURN_OFF -> {
                    turnOffPart1(mutableMapOf, a1, a2, b1, b2)
                }
                ACTION.TOGGLE -> {
                    toggle(mutableMapOf, a1, a2, b1, b2)
                }
            }
//            println("$action == ${mutableMapOf.size}")
        }

        val filter = mutableMapOf.filter { it.value > 0 }
        return filter.size

    }

    fun turnOn(
        map: MutableMap<Pair<Int, Int>, Int>,
        startingA1: Int,
        startingA2: Int,
        b1: Int,
        b2: Int,
        increaseAmount: Int = 1
    ) {

        var a1 = startingA1;
        var a2 = startingA2;

        while (a1 <= b1 ) {
            map[Pair(a1, a2)] = map.getOrDefault(Pair(a1, a2), 0) + increaseAmount
            while (a2++ < b2 ) {
                map[Pair(a1, a2)] = map.getOrDefault(Pair(a1, a2), 0) + increaseAmount
            }
            a2 = startingA2
            a1++

        }

    }

    fun turnOff(map: MutableMap<Pair<Int, Int>, Int>, startingA1: Int, startingA2: Int, b1: Int, b2: Int) {

        var a1 = startingA1;
        var a2 = startingA2;


        while (a1 <= b1) {
            var value = map.getOrDefault(Pair(a1, a2), 0) - 1
            value = value.minValue()
            map[Pair(a1, a2)] = value
            while (a2 <= b2) {
                value = map.getOrDefault(Pair(a1, a2), 0) - 1
                value = value.minValue()
                map[Pair(a1, a2)] = value
                a2++
            }
            a2 = startingA2
            a1++
        }

    }

    fun turnOffPart1(map: MutableMap<Pair<Int, Int>, Int>, startingA1: Int, startingA2: Int, b1: Int, b2: Int) {

        var a1 = startingA1;
        var a2 = startingA2;


        while (a1 <= b1) {
            map[Pair(a1, a2)] = 0
            while (a2 <= b2) {
                map[Pair(a1, a2)] = 0
                a2++
            }
            a2 = startingA2
            a1++
        }


    }


    fun toggle(map: MutableMap<Pair<Int, Int>, Int>, startingA1: Int, startingA2: Int, b1: Int, b2: Int) {

        var a1 = startingA1;
        var a2 = startingA2;

        var toUpdate = mutableSetOf<Pair<Int, Int>>()

        while (a1 <= b1) {
            toUpdate.add(Pair(a1, a2))
            while (a2 <= b2) {
                toUpdate.add(Pair(a1, a2++))
            }
            a2 = startingA2
            a1++
        }

        toUpdate.forEach {
            if (map.getOrDefault(it, 0) > 0)
                map[it] = 0
            else
                map[it] = 1
        }

    }

    fun togglePart2(map: MutableMap<Pair<Int, Int>, Int>, startingA1: Int, startingA2: Int, b1: Int, b2: Int) {
        var a1 = startingA1;
        var a2 = startingA2;

        var toUpdate = mutableSetOf<Pair<Int, Int>>()

        while (a1 <= b1) {
            toUpdate.add(Pair(a1, a2))
            while (a2 <= b2) {
                toUpdate.add(Pair(a1, a2++))
            }
            a2 = startingA2
            a1++
        }

        toUpdate.forEach {
            if (map.getOrDefault(it, 0) > 0)
                map[it] = 0
            else
                map[it] = 1
        }

    }


    fun Int.minValue(): Int {
        if (this < 0)
            return 0

        return this
    }


    fun String.after(action: ACTION): String {
        return this.substringAfter(action.toString())
    }

    fun String.leftHalf(): String {
        return this.split("through")[0];
    }

    fun String.rightHalf(): String {
        return this.split("through")[1];
    }

    fun String.leftNumber(): Int {
        return this.split(",")[0].trim().toBigInteger().toInt();
    }

    fun String.rightNumber(): Int {
        return this.split(",")[1].trim().toBigInteger().toInt();
    }

    private fun getAction(input: String): ACTION {
        if (input.startsWith("turn on")) {
            return ACTION.TURN_ON
        } else if (input.startsWith("turn off")) {
            return ACTION.TURN_OFF
        } else {
            return ACTION.TOGGLE
        }
    }

    fun part2(): Int {
        val mutableMapOf: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()

        input.forEach {

            val regex = Regex("""(turn on|turn off|toggle)\s+(\d+),(\d+)\s+through\s+(\d+),(\d+)""")
            var (action, a1, a2, b1, b2) = regex.find(it)!!.destructured

//            var action = getAction(it);
//            val substringAfter = it.after(action)
//
//            var a1 = substringAfter.leftHalf().leftNumber()
//            var a2 = substringAfter.leftHalf().rightNumber()
//
//            var b1 = substringAfter.rightHalf().leftNumber()
//            var b2 = substringAfter.rightHalf().rightNumber()

            when (action) {
                "turn on" -> {
                    turnOn(mutableMapOf, a1.toInt(), a2.toInt(), b1.toInt(), b2.toInt(), 1)
                }
                "turn off" -> {
                    turnOff(mutableMapOf, a1.toInt(), a2.toInt(), b1.toInt(), b2.toInt())
                }
                "toggle" -> {
                    turnOn(mutableMapOf, a1.toInt(), a2.toInt(), b1.toInt(), b2.toInt(), 2)
                }
            }
//            println("$action == ${mutableMapOf.size}")
        }

        return mutableMapOf.mapValues { it.value }.toList().sumOf { it.second }

    }


    enum class ACTION(private val s: String) {
        TURN_ON("turn on"),
        TURN_OFF("turn off"),
        TOGGLE("toggle");

        override fun toString(): String {
            return s
        }

    }
}


fun main(args: Array<String>) {

    var readAllLines = MarcoUtil.readInput(6)
    val day = Day6(readAllLines)

    val part1: () -> Unit = {
        println("part1 = ${day.part1()}")
    }
    val part2: () -> Unit = {
        println("part2 = ${day.part2()}")
    }
    MarcoUtil.time("part1", part1);
    MarcoUtil.time("part2", part2);
}

