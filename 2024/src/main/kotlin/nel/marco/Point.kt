package nel.marco

data class Point(var x: Int = 0, var y: Int = 0, var value: String = "") {

    val isDigitRegex = "\\d".toRegex()

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

    fun isOneSpotAway(other: Point): Boolean {

        val above = Point(this).up() == other ||
                Point(this).up().right() == other ||
                Point(this).up().left() == other


        val below = Point(this).down() == other ||
                Point(this).down().left() == other ||
                Point(this).down().right() == other


        val sides = Point(this).left() == other ||
                Point(this).right() == other


        return Point(this) == other || above || below || sides

    }

    fun direction(input: String): Point {
        when (input) {
            "D" -> {
                down()
            }

            "U" -> {
                up()
            }

            "R" -> {
                right()
            }

            "L" -> {
                left()
            }
        }
        return this
    }

    fun right(): Point {
        x++
        return this
    }

    fun left(): Point {
        x--
        return this
    }

    fun up(): Point {
        y++
        return this
    }

    fun down(): Point {
        y--
        return this
    }

    fun Point.surroundingPoints(): List<Point> {
        val down = this.clone().up()
        val downLeft = this.clone().left().up()
        val downRight = this.clone().right().up()

        val left = this.clone().left()
        val right = this.clone().right()

        val up = this.clone().down()
        val upRight = this.clone().right().down()
        val upLeft = this.clone().left().down()


        return listOf(up, down, left, right, upLeft, upRight, downLeft, downRight)
    }

    fun isDigit(): Boolean {
        return isDigitRegex.matches(this.value)
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