import java.io.File

fun main() {
    var sumPowers = 0

    File("input/day2/data").forEachLine { line: String ->
        val game = line.split(":")
        val id = game[0].split(" ")[1].toInt()

        val rounds = game[1].split(";")



        val numReds = mutableListOf<Int>()
        val numBlues = mutableListOf<Int>()
        val numGreens = mutableListOf<Int>()

        rounds.forEachIndexed() { i, round ->
            val colours = round.split(',')

            colours.forEach { colour ->
                val colourLine = colour.split(" ")
                val numColour = colourLine[1].trim().toInt()
                val colourVal = colourLine[2].trim()

                when(colourVal) {
                    "red" -> numReds.add(numColour)
                    "blue" -> numBlues.add(numColour)
                    "green" -> numGreens.add(numColour)
                }
            }
        }

        println("${id}: ${numReds.max()} red, ${numGreens.max()} greens, ${numBlues.max()} blues")

        sumPowers += numReds.max() * numGreens.max() * numBlues.max()

    }

    println(sumPowers)


}
