package nel.marco


class Day12(useExample: Boolean = false, useMac: Boolean = false) : Day(12, useExample = useExample, macBook = useMac) {
    override fun answerOne(): String {
        var map = setupMap()
        var groups: MutableList<Pair<Point, List<Point>>> = mutableListOf()
        for (y in 0..readInput.size) {
            for (x in 0..readInput.size) {
                map[Point(x, y)]?.let {
                    if (it == ".") {
                        return@let
                    }

                    val findGroup = Point(x, y, it).findGroup(map)
                    groups.add(Point(x, y, it) to findGroup)
                }
            }
        }

        map = setupMap()

        var globalCounter = 0

        groups.forEach { (point, listOfValues) ->
            var counter = 0

            listOfValues.forEach { localPoint ->
                var test = localPoint.surroundingPoints(false)
                    .mapNotNull { map[it] }
                    .filter { it == point.value }

                when (test.size) {
                    1 -> counter += 3
                    2 -> counter += 2
                    3 -> counter += 1
                    4 -> counter += 0
                    else -> counter++
                }

            }
            if (listOfValues.size == 0) {
//                println("${point.value}: 1 * 4 = 4")
                globalCounter += 4
            } else {
//                println("${point.value}: ${listOfValues.size} * ${counter} = ${listOfValues.size * counter}")
                globalCounter += listOfValues.size * counter
            }
        }

        return globalCounter.toString()
    }

    override fun answerTwo(): String {
        var map = setupMap()
        var groups = mutableListOf<Pair<Square, List<Square>>>()
        for (y in 0..readInput.size) {
            for (x in 0..readInput.size) {
                map[Point(x, y)]?.let {
                    if (it == ".") {
                        return@let
                    }

                    val findGroup = Point(x, y, it).findGroup(map).map { it.toSquare() }
                    groups.add(Point(x, y, it).toSquare() to findGroup)
                }
            }
        }

        map = setupMap()

        var globalCounter = 0

        groups.forEach { (square, listOfValues) ->

            // 1. Mark all sides where you can go left,right,up,down
            markOnEachSquareSidesThatDontCount(listOfValues, map)

            // while all sides are not account for
            while (listOfValues.map { it.sidesDontCount.size <= 4 }.isNotEmpty()) {
                // 2. Find most complete squares?
                var firstSideMostCompleted = listOfValues.firstOrNull { it.sidesDontCount.size == 3 }
                    ?: listOfValues.firstOrNull { it.sidesDontCount.size == 2 }
                    ?: listOfValues.firstOrNull { it.sidesDontCount.size == 1 }
                    ?: listOfValues.firstOrNull { it.sidesDontCount.size == 0 }
                    ?: break

                cantGoUpLogic(firstSideMostCompleted, listOfValues)
                cantGoDownLogic(firstSideMostCompleted, listOfValues)
                cantGoLeftLogic(firstSideMostCompleted, listOfValues)
                cantGoRightLogic(firstSideMostCompleted, listOfValues)
            }


            var count = listOfValues.map { it.sidesCount }.sumOf { it.size }

            if(count == 0){
                count+=4

                globalCounter+=4
                println("${square.value}:  size ${1} * count $count | == " + count * 1)
            }else {

                globalCounter+=count * listOfValues.size
                println("${square.value}:  size ${listOfValues.size} * count $count == " + count * listOfValues.size)
            }


        }

        return globalCounter.toString()
    }

