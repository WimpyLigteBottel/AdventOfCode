package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test

class Day6Test {

    val day = Day6(useExample = false, useMac = false)
    val example = Day6(useExample = true, useMac = false)



    @Test
    fun answerOne() {
        assertEquals("41", example.answerOne())
        assertEquals("4433", day.answerOne())
    }


    @Test
    fun answerTwo() {
//        assertEquals("6", example.answerTwo()) // Should be 6
        assertEquals("1516", day.answerTwo())
    }

    fun avg() {
        val day = day
        MarcoUtil.avgTime("part1",5) {
            assertEquals("4433", day.answerOne())
        }
        MarcoUtil.avgTime("part2",30) {
            assertEquals("1516", day.answerTwo())
        }
    }
}