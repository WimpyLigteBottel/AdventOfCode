package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class Day9Test {
    val dayNumber = 8

    private val example = ReadUtil.readInputAsList(dayNumber, exampleInput = true)
    private val actual = ReadUtil.readInputAsList(dayNumber, exampleInput = false)

    var dayExample = Day9(example)
    var day = Day9(actual)


    @Test
    fun stepRight() {
        dayExample = Day9(example, 4)
        var input = """
            ..TH
            ....
            ....
            ....
        """.trimIndent()
        dayExample.executeStep("U 1", it.dayExample.moveAmount())
        dayExample.executeStep("U 1", it.dayExample.moveAmount())
        dayExample.executeStep("U 1", it.dayExample.moveAmount())
        assertEquals(input, dayExample.printMap())
    }

    @Test
    fun stepDownRight() {
        dayExample = Day9(example, 4)
        dayExample.executeStep("U 1", it.dayExample.moveAmount())
        dayExample.executeStep("R 1", it.dayExample.moveAmount())
        dayExample.executeStep("U 1", it.dayExample.moveAmount())
        dayExample.executeStep("R 1", it.dayExample.moveAmount())
        dayExample.executeStep("U 1", it.dayExample.moveAmount())
        dayExample.executeStep("R 1", it.dayExample.moveAmount())
        var input = """
            ....
            ....
            ..T.
            ...H
        """.trimIndent()
        assertEquals(input, dayExample.printMap())
    }

    @Test
    fun step2() {
        dayExample = Day9(example, 4)
        dayExample.executeStep("U 1", it.dayExample.moveAmount())
        dayExample.executeStep("R 1", it.dayExample.moveAmount())
        dayExample.executeStep("R 1", it.dayExample.moveAmount())
        var input = """
            ....
            .T..
            .H..
            ....
        """.trimIndent()
        assertEquals(input, dayExample.printMap())
    }

    @Test
    fun step3() {
        dayExample = Day9(example, 4)
        dayExample.executeStep("U 1", it.dayExample.moveAmount())
        dayExample.executeStep("R 1", it.dayExample.moveAmount())
        dayExample.executeStep("R 1", it.dayExample.moveAmount())
        dayExample.executeStep("R 1", it.dayExample.moveAmount())
        var input = """
            ....
            ....
            .T..
            .H..
        """.trimIndent()
        assertEquals(input, dayExample.printMap())
    }

    @Test
    fun step4() {
        dayExample = Day9(example, 4)
        dayExample.executeStep("U 1", it.dayExample.moveAmount())
        dayExample.executeStep("R 1", it.dayExample.moveAmount())
        dayExample.executeStep("R 1", it.dayExample.moveAmount())
        dayExample.executeStep("R 1", it.dayExample.moveAmount())
        dayExample.executeStep("D 1", it.dayExample.moveAmount())
        var input = """
            ....
            ....
            .T..
            H...
        """.trimIndent()
        assertEquals(input, dayExample.printMap())
    }

    @Test
    fun step5() {
        dayExample = Day9(example, 4)
        dayExample.executeStep("U 1", it.dayExample.moveAmount())
        dayExample.executeStep("R 1", it.dayExample.moveAmount())
        dayExample.executeStep("R 1", it.dayExample.moveAmount())
        dayExample.executeStep("R 1", it.dayExample.moveAmount())
        dayExample.executeStep("D 1", it.dayExample.moveAmount())
        dayExample.executeStep("L 1", it.dayExample.moveAmount())
        var input = """
            ....
            ....
            HT..
            ....
        """.trimIndent()
        assertEquals(input, dayExample.printMap())
    }

    @Test
    fun step6() {
        dayExample = Day9(example, 4)
        dayExample.executeStep("U 1", it.dayExample.moveAmount())
        dayExample.executeStep("R 1", it.dayExample.moveAmount())
        dayExample.executeStep("R 1", it.dayExample.moveAmount())
        dayExample.executeStep("R 1", it.dayExample.moveAmount())
        dayExample.executeStep("D 1", it.dayExample.moveAmount())
        dayExample.executeStep("L 1", it.dayExample.moveAmount())
        dayExample.executeStep("L 1", it.dayExample.moveAmount())
        dayExample.executeStep("L 1", it.dayExample.moveAmount())
        var input = """
            H...
            T...
            ....
            ....
        """.trimIndent()
        assertEquals(input, dayExample.printMap())
    }



//    @Test
//    fun answerOne() {
//        assertEquals(21, dayExample.answerOne())
//        assertEquals(1776, day.answerOne())
//    }
//
//    @Test
//    fun answerTwo() {
//
//        assertEquals(8, dayExample.answerTwo())
//        assertEquals(8, day.answerTwo())
//    }
}