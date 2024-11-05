package nel.marco

class Day3 {

    fun part1(input: List<String>): String {
        val wireMap = mutableMapOf<Point, String>()
        val wireMapB = mutableMapOf<Point, String>()

        val wireA = input[0].split(",")
        val wireB = input[1].split(",")


        var lastLocation = Point(0, 0)
        wireMap[lastLocation] = "o"
        wireA.forEach {
            process(it, lastLocation, wireMap)
        }

        lastLocation = Point(0, 0)
        wireMapB[lastLocation] = "o"
        wireB.forEach {
            process(it, lastLocation, wireMapB)
        }


        wireMapB.remove(Point(0,0))
        wireMap.remove(Point(0,0))
        val count = wireMap
            .filter {
                val answer = wireMapB[it.key]?.let { true } ?: false
                answer
            }.count()

        println(count)

        return "X"
    }

    private fun process(
        it: String,
        lastLocation: Point,
        pointMap: MutableMap<Point, String>
    ) {
        var currentLocation = lastLocation
        val direction = it.substring(0, 1) //D, U , R , L
        val amount = it.substring(1) // D[xxx]
        when (Direction.valueOf(direction)) {
            Direction.L -> {
                repeat(amount.toInt()) {
                    currentLocation = currentLocation.left()
                    updateLocation(pointMap, currentLocation, "-")
                }
            }

            Direction.R -> {
                repeat(amount.toInt()) {
                    currentLocation = currentLocation.right()
                    updateLocation(pointMap, currentLocation, "-")
                }
            }

            Direction.U -> {
                repeat(amount.toInt()) {
                    currentLocation = currentLocation.up()
                    updateLocation(pointMap, currentLocation, "|")
                }
            }

            Direction.D -> {
                repeat(amount.toInt()) {
                    currentLocation = currentLocation.down()
                    updateLocation(pointMap, currentLocation, "|")
                }
            }
        }
        pointMap[currentLocation] = "+"
    }

    private fun updateLocation(
        mapA: MutableMap<Point, String>,
        lastLocation1: Point,
        character: String
    ) {
        mapA[lastLocation1] = character
    }


    fun part2(input: List<String>): String {
        TODO()
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
