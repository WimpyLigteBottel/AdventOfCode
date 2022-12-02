package nel.marco

import java.lang.RuntimeException

class Day2(var readInput: List<String>) {


    fun answerOne(): String {
        return readInput.map {
            val results = it.split(" ")

            val opponent = OPPONENT.values().find { it.letter == (results[0]) }!!
            val me = ME.values().find { it.letter == (results[1]) }!!

            determineScore(opponent, me)
        }.sum().toString()
    }

    fun answerTwo(): String {
        return readInput.map {
            val results = it.split(" ")

            val opponent = OPPONENT.values().find { it.letter == (results[0]) }!!
            val me = determineCounterPlay(opponent, results[1])

            determineScore(opponent, me)
        }.sum().toString()
    }

    fun determineCounterPlay(opponent: OPPONENT, input: String): ME {

        var condition = when (input) {
            "Y" -> "DRAW"
            "X" -> "LOSE"
            "Z" -> "WIN"
            else -> ""
        }


        when (condition) {
            "DRAW" -> {
                return when (opponent) {
                    OPPONENT.ROCK -> ME.ROCK
                    OPPONENT.PAPER -> ME.PAPER
                    OPPONENT.SCISSORS -> ME.SCISSORS
                }
            }

            "LOSE" -> {
                return when (opponent) {
                    OPPONENT.ROCK -> ME.SCISSORS
                    OPPONENT.PAPER -> ME.ROCK
                    OPPONENT.SCISSORS -> ME.PAPER
                }
            }

            "WIN" -> {
                return when (opponent) {
                    OPPONENT.ROCK -> ME.PAPER
                    OPPONENT.PAPER -> ME.SCISSORS
                    OPPONENT.SCISSORS -> ME.ROCK
                }
            }

            else -> {
                throw RuntimeException("Failed to understand win condition")
            }
        }
    }


    fun determineScore(opponent: OPPONENT, me: ME): Int {
        return when (opponent) {
            OPPONENT.ROCK -> {
                when (me) {
                    ME.ROCK -> 1 + 3
                    ME.PAPER -> 2 + 6
                    ME.SCISSORS -> 3 + 0
                }
            }

            OPPONENT.PAPER -> {
                when (me) {
                    ME.ROCK -> 1 + 0
                    ME.PAPER -> 2 + 3
                    ME.SCISSORS -> 3 + 6
                }
            }

            OPPONENT.SCISSORS -> {
                when (me) {
                    ME.ROCK -> 1 + 6
                    ME.PAPER -> 2 + 0
                    ME.SCISSORS -> 3 + 3
                }
            }
        }

    }

    enum class OPPONENT(var letter: String) {
        ROCK("A"),
        PAPER("B"),
        SCISSORS("C")
    }

    enum class ME(var letter: String) {
        ROCK("X"),
        PAPER("Y"),
        SCISSORS("Z")
    }

}


fun main(args: Array<String>) {
    val input = ReadUtil.readInputAsList(2)
    println("answer 1=" + Day2(input).answerOne())
    println("answer 2=" + Day2(input).answerTwo())
}


