package nel.marco


class Day1(useExample: Boolean = false, useMac: Boolean = true) :
    Day(dayNumber = 1, useExample = useExample, macBook = useMac) {

    override fun answerOne(): String {
        var list = setupDial()

        var counter = 0

        readInput.forEach {
            val direction = it.first()
            val turns = it.substring(1).toInt()

            when(direction) {
                'L' -> {
                    repeat (turns) {
                        list.addFirst(list.removeLast())
                    }
                }
                'R' -> {
                    repeat (turns) {
                        list.add(list.removeFirst())
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

            when(direction) {
                'L' -> {
                    repeat (turns) {
                        if (list.first() == 0) {
                            counter++
                        }
                        list.addFirst(list.removeLast())
                    }
                }
                'R' -> {
                    repeat (turns) {
                        if (list.first() == 0) {
                            counter++
                        }
                        list.addLast(list.removeFirst())
                    }
                }
            }
        }

        return "$counter"
    }

    private fun setupDial(startingDigit: Int = 50): MutableList<Int> {
        var list = ArrayList<Int>(100)

        for (i in startingDigit..99) {
            list.add(i)
        }
        for (i in 0..<startingDigit) {
            list.add(i)
        }

        return list
    }
}
