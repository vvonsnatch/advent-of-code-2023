import java.io.File

fun main() {
    val lines = File("input/day4/data").readLines()

    var cardCount = Array(lines.size) { 1 }

    var points = 0.0

    lines.forEachIndexed() { ind, line ->
        val card = line.split(":")[1]
        val winningNumbers = card.split("|")[0].split(" ").map { numStr -> numStr.toIntOrNull() }.filterNotNull()
        val myNumbers = card.split("|")[1].split(" ").map { numStr -> numStr.toIntOrNull() }.filterNotNull()
        val intersection = myNumbers.intersect(winningNumbers)

        println("Card ${ind + 1}: ${intersection.size} wins - ${cardCount[ind]} copies")

        (ind + 1..<ind + 1 + intersection.size).forEach { i ->
            for (j in 0 until cardCount[ind]) {
                println("  +1 copy of card ${i + 1}")
                cardCount[i] += 1
            }

        }
    }

    println()
    println(cardCount.sum())
}
