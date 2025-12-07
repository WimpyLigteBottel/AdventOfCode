package nel.marco

import java.math.BigInteger
import java.util.*


data class Day7(
    val useExample: Boolean = false,
    val useMac: Boolean = true,
) : Day(dayNumber = 7, useExample = useExample, macBook = useMac) {


    override fun answerOne(): String {
        val grid = createGrid()
        val start = findStart(grid)

        processActions(start, grid)

        val result = grid.flatten()
            .filter { it.value == "^" }
            .count {
                val left = it.clone().left().retrieve(grid)
                val right = it.clone().right().retrieve(grid)
                val up = it.clone().down().retrieve(grid)

                listOf(left.value, right.value, up.value).count {
                    runCatching {
                        BigInteger(it)
                    }.isSuccess
                } == 3
            }

        printMap(grid)

        return result.toString()
    }

    private fun processActions(
        start: Point?,
        grid: List<List<Point>>
    ) {
        val stack = ArrayDeque<Point>()
        stack.add(start)

        while (stack.isNotEmpty()) {
            val current = stack.pop()

            if (current.y >= grid.size - 1) {
                continue
            }

            val next = current.clone().up().retrieve(grid)

            if (next.value == ".") {
                if (current.value == "║") {
                    next.value = current.value
                } else {
                    next.value = "1"
                }
                stack.add(next);
            } else if (next.value == "^") {
                stack.add(next.clone().down().left())
                stack.add(next.clone().down().right())
            } else {
                next.value = BigInteger(next.value).plus(BigInteger.ONE).toString()
            }
        }
    }


    override fun answerTwo(): String {
        val grid = createGrid()
        val width = grid[0].size
        val height = grid.size

        // dp[row][col] = number of timelines reaching this cell
        val dp = Array(height) { Array(width) { BigInteger.ZERO } }

        val start = findStart(grid)
        dp[start!!.y][start.x] = BigInteger.ONE

        for (r in start.y until height - 1) {
            for (c in 0 until width) {
                val timelines = dp[r][c]
                if (timelines == BigInteger.ZERO) continue

                when (grid[r + 1][c].value) {
                    "." -> dp[r + 1][c] += timelines
                    "^" -> {
                        if (c - 1 >= 0) dp[r + 1][c - 1] += timelines
                        if (c + 1 < width) dp[r + 1][c + 1] += timelines
                    }
                }
            }
        }

        // Sum timelines in the last row
        return dp[height - 1].fold(BigInteger.ZERO) { acc, v -> acc + v }.toString()
    }


    fun Point.retrieve(grid: List<List<Point>>): Point = grid[this.y][this.x]

    fun createGrid(): List<List<Point>> {

        val grid = mutableListOf<List<Point>>()
        for (row in 0..readInput.size - 1) {
            val columns = mutableListOf<Point>()
            for (column in 0..readInput[row].length - 1) {
                columns.add(Point(x = column, y = row, value = readInput[row][column].toString()))
            }

            grid.add(columns)
        }

        return grid
    }

    private fun findStart(grid: List<List<Point>>): Point? {
        for (row in 0..grid.size) {
            for (column in 0..grid[row].size) {
                if (grid[row][column].value == "S")
                    return grid[row][column]
            }
        }

        return null;
    }

    fun printMap(newMap: List<List<Point>>) {
        println()
        for (x in 0..<newMap.size) {
            for (y in 0..<newMap[x].size) {
                print(newMap[x][y].value)
            }
            println()
        }
    }

}
