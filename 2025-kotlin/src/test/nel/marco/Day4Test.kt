package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day4Test {

    val day = Day4(useExample = false, useMac = true)
    val example = Day4(useExample = true, useMac = true)

    @Test
    fun answerOne() {
        assertEquals("13", example.answerOne())
        assertEquals("1416", day.answerOne())
    }


    @Test
    fun answerTwo() {
        assertEquals("43", example.answerTwo())
        assertEquals("xx", day.answerTwo())
    }

//    day 2 part 1 took 2.8890347997373604 ms average (totalRuns=1523;inSeconds=5)
//    day 2 part 2 took 0.14646574626540657 ms average (totalRuns=22961;inSeconds=5)


//    day 2 part 1 took 0.17565981294255748 ms average (totalRuns=26409;inSeconds=5)
//    day 2 part 2 took 0.14095934754277548 ms average (totalRuns=23787;inSeconds=5)
    @Test
    fun avgPart1() {
        MarcoUtil.avgTime("day 2 part 1", 5) {
            assertEquals("XX", day.answerOne())
        }

        MarcoUtil.avgTime("day 2 part 2", 5) {
            assertEquals("xx", day.answerTwo())
        }
    }
}
