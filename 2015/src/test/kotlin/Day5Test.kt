import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day5Test {

    val day = Day5()

    @Test
    fun part1() {
        assertEquals(0, day.part1(listOf("jchzalrnumimnmhp")), "Should FAIL!")
        assertEquals(0, day.part1(listOf("haegwjzuvuyypxyu")), "Should FAIL!")
        assertEquals(0, day.part1(listOf("dvszwmarrgswjxmb")), "Should FAIL!")

        assertEquals(1, day.part1(listOf("ugknbfddgicrmopn")), "SHOULD WORK!")
        assertEquals(1, day.part1(listOf("aaa")), "SHOULD WORK!")
    }

    @Test
    fun part1_jchzalrnumimnmhp_isNAUGHTY_becauseHasNoDoubleLetters() {
        assertEquals(0, day.part1(listOf("jchzalrnumimnmhp")), "Should FAIL!")
    }

    @Test
    fun part2_isNAUGHTY() {
        assertEquals(0, day.part1(listOf("ieodomkazucvgmuy")), "Should FAIL!")
        assertEquals(0, day.part2(listOf("uurcxstgmygtbstg")), "Should FAIL!")
    }

    @Test
    fun part2_isNice() {

        assertEquals(1, day.part2(listOf("qjhvhtzxzqqjkmpb")), "SHOULD WORK!")
        assertEquals(1, day.part2(listOf("xxyxx")), "SHOULD WORK!")
    }
}