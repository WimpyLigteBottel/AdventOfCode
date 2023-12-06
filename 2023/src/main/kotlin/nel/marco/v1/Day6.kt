package nel.marco.v1

import java.math.BigInteger


class Day6(readInput: List<String>) : Day(readInput) {

    //    Time:      7  15   30
//    Distance:  9  40  200
    override fun answerOne(): String {

        val times = "\\d+".toRegex().findAll(readInput[0]).map { it.value.toLong() }.toList()

        val distances = "\\d+".toRegex().findAll(readInput[1]).map { it.value.toLong() }.toList()

        var races = List(times.size) { index ->
            Race(times[index], distances[index])
        }

        var speedTimeEndRange = mutableListOf<Long>()

        for (element in races) {
            val boat = Boat(1, 1)
            speedTimeEndRange.add(boat.timesToBeatRace(element))
        }


        var total = 1L

        speedTimeEndRange.forEach {
            total *= it
        }


        return total.toString()
    }

    override fun answerTwo(): String {
        val times = "\\d+".toRegex().findAll(readInput[0]).map { it.value.toLong() }.joinToString("")
        val distances = "\\d+".toRegex().findAll(readInput[1]).map { it.value.toLong() }.joinToString("")

        var race = Race(times.toLong(), distances.toLong())
        val boat = Boat(1, 1)
        return boat.timesToBeatRace(race).toString()
    }

}

data class Boat(
    var speed: Int,
    var waited: Int,
) {

    fun timesToBeatRace(race: Race): Long {
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