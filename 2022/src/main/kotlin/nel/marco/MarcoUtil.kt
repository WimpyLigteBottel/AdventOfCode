package nel.marco

import java.nio.file.Files
import java.nio.file.Path
import java.time.Duration
import java.time.Instant

class MarcoUtil {

    companion object {


        val basePath = "D:\\coding repo\\AdventOfCode\\2022\\src\\main\\resources\\"
        fun time(name: String, work: () -> Unit): Unit {
            val now = Instant.now()
            work.invoke()
            val timeItTook = Duration.between(now, Instant.now()).toMillis()

            println("$name took $timeItTook ms");
        }

        fun avgTime(name: String, work: () -> Unit): Unit {

            val futureDate = Instant.now().plusSeconds(5)

            val mutableListOf = mutableListOf<Long>()
            while (Instant.now().isBefore(futureDate)) {
                val now = Instant.now()
                work.invoke()
                val timeItTook = Duration.between(now, Instant.now()).toMillis()
                mutableListOf.add(timeItTook)
            }

            println("$name took ${mutableListOf.average()} ms");
        }


        fun readInput(day: Int): List<String> {
            return Files.readAllLines(Path.of("${basePath}day$day"))
        }

        fun readInput(day: Int, testInput: Boolean = false): List<String> {
            if (testInput)
                return Files.readAllLines(Path.of("${basePath}day${day}_example"))
            else
                return readInput(day)
        }
    }

}

