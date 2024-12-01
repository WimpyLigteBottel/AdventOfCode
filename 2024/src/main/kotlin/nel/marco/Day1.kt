package nel.marco


class Day1(readInput: List<String>) : Day(readInput) {

    override fun answerOne(): String {

        var leftList = readInput.map { it.split("   ")[0] }.map { it.toInt() }.sorted()
        var rightList = readInput.map { it.split("   ")[1] }.map { it.toInt() }.sorted()



        return leftList
            .zip(rightList) { left, right -> right - left }
            .map { Math.abs(it) }
            .sum()
            .toString()

    }

    override fun answerTwo(): String {
        var leftList = readInput.map { it.split("   ")[0] }.map { it.toInt() }.sorted()
        var rightList = readInput.map { it.split("   ")[1] }.map { it.toInt() }

        var map = mutableMapOf<Int, Int>()

        rightList.forEach {
            map.putIfAbsent(it, 0)
            map[it] = map[it]!! + 1
        }

        return leftList
            .map {
                it * map.getOrDefault(it, 0)
            }.sum().toString()

    }
}
