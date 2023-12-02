import java.io.File

fun main() {

        File("./input/day1/data")
            .readLines()
            .mapIndexed() { i, line ->
                var pos = 0
                var ints = emptyArray<Int>()

                while (pos < line.length) {
                    if (line[pos].isDigit()) {
                        ints += line[pos].digitToInt()
                        pos++
                    } else {
                        if (pos + 2 < line.length && line.substring(pos..pos + 2) == "one") {
                            ints += 1
                            pos += 3
                        } else if (pos + 2 < line.length && line.substring(pos..pos + 2) == "two") {
                            ints += 2
                            pos += 3
                        } else if (pos + 4 < line.length && line.substring(pos..pos + 4) == "three") {
                            ints += 3
                            pos += 4
                        } else if (pos + 3 < line.length && line.substring(pos..pos + 3) == "four") {
                            ints += 4
                            pos += 3
                        } else if (pos + 3 < line.length && line.substring(pos..pos + 3) == "five") {
                            ints += 5
                            pos += 3
                        } else if (pos + 2 < line.length && line.substring(pos..pos + 2) == "six") {
                            ints += 6
                            pos += 2
                        } else if (pos + 4 < line.length && line.substring(pos..pos + 4) == "seven") {
                            ints += 7
                            pos += 4
                        } else if (pos + 4 < line.length && line.substring(pos..pos + 4) == "eight") {
                            ints += 8
                            pos += 4
                        } else if (pos + 3 < line.length && line.substring(pos..pos + 3) == "nine") {
                            ints += 9
                            pos += 3
                        } else {
                            pos++
                        }
                    }
                }

                val sum = if (ints.size == 0) 0 else "${ints[0]}${ints[ints.size - 1]}".toInt()

                println("${i+1} ${line} ${sum}")

                sum
            }
//            .reduce { acc, num -> acc + num },

}
