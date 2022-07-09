import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path

internal class Day3Test {

    @Test
    fun part2() {
        val day = Day3()

        assertEquals(3, day.part2(listOf("^v")))
        assertEquals(3, day.part2(listOf("^>v<")))
        assertEquals(11, day.part2(listOf("^v^v^v^v^v")))
    }
}