import java.nio.file.Files
import java.nio.file.Path
import java.time.Duration
import java.time.Instant

class MarcoUtil {

    companion object {


        val basePath = "D:\\coding repo\\AdventOfCode\\2015\\src\\main\\resources\\"
        fun time(name: String, work: () -> Unit): Unit {
            val now = Instant.now()
            work.invoke()
            val timeItTook = Duration.between(now, Instant.now()).toMillis()

            println("$name took $timeItTook ms");
        }

        fun readInput(day: Int): List<String> {
            return Files.readAllLines(Path.of("${basePath}day$day"))
        }

        fun readInput(day: Int, testInput: Boolean = false): List<String> {
            if (testInput)
                return Files.readAllLines(Path.of("${basePath}day${day}Test"))
            else
                return readInput(day)
        }
    }

}

