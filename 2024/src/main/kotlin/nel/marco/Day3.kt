package nel.marco


class Day3(useExample: Boolean = false, useMac: Boolean = false) : Day(3, useExample = useExample, macBook = useMac) {

    companion object {
        private val MULTIPLY_REGEX = "mul\\((\\d+),(\\d+)\\)".toRegex()
        private val REMOVE_REGEX = """don't\(\).*?do\(\)""".toRegex()
    }

    override fun answerOne(): String {
        return readInput
            .parallelStream()
            .mapToLong { calculateWithMultiply(it) }
            .sum()
            .toString()
    }

    private fun calculateWithMultiply(it: String) = MULTIPLY_REGEX
        .findAll(it)
        .map { match -> match.groupValues[1].toLong() * match.groupValues[2].toLong() }
        .sumOf { it }


    override fun answerTwo(): String {
        var cleanedInput = REMOVE_REGEX.replace(readInput.joinToString("")) { "" } // batch replace all invalid regex's

        return calculateWithMultiply(cleanedInput).toString()
    }

}
