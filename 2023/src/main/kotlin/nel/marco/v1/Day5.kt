package nel.marco.v1


class Day5(readInput: List<String>) : Day(readInput) {
    override fun answerOne(): String {
        val seedsToBePlanted = readInput.get(0).split(":")
        val seedData = SeedData(seedsToBePlanted[1].extraNumbers())
        populateSeedData(seedData)

        val lowesPerSeed = seedData.seedsToBePlanted
            .map {
                seedData.getMapping(seedData.seed_to_soil, it.toLong())
            }
            .map {
                seedData.getMapping(seedData.soil_to_ferilizer, it)
            }.map {
                seedData.getMapping(seedData.fertilizer_to_water, it)
            }
            .map {
                seedData.getMapping(seedData.water_to_light, it)
            }
            .map {
                seedData.getMapping(seedData.light_to_temperature, it)
            }
            .map {
                seedData.getMapping(seedData.temperature_to_humidity, it)
            }
            .map {
                seedData.getMapping(seedData.humidity_to_location, it)
            }

        println(lowesPerSeed)


        return lowesPerSeed.minOf { it.toString() }.toString()
    }

    private fun populateSeedData(seedData: SeedData) {
        var lastAction = ACTIONS.UNKOWN

        readInput.forEach {
            lastAction = setMapToFill(it, lastAction)
            val mapToGuide = it.extraNumbers().mapToGuide()
            when (lastAction) {
                ACTIONS.seed_to_soil -> mapToGuide?.let { seedData.seed_to_soil.add(it) }
                ACTIONS.soil_to_fertilizer -> mapToGuide?.let { seedData.soil_to_ferilizer.add(it) }
                ACTIONS.fertilizer_to_water -> mapToGuide?.let { seedData.fertilizer_to_water.add(it) }
                ACTIONS.water_to_light -> mapToGuide?.let { seedData.water_to_light.add(it) }
                ACTIONS.light_to_temperature -> mapToGuide?.let { seedData.light_to_temperature.add(it) }
                ACTIONS.temperature_to_humidity -> mapToGuide?.let { seedData.temperature_to_humidity.add(it) }
                ACTIONS.humidity_to_location -> mapToGuide?.let { seedData.humidity_to_location.add(it) }
                else -> {}
            }
        }
    }

    private fun setMapToFill(it: String, action: ACTIONS): ACTIONS {
        if (it.contains("seed-to-soil map:")) {
            return ACTIONS.seed_to_soil
        }

        if (it.contains("soil-to-fertilizer map:")) {
            return ACTIONS.soil_to_fertilizer
        }

        if (it.contains("fertilizer-to-water map:")) {
            return ACTIONS.fertilizer_to_water
        }

        if (it.contains("water-to-light map:")) {
            return ACTIONS.water_to_light
        }

        if (it.contains("light-to-temperature map:")) {
            return ACTIONS.light_to_temperature
        }


        if (it.contains("temperature-to-humidity map:")) {
            return ACTIONS.temperature_to_humidity
        }

        if (it.contains("humidity-to-location map:")) {
            return ACTIONS.humidity_to_location
        }

        return action
    }

    override fun answerTwo(): String {
        return ""
    }

    fun String.extraNumbers() = "\\d+".toRegex().findAll(this).map { it.value }.toList()

    fun List<String>.mapToGuide(): Guide? {
        if (this.size == 3) {
            return Guide(this[0].toLong(), this[1].toLong(), this[2].toLong())
        }

        return null
    }

}

data class SeedData(
    var seedsToBePlanted: List<String> = emptyList(),
    var seed_to_soil: MutableList<Guide> = mutableListOf(),
    var soil_to_ferilizer: MutableList<Guide> = mutableListOf(),
    var fertilizer_to_water: MutableList<Guide> = mutableListOf(),
    var water_to_light: MutableList<Guide> = mutableListOf(),
    var light_to_temperature: MutableList<Guide> = mutableListOf(),
    var temperature_to_humidity: MutableList<Guide> = mutableListOf(),
    var humidity_to_location: MutableList<Guide> = mutableListOf(),
) {

    fun getMapping(list: List<Guide>, numberToRetrieve: Long): Long {

        val guideWithinRange = list
            .filter { it.source + it.range >= numberToRetrieve }
            .sortedBy { it.source + it.range }
            .let {
                if (it.isEmpty())
                    return numberToRetrieve

                it.first
            }

        var bigMap = guideWithinRange.toMatchingPair(numberToRetrieve)

        return bigMap[numberToRetrieve] ?: numberToRetrieve
    }

    fun getListToMap(list: List<Guide>): MutableMap<Long, Long> {
        var bigMap = mutableMapOf<Long, Long>()
        list.map { guide ->
            bigMap.putAll(guide.toMap())
        }
        return bigMap
    }


}

data class Guide(
    val destination: Long,
    val source: Long,
    val range: Long,
) {

    fun toMatchingPair(numberToRetrieve: Long): MutableMap<Long, Long> {
        val seedToSoilMap: MutableMap<Long, Long> = mutableMapOf()

        val destinations = this.toDestinationRange(numberToRetrieve)

        val sources = this.toSourceRange(numberToRetrieve)

        destinations.forEachIndexed { index, i ->
            seedToSoilMap[sources[index]] = destinations[index]
        }
        return seedToSoilMap
    }

    fun toMap(): MutableMap<Long, Long> {
        val seedToSoilMap: MutableMap<Long, Long> = mutableMapOf()

        val destinations = this.toDestinationRange(0)

        val sources = this.toSourceRange(0)

        destinations.forEachIndexed { index, i ->
            seedToSoilMap[sources[index]] = destinations[index]
        }
        return seedToSoilMap
    }

    fun toDestinationRange(numberToRetrieve: Long): List<Long> {
        return LongRange(destination, destination + range - 1 - 0).map { it }
    }

    fun toSourceRange(numberToRetrieve: Long): List<Long> {
        return LongRange(source, source + range - 1 - 0).map { it }
    }
}

enum class ACTIONS {
    UNKOWN,
    seed_to_soil,
    soil_to_fertilizer,
    fertilizer_to_water,
    water_to_light,
    light_to_temperature,
    temperature_to_humidity,
    humidity_to_location
}