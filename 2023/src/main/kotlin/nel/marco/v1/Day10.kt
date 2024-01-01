package nel.marco.v1

class Day10(readInput: List<String>) : Day(readInput) {

    /*
    | is a vertical pipe connecting north and south.
    - is a horizontal pipe connecting east and west.
    L is a 90-degree bend connecting north and east.
    J is a 90-degree bend connecting north and west.
    7 is a 90-degree bend connecting south and west.
    F is a 90-degree bend connecting south and east.
    . is ground; there is no pipe in this tile.
    S is the starting position of the animal; there is a pipe on this
    */
    override fun answerOne(): String {
        val map: MutableList<MutableList<String>> = createPipeMap()

        val startingPoint = map.findStartingCoord()


        var lengthOfPipe = startingPoint.followPipeAndCountLength(map)


        return lengthOfPipe.toString()
    }

    override fun answerTwo(): String {
        TODO("Not yet implemented")
    }


    fun createPipeMap(): MutableList<MutableList<String>> {
        return readInput.map {
            it.split("")
                .filter { it != "" }
                .toMutableList()
        }.toMutableList()
    }

    fun MutableList<MutableList<String>>.findStartingCoord(): Pair<Int, Int> {
        for ((y, row) in this.withIndex()) {
            for ((x, column) in this.withIndex()) {
                if (this[y][x] == "S") {
                    return Pair(x, y)
                }
            }
        }

        throw RuntimeException("Failed to find `S`")
    }


    fun Pair<Int, Int>.followPipeAndCountLength(map: MutableList<MutableList<String>>): Long {
        var counter = 0L;
        var direction = ""
        var nextMove = Point(this)

        var point = Point(this)

        // starting move
        if (isStartingPoint(point, map)) {

            val below = point.down()
            val left = point.left()
            val right = point.right()
            val above = point.up()
            counter++
            point.updateMap(map, "X")

            if (below.getFromMap(map) == "|") {
                nextMove = below
                direction = "D"
            } else if (left.getFromMap(map) == "-") {
                nextMove = left
                direction = "L"
            } else if (right.getFromMap(map) == "-") {
                nextMove = right
                direction = "R"
            } else if (above.getFromMap(map) == "|") {
                nextMove = above
                direction = "U"
            }
        }
//        map.printMap()

        while (nextMove.getFromMap(map) != "X") {
            point = nextMove.clone()
            val nextPointCharacter = nextMove.getFromMap(map)
            when (direction) {
                "R" -> {
                    println("GOING RIGHT")
                    when (nextPointCharacter) {
                        "-" -> {
                            direction = "R"
                            nextMove = point.right()
                        }
                        "J" -> {
                            direction = "U"
                            nextMove = point.up()
                        }

                        "7" -> {
                            direction = "D"
                            nextMove = point.down()
                        }
                        else -> { throw RuntimeException("FAILURE")}
                    }
                }
                "D" -> {
                    println("GOING DOWN")
                    when (nextPointCharacter) {
                        "|" -> {
                            direction = "D"
                            nextMove = point.down()
                        }
                        "L" -> {
                            direction = "R"
                            nextMove = point.right()
                        }
                        "J" -> {
                            direction = "L"
                            nextMove = point.left()
                        }
                        else -> { throw RuntimeException("FAILURE")}
                    }
                }
                "L" -> {
                    println("GOING LEFT")
                    when (nextPointCharacter) {
                        "-" -> {
                            direction = "L"
                            nextMove = point.left()
                        }
                        "L" -> {
                            direction = "U"
                            nextMove = point.up()
                        }
                        "F" -> {
                            direction = "D"
                            nextMove = point.down()
                        }
                        else -> { throw RuntimeException("FAILURE")}
                    }
                }
                "U" -> {
                    println("GOING UP")
                    when (nextPointCharacter) {
                        "|" -> {
                            direction = "U"
                            nextMove = point.up()
                        }
                        "7" -> {
                            direction = "L"
                            nextMove = point.left()
                        }
                        "F" -> {
                            direction = "R"
                            nextMove = point.right()
                        }

                        else -> { throw RuntimeException("FAILURE")}
                    }
                }
            }
            counter++
            point.updateMap(map, "X")
//            map.printMap()
        }
        counter++

        return counter / 2 // half the pipe should be the entire pipe

    }
    /*
    XXXXXX
    XS X E
    XX X X
    XX X X
    X  X X
    X X  X
    X   X
     */

    private fun isStartingPoint(
        point: Point,
        map: MutableList<MutableList<String>>
    ) = point.getFromMap(map) == "S"


    fun Point.getFromMap(map: MutableList<MutableList<String>>): String {
        return map.get(this.y).get(this.x)
    }


    fun Point.updateMap(map: MutableList<MutableList<String>>, newChar: String): String {
        return map.get(this.y).set(this.x, newChar)
    }

    fun  MutableList<MutableList<String>>.printMap(){
        println("XXXXXXXXXXXX")

        this.forEach {
            it.forEach {
                print(it)
            }
            println()
        }
        println("XXXXXXXXXXXX")

    }


}


data class Point(var x: Int = 0, var y: Int = 0, var value: String = "") {

    constructor(pair: Pair<Int, Int>) : this(0, 0) {
        this.x = pair.first
        this.y = pair.second
    }

    constructor(pair: Point) : this(0, 0) {
        this.x = pair.x
        this.y = pair.y
    }

    fun clone(): Point {
        return Point(this.x, this.y)
    }


    fun right(): Point {
        val temp = this.clone()
        temp.x++
        return temp
    }

    fun left(): Point {
        val temp = this.clone()
        temp.x--
        return temp
    }

    fun up(): Point {
        val temp = this.clone()
        temp.y--
        return temp
    }

    fun down(): Point {
        val temp = this.clone()
        temp.y++
        return temp
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Point

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }

}