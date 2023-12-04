package nel.marco.v1

import nel.marco.Point


class Day3( readInput: List<String>): Day(readInput) {
    override fun answerOne(): String {

        var map = Map().buildMap(readInput)

        return map
            .findAllSymbolLocations()
            .map { map.findNumbersSoundingPoint(it) }
            .flatMap { it.toList() }
            .sumOf { it }
            .toString()
    }

    override fun answerTwo(): String {

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
        val listOfLocations = mutableListOf<Point>()
        transformed.forEachIndexed { y, row ->
            transformed[y].forEachIndexed { x, point ->
                if ("[^0-9\\.\\n]".toRegex().matches(point.value)) {
                    listOfLocations.add(point)
                }
            }
        }
        return listOfLocations
    }

    fun findNumbersSoundingPoint(center: Point): MutableList<Int> {
        val down = center.clone().up().getPointFromMap()
        val downLeft = center.clone().left().up().getPointFromMap()
        val downRight = center.clone().right().up().getPointFromMap()

        val left = center.clone().left().getPointFromMap()
        val right = center.clone().right().getPointFromMap()

        val up = center.clone().down().getPointFromMap()
        val upRight = center.clone().right().down().getPointFromMap()
        val upLeft = center.clone().left().down().getPointFromMap()

        val tempNumbers = mutableListOf<Int>()

        // Gets the top row correct digit
        if (upLeft.isDigit()) {
            tempNumbers.add(findFullNumber(upLeft))
        }
        if (up.isDigit() && !upLeft.isDigit()) {
            tempNumbers.add(findFullNumber(up))
        }
        if (upRight.isDigit() && !up.isDigit()) {
            tempNumbers.add(findFullNumber(upRight))
        }

        // same row digit
        if (left.isDigit()) {
            tempNumbers.add(findFullNumber(left))
        }
        if (right.isDigit()) {
            tempNumbers.add(findFullNumber(right))
        }

        // Gets the bottom row correct digit
        if (downLeft.isDigit()) {
            tempNumbers.add(findFullNumber(downLeft))
        }
        if (down.isDigit() && !downLeft.isDigit()) {
            tempNumbers.add(findFullNumber(down))
        }
        if (downRight.isDigit() && !down.isDigit()) {
            tempNumbers.add(findFullNumber(downRight))
        }
        return tempNumbers
    }

    private fun findFullNumber(startPoint: Point): Int {
        var left = startPoint.clone().left().getPointFromMap()

        while (left.isDigit()) {
            left = left.clone().left().getPointFromMap()
        }

        val lastValidDigit = left.right().getPointFromMap()

        return readFromCurrentToRight(lastValidDigit)
    }

    private fun readFromCurrentToRight(point: Point): Int {
        val listOfNumber = mutableListOf(point.value)

        var right = point.right().getPointFromMap();
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
