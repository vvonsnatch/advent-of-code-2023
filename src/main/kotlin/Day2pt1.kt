import java.io.File

const val MAX_RED = 12
const val MAX_GREEN = 13
const val MAX_BLUE = 14
fun main() {
    var sumPossibleIDs = 0

    File("input/day2/data").forEachLine { line: String ->
        val game = line.split(":")
        val id = game[0].split(" ")[1].toInt()

        val rounds = game[1].split(";")
        var possible = true

        rounds.forEachIndexed() { i, round ->
            val colours = round.split(',')

            colours.forEach { colour ->
                val colourLine = colour.split(" ")
                val numColour = colourLine[1].trim().toInt()
                val colourVal = colourLine[2].trim()

                println("${id} - ${i} - ${numColour} ${colourVal}")

                if (
                    colourVal == "blue" && numColour > MAX_BLUE ||
                    colourVal == "red" && numColour > MAX_RED ||
                    colourVal == "green" && numColour > MAX_GREEN
                ) {
                    possible = false
                }
            }
        }

        if (possible) {
            sumPossibleIDs += id
        }
    }

    println(sumPossibleIDs)
}
