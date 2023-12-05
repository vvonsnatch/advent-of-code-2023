import java.io.File

fun main() {
    val grid = mutableListOf<List<String>>()

    File("input/day3/data").readLines().forEach { line ->
        grid.add(line.split("").slice(1..line.length))
    }

    val numRows = grid.size
    val numCols = grid[0].size

    var partNumSum = 0

    println("numRows: $numRows")
    println("numCols: $numCols")

    var i = 0
    var j: Int?

    var numStart: Int? = null
    var numEnd: Int? = null

    while (i < numRows) {
        j = 0
        println("$i - [${grid[i].joinToString()}]")
        while (j < numCols) {
            if (grid[i][j].toIntOrNull() != null) { // if grid[i][j] is an integer
                if (numStart == null) {
                    numStart = j
                } else if (j == numCols - 1) {
                    numEnd = j
                }
            } else { // if (grid[i][j].toIntOrNull() == null) // if grid[i][j] not an int
                if (numStart != null) {
                    numEnd = j - 1
                }
            }

            if (numStart != null && numEnd != null) {
                try {
                    val numRange = numStart..numEnd // if (numEnd == numCols) numStart..numEnd else numStart..<numEnd
                    val currentNum = grid[i].slice(numRange).joinToString(separator = "").toInt()
                    val currentNumIndices: Array<Array<Int>> = (numRange).toList().map { col -> arrayOf(i, col) }.toTypedArray()

                    val isPartNum = isPartNumber(grid, currentNumIndices)
                    println("$currentNum:")
                    println("- numStart: $numStart")
                    println("- numEnd: $numEnd")
                    println("- [${currentNumIndices.map { arr -> "[${arr.joinToString()}]" }}]")
                    println("- ${if (isPartNum) "Y" else "N"}")
                    if (isPartNum) {
                        partNumSum += currentNum
                    }
                    numStart = null
                    numEnd = null
                } catch (e: NumberFormatException) {
                    val numRange = if (numEnd == numCols - 1) numStart..numEnd else numStart..<numEnd
                    println("NUM START: $numStart")
                    println("NUM END: $numEnd")
                    println("i: $i")
                    println("j: $j")
                    println("ATTEMPTED TO PARSE: ${grid[i].slice(numRange).joinToString(separator = "")}")
                    println("INDICES: ${List(numCols) { ind -> "[${arrayOf(i, ind).joinToString()}]" }.slice(numStart..<numEnd)}")

                    throw e
                }
            }
            j++
        }
        i++
        println()
    }

    println(partNumSum)
}

fun isPartNumber(grid: MutableList<List<String>>, numberIndices: Array<Array<Int>>): Boolean {
    val numberRow = numberIndices.first().first()
    val numberCols = numberIndices.map { digitIndex -> digitIndex.last() }

    val symbolStartCol: Int = if (numberCols.first() == 0) 0 else numberCols.first() - 1
    val symbolEndCol: Int = if (numberCols.last() == grid[0].size - 1) grid[0].size - 1 else numberCols.last() + 1

    val isSymbol: (String) -> Boolean = { c: String -> c.toIntOrNull() == null && c != "." }

    if (numberRow > 0 && grid[numberRow - 1].slice(symbolStartCol..symbolEndCol).any(isSymbol)) { // up
        return true
    } else if (isSymbol(grid[numberRow][symbolStartCol])) { // left
        return true
    } else if (isSymbol(grid[numberRow][symbolEndCol])) { // right
        return true
    } else if (numberRow < grid.size - 1 && grid[numberRow + 1].slice(symbolStartCol..symbolEndCol).any(isSymbol)) { // down
        return true
    }

    return false
}
