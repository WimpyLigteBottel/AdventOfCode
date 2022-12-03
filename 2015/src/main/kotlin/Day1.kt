import java.nio.file.Files
import java.nio.file.Path

class Part1 {

    fun main(args: Array<String>) {
        var input = MarcoUtil.readInput(1).get(0);

        part1(input)
        part2(input)


    }

    fun part1(input: String) {
        var upfloor = input.filter { it.toString() == "(" }.length
        var downfloor = input.filter { it.toString() == ")" }.length

        println("part 1 == ${upfloor - downfloor}")
    }

    fun part2(input: String) {
        var counter = 0

        for( x in 1 until input.length){
            var substring = input.subSequence(0,x)
            var upfloor = substring.filter { it.toString() == "(" }.length
            var downfloor = substring.filter { it.toString() == ")" }.length

            if(upfloor - downfloor == -1){
                counter = x
                break
            }
        }


        println("part 2 == $counter")
    }
}






