package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day2Test {

    val day = Day2(useExample = false, useMac = false)
    val example = Day2(useExample = true, useMac = false)

    @Test
    fun answerOne() {
        assertEquals("1227775554", example.answerOne())
        assertEquals("23534117921", day.answerOne())
    }


    @Test
    fun answerTwo() {
        assertEquals("4174379265", example.answerTwo())
        assertEquals("31755323497", day.answerTwo())
    }


    @Test
    fun avgPart1(){
        MarcoUtil.avgTime("day 2 part 1", 5){
            assertEquals("23534117921", day.answerOne())
        }

        MarcoUtil.avgTime("day 2 part 2",5 ){
            assertEquals("31755323497", day.answerTwo())
        }
    }
}
