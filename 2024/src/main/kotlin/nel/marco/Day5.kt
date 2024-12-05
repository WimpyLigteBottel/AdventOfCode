package nel.marco


class Day5(readInput: List<String>) : Day(readInput) {

    override fun answerOne(): String {

        //split the list
        val verifyList = readInput.filter { it.length == 5 }
        val rows = readInput.filter { it.length > 5 }

        return rows.map {

            val numbersToCheck = it.split(",")
            val middleNumber = findMiddleDigit(numbersToCheck)

            verifyList.forEach {
                var (first, second) = it.split("|")

                var a = numbersToCheck.indexOf(first)
                var b = numbersToCheck.indexOf(second)

                if (b == -1) {
                    b = Int.MAX_VALUE
                }

                if (a == -1) {
                    b = Int.MAX_VALUE
                }

                //exit early because its invalid
                if (a > b) {
                    return@map 0
                }
            }


            return@map middleNumber
        }.sum().toString()
    }

    private fun findMiddleDigit(numbersToCheck: List<String>): Int {
        val clone = numbersToCheck.toMutableList()

        while (clone.size != 1) {
            clone.removeFirst()
            clone.removeLast()
        }

        return clone.first().toInt()
    }


    override fun answerTwo(): String {
        //split the list
        val verifyList = readInput.filter { it.length == 5 }
        val rows = readInput.filter { it.length > 5 }

        val invalidRows = isInvalidCheck(rows, verifyList)


        val fixedRows = invalidRows
            .map { invalidRow ->

                val numbersToCheck = invalidRow.split(",").toMutableList()

                while (true) {
                    verifyList.forEach {
                        var (first, second) = it.split("|")

                        var a = numbersToCheck.indexOf(first)
                        var b = numbersToCheck.indexOf(second)

                        if (b == -1) {
                            b = Int.MAX_VALUE
                        }

                        if (a == -1) {
                            b = Int.MAX_VALUE
                        }

                        if (a > b) {
                            var tempA = numbersToCheck[a]
                            var tempB = numbersToCheck[b]

                            numbersToCheck[a] = tempB
                            numbersToCheck[b] = tempA
                        }
                    }
                    if (isInvalidCheck(listOf(numbersToCheck.joinToString(",")), verifyList).size == 0) {
                        break
                    }
                }


                return@map numbersToCheck
            }


        // all rows are fixed now just find middle digit and add them
        return fixedRows.map { findMiddleDigit(it) }.sum().toString()
    }

    private fun isInvalidCheck(
        rows: List<String>,
        verifyList: List<String>
    ) = rows.map { row ->
        val numbersToCheck = row.split(",")
        verifyList.forEach {
            var (first, second) = it.split("|")

            var a = numbersToCheck.indexOf(first)
            var b = numbersToCheck.indexOf(second)

            if (b == -1) {
                b = Int.MAX_VALUE
            }

            if (a == -1) {
                b = Int.MAX_VALUE
            }

            //exit early because its invalid
            if (a > b) {
                return@map row
            }
        }
        return@map ""
    }.filter { it.isNotBlank() }
}
