package nel.marco

import kotlin.math.abs


class Day8(readInput: List<String>) : Day(readInput) {

    override fun answerOne(): String {

        var newMap = mutableMapOf<Point, String>()
        for (x in readInput.indices) {
            for (y in readInput[x].indices) {
                val key = Point(y, x) // flipped only for this once
                newMap[key] = readInput[x][y].toString()
            }
        }

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


        var counter = 0
        println("XXXXXXX")


        toBeChecked.forEach {

            if (it.first.x <= it.second.x) {
                counter += anodeFitOnMap(it, newMap)
            } else {
                counter += anodeFitOnMapReverse(it, newMap)
            }

        }

        printMap(newMap, readInput.size)



        return "${newMap.filter { it.value == "#" }.count()}"
    }

    private fun anodeFitOnMap(
        it: Pair<Point, Point>,
        newMap: MutableMap<Point, String>,
    ): Int {

        val distanceX = abs(it.second.x - it.first.x)
        val distanceY = abs(it.second.y - it.first.y)

        // top
        var counter1 = 0
        kotlin.runCatching {
            var aX = it.first.x - distanceX
            var ay = it.first.y - distanceY

            newMap[Point(aX, ay)] = "#"
        }.onSuccess {
            counter1++
        }

        // bottom
        kotlin.runCatching {
            var aX = it.second.x + distanceX
            var ay = it.second.y + distanceY
            newMap[Point(aX, ay)] = "#"
        }.onSuccess {
            counter1++
        }

        return counter1
    }

    private fun anodeFitOnMapReverse(
        it: Pair<Point, Point>,
        newMap: MutableMap<Point, String>
    ): Int {

        val distanceX = abs(it.second.x - it.first.x)
        val distanceY = abs(it.second.y - it.first.y)

        // top
        var counter1 = 0
        kotlin.runCatching {
            var aX = it.second.x - distanceX
            var ay = it.second.y + distanceY

            newMap[Point(aX, ay)] = "#"
        }.onSuccess {
            counter1++
        }

        // bottom
        kotlin.runCatching {
            var aX = it.first.x + distanceX
            var ay = it.first.y - distanceY
            newMap[Point(aX, ay)] = "#"
        }.onSuccess {
            counter1++
        }

        return counter1
    }


    override fun answerTwo(): String {
        return ""
    }

    fun printMap(newMap: MutableMap<Point, String>, size: Int) {

        for (x in 0..<size) {
            for (y in 0..<size) {

                print(newMap[Point(y, x)])
            }
            println()
        }
    }


    private fun Pair<Point, Point>.distanceBetween() = manhattanDistance(this.first, this.second)

    private fun manhattanDistance(pointA: Point, pointB: Point) =
        abs(pointB.x - pointA.x) + abs(pointB.y - pointA.y);

}
