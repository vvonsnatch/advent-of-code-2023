import java.io.File
import kotlin.math.pow

fun main() {
    val lines = File("input/day4/data").readLines()

    var points = 0.0

    lines.forEach { line ->
        val card = line.split(":")[1]
        val winningNumbers = card.split("|")[0].split(" ").map { numStr -> numStr.toIntOrNull() }.filterNotNull()
        val myNumbers = card.split("|")[1].split(" ").map { numStr -> numStr.toIntOrNull() }.filterNotNull()
        val intersection = myNumbers.intersect(winningNumbers)

        println("winning numbers: $winningNumbers")
        println("my numbers: $myNumbers")
        println("intersection: $intersection")
        println()

        points += if (intersection.size > 0) (2.0).pow(intersection.size - 1) else 0.0
    }

    println(points)
}
