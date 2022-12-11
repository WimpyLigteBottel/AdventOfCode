package nel.marco


class Day8(var readInput: List<String>) {

    var map = Array(readInput.size) { Array(readInput[0].length) { 0 } }
    var changedMap = Array(readInput.size) { Array(readInput[0].length) { 0 } }


    companion object {
        val day: Int = 8
    }

    fun answerOne(): Int {
        setupMap()
        visible()
        canSeeTrees()

        return changedMap.flatMap { it.toList() }.filter { it == -1 }.count()
    }

    fun answerTwo(): Int {
        setupMap()

        var highestScore = 0

        for (x in 1 until map[0].size - 1) {
            for (y in 1 until map.size - 1) {
                val current = map[x][y]
                val left = map[x].toList().subList(0, y)
                val right = map[x].toList().subList(y + 1, map.size)
                val above = getAllAbove(y, x)
                val below = getAllBelow(y, x)

                var leftScore = calcScore(left.reversed(), current, 0)
                var rightScore = calcScore(right, current, 0)
                var aboveScore = calcScore(above.reversed(), current, 0)
                var belowScore = calcScore(below, current, 0)

                var score = leftScore * rightScore * aboveScore * belowScore
                if (score >= highestScore) {
                    highestScore = score
                }

            }
        }


        return highestScore
    }

    private fun calcScore(list: List<Int>, current: Int, currentScore: Int): Int {
        var score = currentScore
        for (element in list) {
            score++
            if (current <= element) {
                break
            }
        }
        return score
    }

    private fun canSeeTrees() {
        for (x in 1 until map[0].size - 1) {
            for (y in 1 until map.size - 1) {
                val current = map[x][y]
                val left = map[x].toList().subList(0, y)
                val right = map[x].toList().subList(y + 1, map.size)
                val above = getAllAbove(y, x)
                val below = getAllBelow(y, x)

                // if digit is bigger than expect outer all left == visible
                // if digit is bigger than expect outer all right == visible
                // if digit is bigger than expect outer all bottom == visible
                // if digit is bigger than expect outer all top == visible
                var isVisible = left.isSmaller(current) ||
                        right.isSmaller(current) ||
                        above.isSmaller(current) ||
                        below.isSmaller(current)


                if (isVisible)
                    changedMap[x][y] = -1
            }
        }
    }

    fun List<Int>.isSmaller(current: Int): Boolean {
        return this.max() < current
    }


    private fun getAllBelow(y: Int, until: Int): List<Int> {
        val list = mutableListOf<Int>()
        for (x in until + 1..map.size - 1) {
            list.add(map[x][y])
        }
        return list
    }

    private fun getAllAbove(currentSpot: Int, until: Int): List<Int> {
        val list = mutableListOf<Int>()
        for (x in 0 until until) {
            list.add(map[x][currentSpot])
        }
        return list
    }


    private fun visible() {
        for (x in map.indices) {
            changedMap[0][x] = -1
            changedMap[x][0] = -1
        }
        for (x in 0 until map[0].size) {
            changedMap[x][map.size - 1] = -1
            changedMap[map.size - 1][x] = -1
        }
    }

    private fun printMap(innerMap: Boolean = false) {

        if (innerMap) {
            for (x in 1 until map[0].size - 1) {
                for (y in 1 until map.size - 1) {
                    print(changedMap[x][y])
                }
                println()
            }
        } else {
            for (x in 0 until changedMap[0].size) {
                for (y in 0 until changedMap.size) {
                    print(changedMap[x][y])
                }
                println()
            }
        }

    }


    private fun setupMap() {
        readInput.forEachIndexed { index, s ->
            val chars: CharArray = s.toCharArray()
            chars.forEachIndexed { charIndex, letter ->
                map[index][charIndex] = letter.digitToInt()
                changedMap[index][charIndex] = letter.digitToInt()
            }
        }
    }


}

fun main(args: Array<String>) {
//    var readAllLines = MarcoUtil.readInput(Day8.day, true) as MutableList<String>
    var readAllLines = MarcoUtil.readInput(Day8.day, false) as MutableList<String>

    val part1: () -> Unit = {
        val day = Day8(readAllLines)
        println("part1 = ${day.answerOne()}")
    }
    val part2: () -> Unit = {
        val day = Day8(readAllLines)
        println("part2 = ${day.answerTwo()}")
    }

    MarcoUtil.time("part1", part1)
    MarcoUtil.time("part2", part2)

}