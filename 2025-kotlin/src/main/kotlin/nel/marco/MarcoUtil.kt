package nel.marco

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

        fun avgTime(name: String, inSeconds: Long = 1, work: () -> Unit) {
            val futureDate = Instant.now().plusSeconds(inSeconds)

            val mutableListOf = ArrayList<Long>(10000)
            while (Instant.now().isBefore(futureDate)) {
                val now = Instant.now()
                work.invoke()
                val timeItTook = Duration.between(now, Instant.now()).toMillis()
                mutableListOf.add(timeItTook)
            }

            println("$name took ${mutableListOf.average()} ms average (totalRuns=${mutableListOf.size};inSeconds=$inSeconds)")
        }

        fun <T> printMap(newMap: MutableMap<Point, T>, size: Int) {

            for (x in 0..<size) {
                for (y in 0..<size) {
                    print(newMap[Point(y, x)])
                }
                println()
            }
        }

    }
}
