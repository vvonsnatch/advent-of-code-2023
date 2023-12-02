import java.io.File

fun main() {
    print(File("./input/day1/data")
        .readLines()
        .mapIndexed() { i, line ->
            var ints = emptyArray<Int>()

            for (pos in line.indices) {
                if (line[pos].isDigit()) {
                    ints += line[pos].digitToInt()
                } else {
                    if (pos + 2 < line.length && line.substring(pos..pos + 2) == "one") {
                        ints += 1
                    } else if (pos + 2 < line.length && line.substring(pos..pos + 2) == "two") {
                        ints += 2
                    } else if (pos + 4 < line.length && line.substring(pos..pos + 4) == "three") {
                        ints += 3
                    } else if (pos + 3 < line.length && line.substring(pos..pos + 3) == "four") {
                        ints += 4
                    } else if (pos + 3 < line.length && line.substring(pos..pos + 3) == "five") {
                        ints += 5
                    } else if (pos + 2 < line.length && line.substring(pos..pos + 2) == "six") {
                        ints += 6
                    } else if (pos + 4 < line.length && line.substring(pos..pos + 4) == "seven") {
                        ints += 7
                    } else if (pos + 4 < line.length && line.substring(pos..pos + 4) == "eight") {
                        ints += 8
                    } else if (pos + 3 < line.length && line.substring(pos..pos + 3) == "nine") {
                        ints += 9
                    }
                }
            }

            if (ints.size == 0) 0 else "${ints[0]}${ints[ints.size - 1]}".toInt()
        }
        .reduce { acc, num -> acc + num },)
}
