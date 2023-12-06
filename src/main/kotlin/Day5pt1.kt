import java.io.BufferedReader
import java.io.FileReader

class RangeMap(val source: Long, val destination: Long, val range: Long)

fun main() {
    val reader = BufferedReader(FileReader("input/day5/data"))

    var seeds: List<Long> = emptyList()
    val seedToSoil: MutableList<RangeMap> = mutableListOf()
    val soilToFertilizer: MutableList<RangeMap> = mutableListOf()
    val fertilizerToWater: MutableList<RangeMap> = mutableListOf()
    val waterToLight: MutableList<RangeMap> = mutableListOf()
    val lightToTemp: MutableList<RangeMap> = mutableListOf()
    val tempToHumidity: MutableList<RangeMap> = mutableListOf()
    val humidityToLocation: MutableList<RangeMap> = mutableListOf()

    var line: String?

    while (reader.readLine().also { line = it } != null) {
        if (line == "") {
            continue
        } else if (line!!.contains("seeds")) {
            seeds = line!!.split(":")[1].split(" ").mapNotNull { it.toLongOrNull() }
        } else if (line!!.contains("seed-to-soil")) {
            var seedToSoilMapStr: String?
            while (reader.readLine().also { seedToSoilMapStr = it } != "") {
                println("SEED TO SOIL: $seedToSoilMapStr")
                addToMap(seedToSoilMapStr!!, seedToSoil)
            }
            continue
        } else if (line!!.contains("soil-to-fertilizer")) {
            var soilToFertilizerMapStr: String?
            while (reader.readLine().also { soilToFertilizerMapStr = it } != "") {
                println("SOIL TO FERTILIZER: $soilToFertilizerMapStr")
                addToMap(soilToFertilizerMapStr!!, soilToFertilizer)
            }
            continue
        } else if (line!!.contains("fertilizer-to-water")) {
            var fertilizerToWaterMapStr: String?
            while (reader.readLine().also { fertilizerToWaterMapStr = it } != "") {
                println("FERTILIZER TO WATER: $fertilizerToWaterMapStr")
                addToMap(fertilizerToWaterMapStr!!, fertilizerToWater)
            }
            continue
        } else if (line!!.contains("water-to-light")) {
            var waterToLightMapStr: String?
            while (reader.readLine().also { waterToLightMapStr = it } != "") {
                println("WATER TO LIGHT: $waterToLightMapStr")
                addToMap(waterToLightMapStr!!, waterToLight)
            }
            continue
        } else if (line!!.contains("light-to-temperature")) {
            var lightToTempMapStr: String?
            while (reader.readLine().also { lightToTempMapStr = it } != "") {
                println("LIGHT TO TEMP: $lightToTempMapStr")
                addToMap(lightToTempMapStr!!, lightToTemp)
            }
            continue
        } else if (line!!.contains("temperature-to-humidity")) {
            var tempToHumidityMapStr: String?
            while (reader.readLine().also { tempToHumidityMapStr = it } != "") {
                println("TEMP TO HUMIDITY: $tempToHumidityMapStr")
                addToMap(tempToHumidityMapStr!!, tempToHumidity)
            }
            continue
        } else if (line!!.contains("humidity-to-location")) {
            var humidityToLocationMapStr: String?
            while (reader.readLine().also { humidityToLocationMapStr = it } != "" && humidityToLocationMapStr != null) {
                println("HUMIDITY TO LOCATION: $humidityToLocationMapStr")
                addToMap(humidityToLocationMapStr!!, humidityToLocation)
            }
            continue
        }

        // Process each line
        println(line)
    }

    val locations: List<Long> = seeds.map {
        val soil: Long?
        val fertilizer: Long?
        val water: Long?
        val light: Long?
        val temp: Long?
        val humidity: Long?

        soil = findInMap(it, seedToSoil)
        fertilizer = findInMap(soil, soilToFertilizer)
        water = findInMap(fertilizer, fertilizerToWater)
        light = findInMap(water, waterToLight)
        temp = findInMap(light, lightToTemp)
        humidity = findInMap(temp, tempToHumidity)

        findInMap(humidity, humidityToLocation)
    }

    println(locations.min())
}

fun addToMap(mapStr: String, list: MutableList<RangeMap>) {
    val mapList = mapStr.split(" ").mapNotNull { it.toLongOrNull() }
    val destination = mapList[0]
    val source = mapList[1]
    val range = mapList[2]

    list.add(RangeMap(source, destination, range))

    println("source: $source, destination: $destination, range: $range")
}

fun findInMap(item: Long, list: List<RangeMap>): Long {
    list.forEach { rangeMap ->
        if (item >= rangeMap.source && item < (rangeMap.source + rangeMap.range)) {
            if (rangeMap.destination > rangeMap.source) {
                return item + (rangeMap.destination - rangeMap.source)
            } else {
                return item - (rangeMap.source - rangeMap.destination)
            }
        }
    }

    return item
}
