package nel.marco.v1


class Day1(readInput: List<String>) : Day(readInput) {
    var map = mutableMapOf<String, String>()
    var mapReversed = mutableMapOf<String, String>()
    var letters = mutableListOf<String>()

    init {
        for (x in 'a'..'z') {
            letters.add(x.toString())
        }
        map["one"] = "1"
        map["two"] = "2"
        map["three"] = "3"
        map["four"] = "4"
        map["five"] = "5"
        map["six"] = "6"
        map["seven"] = "7"
        map["eight"] = "8"
        map["nine"] = "9"
        mapReversed["one".reversed()] = "1"
        mapReversed["two".reversed()] = "2"
        mapReversed["three".reversed()] = "3"
        mapReversed["four".reversed()] = "4"
        mapReversed["five".reversed()] = "5"
        mapReversed["six".reversed()] = "6"
        mapReversed["seven".reversed()] = "7"
        mapReversed["eight".reversed()] = "8"
        mapReversed["nine".reversed()] = "9"
    }

    override fun answerOne(): String {
        return readInput
            .map { it.replaceRemainingLetters().trim() } // gets rid of letters
            .map { it.first() + "" + it.last() }
            .sumOf { it.toInt() }
            .toString()
    }

    override fun answerTwo(): String {
        return readInput
            .map {
                var first = findFirstDigit(it, map)
                var last = findFirstDigit(it.reversed(), mapReversed)

                first + last
            }
            .sumOf { it.toLong() }
            .toString()
    }

    private fun findFirstDigit(inputNotMutable: String, map: MutableMap<String, String>): String {
        var input = inputNotMutable
        outerloop@ for (start in 0..input.length) {//innerLoop
            for (end in start + 3..input.length) {
                val selection = inputNotMutable.substring(start, end)
                for ((key, value) in map) {
                    if (selection.contains(key)) {
                        input = input.replaceFirst(key, value)
                        break@outerloop
                    }
                }
            }
        }
        input = input.replaceRemainingLetters()

        return input.first().toString()
    }

    private fun String.replaceRemainingLetters(): String {
        var string = this
        letters.forEach {
            string = string.replace(it, "")
        }

        return string
    }
}
