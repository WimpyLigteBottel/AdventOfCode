package nel.marco

class Day4 {

    private fun List<String>.getMinAndMax() = this.first().split("-")[0].toLong() to this.first().split("-")[1].toLong()

    fun part1(input: List<String>): String {
        val (min, max) = input.getMinAndMax()
        var counter = 0

        for (range in min..max) {
            if (
                !range.toString().isOnlyIncreasing() ||
                !range.toString().contain2SameDigitAdjacent()
            ) {
                continue
            }

            counter++
        }

        return "$counter"
    }

    fun part2(input: List<String>): String {
        val (min, max) = input.getMinAndMax()
        var counter = 0

        for (range in min..max) {
            if (
                !range.toString().isOnlyIncreasing() ||
                !range.toString().contain2SameDigitAdjacent() ||
                !range.toString().containOnly2SameDigitAdjacent()
            ) {
                continue
            }

            counter++
        }


        return "$counter"
    }

    private fun String.contain2SameDigitAdjacent(): Boolean {
        for (x in 0 until this.length - 1) {
            if (this[x].toString().toInt() == this[x + 1].toString().toInt()) {
                return true
            }
        }
        return false
    }

    private fun String.containOnly2SameDigitAdjacent(): Boolean {
        val map = mutableMapOf(
            "0" to 0,
            "1" to 0,
            "2" to 0,
            "3" to 0,
            "4" to 0,
            "5" to 0,
            "6" to 0,
            "7" to 0,
            "8" to 0,
            "9" to 0,
        )

        for (x in 0 until this.length) {
            map["${this[x]}"] = map["${this[x]}"]!! + 1
        }

        val find = map.entries.find { it.value == 2 }
        return find != null
    }


    private fun String.isOnlyIncreasing(): Boolean {
        for (x in 0 until this.length - 1) {
            if ("${this[x]}".toInt() > "${this[x+1]}".toInt()) {
                return false
            }
        }

        return true
    }

}