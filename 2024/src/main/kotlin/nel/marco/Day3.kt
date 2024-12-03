package nel.marco


class Day3(readInput: List<String>) : Day(readInput) {

    override fun answerOne(): String {
        return readInput.map {
            calculateWithMultiply(it)
        }.sum().toString()
    }

    private fun calculateWithMultiply(it: String) = "mul\\(\\d{1,3},\\d{1,3}\\)".toRegex()
        .findAll(it)
        .map {
            val digits = it.value
                .replace("mul(", "")
                .replace(")", "")
                .split(",")
            digits[0].toLong() * digits[1].toLong()
        }
        .sumOf { it }


    override fun answerTwo(): String {
//        The do() instruction enables future mul instructions.
//        The don't() instruction disables future mul instructions.
        val singleLine = readInput.joinToString("")
        var combine = singleLine.substringBefore("don't()") + singleLine.substringAfterLast("do()")

        return calculateWithMultiply(combine).toString()
    }


}
