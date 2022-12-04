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
            var me = ME.find(results[1])
            me = determineCounterPlay(opponent, me)

            determineScore(opponent, me)
        }.toString()
    }

    fun determineCounterPlay(opponent: OPPONENT, input: ME): ME {
        when (input.condition) {
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
        }

        throw RuntimeException("failed to determine win condition")
    }

    fun determineScore(opponent: OPPONENT, me: ME): Int {
        val DRAW = 3
        val WIN = 6
        val LOSE = 0

        return when (opponent) {
            OPPONENT.ROCK -> {
                when (me) {
                    ME.ROCK -> me.playScore + DRAW
                    ME.PAPER -> me.playScore + WIN
                    ME.SCISSORS -> me.playScore + LOSE
                }
            }
            OPPONENT.PAPER -> {
                when (me) {
                    ME.ROCK -> me.playScore + LOSE
                    ME.PAPER -> me.playScore + DRAW
                    ME.SCISSORS -> me.playScore + WIN
                }
            }
            OPPONENT.SCISSORS -> {
                when (me) {
                    ME.ROCK -> me.playScore + WIN
                    ME.PAPER -> me.playScore + LOSE
                    ME.SCISSORS -> me.playScore + DRAW
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

    enum class ME(var letter: String, var playScore: Int, var condition: String) {
        ROCK("X", 1, "LOSE"),
        PAPER("Y", 2, "DRAW"),
        SCISSORS("Z", 3, "WIN");

        companion object {
            fun find(s: String): ME {
                return ME.values().find { it.letter == (s) }!!
            }
        }
    }

}


fun main(args: Array<String>) {
    var readAllLines = MarcoUtil.readInput(2, false) as MutableList<String>

    val part1: () -> Unit = {
        val day = Day2(readAllLines)
        println("part1 = ${day.answerOne()}")
    }
    val part2: () -> Unit = {
        val day = Day2(readAllLines)
        println("part2 = ${day.answerTwo()}")
    }
    MarcoUtil.time("part1", part1)
    MarcoUtil.time("part2", part2)
}


