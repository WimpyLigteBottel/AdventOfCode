import java.nio.file.Files
import java.nio.file.Path

/*
--- Day 3: Perfectly Spherical Houses in a Vacuum ---
Santa is delivering presents to an infinite two-dimensional grid of houses.

He begins by delivering a present to the house at his starting location, and then an elf at the North Pole calls him via radio and tells him where to move next.
Moves are always exactly one house to the north (^), south (v), east (>), or west (<).
After each move, he delivers another present to the house at his new location.

However, the elf back at the north pole has had a little too much eggnog, and so his directions are a little off,
and Santa ends up visiting some houses more than once. How many houses receive at least one present?

For example:

> delivers presents to 2 houses: one at the starting location, and one to the east.
^>v< delivers presents to 4 houses in a square, including twice to the house at his starting/ending location.
^v^v^v^v^v delivers a bunch of presents to some very lucky children at only 2 houses.



 */
class Day3 {


    fun part2(readAllLines: List<String>): Int {

        var xR = 0;
        var yR = 0;

        var xS = 0;
        var yS = 0;


        var houses = hashMapOf<Pair<Int, Int>, Int>()


        var robotPair = Pair(xR, yR)
        var santaPair = Pair(xS, yS)
        houses.put(robotPair, 1)
        houses.put(santaPair, 1)

        var isRobotTurn = true;

        readAllLines[0].split("")
            .filter { it.isNotBlank() }
            .map { it.lowercase() }
            .forEach {
                isRobotTurn = !isRobotTurn
                if (isRobotTurn) {
                    if (it == ">")
                        xR++
                    if (it == "<")
                        xR--
                    if (it == "^")
                        yR++
                    if (it == "v")
                        yR--

                    robotPair = Pair(xR, yR)
                    houses[robotPair] = houses.getOrDefault(robotPair, 0) + 1
                } else {
                    if (it == ">")
                        xS++
                    if (it == "<")
                        xS--
                    if (it == "^")
                        yS++
                    if (it == "v")
                        yS--


                    santaPair = Pair(xS, yS)
                    houses[santaPair] = houses.getOrDefault(santaPair, 0) + 1
                }

            }




        return houses.size

    }

    fun part1(readAllLines: List<String>): Int {

        var x = 0;
        var y = 0;
        var points = hashSetOf<Pair<Int, Int>>()
        points.add(Pair(x, y))
        readAllLines.get(0).split("").forEach {
            if (it == ">")
                x++
            if (it == "<")
                x--
            if (it == "^")
                y++
            if (it == "v")
                y--

            points.add(Pair(x, y))
        }

        return points.size

    }

}

fun main(args: Array<String>) {
    var readAllLines = Files.readAllLines(Path.of("D:\\coding repo\\AdventOfCode\\2015\\src\\main\\resources\\day3"))
//    readAllLines = Files.readAllLines(Path.of("D:\\coding repo\\AdventOfCode\\2015\\src\\main\\resources\\day3Test"))
    val day = Day3()
    println("part1 = ${day.part1(readAllLines)}")
    println("part2 = ${day.part2(readAllLines)}")
}






