package nel.marco

class Day4(
    useExample: Boolean = false,
    useMac: Boolean = true,
) : Day(dayNumber = 4, useExample = useExample, macBook = useMac) {


    override fun answerOne(): String {

        val map = toiletPaperTowers()

        val points = map
            .filter { (key, value) -> value == "@" }
            .filter { (key, value) ->
                val getAdjustSpots = key.surroundingPoints(true).map { map[it] }.count { it == "@" }

                getAdjustSpots < 4
            }

        return points.size.toString()
    }


    override fun answerTwo(): String {
        val map = toiletPaperTowers()


        var hasRemovedPaper = true

        while (hasRemovedPaper) {
            hasRemovedPaper = false
            map
                .filter { (key, value) -> value == "@" }
                .filter { (key, value) ->
                    val getAdjustSpots = key.surroundingPoints(true).map { map[it] }.count { it == "@" }
                    getAdjustSpots < 4
                }.forEach {
                    map[it.key] = "X"
                    hasRemovedPaper = true
                }
        }


        return map.filter { it.value == "X" }.size.toString()
    }


    fun toiletPaperTowers(): MutableMap<Point, String> {
        val map: Array<Array<String>> = readInput.map {
            val split = it.split("")
            split.toTypedArray()
        }.toTypedArray()

        var newMap = mutableMapOf<Point, String>()

        for (y in 0 until map.size) {
            for (x in 0 until map[y].size) {
                newMap[Point(x, y)] = map[y][x]
            }
        }

//        printMap(newMap, map.size + 1)
        return newMap
    }
}
