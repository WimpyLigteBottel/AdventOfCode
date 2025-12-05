package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day4Test {

    val day = Day4(useExample = false, useMac = false)
    val example = Day4(useExample = true, useMac = false)

    @Test
    fun answerOne() {
        assertEquals("13", example.answerOne())
        assertEquals("1416", day.answerOne())
    }


    @Test
    fun answerTwo() {
        assertEquals("43", example.answerTwo())
        assertEquals("9086", day.answerTwo())
    }

    //part 1 took 8.924214417744917 ms average (totalRuns=541;inSeconds=5)
    //part 2 took 154.57575757575756 ms average (totalRuns=33;inSeconds=5)
    @Test
    fun avgPart1() {
        MarcoUtil.avgTime("part 1", 5) {
            assertEquals("1416", day.answerOne())
        }

        MarcoUtil.avgTime("part 2", 5) {
            assertEquals("9086", day.answerTwo())
        }
    }
}
