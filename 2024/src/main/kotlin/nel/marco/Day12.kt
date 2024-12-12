package nel.marco


class Day12(useExample: Boolean = false, useMac: Boolean = false) : Day(12, useExample = useExample, macBook = useMac) {
    override fun answerOne(): String {

        var map = setupMap()

        val groupBy: Map<String, List<MutableMap.MutableEntry<Point, String>>> =
            map.entries.groupBy { it.value }.filter { it.key != "." }


        var globalCounter = 0

        groupBy.forEach { key, listOfValues ->
            var counter = 0
            listOfValues.forEach { point ->
                var test = point.key.surroundingPoints(false)
                    .mapNotNull { map[it] }
                    .filter { it == point.value }

                when (test.size) {
                    1 -> counter += 3
                    2 -> counter += 2
                    3 -> counter += 1
                    4 -> counter += 0
                    else -> counter++
                }

            }
            println("$key: ${listOfValues.size} * ${counter} = ${listOfValues.size * counter}")
//            println("$key: ${listOfValues.size} -> ${listOfValues.size * 2 +2}")
            globalCounter += listOfValues.size * counter

        }

        MarcoUtil.printMap(map, readInput.size)


        return globalCounter.toString()
    }

    override fun answerTwo(): String {
        TODO("Not yet implemented")
    }


    private fun setupMap(): MutableMap<Point, String> {
        var map = mutableMapOf<Point, String>()
        readInput.mapIndexed { y, s ->
            s.mapIndexed { x, c ->
                map[Point(x, y, c.toString())] = c.toString()
            }
        }
        return map
    }

}