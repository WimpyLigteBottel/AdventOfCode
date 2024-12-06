package nel.marco

import java.util.concurrent.atomic.AtomicLong


class Day6(readInput: List<String>) : Day(readInput) {


    override fun answerOne(): String {
        val guardMap: MutableList<MutableList<Point>> = locationsWalked()

        return (guardMap.flatten().count { it.value != "." && it.value != "#" }).toString()
    }

    private fun locationsWalked(): MutableList<MutableList<Point>> {
        val guardMap: MutableList<MutableList<Point>> = readInput.mapIndexed { y, s ->
            s.mapIndexed { x, c ->
                Point(x, y, value = c.toString())
            }.toMutableList()
        }.toMutableList()


        runCatching {
            var guard = guardMap.flatten().find { it.value == "^" }!!

            while (true) {
                val nextPosition = findNextPosition(guard, guardMap)

                //update old position
                guardMap[guard.y][guard.x] = guard.clone().copy(value = "X")
                // update future position
                guardMap[nextPosition.y][nextPosition.x] = nextPosition.copy()

                guard = nextPosition
            }
        }
        return guardMap
    }


    private fun findNextPosition(guard: Point, guardMap: List<List<Point>>): Point {
        val nextStep: Point = when (guard.copy().value) {
            "^" -> guard.copy().down()
            "<" -> guard.copy().left()
            ">" -> guard.copy().right()
            "v" -> guard.copy().up()
            else -> throw RuntimeException("I dont know this position ${guard.value}")
        }

        if (guardMap[nextStep.y][nextStep.x].value != "#") {
            return nextStep
        }

        return findNextPosition(rotateGuardRight(guard), guardMap)
    }


    private fun rotateGuardRight(guard: Point): Point {
        return when (guard.copy().value) {
            "^" -> guard.copy(value = ">")
            "<" -> guard.copy(value = "^")
            ">" -> guard.copy(value = "v")
            "v" -> guard.copy(value = "<")
            else -> throw RuntimeException("I can't point this way ${guard.value}")
        }
    }


    override fun answerTwo(): String {
        val initialGuardMap: MutableList<MutableList<Point>> = readInput.mapIndexed { y, s ->
            s.mapIndexed { x, c ->
                Point(x, y, value = c.toString())
            }.toMutableList()
        }.toMutableList()

        // Find the guard's starting position
        val guardStart = initialGuardMap.flatten().find { it.value == "^" }!!

        val walkableLocations = locationsWalked().flatten().filter { it.value == "X" }

        var loopCount = AtomicLong(0)

        walkableLocations.parallelStream().forEach { obstacle ->
            val clonedMap = initialGuardMap.map { row -> row.map { it.copy() }.toMutableList()}
            clonedMap[obstacle.y][obstacle.x].value = "#"

            if (isGuardLoop(clonedMap, guardStart)) {
                loopCount.getAndIncrement()
            }
        }

        return loopCount.toString()
    }

    private fun isGuardLoop(guardMap: List<MutableList<Point>>, guardStart: Point): Boolean {
        val visitedStates = mutableSetOf<Pair<Point, String>>()
        var guard = guardStart.copy()

        while (true) {
            val currentState = guard to guard.value // Position and direction

            if (visitedStates.contains(currentState)) {
                return true // Loop detected
            }
            visitedStates.add(currentState)

            // Find the next position for the guard
            val nextPosition = runCatching { findNextPosition(guard, guardMap) }.getOrElse {
                return false // Guard stopped moving, no loop
            }

            guardMap[guard.y][guard.x] = guard.copy(value = "X") // Mark the old position
            guard = nextPosition // Move guard
            guardMap[nextPosition.y][nextPosition.x] = nextPosition.copy() // Update map with new position
        }
    }

}
