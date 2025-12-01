package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day1Test {

    val day = Day1(useExample = false, useMac = true)
    val example = Day1(useExample = true, useMac = true)

    @Test
    fun answerOne() {
        assertEquals("3", example.answerOne())
        assertEquals("1086", day.answerOne())
    }


    @Test
    fun answerTwo() {
        assertEquals("6", example.answerTwo())
        assertEquals("6268", day.answerTwo())
    }


    @Test
    fun avgPart1(){
        MarcoUtil.avgTime("day 1 part 1"){
            day.answerOne()
        }

        MarcoUtil.avgTime("day 1 part 2"){
            day.answerTwo()
        }
    }
}
