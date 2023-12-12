import java.io.File

fun main() {
    val lines = File("input/day6/data").readLines()
    val seconds = lines[0].split(":")[1].split("""\s+""".toRegex()).mapNotNull { it.toIntOrNull() }
    val records = lines[1].split(":")[1].split("""\s+""".toRegex()).mapNotNull { it.toIntOrNull() }

    var numPossibleValues: Array<Int> = Array(records.size) { 0 }

    seconds.forEachIndexed { i, s ->
        var secondsPressed = 0

        while(secondsPressed <= s) {
            val distanceTravelled = (s - secondsPressed) * secondsPressed
            if (distanceTravelled > records[i]) {
                numPossibleValues[i]++
            }
            secondsPressed++
        }
    }

    println(seconds)
    println(records)
    println(numPossibleValues.joinToString())
    println(numPossibleValues.reduce { acc, i ->  acc * i } )

}
