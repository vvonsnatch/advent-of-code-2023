import java.io.BufferedReader
import java.io.FileReader
class RangeMap(source: Long, destination: Long, range: Long)
fun main() {
    val reader = BufferedReader(FileReader("input/day5/data"))

    var seeds: List<Long> = emptyList()
    val seedToSoil: MutableMap<Long, Long> = mutableMapOf()
    val soilToFertilizer: MutableMap<Long, Long> = mutableMapOf()
    val fertilizerToWater: MutableMap<Long, Long> = mutableMapOf()
    val waterToLight: MutableMap<Long, Long> = mutableMapOf()
    val lightToTemp: MutableMap<Long, Long> = mutableMapOf()
    val tempToHumidity: MutableMap<Long, Long> = mutableMapOf()
    val humidityToLocation: MutableMap<Long, Long> = mutableMapOf()

    var line: String?

    while (reader.readLine().also { line = it } != null) {
        if (line == "") {
            continue
        } else if (line!!.contains("seeds")) {
            seeds = line!!.split(":")[1].split(" ").mapNotNull { it.toLongOrNull()}
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
        var soil: Long?
        var fertilizer: Long?
        var water: Long?
        var light: Long?
        var temp: Long?
        var humidity: Long?

        soil = if (seedToSoil.containsKey(it)) seedToSoil[it] else it
        fertilizer = if (soilToFertilizer.containsKey(soil)) soilToFertilizer[soil] else soil
        water = if (fertilizerToWater.containsKey(fertilizer)) fertilizerToWater[fertilizer] else fertilizer
        light = if (waterToLight.containsKey(water)) waterToLight[water] else water
        temp = if (lightToTemp.containsKey(light)) lightToTemp[light] else light
        humidity = if (tempToHumidity.containsKey(temp)) tempToHumidity[temp] else temp

        if (humidityToLocation.containsKey(humidity)) humidityToLocation[humidity]!! else humidity!!
    }

    println(locations.min())
}

fun addToMap(mapStr: String, map: MutableMap<Long, Long>) {
    val mapList = mapStr.split(" ").mapNotNull { it.toLongOrNull() }
    val destination = mapList[0]
    val source = mapList[1]
    val rangeLength = mapList[2]

    var it = 0
    while (it < rangeLength){
//        println("${source + it} -> ${destination + it}")
        map[source + it] = destination + it
        it++
    }

    println(map)
}
