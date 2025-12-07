package nel.marco

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

                listOf(left.value, right.value, up.value).count { it == "|" || it == "║" } == 3
            }

        return result.toString()
    }

    private fun processActions(
        start: Point?,
        grid: List<List<Point>>
    ) {
        val stack = ArrayDeque<Point>()
        stack.add(start)

        while (stack.isNotEmpty()) {
            printMap(grid)
            val current = stack.peek()

            if (current.y >= grid.size - 1) {
                stack.pop();
                continue
            }

            val next = current.clone().up().retrieve(grid)

            if (next.value == ".") {
                stack.pop()

                if (current.value == "║") {
                    next.value = current.value
                } else {
                    next.value = "|"
                }
                stack.add(next);
            } else if (next.value == "^") {
                stack.pop()
                stack.add(next.clone().down().left())
                stack.add(next.clone().down().right())
            } else if (next.value == "|" || next.value == "║") {
                next.value = "║"
                stack.pop()
            }

        }
    }


    override fun answerTwo(): String {
        val grid = createGrid()
        val start = findStart(grid)

        processActions(start, grid)

        return ""
    }

    fun Point.retrieve(grid: List<List<Point>>): Point = grid[this.y][this.x]

    fun createGrid(): List<List<Point>> {

        val grid = mutableListOf<List<Point>>()
        for (row in 0..readInput.size - 1) {
            val columns = mutableListOf<Point>()
            for (column in 0..readInput[row].length - 1) {
                columns.add(Point(x = column, y = row, value = readInput[row][column].toString()))
            }

            if (columns.size != columns.filter { it.value == "." }.size) {
                grid.add(columns)
            }
        }
        printMap(grid)

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
