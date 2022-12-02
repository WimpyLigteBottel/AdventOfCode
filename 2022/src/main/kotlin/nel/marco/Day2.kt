package nel.marco

import java.lang.RuntimeException

class Day2(var readInput: List<String>) {


    fun answerOne(): String {
        return readInput.sumOf {
            val results = it.split(" ")

            val opponent = OPPONENT.find(results[0])
            val me = ME.find(results[1])

            determineScore(opponent, me)
        }.toString()
    }

    fun answerTwo(): String {
        return readInput.sumOf {
            val results = it.split(" ")

            val opponent = OPPONENT.find(results[0])
            val me = determineCounterPlay(opponent, results[1])

            determineScore(opponent, me)
        }.toString()
    }

    fun determineCounterPlay(opponent: OPPONENT, input: String): ME {
        when (WinCondition.find(input)) {
            WinCondition.DRAW -> {
                return when (opponent) {
                    OPPONENT.ROCK -> ME.ROCK
                    OPPONENT.PAPER -> ME.PAPER
                    OPPONENT.SCISSORS -> ME.SCISSORS
                }
            }

            WinCondition.LOSE -> {
                return when (opponent) {
                    OPPONENT.ROCK -> ME.SCISSORS
                    OPPONENT.PAPER -> ME.ROCK
                    OPPONENT.SCISSORS -> ME.PAPER
                }
            }

            WinCondition.WIN -> {
                return when (opponent) {
                    OPPONENT.ROCK -> ME.PAPER
                    OPPONENT.PAPER -> ME.SCISSORS
                    OPPONENT.SCISSORS -> ME.ROCK
                }
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
        SCISSORS("C");

        companion object {
            fun find(s: String): OPPONENT {
                return OPPONENT.values().find { it.letter == (s) }!!
            }
        }

    }

    enum class ME(var letter: String) {
        ROCK("X"),
        PAPER("Y"),
        SCISSORS("Z");

        companion object {
            fun find(s: String): ME {
                return ME.values().find { it.letter == (s) }!!
            }
        }
    }

    enum class WinCondition(var letter: String) {
        LOSE("X"),
        DRAW("Y"),
        WIN("Z");

        companion object {
            fun find(s: String): WinCondition {
                return WinCondition.values().find { it.letter == (s) }!!
            }
        }
    }

}


fun main(args: Array<String>) {
    val input = ReadUtil.readInputAsList(2)
    println("answer 1=" + Day2(input).answerOne())
    println("answer 2=" + Day2(input).answerTwo())
}


