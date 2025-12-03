package nel.marco

class Day2(
    useExample: Boolean = false,
    useMac: Boolean = true,
) : Day(dayNumber = 2, useExample = useExample, macBook = useMac) {
    override fun answerOne(): String {
        val digits = expandDigits()

        val count =
            digits
                .parallelStream()
                .filter { it.length % 2 == 0 }
                .map { it ->
                    val midPoint = it.length / 2

                    val left = it.substring(0, midPoint)
                    val right = it.substring(midPoint)

                    if (!right.startsWith("0") && left == right) {
                        return@map it.toLong()
                    }

                    return@map 0L
                }
                .toList()
                .fold(0L) { a, b -> a + b }

        return count.toString()
    }


    override fun answerTwo(): String {
        val digits = expandDigits()

        val invalidDigits =
            digits
                .parallelStream()
                .map {
                    // if more than half the does not work exit out
                    val max = it.length / 2 + 1
                    for (end in 1 until max) {
                        val temp = it.substring(0, end)
                        if (it.replace(temp, "").isEmpty()) {
                            return@map it.toLong()
                        }
                    }
                    return@map 0L
                }
                .toList()


        return invalidDigits.fold(0L) { a, b -> a + b }.toString()
    }

    private fun expandDigits(): List<String> {
        val lines = readInput.first().split(",")

        return lines
            .stream()
            .parallel()
            .flatMap { line ->
                val split = line.split("-")

                val first = split[0].toLong()
                val until = split[1].toLong()

                val digits = ArrayDeque<String>(initialCapacity = (until.toInt() - first.toInt()))

                for (x in first..until) {
                    digits.add(x.toString())
                }
                digits.stream()
            }
            .toList()
    }
}
