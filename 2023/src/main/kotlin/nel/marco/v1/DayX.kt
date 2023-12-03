package nel.marco.v1

import nel.marco.MarcoUtil



fun main(args: Array<String>) {
    var readAllLines = MarcoUtil.readInput(3, true) as MutableList<String>
    val day = DayX(readAllLines)
    executeTimes("ANSWER 1") {
        day.answerOne()
    }
    executeTimes("ANSWER 2") {
        day.answerTwo()
    }
}


class DayX(var readInput: List<String>) {

    fun answerOne(): String {
        return ""
    }

    fun answerTwo(): String {
        return ""
    }

}