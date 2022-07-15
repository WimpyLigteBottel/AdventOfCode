import java.nio.file.Files
import java.nio.file.Path
import java.time.Duration
import java.time.Instant

class MarcoUtil {

    companion object {
        fun time(name: String, work: () -> Unit): Unit {
            val now = Instant.now()
            work.invoke()
            val timeItTook = Duration.between(now, Instant.now()).toMillis()

            println("$name took $timeItTook ms");
        }

        fun readInput(day: Int): List<String> {
            return Files.readAllLines(Path.of("D:\\coding repo\\AdventOfCode\\2015\\src\\main\\resources\\day$day"))
        }

        fun readInput(day: Int, testInput: Boolean): List<String> {
            if (testInput)
                return Files.readAllLines(Path.of("D:\\coding repo\\AdventOfCode\\2015\\src\\main\\resources\\day${day}Test"))
            else
                return readInput(day)
        }
    }

}

