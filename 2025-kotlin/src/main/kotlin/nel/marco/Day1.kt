package nel.marco

class Day1(
    useExample: Boolean = false,
    useMac: Boolean = true,
) : Day(dayNumber = 1, useExample = useExample, macBook = useMac) {
    override fun answerOne(): String {
        var list = setupDial()

        var counter = 0

        readInput.forEach {
            val direction = it.first()
            val turns = it.substring(1).toInt()

            when (direction) {
                'L' -> {
                    repeat(turns) {
                        turnLeft(list)
                    }
                }

                'R' -> {
                    repeat(turns) {
                        turnRight(list)
                    }
                }
            }
            if (list.first() == 0) {
                counter++
            }
        }

        return "$counter"
    }

    override fun answerTwo(): String {
        var list = setupDial()
        var counter = 0

        readInput.forEach {
            val direction = it.first()
            val turns = it.substring(1).toInt()

            when (direction) {
                'L' -> {
                    repeat(turns) {
                        if (list.first() == 0) {
                            counter++
                        }
                        turnLeft(list)
                    }
                }

                'R' -> {
                    repeat(turns) {
                        if (list.first() == 0) {
                            counter++
                        }
                        turnRight(list)
                    }
                }
            }
        }

        return "$counter"
    }

    private fun turnRight(list: MutableList<Int>) {
        list.addLast(list.removeFirst())
    }

    private fun turnLeft(list: MutableList<Int>) {
        list.addFirst(list.removeLast())
    }

    private fun setupDial(startingDigit: Int = 50): ArrayDeque<Int> {
        val list = ArrayDeque<Int>(100)

        for (i in startingDigit..99) {
            list.add(i)
        }
        for (i in 0..<startingDigit) {
            list.add(i)
        }

        return list
    }
}
