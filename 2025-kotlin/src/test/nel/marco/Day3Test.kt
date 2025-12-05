package nel.marco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day3Test {

    val day = Day3(useExample = false, useMac = false)
    val example = Day3(useExample = true, useMac = false)

    @Test
    fun answerOne() {
        assertEquals("357", example.answerOne())
        assertEquals("17100", day.answerOne())
    }


    @Test
    fun answerTwo() {
        assertEquals("3121910778619", example.answerTwo())
        assertEquals("170418192256861", day.answerTwo())
    }

//    day 3 part 1 took 2.8890347997373604 ms average (totalRuns=1523;inSeconds=5)
//    day 3 part 2 took 0.14646574626540657 ms average (totalRuns=22961;inSeconds=5)


//   day 3 part 1 took 0.1733249098814523 ms average (totalRuns=26909;inSeconds=5)
//   day 3 part 2 took 0.15223284348694535 ms average (totalRuns=28036;inSeconds=5)
    @Test
    fun avgPart1() {
        MarcoUtil.avgTime("day 3 part 1", 5) {
            assertEquals("17100", day.answerOne())
        }

        MarcoUtil.avgTime("day 3 part 2", 5) {
            assertEquals("170418192256861", day.answerTwo())
        }
    }
}
