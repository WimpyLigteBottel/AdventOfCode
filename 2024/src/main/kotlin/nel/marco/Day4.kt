package nel.marco


class Day4(readInput: List<String>) : Day(readInput) {

    val lengthOfPuzzle = readInput[0].length // 10 -> 140 depending on size

    override fun answerOne(): String {
        val array = readInput.map { it.toCharArray() }.toTypedArray() // make 2D array

        var count = 0

        for (x in 0 until lengthOfPuzzle) {
            for (y in 0 until lengthOfPuzzle) {
                runCatching { count += across(array, x, y) }
                runCatching { count += vertical(array, x, y) }
                runCatching { count += diagonal(array, x, y) }
                runCatching { count += diagonalReversed(array, x, y) }
            }
        }


        return count.toString()
    }


    override fun answerTwo(): String {
        val array = readInput.map { it.toCharArray() }.toTypedArray() // make 2D array
        var count = 0
        for (x in 0 until lengthOfPuzzle) {
            for (y in 0 until lengthOfPuzzle) {
                runCatching { count += xmasMleft(array, x, y) }
                runCatching { count += xmasMtop(array, x, y) }
                runCatching { count += xmasMbottom(array, x, y) }
                runCatching { count += xmasMright(array, x, y) }
            }
        }
        return count.toString()
    }

    private fun xmasMleft(array: Array<CharArray>, x: Int, y: Int): Int {
        var count = 0
        val a = array[x + 0][y + 0] // top left
        val b = array[x + 0][y + 2] // top right
        val c = array[x + 1][y + 1] // center
        val d = array[x + 2][y + 0] // bottom left
        val e = array[x + 2][y + 2] // bottom right
        // M S
        //  A
        // M S
        if (a == 'M' && b == 'S') {
            if (d == 'M' && e == 'S') {
                if (c == 'A') {
                    count++
                }
            }
        }
        return count
    }

    private fun xmasMtop(array: Array<CharArray>, x: Int, y: Int): Int {
        var count = 0
        val a = array[x + 0][y + 0] // top left
        val b = array[x + 0][y + 2] // top right
        val c = array[x + 1][y + 1] // center
        val d = array[x + 2][y + 0] // bottom left
        val e = array[x + 2][y + 2] // bottom right

        // M M
        //  A
        // S S
        if (a == 'M' && b == 'M') {
            if (d == 'S' && e == 'S') {
                if (c == 'A') {
                    count++
                }
            }
        }
        return count
    }

    private fun xmasMbottom(array: Array<CharArray>, x: Int, y: Int): Int {
        var count = 0
        val a = array[x + 0][y + 0] // top left
        val b = array[x + 0][y + 2] // top right
        val c = array[x + 1][y + 1] // center
        val d = array[x + 2][y + 0] // bottom left
        val e = array[x + 2][y + 2] // bottom right

        // S S
        //  A
        // M M
        if (a == 'S' && b == 'S') {
            if (d == 'M' && e == 'M') {
                if (c == 'A') {
                    count++
                }
            }
        }

        return count
    }

    private fun xmasMright(array: Array<CharArray>, x: Int, y: Int): Int {
        var count = 0
        val a = array[x + 0][y + 0] // top left
        val b = array[x + 0][y + 2] // top right
        val c = array[x + 1][y + 1] // center
        val d = array[x + 2][y + 0] // bottom left
        val e = array[x + 2][y + 2] // bottom right

        // S M
        //  A
        // S M
        if (a == 'S' && b == 'M') {
            if (d == 'S' && e == 'M') {
                if (c == 'A') {
                    count++
                }
            }
        }

        return count
    }

    private fun across(array: Array<CharArray>, x: Int, y: Int): Int {
        var count = 0
        val a = array[x][y]
        val b = array[x][y + 1]
        val c = array[x][y + 2]
        val d = array[x][y + 3]

        if (a == 'X' && b == 'M' && c == 'A' && d == 'S') {
            count++
        }
        if (a == 'S' && b == 'A' && c == 'M' && d == 'X') {
            count++
        }

        return count
    }

    private fun vertical(array: Array<CharArray>, x: Int, y: Int): Int {
        var count = 0
        val a = array[x][y]
        val b = array[x + 1][y]
        val c = array[x + 2][y]
        val d = array[x + 3][y]

        if (a == 'X' && b == 'M' && c == 'A' && d == 'S') {
            count++
        }
        if (a == 'S' && b == 'A' && c == 'M' && d == 'X') {
            count++
        }

        return count
    }

    private fun diagonal(array: Array<CharArray>, x: Int, y: Int): Int {
        var count = 0
        val a = array[x][y]
        val b = array[x + 1][y + 1]
        val c = array[x + 2][y + 2]
        val d = array[x + 3][y + 3]
        if (a == 'X' && b == 'M' && c == 'A' && d == 'S') {
            count++
        }
        if (a == 'S' && b == 'A' && c == 'M' && d == 'X') {
            count++
        }

        return count
    }

    private fun diagonalReversed(array: Array<CharArray>, x: Int, y: Int): Int {
        var count = 0
        val a = array[x + 3][y + 0]
        val b = array[x + 2][y + 1]
        val c = array[x + 1][y + 2]
        val d = array[x + 0][y + 3]
        if (a == 'X' && b == 'M' && c == 'A' && d == 'S') {
            count++
        }
        if (a == 'S' && b == 'A' && c == 'M' && d == 'X') {
            count++
        }

        return count
    }

}
