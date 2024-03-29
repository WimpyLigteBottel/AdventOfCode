package nel.marco.v1


class Day2(readInput: List<String>) : Day(readInput) {

    override fun answerOne(): String {

        val result = readInput
            .map { GameOuput().build(it) } // build the game with necessary fields filled
            .filter { it.isValid() }
            .sumOf { it.gameId }



        return "$result"
    }

    override fun answerTwo(): String {

        val result = readInput
            .map { GameOuput().build(it) }
            .sumOf { it.powerSetOfEachColorMaxValue() }

        return result.toString()
    }

}


data class GameOuput(
    var gameId: Int = 0,
    var blue: List<Int> = emptyList(),
    var red: List<Int> = emptyList(),
    var green: List<Int> = emptyList()
) {

    //Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
    fun build(input: String): GameOuput {
        gameId = "Game \\d+".toRegex().findAll(input).first().value.replace(":", "").replace("Game ", "").toInt()

        green = "\\d+ green".toRegex().findAll(input).map {
            it.value.replace(" green", "").toInt()
        }.toList()

        red = "\\d+ red".toRegex().findAll(input).map {
            it.value.replace(" red", "").toInt()
        }.toList()

        blue = "\\d+ blue".toRegex().findAll(input).map {
            it.value.replace(" blue", "").toInt()

        }.toList()

        return this
    }

    fun powerSetOfEachColorMaxValue(): Long {
        val redMax = red.max().toLong()
        val greenMax = green.max().toLong()
        val blueMax = blue.max().toLong()
        return redMax * greenMax * blueMax
    }


    fun isNotValid(): Boolean {
        //12 red cubes, 13 green cubes, and 14 blue cubes

        return blue.any { it > 14 }
                || red.any { it > 12 }
                || green.any { it > 13 }

    }

    fun isValid(): Boolean {
        return !isNotValid()

    }

}