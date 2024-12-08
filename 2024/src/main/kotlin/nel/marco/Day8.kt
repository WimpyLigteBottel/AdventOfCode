package nel.marco

import kotlin.math.abs


class Day8(readInput: List<String>) : Day(readInput) {

    override fun answerOne(): String {

        var newMap = setupGridMap()
        var toBeChecked = setupAtennasToCheck(newMap)

        toBeChecked.forEach {
            if (it.first.x <= it.second.x) {
                anodeFitOnMap(it, newMap)
            } else {
                anodeFitOnMapReverse(it, newMap)
            }
        }

        return "${newMap.filter { it.value == "#" }.count()}"
    }

    private fun anodeFitOnMap(
        it: Pair<Point, Point>,
        newMap: MutableMap<Point, String>,
    ) {

        val distanceX = abs(it.second.x - it.first.x)
        val distanceY = abs(it.second.y - it.first.y)

        kotlin.runCatching {
            var aX = it.first.x - distanceX
            var ay = it.first.y - distanceY

            newMap[Point(aX, ay)]!! // fail if its off the map

            newMap[Point(aX, ay)] = "#"
        }

        // bottom
        kotlin.runCatching {
            var aX = it.second.x + distanceX
            var ay = it.second.y + distanceY
            newMap[Point(aX, ay)]!! // fail if its off the map

            newMap[Point(aX, ay)] = "#"
        }
    }

    private fun anodeFitOnMapReverse(
        it: Pair<Point, Point>,
        newMap: MutableMap<Point, String>
    ) {

        val distanceX = abs(it.second.x - it.first.x)
        val distanceY = abs(it.second.y - it.first.y)

        // top
        kotlin.runCatching {
            var aX = it.second.x - distanceX
            var ay = it.second.y + distanceY
            newMap[Point(aX, ay)]!!

            newMap[Point(aX, ay)] = "#"
        }
        // bottom
        kotlin.runCatching {
            var aX = it.first.x + distanceX
            var ay = it.first.y - distanceY
            newMap[Point(aX, ay)]!!

            newMap[Point(aX, ay)] = "#"
        }
    }


    override fun answerTwo(): String {

        var newMap = setupGridMap()

        var toBeChecked = setupAtennasToCheck(newMap)


        // if first and second
        // if first and third
        // if second and third

        toBeChecked.forEach {
            if (it.first.x <= it.second.x) {
                anodeFitOnMap(it, newMap)
            } else {
                anodeFitOnMapReverse(it, newMap)
            }

        }

        printMap(newMap, readInput.size)



        return "${newMap.filter { it.value == "#" }.count()}"
    }

    private fun setupAtennasToCheck(newMap: MutableMap<Point, String>): MutableList<Pair<Point, Point>> {
        var toBeChecked = mutableListOf<Pair<Point, Point>>()

        newMap
            .filter { it.value != "." } // dont want base map
            .map { it.value }.toSet() // all the unique letters
            .forEach { uniqueValue ->
                val listOfSameLetter = newMap.filter { it.value == uniqueValue }.map { it.key }
                for (a in listOfSameLetter.indices) {
                    val pointA = listOfSameLetter[a]
                    for (b in listOfSameLetter.indices) {
                        val pointB = listOfSameLetter[b]
                        val wayOne = pointA to pointB
                        val wayTwo = pointB to pointA
                        if (a != b) {
                            if (!toBeChecked.contains(wayOne) && !toBeChecked.contains(wayTwo))
                                toBeChecked.add(wayOne)
                        }
                    }
                }
            }
        return toBeChecked
    }

    private fun setupGridMap(): MutableMap<Point, String> {
        var newMap = mutableMapOf<Point, String>()
        for (x in readInput.indices) {
            for (y in readInput[x].indices) {
                val key = Point(y, x) // flipped only for this once
                newMap[key] = readInput[x][y].toString()
            }
        }
        return newMap
    }

    fun printMap(newMap: MutableMap<Point, String>, size: Int) {

        for (x in 0..<size) {
            for (y in 0..<size) {

                print(newMap[Point(y, x)])
            }
            println()
        }
    }


    private fun manhattanDistance(pointA: Point, pointB: Point) =
        abs(pointB.x - pointA.x) + abs(pointB.y - pointA.y);

}
