package nel.marco

import nel.marco.Day3.Point
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import kotlin.math.absoluteValue

class Day3(
    val fileName: String = "example"
) {

    val day3Helper = Day3Helper(fileName)

    var cacheMap = mutableMapOf<Point, Pair<String, String>>()

    fun part1(input: List<String>): String {
        val map = populateMap(input)

        cacheMap = map

        val crossingPoints = map
            .filter { it.value.second == "X" } //Points that crossed
            .map { manhattanDistance(Point(0, 0), it.key) } // Calculate the distance
            .sorted() // sort ascending

        day3Helper.printMap(map)

        return crossingPoints.first().toString()
    }

    private fun populateMap(input: List<String>): MutableMap<Point, Pair<String, String>> {
        val map = mutableMapOf<Point, Pair<String, String>>()

        //Place wireA
        input[0].split(",").let {
            var lastLocation = Point(0, 0)
            map[lastLocation] = "A" to "o"
            it.forEach { input ->
                val direction = input.toDirection()
                val amount = input.toAmount()
                lastLocation = process(direction, amount, lastLocation, map)
            }
        }
        //Place wireB
        input[1].split(",").let {
            var lastLocation = Point(0, 0)
            map[lastLocation] = "B" to "o"
            it.forEach { input ->
                val direction = input.toDirection()
                val amount = input.toAmount()
                lastLocation = process(direction, amount, lastLocation, map)
            }
        }

        return map
    }

    private fun manhattanDistance(a: Point, b: Point) =
        ((a.x.absoluteValue - b.x.absoluteValue) + (a.y.absoluteValue - b.y.absoluteValue))
            .let {
                Math.abs(it)
            }

    private fun process(
        direction: Direction,
        amount: Int,
        lastLocation2: Point,
        map: MutableMap<Point, Pair<String, String>>,
        sayIfPointIsReached: Point? = null
    ): Point {
        var lastLocation = lastLocation2
        val wireType = map[Point(0, 0)]!!.first

        var count = 0 // part 2

        when (direction) {
            Direction.L -> {
                repeat(amount) {
                    count++
                    lastLocation = lastLocation.left()

                    //part 2
                    if (sayIfPointIsReached == lastLocation) {
                        throw CountException(count)
                    }

                    map[lastLocation]?.let {
                        crossingPoint(map[lastLocation]!!, lastLocation, wireType, map)
                    }
                    map.putIfAbsent(lastLocation, wireType to "o")
                }
            }

            Direction.R -> {
                repeat(amount) {
                    count++
                    lastLocation = lastLocation.right()
                    if (sayIfPointIsReached == lastLocation) {
                        throw CountException(count)
                    }
                    map[lastLocation]?.let {
                        crossingPoint(map[lastLocation]!!, lastLocation, wireType, map)
                    }
                    map.putIfAbsent(lastLocation, wireType to "o")
                }
            }

            Direction.U -> {
                repeat(amount) {
                    count++

                    lastLocation = lastLocation.up()
                    if (sayIfPointIsReached == lastLocation) {
                        throw CountException(count)
                    }

                    map[lastLocation]?.let {
                        crossingPoint(map[lastLocation]!!, lastLocation, wireType, map)
                    }
                    map.putIfAbsent(lastLocation, wireType to "o")
                }
            }

            Direction.D -> {
                repeat(amount) {
                    count++
                    lastLocation = lastLocation.down()
                    if (sayIfPointIsReached == lastLocation) {
                        throw CountException(count)
                    }
                    map[lastLocation]?.let {
                        crossingPoint(map[lastLocation]!!, lastLocation, wireType, map)
                    }
                    map.putIfAbsent(lastLocation, wireType to "o")
                }
            }
        }
        map[lastLocation] = wireType to "+"

        return lastLocation
    }

    class CountException(val count: Int) : RuntimeException() {
    }

    private fun crossingPoint(
        existingPoint: Pair<String, String>,
        currentLocation: Point,
        wireType: String,
        pointMap: MutableMap<Point, Pair<String, String>>
    ) {
        if (existingPoint.first != wireType) {
            pointMap[currentLocation] = wireType to "X"
        }
    }


    fun part2(input: List<String>): String {
        val minimalStepsToCrossWire = populateMap(input) // gets all the wires placed
            .filter { it.value.second == "X" } // Gets all the points that crossed
            .map { it.key } // Gets only the points
            .map { search ->
                val wireA = input[0].split(",")
                val wireB = input[1].split(",")

                val aCount = countWire(wireA = wireA, search = search)
                val bCount = countWire(wireA = wireB, search = search)

                aCount + bCount
            }.min()
        return "$minimalStepsToCrossWire"
    }

    private fun countWire(
        wireA: List<String>,
        search: Point
    ): Int {
        // setup
        var counter = 0
        val map = mutableMapOf<Point, Pair<String, String>>()
        var lastLocation = Point(0, 0)
        map[lastLocation] = "A" to "o"

        wireA.forEachIndexed { index, input ->
            val direction = input.toDirection()
            val amount = input.toAmount()

            runCatching {
                lastLocation = process(direction, amount, lastLocation, map, search)
            }.onSuccess {
                counter += amount
            }.onFailure {
                val exception = it as CountException
                counter += exception.count
                return counter
            }
        }

        //Ideally the wires did cross otherwise its the entire wire length
        return counter
    }

    private fun String.toDirection() = Direction.valueOf(this.substring(0, 1))
    private fun String.toAmount() = this.substring(1).toInt()

    enum class Direction {
        L,
        R,
        U,
        D;
    }

    data class Point(val x: Int, val y: Int) {

        fun up() = Point(x, y + 1)

        fun down() = Point(x, y - 1)

        fun left() = Point(x - 1, y)

        fun right() = Point(x + 1, y)
    }
}

class Day3Helper(val fileName: String) {

    fun printMap(map: MutableMap<Point, Pair<String, String>>) {
        var maxX = map.maxBy { it.key.x }.key.x
        var minX = map.minBy { it.key.x }.key.x
        var maxY = map.maxBy { it.key.y }.key.y
        var minY = map.minBy { it.key.y }.key.y

        var sb = StringBuilder(100000)
        val file = Path.of("C:\\Users\\eposm\\AppData\\Roaming\\JetBrains\\IdeaIC2024.2\\scratches\\$fileName")

        Files.deleteIfExists(file)


        for (x in minX..maxX + 1) {
            for (y in minY..maxY + 1) {
                val location = map[Point(x, y)]
                location?.let {
                    sb.append(it.second)
                } ?: sb.append(" ")
            }
            sb.append(System.lineSeparator())
        }

        Files.writeString(
            file,
            sb.toString(),
            StandardOpenOption.CREATE_NEW
        )
    }
}
