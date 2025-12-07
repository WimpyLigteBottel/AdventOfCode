package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day6Test {

    val day = Day6(useExample = false, useMac = false)
    val example = Day6(useExample = true, useMac = false)

    @Test
    fun answerOne() {
        assertEquals("4277556", example.answerOne())
        assertEquals("6343365546996", day.answerOne())
    }


    @Test
    fun answerTwo() {
        assertEquals("3263827", example.answerTwo())
        assertEquals("11136895955912", day.answerTwo())

    }

//    part 1 took 0.10298581209853885 ms average (totalRuns=28334;inSeconds=5)
//    part 2 took 0.16965194991912777 ms average (totalRuns=16693;inSeconds=5)

    @Test
    fun avgPart1() {
        MarcoUtil.avgTime("part 1", 5) {
            assertEquals("6343365546996", day.answerOne())
        }

        MarcoUtil.avgTime("part 2", 5) {
            assertEquals("11136895955912", day.answerTwo())
        }
    }
}
