package nel.marco


class Day3(readInput: List<String>) : Day(readInput) {

    override fun answerOne(): String {
        return readInput
            .parallelStream()
            .map { calculateWithMultiply(it) }
            .toList()
            .sum()
            .toString()
    }

    private fun calculateWithMultiply(it: String) = "mul\\(\\d+,\\d+\\)".toRegex()
        .findAll(it)
        .map { matchResult ->
            matchResult.value
                .replace("mul(", "")
                .replace(")", "")
                .split(",")
                .let { digits ->
                    digits[0].toLong() * digits[1].toLong()
                }
        }
        .sumOf { it }


    override fun answerTwo(): String {
        var singleLine = readInput.joinToString("")

        """don't\(\).*?do\(\)""".toRegex()
            .findAll(singleLine)
            .forEach {
                singleLine = singleLine.replaceFirst(it.value, "")
            }

        return calculateWithMultiply(singleLine).toString()
    }


}
