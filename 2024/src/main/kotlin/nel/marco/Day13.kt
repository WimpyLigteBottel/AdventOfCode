package nel.marco


class Day13(useExample: Boolean = false, useMac: Boolean = false) :
    Day(dayNumber = 13, useExample = useExample, macBook = useMac) {


    override fun answerTwo(): String {
        readInput.add("")

        var total = 0L

        while (readInput.isNotEmpty()) {
            var buttonA = readInput.removeFirst().setupButton()
            var buttonB = readInput.removeFirst().setupButton()
            var prizeTarget = readInput.removeFirst().setupPrizeTarget(true)
            readInput.removeFirst()
            val rationPress = isPossible(prizeTarget, buttonA, buttonB)
            rationPress?.let {
                total += rationPress.tokenCost()
            }
        }


        return total.toString()
    }

    override fun answerOne(): String {
        readInput.add("")

        var total = 0L

        while (readInput.isNotEmpty()) {
            var buttonA = readInput.removeFirst().setupButton()
            var buttonB = readInput.removeFirst().setupButton()
            var prizeTarget = readInput.removeFirst().setupPrizeTarget()
            readInput.removeFirst()
            val rationPress = isPossible(prizeTarget, buttonA, buttonB)
            rationPress?.let {
                total += rationPress.tokenCost()
                println("${rationPress.tokenCost()} == ${rationPress.first} * 3 + ${rationPress.second} * 1")
            }
        }


        return total.toString()
    }

    fun Pair<Long, Long>.tokenCost() = this.first * 3 + this.second * 1


    fun findTarget(target: Long, a: Long, b: Long): MutableList<Pair<Long, Long>> {
        var possible = mutableListOf<Pair<Long, Long>>()


        for (counterA in 0..100L) {
            for (counterB in 0..100L) {
                val aTimes = a * counterA
                val bTimes = b * counterB

                if (aTimes + bTimes > target) {
                    break
                } else if (aTimes + bTimes == target) {
                    possible.add(counterA to counterB)
                }
            }
        }

        return possible
    }

    fun isPossible(target: Pair<Long, Long>, a: Pair<Long, Long>, b: Pair<Long, Long>): Pair<Long, Long>? {

        var xIsPossible = findTarget(target.first, a.first, b.first)
        var yIsPossible = findTarget(target.second, a.second, b.second)

        var overlap = xIsPossible.intersect(yIsPossible.toSet())


        return overlap.singleOrNull()
    }


    fun String.setupButton(): Pair<Long, Long> {

        var x = this.substringAfter("X+").substringBefore(",").toLong()
        var y = this.substringAfter("Y+").substringAfterLast(",").toLong()

        return x to y
    }

    fun String.setupPrizeTarget(part2: Boolean = false): Pair<Long, Long> {

        var x = this.substringAfter("X=").substringBefore(",").toLong()
        var y = this.substringAfter("Y=").substringAfterLast(",").toLong()

        if (part2)
            return 10000000000000 + x to 10000000000000 + y
        else
            return x to y
    }


}
