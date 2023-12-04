package nel.marco.v1


abstract class Day(var readInput: List<String>) {
    abstract fun answerOne(): String
    abstract fun answerTwo(): String
}