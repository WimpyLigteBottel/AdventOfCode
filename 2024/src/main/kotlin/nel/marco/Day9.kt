package nel.marco


class Day9(useExample: Boolean = false, useMac: Boolean = false) : Day(9, useExample = useExample, macBook = useMac) {

    override fun answerOne(): String {

        val map: Map<Int,String> = ('A'..'Z').mapIndexed { index, c ->
            index + 10 to c.toString()
        }.associate { it.first to it.second }

        val line = readInput.first().map { it }


        var digitsList = mutableListOf<String>()
        var spaceList = mutableListOf<String>()

        var index = 0
        var digit = 0
        while (index <= line.size - 2) {
            var file = line[index].digitToInt()
            var space = line[index + 1].digitToInt()
            repeat(file) {
                print(map[digit] ?: digit)
            }
            repeat(space) {
                print(".")
            }
            digit++
            index++
        }

        return ""
    }


    fun List<String>.packInto(packedInto: MutableList<String>) {
        this.asReversed().forEach { numberToPack ->
            for ((index, x) in packedInto.withIndex()) {
                if (packedInto[index] == ".") {
                    packedInto[index] = numberToPack
                }
            }
        }
    }

    override fun answerTwo(): String {
        return ""
    }
}
