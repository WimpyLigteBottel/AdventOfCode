package nel.marco

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import kotlin.math.absoluteValue

class Day3(
    val fileName: String = "example"
) {

    var cacheMap = mutableMapOf<Point, Pair<String, String>>()

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

    fun part1(input: List<String>): String {
        val map = mutableMapOf<Point, Pair<String, String>>()
        val wireA = input[0].split(",")
        val wireB = input[1].split(",")


        var lastLocation = Point(0, 0)
        map[lastLocation] = "A" to "o"
        wireA.forEach { input ->

            val direction = input.substring(0, 1) //D, U , R , L
            val amount = input.substring(1) // D[xxx]

            lastLocation = process(direction, amount, lastLocation, map)
        }

        lastLocation = Point(0, 0)
        map[lastLocation] = "B" to "o"
        wireB.forEach { input ->
            val direction = input.substring(0, 1) //D, U , R , L
            val amount = input.substring(1) // D[xxx]]

            lastLocation = process(direction, amount, lastLocation, map)
        }

        cacheMap = map

        val crossingPoints = map
            .filter { it.value.second == "X" } //Points that crossed
            .map { manhattanDistance(Point(0, 0), it.key) } // Calculate the distance
            .sorted() // sort ascending

        printMap(map)

        return crossingPoints.first().toString()
    }

    private fun manhattanDistance(a: Point, b: Point) =
        ((a.x.absoluteValue - b.x.absoluteValue) + (a.y.absoluteValue - b.y.absoluteValue))
            .let {
                Math.abs(it)
            }

    private fun process(
        direction: String,
        amount: String,
        lastLocation2: Point,
        map: MutableMap<Point, Pair<String, String>>,
        sayIfPointIsReached: Point? = null
    ): Point {
        var lastLocation = lastLocation2
        val wireType = map[Point(0, 0)]!!.first

        var count = 0

        when (Direction.valueOf(direction)) {
            Direction.L -> {
                repeat(amount.toInt()) {
                    count++
                    lastLocation = lastLocation.left()

                    if (sayIfPointIsReached == lastLocation) {
                        throw CountException(count)
                    }

                    if (map[lastLocation] == null) {
                        map[lastLocation] = wireType to "|"
                    } else {
                        crossingPoint(map[lastLocation]!!, lastLocation, wireType, map)
                    }
                }
            }

            Direction.R -> {
                repeat(amount.toInt()) {
                    count++

                    lastLocation = lastLocation.right()
                    if (sayIfPointIsReached == lastLocation) {
                        throw CountException(count)
                    }
                    if (map[lastLocation] == null) {
                        map[lastLocation] = wireType to "|"
                    } else {
                        crossingPoint(map[lastLocation]!!, lastLocation, wireType, map)
                    }
                }
            }

            Direction.U -> {
                repeat(amount.toInt()) {
                    count++

                    lastLocation = lastLocation.up()
                    if (sayIfPointIsReached == lastLocation) {
                        throw CountException(count)
                    }

                    if (map[lastLocation] == null) {
                        map[lastLocation] = wireType to "-"
                    } else {
                        crossingPoint(map[lastLocation]!!, lastLocation, wireType, map)
                    }
                }
            }

            Direction.D -> {
                repeat(amount.toInt()) {
                    count++
                    lastLocation = lastLocation.down()
                    if (sayIfPointIsReached == lastLocation) {
                        throw CountException(count)
                    }
                    if (map[lastLocation] == null) {
                        map[lastLocation] = wireType to "-"
                    } else {
                        crossingPoint(map[lastLocation]!!, lastLocation, wireType, map)
                    }
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
        part1(input) // create the map

        val crossingPoints = cacheMap
            .filter { it.value.second == "X" } //Points that crossed
            .map { it.key }
            .map { search ->
                val wireA = input[0].split(",")
                val wireB = input[1].split(",")

                val aCount = countWire(mutableMapOf(), wireA, search)
                val bCount = countWire(mutableMapOf(), wireB, search)

                aCount + bCount
            }.min()
        return "$crossingPoints"
    }

    private fun countWire(
        map: MutableMap<Point, Pair<String, String>> = mutableMapOf(),
        wireA: List<String>,
        search: Point
    ): Int {
        var ACount = 0
        var lastLocation = Point(0, 0)
        map[lastLocation] = "A" to "o"
        wireA.forEachIndexed { index, input ->
            val direction = input.substring(0, 1) //D, U , R , L
            val amount = input.substring(1) // D[xxx]

            var exit = false
            runCatching {
                lastLocation = process(direction, amount, lastLocation, map, search)
            }.onSuccess {
                ACount = ACount + amount.toInt()
            }.onFailure {
                exit = true
                val exception = it as CountException
                ACount += exception.count
                return ACount
            }
        }
        return ACount
    }


    enum class Direction {
        L,
        R,
        U,
        D
    }

    data class Point(val x: Int, val y: Int) {

        fun up() = Point(x, y + 1)

        fun down() = Point(x, y - 1)

        fun left() = Point(x - 1, y)

        fun right() = Point(x + 1, y)
    }
}
