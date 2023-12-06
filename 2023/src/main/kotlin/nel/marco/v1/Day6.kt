package nel.marco.v1


class Day6(readInput: List<String>) : Day(readInput) {

    override fun answerOne(): String {

        val times = readInput[0].extractNumbers()
        val distances = readInput[1].extractNumbers()

        return times
            .mapIndexed { index, _ -> Race(times[index], distances[index]) }// Create race for each time
            .map { Boat().numberOfWaysToBeatRace(it) }
            .fold(1L) { acc, l -> acc * l } // multiply each value with itself until all is combined
            .toString()

    }


    override fun answerTwo(): String {
        val times = readInput[0].extractNumbers().joinToString("").toLong()
        val distances = readInput[1].extractNumbers().joinToString("").toLong()

        return Race(times, distances)
            .let { Boat().numberOfWaysToBeatRace(it) }
            .toString()
    }

    private fun String.extractNumbers() = "\\d+".toRegex().findAll(this).map { it.value.toLong() }.toList()

}

data class Boat(
    var speed: Int = 1,
    var waited: Int = 1,
) {
    fun numberOfWaysToBeatRace(race: Race): Long {
        var counter = 0L

        while (waited < race.time) {
            increaseSpeed()
            if (calculateDistance(race.time - waited) > race.distance) {
                counter++
            }
        }
        return counter
    }

    fun increaseSpeed() {
        speed++
        waited++
    }

    fun calculateDistance(remainingTime: Long): Long {
        return speed * remainingTime
    }
}

data class Race(
    var time: Long,
    var distance: Long,
)