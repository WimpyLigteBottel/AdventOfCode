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

        val symbolLocations = map.findAllSymbolLocations()

        val connectedNumbersToSymbols = map.findNumbersConnected(symbolLocations)

        val result = connectedNumbersToSymbols.sumOf { it }


        return "$result"
    }

    fun answerTwo(): String {

        var map = Map().buildMap(readInput)

        val symbolLocations = map.findAllSymbolLocations()

        val connectedNumbersToSymbols = map.findNumbersConnectedVersion2(symbolLocations)

        val result = connectedNumbersToSymbols.sumOf { it }

        return "$result"
    }

}


data class Map(
    var map: Array<Array<String>> = emptyArray(),
    var transformed: Array<Array<Point>> = emptyArray(),
) {

    fun buildMap(row: List<String>): Map {
        map = row.map {
            it.split("").toTypedArray() // 467..114..
        }.toTypedArray()

        val columns = Array(map[0].size, { j -> Point(0, 0, "") }) // create columns
        transformed = Array(map.size, { i -> columns.clone() }) // create rows


        updateTransformMap()

        return this
    }


    fun updateTransformMap() {
        map.forEachIndexed { y, row ->
            map[y].forEachIndexed { x, character ->
                transformed[y][x] = Point(
                    x = x, y = y, value = character
                )
            }
        }
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

    fun findNumbersConnected(listOfLocations: List<Point>): MutableList<Int> {

        var numbers = mutableListOf<Int>()

        listOfLocations.forEach {
            var down = it.clone().up().getPointFromMap()
            var downLeft = it.clone().left().up().getPointFromMap()
            var downRight = it.clone().right().up().getPointFromMap()

            var left = it.clone().left().getPointFromMap()
            var right = it.clone().right().getPointFromMap()

            var up = it.clone().down().getPointFromMap()
            var upRight = it.clone().right().down().getPointFromMap()
            var upLeft = it.clone().left().down().getPointFromMap()

            var tempNumbers = mutableListOf<Int>()

            // Gets the top row correct digit
            if (upLeft.isDigit() && !up.isDigit()) {
                tempNumbers.add(findFullNumber(upLeft.getPointFromMap()))
            }
            if (!upLeft.isDigit() && up.isDigit()) {
                tempNumbers.add(findFullNumber(up.getPointFromMap()))
            }
            if (!up.isDigit() && upRight.isDigit()) {
                tempNumbers.add(findFullNumber(upRight.getPointFromMap()))
            }
            if (up.isDigit() && upLeft.isDigit()) {
                tempNumbers.add(findFullNumber(upLeft.getPointFromMap()))
            }

            // same row digit
            if (left.isDigit()) {
                tempNumbers.add(findFullNumber(left.getPointFromMap()))
            }
            if (right.isDigit()) {
                tempNumbers.add(findFullNumber(right.getPointFromMap()))
            }

            // Gets the bottom row correct digit
            if (downLeft.isDigit() && down.isDigit()) {
                tempNumbers.add(findFullNumber(down.getPointFromMap()))
            }
            if (downLeft.isDigit() && !down.isDigit()) {
                tempNumbers.add(findFullNumber(downLeft.getPointFromMap()))
            }
            if (!downLeft.isDigit() && down.isDigit()) {
                tempNumbers.add(findFullNumber(down.getPointFromMap()))
            }
            if (!down.isDigit() && downRight.isDigit()) {
                tempNumbers.add(findFullNumber(downRight.getPointFromMap()))
            }

            numbers.addAll(tempNumbers)
        }

        return numbers
    }

    fun findNumbersConnectedVersion2(listOfLocations: List<Point>): MutableList<Int> {

        var numbers = mutableListOf<Int>()

        listOfLocations.forEach {
            var down = it.clone().up().getPointFromMap()
            var downLeft = it.clone().left().up().getPointFromMap()
            var downRight = it.clone().right().up().getPointFromMap()

            var left = it.clone().left().getPointFromMap()
            var right = it.clone().right().getPointFromMap()

            var up = it.clone().down().getPointFromMap()
            var upRight = it.clone().right().down().getPointFromMap()
            var upLeft = it.clone().left().down().getPointFromMap()

            var tempNumbers = mutableListOf<Int>()

            // Gets the top row correct digit
            if (upLeft.isDigit() && !up.isDigit()) {
                tempNumbers.add(findFullNumber(upLeft.getPointFromMap()))
            }
            if (!upLeft.isDigit() && up.isDigit()) {
                tempNumbers.add(findFullNumber(up.getPointFromMap()))
            }
            if (!up.isDigit() && upRight.isDigit()) {
                tempNumbers.add(findFullNumber(upRight.getPointFromMap()))
            }
            if (up.isDigit() && upLeft.isDigit()) {
                tempNumbers.add(findFullNumber(upLeft.getPointFromMap()))
            }

            // same row digit
            if (left.isDigit()) {
                tempNumbers.add(findFullNumber(left.getPointFromMap()))
            }
            if (right.isDigit()) {
                tempNumbers.add(findFullNumber(right.getPointFromMap()))
            }

            // Gets the bottom row correct digit
            if (downLeft.isDigit() && down.isDigit()) {
                tempNumbers.add(findFullNumber(down.getPointFromMap()))
            }
            if (downLeft.isDigit() && !down.isDigit()) {
                tempNumbers.add(findFullNumber(downLeft.getPointFromMap()))
            }
            if (!downLeft.isDigit() && down.isDigit()) {
                tempNumbers.add(findFullNumber(down.getPointFromMap()))
            }
            if (!down.isDigit() && downRight.isDigit()) {
                tempNumbers.add(findFullNumber(downRight.getPointFromMap()))
            }

            if (tempNumbers.size == 2) {
                numbers.add(tempNumbers[0] * tempNumbers[1])
            }
        }

        return numbers
    }

    fun findFullNumber(startPoint: Point): Int {
        var left = startPoint.clone().left().getPointFromMap()

        while (left.isDigit()) {
            left = left.clone().left().getPointFromMap()
        }

        val lastValidDigit = left.clone().right().getPointFromMap()


        return readFromCurrentToRight(lastValidDigit)
    }

    fun readFromCurrentToRight(point: Point): Int {
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
