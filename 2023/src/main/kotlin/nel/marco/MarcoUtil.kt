package nel.marco

import java.nio.file.Files
import java.nio.file.Path
import java.time.Duration
import java.time.Instant

class MarcoUtil {

    companion object {

        fun time(name: String, work: () -> Unit) {
            val now = Instant.now()
            work.invoke()
            val timeItTook = Duration.between(now, Instant.now()).toMillis()

            println("$name took $timeItTook ms")
        }

        fun avgTime(name: String, work: () -> Unit) {
            val futureDate = Instant.now().plusSeconds(5)

            val mutableListOf = ArrayList<Long>(1000)
            while (Instant.now().isBefore(futureDate)) {
                val now = Instant.now()
                work.invoke()
                val timeItTook = Duration.between(now, Instant.now()).toMillis()
                mutableListOf.add(timeItTook)
            }

            println("$name took ${mutableListOf.average()} ms")
        }
    }
}
