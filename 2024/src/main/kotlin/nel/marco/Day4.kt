package nel.marco


class Day4(readInput: List<String>) : Day(readInput) {

    override fun answerOne(): String {
        val array = readInput.map { it.toCharArray() }.toTypedArray() // make 2D array

        var count = 0

        val lengthOfPuzzle = readInput[0].length // 10 -> 140 depending on size
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
        val lengthOfPuzzle = readInput[0].length // 10 -> 140 depending on size
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
        if ("${a}${b}" == "MS") {
            if ("${d}${e}" == "MS") {
                if ("${c}" == "A") {
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
        if ("${a}${b}" == "MM") {
            if ("${d}${e}" == "SS") {
                if ("${c}" == "A") {
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
        if ("${a}${b}" == "SS") {
            if ("${d}${e}" == "MM") {
                if ("$c" == "A") {
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
        if ("${a}${b}" == "SM") {
            if ("${d}${e}" == "SM") {
                if ("${c}" == "A") {
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
        if ("${a}${b}${c}${d}" == "XMAS") {
            count++
        }
        if ("${a}${b}${c}${d}" == "SAMX") {
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
        if ("${a}${b}${c}${d}" == "XMAS") {
            count++
        }
        if ("${a}${b}${c}${d}" == "SAMX") {
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
        if ("${a}${b}${c}${d}" == "XMAS") {
            count++
        }
        if ("${a}${b}${c}${d}" == "SAMX") {
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
        if ("${a}${b}${c}${d}" == "XMAS") {
            count++
        }
        if ("${a}${b}${c}${d}" == "SAMX") {
            count++
        }

        return count
    }

}
