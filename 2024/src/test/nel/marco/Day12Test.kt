package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day12Test {

    val example = Day12(useExample = true, useMac = false)
    val day = Day12(useExample = false, useMac = false)

    @Test
    fun `cantGoRightLogic && but something right exist && up to below`() {
        val a =         Square(x = 0, y = 0, value = "X")
        val b =         Square(x = 0, y = 1, value = "X")
        val rightB =   Square(x = 1, y = 1, value = "X")
        val c =         Square(x = 0, y = 2, value = "X")


        day.cantGoRightLogic(a, listOfValues = mutableListOf(c, b, rightB))

        assertEquals(1, a.sidesCount.size)
        assertEquals(1, a.sidesDontCount.size)
        assertEquals(0, b.sidesCount.size)
        assertEquals(0, b.sidesDontCount.size)
        assertEquals(0, c.sidesCount.size)
        assertEquals(0, c.sidesDontCount.size)

        val a1 = Square(x = 0, y = 0, value = "X")
        val b1 = Square(x = 0, y = 1, value = "X")
        val c1 = Square(x = 0, y = 2, value = "X")
        val rightC1 = Square(x = 1, y = 2, value = "X")

        day.cantGoRightLogic(a1, listOfValues = mutableListOf(c1, b1, rightC1))

        assertEquals(1, a1.sidesCount.size)
        assertEquals(1, a1.sidesDontCount.size)
        assertEquals(0, b1.sidesCount.size)
        assertEquals(1, b1.sidesDontCount.size)
        assertEquals(0, c1.sidesCount.size)
        assertEquals(0, c1.sidesDontCount.size)


        val a2 = Square(x = 0, y = 0, value = "X")
        val rightA2 = Square(x = 1, y = 0, value = "X")
        val b2 = Square(x = 0, y = 1, value = "X")
        val c2 = Square(x = 0, y = 2, value = "X")

        day.cantGoRightLogic(a2, listOfValues = mutableListOf(c2, b2, rightA2))

        assertEquals(0, a2.sidesCount.size)
        assertEquals(0, a2.sidesDontCount.size)
        assertEquals(0, b2.sidesCount.size)
        assertEquals(0, b2.sidesDontCount.size)
        assertEquals(0, c2.sidesCount.size)
        assertEquals(0, c2.sidesDontCount.size)
    }

    @Test
    fun `cantGoLeftLogic && but something left exist && up to below`() {
        val a =         Square(x = 0, y = 0, value = "X")
        val b =         Square(x = 0, y = 1, value = "X")
        val leftOfB =   Square(x = -1, y = 1, value = "X")
        val c =         Square(x = 0, y = 2, value = "X")


        day.cantGoLeftLogic(a, listOfValues = mutableListOf(c, b, leftOfB))

        assertEquals(1, a.sidesCount.size)
        assertEquals(1, a.sidesDontCount.size)
        assertEquals(0, b.sidesCount.size)
        assertEquals(0, b.sidesDontCount.size)
        assertEquals(0, c.sidesCount.size)
        assertEquals(0, c.sidesDontCount.size)

        val a1 = Square(x = 0, y = 0, value = "X")
        val b1 = Square(x = 0, y = 1, value = "X")
        val c1 = Square(x = 0, y = 2, value = "X")
        val leftOfC1 = Square(x = -1, y = 2, value = "X")

        day.cantGoLeftLogic(a1, listOfValues = mutableListOf(c1, b1, leftOfC1))

        assertEquals(1, a1.sidesCount.size)
        assertEquals(1, a1.sidesDontCount.size)
        assertEquals(0, b1.sidesCount.size)
        assertEquals(1, b1.sidesDontCount.size)
        assertEquals(0, c1.sidesCount.size)
        assertEquals(0, c1.sidesDontCount.size)


        val a2 = Square(x = 0, y = 0, value = "X")
        val leftOfA2 = Square(x = -1, y = 0, value = "X")
        val b2 = Square(x = 0, y = 1, value = "X")
        val c2 = Square(x = 0, y = 2, value = "X")

        day.cantGoLeftLogic(a2, listOfValues = mutableListOf(c2, b2, leftOfA2))

        assertEquals(0, a2.sidesCount.size)
        assertEquals(0, a2.sidesDontCount.size)
        assertEquals(0, b2.sidesCount.size)
        assertEquals(0, b2.sidesDontCount.size)
        assertEquals(0, c2.sidesCount.size)
        assertEquals(0, c2.sidesDontCount.size)


    }

    @Test
    fun `cantGoLeftLogic && but something left exist && below to up`() {
        val a =         Square(x = 0, y = 0, value = "X")
        val b =         Square(x = 0, y = 1, value = "X")
        val leftOfB =   Square(x = -1, y = 1, value = "X")
        val c =         Square(x = 0, y = 2, value = "X")


        day.cantGoLeftLogic(c, listOfValues = mutableListOf(a, b, leftOfB))

        assertEquals(0, a.sidesCount.size)
        assertEquals(0, a.sidesDontCount.size)
        assertEquals(0, b.sidesCount.size)
        assertEquals(0, b.sidesDontCount.size)
        assertEquals(1, c.sidesCount.size)
        assertEquals(1, c.sidesDontCount.size)

        val a1 = Square(x = 0, y = 0, value = "X")
        val b1 = Square(x = 0, y = 1, value = "X")
        val c1 = Square(x = 0, y = 2, value = "X")
        val leftOfC1 = Square(x = -1, y = 2, value = "X")

        day.cantGoLeftLogic(c1, listOfValues = mutableListOf(a1, b1, leftOfC1))

        assertEquals(0, a1.sidesCount.size)
        assertEquals(0, a1.sidesDontCount.size)
        assertEquals(0, b1.sidesCount.size)
        assertEquals(0, b1.sidesDontCount.size)
        assertEquals(0, c1.sidesCount.size)
        assertEquals(0, c1.sidesDontCount.size)


        val a2 = Square(x = 0, y = 0, value = "X")
        val leftOfA2 = Square(x = -1, y = 0, value = "X")
        val b2 = Square(x = 0, y = 1, value = "X")
        val c2 = Square(x = 0, y = 2, value = "X")

        day.cantGoLeftLogic(c2, listOfValues = mutableListOf(a2, b2, leftOfA2))

        assertEquals(0, a2.sidesCount.size)
        assertEquals(0, a2.sidesDontCount.size)
        assertEquals(0, b2.sidesCount.size)
        assertEquals(1, b2.sidesDontCount.size)
        assertEquals(1, c2.sidesCount.size)
        assertEquals(1, c2.sidesDontCount.size)


    }

    @Test
    fun `cant && but something left exist && below to up`() {
        val a =         Square(x = 0, y = 0, value = "X")
        val b =         Square(x = 0, y = 1, value = "X")
        val rightB =   Square(x = 1, y = 1, value = "X")
        val c =         Square(x = 0, y = 2, value = "X")


        day.cantGoRightLogic(c, listOfValues = mutableListOf(a, b, rightB))

        assertEquals(0, a.sidesCount.size)
        assertEquals(0, a.sidesDontCount.size)
        assertEquals(0, b.sidesCount.size)
        assertEquals(0, b.sidesDontCount.size)
        assertEquals(1, c.sidesCount.size)
        assertEquals(1, c.sidesDontCount.size)

        val a1 = Square(x = 0, y = 0, value = "X")
        val b1 = Square(x = 0, y = 1, value = "X")
        val c1 = Square(x = 0, y = 2, value = "X")
        val rightC1 = Square(x = 1, y = 2, value = "X")

        day.cantGoRightLogic(c1, listOfValues = mutableListOf(a1, b1, rightC1))

        assertEquals(0, a1.sidesCount.size)
        assertEquals(0, a1.sidesDontCount.size)
        assertEquals(0, b1.sidesCount.size)
        assertEquals(0, b1.sidesDontCount.size)
        assertEquals(0, c1.sidesCount.size)
        assertEquals(0, c1.sidesDontCount.size)


        val a2 = Square(x = 0, y = 0, value = "X")
        val rightA2 = Square(x = 1, y = 0, value = "X")
        val b2 = Square(x = 0, y = 1, value = "X")
        val c2 = Square(x = 0, y = 2, value = "X")

        day.cantGoRightLogic(c2, listOfValues = mutableListOf(a2, b2, rightA2))

        assertEquals(0, a2.sidesCount.size)
        assertEquals(0, a2.sidesDontCount.size)
        assertEquals(0, b2.sidesCount.size)
        assertEquals(1, b2.sidesDontCount.size)
        assertEquals(1, c2.sidesCount.size)
        assertEquals(1, c2.sidesDontCount.size)
    }

    @Test
    fun `cantGoDownLogic && but something below exist && left to right`() {
        val a = Square(x = 0, y = 0, value = "X")
        val b = Square(x = 1, y = 0, value = "X")
        val below = Square(x = 1, y = -1, value = "X")
        val c = Square(x = 2, y = 0, value = "X")


        day.cantGoDownLogic(a, listOfValues = mutableListOf(c, b, below))

        assertEquals(1, a.sidesCount.size)
        assertEquals(1, a.sidesDontCount.size)
        assertEquals(0, b.sidesCount.size)
        assertEquals(0, b.sidesDontCount.size)
        assertEquals(0, c.sidesCount.size)
        assertEquals(0, c.sidesDontCount.size)

        val a1 = Square(x = 0, y = 0, value = "X")
        val b1 = Square(x = 1, y = 0, value = "X")
        val c1 = Square(x = 2, y = 0, value = "X")
        val belowC1 = Square(x = 2, y = -1, value = "X")

        day.cantGoDownLogic(a1, listOfValues = mutableListOf(c1, b1, belowC1))

        assertEquals(1, a1.sidesCount.size)
        assertEquals(1, a1.sidesDontCount.size)
        assertEquals(0, b1.sidesCount.size)
        assertEquals(1, b1.sidesDontCount.size)
        assertEquals(0, c1.sidesCount.size)
        assertEquals(0, c1.sidesDontCount.size)


        val a2 = Square(x = 0, y = 0, value = "X")
        val belowA = Square(x = 0, y = -1, value = "X")
        val b2 = Square(x = 1, y = 0, value = "X")
        val c2 = Square(x = 2, y = 0, value = "X")

        day.cantGoDownLogic(a2, listOfValues = mutableListOf(c2, b2, belowA))

        assertEquals(0, a2.sidesCount.size)
        assertEquals(0, a2.sidesDontCount.size)
        assertEquals(0, b2.sidesCount.size)
        assertEquals(0, b2.sidesDontCount.size)
        assertEquals(0, c2.sidesCount.size)
        assertEquals(0, c2.sidesDontCount.size)


    }

    @Test
    fun `cantGoDownLogic && but something below exist && right to left`() {
        val a = Square(x = 0, y = 0, value = "X")
        val b = Square(x = 1, y = 0, value = "X")
        val c = Square(x = 2, y = 0, value = "X")
        val belowC = Square(x = 2, y = -1, value = "X")


        day.cantGoDownLogic(c, listOfValues = mutableListOf(a, b, belowC))

        assertEquals(0, a.sidesCount.size)
        assertEquals(0, a.sidesDontCount.size)
        assertEquals(0, b.sidesCount.size)
        assertEquals(0, b.sidesDontCount.size)
        assertEquals(0, c.sidesCount.size)
        assertEquals(0, c.sidesDontCount.size)

        val a1 = Square(x = 0, y = 0, value = "X")
        val b1 = Square(x = 1, y = 0, value = "X")
        val belowB1 = Square(x = 1, y = -1, value = "X")
        val c1 = Square(x = 2, y = 0, value = "X")

        day.cantGoDownLogic(c1, listOfValues = mutableListOf(a1, b1, belowB1))

        assertEquals(0, a1.sidesCount.size)
        assertEquals(0, a1.sidesDontCount.size)
        assertEquals(0, b1.sidesCount.size)
        assertEquals(0, b1.sidesDontCount.size)
        assertEquals(1, c1.sidesCount.size)
        assertEquals(1, c1.sidesDontCount.size)


        val a2 = Square(x = 0, y = 0, value = "X")
        val belowA = Square(x = 0, y = -1, value = "X")
        val b2 = Square(x = 1, y = 0, value = "X")
        val c2 = Square(x = 2, y = 0, value = "X")

        day.cantGoDownLogic(c2, listOfValues = mutableListOf(a2, b2, belowA))

        assertEquals(0, a2.sidesCount.size)
        assertEquals(0, a2.sidesDontCount.size)
        assertEquals(0, b2.sidesCount.size)
        assertEquals(1, b2.sidesDontCount.size)
        assertEquals(1, c2.sidesCount.size)
        assertEquals(1, c2.sidesDontCount.size)
    }

    @Test
    fun `cantGoUpLogic && but something above exis && left to right`() {
        val a = Square(x = 0, y = 0, value = "X")
        val aboveA = Square(x = 0, y = 1, value = "X")
        val b = Square(x = 1, y = 0, value = "X")
        val c = Square(x = 2, y = 0, value = "X")


        day.cantGoUpLogic(a, listOfValues = mutableListOf(c, b, aboveA))

        assertEquals(0, a.sidesCount.size)
        assertEquals(0, a.sidesDontCount.size)
        assertEquals(0, b.sidesCount.size)
        assertEquals(0, b.sidesDontCount.size)
        assertEquals(0, c.sidesCount.size)
        assertEquals(0, c.sidesDontCount.size)

        val a1 = Square(x = 0, y = 0, value = "X")
        val b1 = Square(x = 1, y = 0, value = "X")
        val c1 = Square(x = 2, y = 0, value = "O")
        val aboveC1 = Square(x = 2, y = 1, value = "X")

        day.cantGoUpLogic(a1, listOfValues = mutableListOf(c1, b1, aboveC1))

        assertEquals(1, a1.sidesCount.size)
        assertEquals(1, a1.sidesDontCount.size)
        assertEquals(0, b1.sidesCount.size)
        assertEquals(1, b1.sidesDontCount.size)
        assertEquals(0, c1.sidesCount.size)
        assertEquals(0, c1.sidesDontCount.size)


        val a2 = Square(x = 0, y = 0, value = "X")
        val b2 = Square(x = 1, y = 0, value = "X")
        val aboveB = Square(x = 1, y = 1, value = "X")
        val c2 = Square(x = 2, y = 0, value = "X")

        day.cantGoUpLogic(a2, listOfValues = mutableListOf(c2, b2, aboveB))

        assertEquals(1, a2.sidesCount.size)
        assertEquals(1, a2.sidesDontCount.size)
        assertEquals(0, b2.sidesCount.size)
        assertEquals(0, b2.sidesDontCount.size)
        assertEquals(0, c2.sidesCount.size)
        assertEquals(0, c2.sidesDontCount.size)
    }

    @Test
    fun `cantGoUpLogic && but something above exis && right to left`() {
        val a = Square(x = 0, y = 0, value = "X")
        val aboveA = Square(x = 0, y = 1, value = "X")
        val b = Square(x = 1, y = 0, value = "X")
        val c = Square(x = 2, y = 0, value = "X")


        day.cantGoUpLogic(c, listOfValues = mutableListOf(a, b, aboveA))

        assertEquals(0, a.sidesCount.size)
        assertEquals(0, a.sidesDontCount.size)
        assertEquals(0, b.sidesCount.size)
        assertEquals(1, b.sidesDontCount.size)
        assertEquals(1, c.sidesCount.size)
        assertEquals(1, c.sidesDontCount.size)

        val a1 = Square(x = 0, y = 0, value = "X")
        val b1 = Square(x = 1, y = 0, value = "X")
        val c1 = Square(x = 2, y = 0, value = "O")
        val aboveC1 = Square(x = 2, y = 1, value = "X")

        day.cantGoUpLogic(c1, listOfValues = mutableListOf(a1, b1, aboveC1))

        assertEquals(0, a1.sidesCount.size)
        assertEquals(0, a1.sidesDontCount.size)
        assertEquals(0, b1.sidesCount.size)
        assertEquals(0, b1.sidesDontCount.size)
        assertEquals(0, c1.sidesCount.size)
        assertEquals(0, c1.sidesDontCount.size)


        val a2 = Square(x = 0, y = 0, value = "X")
        val b2 = Square(x = 1, y = 0, value = "X")
        val aboveB = Square(x = 1, y = 1, value = "X")
        val c2 = Square(x = 2, y = 0, value = "X")

        day.cantGoUpLogic(c2, listOfValues = mutableListOf(a2, b2, aboveB))

        assertEquals(0, a2.sidesCount.size)
        assertEquals(0, a2.sidesDontCount.size)
        assertEquals(0, b2.sidesCount.size)
        assertEquals(0, b2.sidesDontCount.size)
        assertEquals(1, c2.sidesCount.size)
        assertEquals(1, c2.sidesDontCount.size)
    }

    @Test
    fun answerOne() {
        assertEquals("1930", example.answerOne())
        assertEquals("1461806", day.answerOne())
    }

    @Test
    fun answerTwo() {
        assertEquals("1206", example.answerTwo())
        assertEquals("X", day.answerTwo())
    }

//    @Test
    fun avg() {
        val day = day
        MarcoUtil.avgTime("part1", 5) {
            assertEquals("1461806", day.answerOne())
        }
//        MarcoUtil.avgTime("part2", 5) {
//            assertEquals("X", day.answerTwo())
//        }
    }
}

