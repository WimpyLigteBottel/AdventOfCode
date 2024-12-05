package nel.marco


class Day5(readInput: List<String>) : Day(readInput) {

    var verifyList: List<Pair<String, String>> = emptyList();
    var rows: List<String> = emptyList()

    override fun answerOne(): String {
        verifyList = readInput.filter { it.length == 5 }.map { it.split("|")[0] to it.split("|")[1] }
        rows = readInput.filter { it.length > 5 }

        return rows
            .parallelStream() // performance
            .mapToInt { // so that i can sum()
                if (isInvalidRow(it)) {
                    return@mapToInt 0
                }
                return@mapToInt findMiddleDigit(it.split(","))
            }
            .sum()
            .toString()
    }

    private fun isNotValid(first: String, second: String, numbersToCheck: List<String>): Boolean {
        val a = numbersToCheck.indexOf(first)
        var b = numbersToCheck.indexOf(second)

        if (b == -1) {
            b = Int.MAX_VALUE
        }

        return a > b
    }

    private fun findMiddleDigit(numbersToCheck: List<String>): Int {
        return numbersToCheck[(numbersToCheck.size / 2)].toInt()
    }


    override fun answerTwo(): String {
        verifyList = readInput.filter { it.length == 5 }.map { it.split("|")[0] to it.split("|")[1] }
        rows = readInput.filter { it.length > 5 }

        return getInvalidRows(rows)
            .parallelStream() // performance
            .map { invalidRow ->
                val numbersToCheck = invalidRow.split(",").toMutableList()

                // keep sorting until its valid
                while (isInvalidRow(numbersToCheck.joinToString(","))) {
                    verifyList.forEach { (first, second) ->
                        if (isNotValid(first, second, numbersToCheck)) {
                            val tempA = numbersToCheck[numbersToCheck.indexOf(first)]
                            val tempB = numbersToCheck[numbersToCheck.indexOf(second)]

                            numbersToCheck[numbersToCheck.indexOf(first)] = tempB
                            numbersToCheck[numbersToCheck.indexOf(second)] = tempA
                        }
                    }
                }


                return@map numbersToCheck
            }
            .mapToInt { findMiddleDigit(it) }
            .sum()
            .toString()
    }

    private fun getInvalidRows(
        rows: List<String>,
    ) = rows
        .parallelStream()
        .map { row ->
            if (isInvalidRow(row)) {
                return@map row
            }
            return@map ""
        }
        .filter { it.isNotBlank() }
        .toList()

    private fun isInvalidRow(row: String): Boolean {
        val numbersToCheck = row.split(",")
        verifyList.forEach { (first, second) ->
            if (isNotValid(first, second, numbersToCheck)) {
                return true
            }
        }
        return false
    }

}
