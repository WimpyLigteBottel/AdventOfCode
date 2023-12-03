package nel.marco.v1

import nel.marco.MarcoUtil
import nel.marco.Point

fun main(args: Array<String>) {
    val readAllLines = MarcoUtil.readInput(3, testInput = true).toMutableList()
    val day = Day3(readAllLines)
    executeTimes("ANSWER 1") {
        day.answerOne()
    }
    executeTimes("ANSWER 2") {
        day.answerTwo()
    }
}


class Day3(var readInput: List<String>) {
    fun answerOne(): String {

        var map = Map().buildMap(readInput)

        return map
            .findAllSymbolLocations()
            .map { map.findNumbersSoundingPoint(it) }
            .flatMap { it.toList() }
            .sumOf { it }
            .toString()
    }

    fun answerTwo(): String {

        var map = Map().buildMap(readInput)

        return map
            .findAllSymbolLocations()
            .map { map.findNumbersSoundingPoint(it) } // find all numbers connect to symbol
            .filter { it.size == 2 } // only allow gear rations (2 numbers connected to 1 symbol only)
            .map { it[0] * it[1] } // calculate the new formula
            .sumOf { it } // Add them together
            .toString()
    }

}


data class Map(
    var transformed: Array<Array<Point>> = emptyArray(),
) {

    fun buildMap(row: List<String>): Map {
        var map = row.map {
            it.split("").toTypedArray() // 467..114..
        }.toTypedArray()

        val columns = Array(map[0].size) { Point() }
        transformed = Array(map.size) { columns.clone() }


        map.forEachIndexed { y, row ->
            map[y].forEachIndexed { x, character ->
                transformed[y][x] = Point(
                    x = x,
                    y = y,
                    value = character
                )
            }
        }

        return this
    }

    fun findAllSymbolLocations(): MutableList<Point> {
        var listOfLocations = mutableListOf<Point>()
        transformed.forEachIndexed { y, row ->
            transformed[y].forEachIndexed { x, point ->
                if ("[^0-9\\.\\n]".toRegex().matches(point.value)) {
                    listOfLocations.add(point)
                }
            }
        }
        return listOfLocations
    }

    fun findNumbersSoundingPoint(it: Point): MutableList<Int> {
        val down = it.clone().up().getPointFromMap()
        val downLeft = it.clone().left().up().getPointFromMap()
        val downRight = it.clone().right().up().getPointFromMap()

        val left = it.clone().left().getPointFromMap()
        val right = it.clone().right().getPointFromMap()

        val up = it.clone().down().getPointFromMap()
        val upRight = it.clone().right().down().getPointFromMap()
        val upLeft = it.clone().left().down().getPointFromMap()

        val tempNumbers = mutableListOf<Int>()

        // Gets the top row correct digit
        if (upLeft.isDigit()) {
            tempNumbers.add(findFullNumber(upLeft.getPointFromMap()))
        }
        if (up.isDigit() && !upLeft.isDigit()) {
            tempNumbers.add(findFullNumber(up.getPointFromMap()))
        }
        if (upRight.isDigit() && !up.isDigit()) {
            tempNumbers.add(findFullNumber(upRight.getPointFromMap()))
        }

        // same row digit
        if (left.isDigit()) {
            tempNumbers.add(findFullNumber(left.getPointFromMap()))
        }
        if (right.isDigit()) {
            tempNumbers.add(findFullNumber(right.getPointFromMap()))
        }

        // Gets the bottom row correct digit
        if (downLeft.isDigit()) {
            tempNumbers.add(findFullNumber(downLeft.getPointFromMap()))
        }
        if (down.isDigit() && !downLeft.isDigit()) {
            tempNumbers.add(findFullNumber(down.getPointFromMap()))
        }
        if (downRight.isDigit() && !down.isDigit()) {
            tempNumbers.add(findFullNumber(downRight.getPointFromMap()))
        }
        return tempNumbers
    }

    private fun findFullNumber(startPoint: Point): Int {
        var left = startPoint.clone().left().getPointFromMap()

        while (left.isDigit()) {
            left = left.clone().left().getPointFromMap()
        }

        val lastValidDigit = left.clone().right().getPointFromMap()

        return readFromCurrentToRight(lastValidDigit)
    }

    private fun readFromCurrentToRight(point: Point): Int {
        var listOfNumber = mutableListOf<String>()
        listOfNumber.add(point.value)

        var right = point.clone().right().getPointFromMap();
        while (right.isDigit()) {
            listOfNumber.add(right.value)
            right = right.right().getPointFromMap()
        }

        return listOfNumber.joinToString("").toInt()
    }

    fun Point.getPointFromMap(): Point {
        return transformed[this.y][this.x]
    }


}
