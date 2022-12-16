package nel.marco


class Day9(var readInput: List<String>, var mapsize: Int = 1000) {

    var map = Array(mapsize) { Array(mapsize) { "." } }
    val tailSpots = mutableSetOf<Point>()

    companion object {
        val day: Int = 9
    }

    var x = 100
    var y = 100

    var knots = hashMapOf<Int, Point>()
    var HEAD = Point(x, y)
    var TAIL = Point(x, y)


    fun draw() {
        map[TAIL.x][TAIL.y] = "T"
        map[HEAD.x][HEAD.y] = "H"
    }

    private fun clearMap() {
        for (a in 0 until mapsize) {
            for (b in 0 until mapsize) {
                map[a][b] = "."
            }
        }
    }

    fun answerOne(): Int {
        knots[0] = HEAD
        knots[1] = TAIL
        tailSpots.add(knots[1]!!)
        readInput.forEach {
            executeStep(it.direction(), it.moveAmount())
        }

        return tailSpots.size
    }

    fun answerTwo(): Int {
        tailSpots.add(Point(TAIL))
        readInput.forEach {
            executeStep(it.direction(), it.moveAmount())
        }

        return tailSpots.size
    }


    fun executeStep(direction: String, moveAmount: Int = 1): String {
        for (x in 0 until moveAmount) {
            HEAD.direction(direction)
            knots[0]!!.direction(direction)
            if (!HEAD.isOneSpotAway(TAIL)) {
                moveTCloser()
                tailSpots.add(Point(TAIL))
            }
        }
        return printMap()
    }

    private fun moveTCloser() {
        if (HEAD.x > TAIL.x) {
            TAIL = TAIL.right()
        } else if (HEAD.x < TAIL.x) {
            TAIL = TAIL.left()
        }
        //y
        if (HEAD.y > TAIL.y) {
            TAIL = TAIL.up()
        } else if (HEAD.y < TAIL.y) {
            TAIL = TAIL.down()
        }
    }


    private fun String.direction() = this.split(" ")[0]
    private fun String.moveAmount() = this.split(" ")[1].toInt()


    fun printMap(): String {
        draw()
        val sb = StringBuilder(100)
        for (x in 0 until map[0].size) {
            for (y in map.indices) {
                sb.append(map[x][y])
            }
            sb.append("\n")
        }
//        clearMap()
        return sb.substring(0, sb.length - 1).toString()
    }


}

fun main(args: Array<String>) {
//    var readAllLines = MarcoUtil.readInput(Day9.day, true) as MutableList<String>
    var readAllLines = MarcoUtil.readInput(Day9.day, false) as MutableList<String>

    val part1: () -> Unit = {
        val day = Day9(readAllLines)
        println("part1 = ${day.answerOne()}")
    }
    val part2: () -> Unit = {
        val day = Day9(readAllLines)
        println("part2 = ${day.answerTwo()}")
    }

    MarcoUtil.time("part1", part1)
    MarcoUtil.time("part2", part2)

}