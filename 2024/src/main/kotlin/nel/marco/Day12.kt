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
                var firstSideMostCompleted = listOfValues.first { it.sidesDontCount.size == 3 }
                    ?: listOfValues.first { it.sidesDontCount.size == 2 }
                    ?: listOfValues.first { it.sidesDontCount.size == 1 }
                    ?: listOfValues.first { it.sidesDontCount.size == 0 }
                    ?: break



                // if you cant go up
                if (
                    !firstSideMostCompleted.sidesDontCount.contains(Sides.TOP) &&
                    !firstSideMostCompleted.sidesCount.contains(Sides.TOP)
                ) {
                    // mark done
                    firstSideMostCompleted.sidesCount.add(Sides.TOP)
                    firstSideMostCompleted.sidesDontCount.add(Sides.TOP)


                    // go left and if you can't go up mark as done
                    var markerForLeft: Square? = firstSideMostCompleted.copy()
                    while (markerForLeft != null) {
                        val valueOnLeft = listOfValues.find { markerForLeft?.left() == it }
                        val valueAbove = listOfValues.find { it.up() == valueOnLeft }

                        valueOnLeft?.let {
                            if (valueAbove != null) {
                                valueOnLeft.sidesDontCount.add(Sides.TOP)
                                markerForLeft = null
                            }

                            markerForLeft = markerForLeft?.left()
                        } ?: break // break out of going left loop
                    }

                    // go right and if you can't go up mark as done
                    var markerForRight: Square? = firstSideMostCompleted.copy()
                    while (markerForRight != null) {
                        val valueOnRight = listOfValues.find { markerForLeft?.right() == it }
                        val valueAbove = listOfValues.find { it.up() == valueOnRight }

                        valueOnRight?.let {
                            if (valueAbove != null) {
                                valueOnRight.sidesDontCount.add(Sides.TOP)
                                markerForLeft = null
                            }
                            markerForRight = markerForRight?.left()
                        } ?: break // break out of going left loop
                    }
                }

                // if you cant go down
                if (
                    !firstSideMostCompleted.sidesDontCount.contains(Sides.BOTTOM) &&
                    !firstSideMostCompleted.sidesCount.contains(Sides.BOTTOM)
                ) {
                    // mark done
                    firstSideMostCompleted.sidesCount.add(Sides.BOTTOM)
                    firstSideMostCompleted.sidesDontCount.add(Sides.BOTTOM)


                    // go left and if you can't go up mark as done
                    var markerForLeft: Square? = firstSideMostCompleted.copy()
                    while (markerForLeft != null) {
                        val valueOnLeft = listOfValues.find { markerForLeft?.left() == it } // remember
                        val valueAbove = listOfValues.find { it.down() == valueOnLeft } // remember

                        valueOnLeft?.let {
                            if (valueAbove != null) {
                                valueOnLeft.sidesDontCount.add(Sides.BOTTOM)
                                markerForLeft = null
                            }

                            markerForLeft = markerForLeft?.left()
                        } ?: break // break out of going left loop
                    }

                    // go right and if you can't go up mark as done
                    var markerForRight: Square? = firstSideMostCompleted.copy()
                    while (markerForRight != null) {
                        val valueOnRight = listOfValues.find { markerForLeft?.right() == it } // remember
                        val valueBelow = listOfValues.find { it.down() == valueOnRight }   // remember

                        valueOnRight?.let {
                            if (valueBelow != null) {
                                valueOnRight.sidesDontCount.add(Sides.BOTTOM)
                                markerForLeft = null
                            }
                            markerForRight = markerForRight?.left()
                        } ?: break // break out of going left loop
                    }
                }




                // if you cant go left
                if (
                    !firstSideMostCompleted.sidesDontCount.contains(Sides.LEFT) &&
                    !firstSideMostCompleted.sidesCount.contains(Sides.LEFT)
                ) {
                    // mark done
                    firstSideMostCompleted.sidesCount.add(Sides.LEFT)
                    firstSideMostCompleted.sidesDontCount.add(Sides.LEFT)


                    // go up and if you can't go left mark as done
                    var markerForLeft: Square? = firstSideMostCompleted.copy()
                    while (markerForLeft != null) {
                        val valueOnLeft = listOfValues.find { markerForLeft?.left() == it } // remember
                        val valueAbove = listOfValues.find { it.down() == valueOnLeft } // remember

                        valueOnLeft?.let {
                            if (valueAbove != null) {
                                valueOnLeft.sidesDontCount.add(Sides.BOTTOM)
                                markerForLeft = null
                            }

                            markerForLeft = markerForLeft?.left()
                        } ?: break // break out of going left loop
                    }

                    // go down and if you can't go down mark as done
                    var markerForRight: Square? = firstSideMostCompleted.copy()
                    while (markerForRight != null) {
                        val valueOnRight = listOfValues.find { markerForLeft?.right() == it } // remember
                        val valueBelow = listOfValues.find { it.down() == valueOnRight }   // remember

                        valueOnRight?.let {
                            if (valueBelow != null) {
                                valueOnRight.sidesDontCount.add(Sides.BOTTOM)
                                markerForLeft = null
                            }
                            markerForRight = markerForRight?.left()
                        } ?: break // break out of going left loop
                    }
                }
                // if you cant go right
                // mark as done
                // go up and if you can't go left mark as done
                // go down and if you can't go down mark as done
            }


        }

        return globalCounter.toString()
    }

    private fun markOnEachSquareSidesThatDontCount(
        listOfValues: List<Square>,
        map: MutableMap<Point, String>
    ) {
        listOfValues.forEach { currentSquare ->
            val currentPoint = currentSquare.toPoint()
            map[currentPoint.right()]?.let {
                currentSquare.sidesDontCount.add(Sides.RIGHT)
            }
            map[currentPoint.left()]?.let {
                currentSquare.sidesDontCount.add(Sides.LEFT)
            }
            map[currentPoint.up()]?.let {
                currentSquare.sidesDontCount.add(Sides.TOP)
            }
            map[currentPoint.down()]?.let {
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