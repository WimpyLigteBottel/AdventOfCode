package nel.marco


class Day1(useExample: Boolean = false, useMac: Boolean = true) :
    Day(dayNumber = 1, useExample = useExample, macBook = useMac) {

    override fun answerOne(): String {
        var list = mutableListOf<Int>()

        for (i in 0..99) {
            list.add(i)
        }

        var pointer = 50

        repeat(pointer){
            list.add(list.removeFirst())
        }

        var counter = 0

        readInput.forEach {
            if(it.startsWith("L")){
                var turns = it.substring(1).toInt()

                while(turns > 0){
                    turns--;
                    list.add(0,list.removeLast())
                }
            }

            if(it.startsWith("R")){
                var turns = it.substring(1).toInt()

                while(turns > 0){
                    turns--;
                    list.add(list.removeFirst())
                }
            }
            if(list.get(0) == 0){
                counter++

            }
        }


        return "$counter"
    }

    override fun answerTwo(): String {
        var list = mutableListOf<Int>()

        for (i in 0..99) {
            list.add(i)
        }

        var pointer = 50

        repeat(pointer){
            list.add(list.removeFirst())
        }

        var counter = 0

        readInput.forEach {
            if(it.startsWith("L")){
                var turns = it.substring(1).toInt()

                while(turns > 0){
                    if(list.get(0) == 0){
                        counter++
                    }
                    turns--;
                    list.add(0,list.removeLast())
                }
            }

            if(it.startsWith("R")){
                var turns = it.substring(1).toInt()

                while(turns > 0){
                    if(list.get(0) == 0){
                        counter++
                    }
                    turns--;
                    list.add(list.removeFirst())
                }
            }

        }


        return "$counter"
    }
}
