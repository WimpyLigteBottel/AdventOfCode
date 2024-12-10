package nel.marco


class Day10(useExample: Boolean = false, useMac: Boolean = false) : Day(10, useExample = useExample, macBook = useMac) {

    var map: MutableMap<Point, Int> = mutableMapOf()

    override fun answerOne(): String {
        map = mutableMapOf()
        readInput.mapIndexed { y, s ->
            s.mapIndexed { x, c ->
                if (c != '.') {
                    map[Point(x, y, c.toString())] = c.digitToInt()
                } else {
                    map[Point(x, y, c.toString())] = -1

                }
            }
        }

        var toBeProcessed = mutableListOf<Point>()

        // get all the 0's
        map.filter { it.value == 0 }.forEach {
            toBeProcessed.add(it.key)
        }

        var totaal = 0

        while (toBeProcessed.isNotEmpty()) {

            var first = toBeProcessed.removeFirst()

            totaal += findNext(first, 1, mutableListOf()).toSet().size
        }

        return totaal.toString()
    }

    fun howManyPathOptions(
        current: Point,
        up: Point,
        down: Point,
        left: Point,
        right: Point
    ): Int {
        val mapCurrentValue = map[current] ?: -1

        var count = 0

        if (map[up] == mapCurrentValue + 1) {
            count++
        }
        if (map[down] == mapCurrentValue + 1) {
            count++
        }
        if (map[left] == mapCurrentValue + 1) {
            count++
        }
        if (map[right] == mapCurrentValue + 1) {
            count++
        }

        return count
    }

    fun findNext(current: Point, score: Int, completed: MutableList<Point>): MutableList<Point> {
        val mapCurrentValue = map[current] ?: -1
        if (mapCurrentValue == 9) {
            completed.add(current)
            return completed
        }

        var up = current.copy().up()
        var down = current.copy().down()
        var left = current.copy().left()
        var right = current.copy().right()

        var pathOptions = howManyPathOptions(current, up, down, left, right)

        if (map[up] == mapCurrentValue + 1) {
            findNext(up, score, completed)
        }
        if (map[down] == mapCurrentValue + 1) {
            findNext(down, score, completed)
        }
        if (map[left] == mapCurrentValue + 1) {
            findNext(left, score, completed)
        }
        if (map[right] == mapCurrentValue + 1) {
            findNext(right, score, completed)
        }

        return completed
    }


    override fun answerTwo(): String {
        map = mutableMapOf()
        readInput.mapIndexed { y, s ->
            s.mapIndexed { x, c ->
                if (c != '.') {
                    map[Point(x, y, c.toString())] = c.digitToInt()
                } else {
                    map[Point(x, y, c.toString())] = -1

                }
            }
        }

        var toBeProcessed = mutableListOf<Point>()

        // get all the 0's
        map.filter { it.value == 0 }.forEach {
            toBeProcessed.add(it.key)
        }

        var totaal = 0

        while (toBeProcessed.isNotEmpty()) {

            var first = toBeProcessed.removeFirst()

            totaal += findNext(first, 1, mutableListOf()).size
        }

        return totaal.toString()
    }

}