    fun cantGoRightLogic(firstSideMostCompleted: Square, listOfValues: List<Square>) {


        if (
            !firstSideMostCompleted.sidesDontCount.contains(Sides.RIGHT) &&
            !firstSideMostCompleted.sidesCount.contains(Sides.RIGHT)
        ) {
            // mark done
            firstSideMostCompleted.sidesCount.add(Sides.RIGHT)
            firstSideMostCompleted.sidesDontCount.add(Sides.RIGHT)

            var goingUp: Square? = firstSideMostCompleted.copy()
            while (goingUp != null) {
                val up = listOfValues.find { goingUp!!.copy().up().isSame(it) }
                val upRight = up?.let { listOfValues.find { goingUp!!.copy().up().right().isSame(it) } }

                if (upRight != null) {
                    goingUp = null
                    continue
                }

                up?.let {
                    up.sidesDontCount.add(Sides.RIGHT)
                    goingUp = up
                } ?: break // break out of going left loop
            }

            var goingDown: Square? = firstSideMostCompleted.copy()
            while (goingDown != null) {
                val down = listOfValues.find { goingDown!!.copy().down().isSame(it) }
                val downLeft = down?.let { listOfValues.find { goingDown!!.copy().down().right().isSame(it) } }

                if (downLeft != null) {
                    goingDown = null
                    continue
                }

                down?.let {
                    down.sidesDontCount.add(Sides.RIGHT)
                    goingDown = down
                } ?: break // break out of going left loop
            }
        }
    }

    fun cantGoLeftLogic(
        firstSideMostCompleted: Square,
        listOfValues: List<Square>
    ) {


        if (
            !firstSideMostCompleted.sidesDontCount.contains(Sides.LEFT) &&
            !firstSideMostCompleted.sidesCount.contains(Sides.LEFT)
        ) {
            // mark done
            firstSideMostCompleted.sidesCount.add(Sides.LEFT)
            firstSideMostCompleted.sidesDontCount.add(Sides.LEFT)

            // go up and if you can't go left mark as done
            var goingUp: Square? = firstSideMostCompleted.copy()
            while (goingUp != null) {
                val up = listOfValues.find { goingUp!!.copy().up().isSame(it) }
                val upLeft = up?.let { listOfValues.find { goingUp!!.copy().up().left().isSame(it) } }

                if (upLeft != null) {
                    goingUp = null
                    continue
                }

                up?.let {
                    up.sidesDontCount.add(Sides.LEFT)
                    goingUp = up
                } ?: break // break out of going left loop
            }

            // go down and if you can't go left mark as done
            var goingDown: Square? = firstSideMostCompleted.copy()
            while (goingDown != null) {
                val down = listOfValues.find { goingDown!!.copy().down().isSame(it) }
                val downLeft = down?.let { listOfValues.find { goingDown!!.copy().down().left().isSame(it) } }

                if (downLeft != null) {
                    goingDown = null
                    continue
                }

                down?.let {
                    down.sidesDontCount.add(Sides.LEFT)
                    goingDown = down
                } ?: break // break out of going left loop
            }
        }
    }

    //WORKING
    fun cantGoDownLogic(
        firstSideMostCompleted: Square,
        listOfValues: List<Square>
    ) {

        if (
            !firstSideMostCompleted.sidesDontCount.contains(Sides.BOTTOM) &&
            !firstSideMostCompleted.sidesCount.contains(Sides.BOTTOM)
        ) {
            // mark done
            firstSideMostCompleted.sidesCount.add(Sides.BOTTOM)
            firstSideMostCompleted.sidesDontCount.add(Sides.BOTTOM)

            var goingLeft: Square? = firstSideMostCompleted.copy()
            while (goingLeft != null) {
                val left = listOfValues.find { goingLeft!!.copy().left().isSame(it) }
                val leftDown = left?.let { listOfValues.find { left.copy().up().isSame(it) } }

                if (left == null) {
                    goingLeft = null
                    continue
                }

                left.sidesDontCount.add(Sides.BOTTOM)
                goingLeft = left
            }

            var goingRight: Square? = firstSideMostCompleted.copy()
            while (goingRight != null) {
                val right = listOfValues.find { goingRight!!.copy().right().isSame(it) }
                val valueBelowAndRight =
                    right?.let { listOfValues.find { right.copy().up().isSame(it) } }

                if (valueBelowAndRight != null) {
                    goingRight = null
                    continue
                }

                right?.sidesDontCount?.add(Sides.BOTTOM)
                goingRight = right
            }
        }
    }

