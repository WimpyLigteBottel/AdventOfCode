package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigInteger

class Day5Test {

    val day = Day5(useExample = false, useMac = false)
    val example = Day5(useExample = true, useMac = false)

    @Test
    fun answerOne() {
        assertEquals("3", example.answerOne())
        assertEquals("664", day.answerOne())
    }


    @Test
    fun answerTwo() {
        assertEquals("14", example.answerTwo())
        assertEquals("350780324308385", day.answerTwo())

    }

    //part 1 took 0.305531167690957 ms average (totalRuns=9112;inSeconds=5)
    //part 2 took 0.02650483722572791 ms average (totalRuns=107603;inSeconds=5)

    @Test
    fun avgPart1() {
        MarcoUtil.avgTime("part 1", 5) {
            assertEquals("664", day.answerOne())
        }

        MarcoUtil.avgTime("part 2", 5) {
            assertEquals("350780324308385", day.answerTwo())
        }
    }
}
