package nel.marco


class Day9(var readInput: List<String>, var mapsize: Int = 10) {

    var map = Array(mapsize) { Array(mapsize) { "." } }
    val tailSpots = mutableSetOf<Point>()

    companion object {
        val day: Int = 9
    }

    var knots = hashMapOf<Int, Point>()


    fun answerOne(): Int {
        for (x in 0 until 2)
            knots[x] = Point(0, 0)
        tailSpots.add(Point(knots[1]!!))
        readInput.forEach {
            executeStep(it.direction(), it.moveAmount())
        }

        return tailSpots.size
    }

    fun answerTwo(): Int {
        for (x in 0 until 10)
            knots[x] = Point(0, 0)
        tailSpots.add(lastPoint())
        readInput.forEach {
            executeStep(it.direction(), it.moveAmount())
        }

        return tailSpots.size
    }


    fun executeStep(direction: String, moveAmount: Int = 1) {
        for (x in 0 until moveAmount) {
            knots[0]!!.direction(direction)
            knots.keys.forEach {
                if (it != 0) {
                    if (!knots[it - 1]!!.isOneSpotAway(knots[it]!!)) {
                        moveTCloser(it - 1, it)
                        tailSpots.add( lastPoint())
                    }
                }
            }
        }

    }

    private fun lastPoint() = Point(knots[knots.keys.max()]!!)

    private fun moveTCloser(headKey: Int = 0, tailKey: Int = 0) {
        if (knots[headKey]!!.x > knots[tailKey]!!.x) {
            knots[tailKey]!!.right()
        } else if (knots[headKey]!!.x < knots[tailKey]!!.x) {
            knots[tailKey]!!.left()
        }
        if (knots[headKey]!!.y > knots[tailKey]!!.y) {
            knots[tailKey]!!.up()
        } else if (knots[headKey]!!.y < knots[tailKey]!!.y) {
            knots[tailKey]!!.down()
        }
    }


    private fun String.direction() = this.split(" ")[0]
    private fun String.moveAmount() = this.split(" ")[1].toInt()

}

fun main(args: Array<String>) {
    var readAllLines = MarcoUtil.readInput(Day9.day, true) as MutableList<String>
//    var readAllLines = MarcoUtil.readInput(Day9.day, false) as MutableList<String>

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