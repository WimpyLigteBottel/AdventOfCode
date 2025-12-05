package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day5Test {

    val day = Day5(useExample = false, useMac = false)
    val example = Day5(useExample = true, useMac = false)

    @Test
    fun answerOne() {
        assertEquals("X", example.answerOne())
        assertEquals("XX", day.answerOne())
    }


    @Test
    fun answerTwo() {
        assertEquals("Y", example.answerTwo())
        assertEquals("YY", day.answerTwo())
    }

    //part 1 took 8.924214417744917 ms average (totalRuns=541;inSeconds=5)
    //part 2 took 154.57575757575756 ms average (totalRuns=33;inSeconds=5)
    @Test
    fun avgPart1() {
        MarcoUtil.avgTime("part 1", 5) {
            assertEquals("X", day.answerOne())
        }

        MarcoUtil.avgTime("part 2", 5) {
            assertEquals("Y", day.answerTwo())
        }
    }
}
