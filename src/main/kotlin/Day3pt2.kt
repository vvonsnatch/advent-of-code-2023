import java.io.File

fun main() {
    val file = File("input/day3/data")
    val lines = file.readLines()

    var gearRatioSum = 0
    val grid = Array<Array<String?>>(lines.size) { arrayOfNulls(lines.first().length) }

    println("${grid.size} x ${grid[0].size}")

    lines.forEachIndexed { row, line ->
        line.split("").slice(1..line.length).forEachIndexed { col, s ->
            grid[row][col] = s
        }

        println(grid[row].joinToString())
    }

    var i = 0
    var j = 0

    while (i < grid.size) {
        while (j < grid[0].size) {
            if (grid[i][j] == "*") {
                gearRatioSum += findGearRatio(grid, i, j)
            }
            j++
        }
        j = 0
        i++
    }

    println(gearRatioSum)
}

fun findGearRatio(grid: Array<Array<String?>>, row: Int, col: Int): Int {
    println("GEAR FOUND AT $row, $col")

    var ratio = 1

    val startRow = if (row == 0) 0 else row - 1
    val endRow = if (row == grid.size - 1) grid.size - 1 else row + 1

    var numStart: Int? = null
    var numEnd: Int? = null

    var nums = mutableListOf<Int>()

    (startRow..endRow).forEach { i ->
        (0..<grid[0].size).forEach { j ->
            if (grid[i][j]!!.toIntOrNull() != null) {
                if (numStart == null) {
                    numStart = j
                } else if (j == grid[0].size - 1) {
                    numEnd = j
                }
            } else {
                if (numStart != null) {
                    numEnd = j - 1
                }
            }

            if (numStart != null && numEnd != null) {
                val overlappingRange = (numStart!! - 1)..(numEnd!! + 1)
                if (overlappingRange.contains(col)) {
                    val partNumber = grid[i].slice(numStart!!..numEnd!!).joinToString(separator = "").toInt()
                    println("- ADJACENT PART NUMBER: $partNumber")

                    nums.add(partNumber)
                }

                numStart = null
                numEnd = null
            }
        }
    }

    if (nums.size >= 2) {
        nums.forEach { num -> ratio *= num }
    }

    ratio = if (ratio == 1) 0 else ratio

    println("- RATIO: $ratio")

    return ratio
}
