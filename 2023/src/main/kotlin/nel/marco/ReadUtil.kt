package nel.marco

import java.nio.file.Files
import java.nio.file.Path

class ReadUtil {
    companion object {
        fun readInput(dayNumber: Int, exampleInput: Boolean = false): String {
            return readInputAsList(dayNumber, exampleInput).joinToString("\n")
        }

        fun readInputAsList(dayNumber: Int, exampleInput: Boolean = false, macBook: Boolean = false): List<String> {

            val basePath = when (macBook) {
                true -> "/Users/mnel/repo/AdventOfCode/2023/src/main/resources"
                false -> "D:\\coding repo\\AdventOfCode\\2023\\src\\main\\resources\\"
            }


            if (exampleInput) {
                return Files.readAllLines(Path.of("$basePath/day${dayNumber}_example"))
            }

            return Files.readAllLines(Path.of("$basePath/day$dayNumber"))
        }
    }
}
