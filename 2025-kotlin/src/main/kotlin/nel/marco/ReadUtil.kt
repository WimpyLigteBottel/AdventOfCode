package nel.marco

import java.nio.file.Files
import java.nio.file.Path

class ReadUtil {
    companion object {
        fun readInput(dayNumber: Int, exampleInput: Boolean = false): String {
            return readInputAsList(dayNumber, exampleInput).joinToString("\n")
        }

        fun readInputAsList(dayNumber: Int, exampleInput: Boolean = false, macBook: Boolean = true): List<String> {

            val basePath = when (macBook) {
                true -> "/Users/mnel/repo/AdventOfCode/2025-kotlin/src/main/resources"
                false -> "C:\\code\\AdventOfCode\\2025-kotlin\\src\\main\\resources"
            }


            if (exampleInput) {
                return Files.readAllLines(Path.of("$basePath/day${dayNumber}_example"))
            }

            return Files.readAllLines(Path.of("$basePath/day$dayNumber"))
        }
    }
}