    fun cantGoUpLogic(
        firstSideMostCompleted: Square,
        listOfValues: List<Square>
    ) {

        if (
            !firstSideMostCompleted.sidesDontCount.contains(Sides.TOP) &&
            !firstSideMostCompleted.sidesCount.contains(Sides.TOP)
        ) {
            // mark done
            firstSideMostCompleted.sidesCount.add(Sides.TOP)
            firstSideMostCompleted.sidesDontCount.add(Sides.TOP)


            // go left and if you can't go up mark as done
            var goingLeft: Square? = firstSideMostCompleted.copy()
            while (goingLeft != null) {
                val left = listOfValues.find { goingLeft!!.copy().left().isSame(it) }
                val leftUp = left?.let { listOfValues.find { goingLeft!!.copy().left().down().isSame(it) } }

                if (leftUp != null) {
                    goingLeft = null
                    continue
                }

                left?.let {
                    left.sidesDontCount.add(Sides.TOP)
                    goingLeft = left
                } ?: break // break out of going left loop
            }


            // go right and if you can't go up mark as done
            var goingRight: Square? = firstSideMostCompleted.copy()
            while (goingRight != null) {
                val right = listOfValues.find { goingRight!!.copy().right().isSame(it) }
                val rightUp =
                    right?.let { listOfValues.find { goingRight!!.copy().right().down().isSame(it) } }

                if (rightUp != null) {
                    goingRight = null
                    continue
                }

                right?.let {
                    right.sidesDontCount.add(Sides.TOP)
                    goingRight = right
                } ?: break // break out of going left loop
            }
        }
    }

    fun markOnEachSquareSidesThatDontCount(
        listOfValues: List<Square>,
        map: MutableMap<Point, String>
    ) {
        listOfValues.forEach { currentSquare ->
            val filterWithoutCurrentSquare = listOfValues.filter { !it.isSame(currentSquare) }

            filterWithoutCurrentSquare.find { currentSquare.isSame(it.copy().right()) }?.let {
                currentSquare.sidesDontCount.add(Sides.LEFT)
            }
            filterWithoutCurrentSquare.find { currentSquare.isSame(it.copy().left()) }?.let {
                currentSquare.sidesDontCount.add(Sides.RIGHT)
            }
            filterWithoutCurrentSquare.find { currentSquare.isSame(it.copy().up()) }?.let {
                currentSquare.sidesDontCount.add(Sides.TOP)
            }
            filterWithoutCurrentSquare.find { currentSquare.isSame(it.copy().down()) }?.let {
                currentSquare.sidesDontCount.add(Sides.BOTTOM)
            }
        }
    }


    private fun setupMap(): MutableMap<Point, String> {
        var map = mutableMapOf<Point, String>()
        readInput.mapIndexed { y, s ->
            s.mapIndexed { x, c ->
                map[Point(x, y)] = c.toString()
            }
        }
        return map
    }

}

fun Point.toSquare(): Square = Square(this.x, this.y, this.value)
fun Square.toPoint(): Point = Point(this.x, this.y, this.value)

data class Square(
    var x: Int = 0,
    var y: Int = 0,
    var value: String = "",
    var sidesCount: MutableSet<Sides> = mutableSetOf(),
    var sidesDontCount: MutableSet<Sides> = mutableSetOf(),
) {

    fun isSame(other: Square?): Boolean {
        other ?: return false


        return this.x == other.x && this.y == other.y
    }

    fun isDone(): Boolean = this.sidesDontCount.size == 4

    fun right(): Square {
        x++
        return this
    }

    fun left(): Square {
        x--
        return this
    }

    fun up(): Square {
        y++
        return this
    }

    fun down(): Square {
        y--
        return this
    }
}