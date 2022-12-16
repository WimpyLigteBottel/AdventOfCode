package nel.marco

data class Point(var x: Int, var y: Int) {

    constructor(pair: Pair<Int, Int>) : this(0, 0) {
        this.x = pair.first
        this.y = pair.second
    }

    constructor(pair: Point) : this(0, 0) {
        this.x = pair.x
        this.y = pair.y
    }

    fun clone(): Point {
        return Point(x, y)
    }

    fun isOneSpotAway(other: Point): Boolean {
        val clone = this.clone()
        return clone == other ||
                clone.up() == other ||
                clone.up().right() == other ||
                clone.up().left() == other ||
                clone.down() == other ||
                clone.down().right() == other ||
                clone.down().left() == other ||
                clone.left() == other ||
                clone.right() == other
    }

    fun direction(input: String) {
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
}