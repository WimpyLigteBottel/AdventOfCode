package nel.marco


class Day3(readInput: List<String>) : Day(readInput) {

    companion object {
        private val MULTIPLY_REGEX = "mul\\(\\d+,\\d+\\)".toRegex()
        private val REMOVE_REGEX = """don't\(\).*?do\(\)""".toRegex()
    }

    override fun answerOne(): String {
        return readInput
            .parallelStream()
            .map { calculateWithMultiply(it) }
            .toList()
            .sum()
            .toString()
    }

    private fun calculateWithMultiply(it: String) = MULTIPLY_REGEX
        .findAll(it)
        .map { matchResult ->
            matchResult.value
                .removePrefix("mul(")
                .removeSuffix(")")
                .split(",")
                .let { (left, right) ->
                    left.toLong() * right.toLong()
                }
        }
        .sumOf { it }


    override fun answerTwo(): String {
        var singleLine = readInput.joinToString("")

        var cleanedInput = REMOVE_REGEX.replace(singleLine) { "" } // batch replace all invalid regex's

        return calculateWithMultiply(cleanedInput).toString()
    }

}
