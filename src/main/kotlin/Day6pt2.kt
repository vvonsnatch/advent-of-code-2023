import java.io.File

fun main() {
    val lines = File("input/day6/data").readLines()
    val seconds = lines[0].split(":")[1].split("""\s+""".toRegex()).joinToString(separator = "").toLong()
    val record = lines[1].split(":")[1].split("""\s+""".toRegex()).joinToString(separator = "").toLong()

    var numPossibleValues = 0
    var secondsPressed = 0

    while(secondsPressed <= seconds) {
        val distanceTravelled = (seconds - secondsPressed) * secondsPressed
        if (distanceTravelled > record) {
            numPossibleValues++
        }
        secondsPressed++
    }

    println(seconds)
    println(record)
    println(numPossibleValues)
}