import java.io.File

fun main() {
    print(
        File("./input/day1/pt1")
            .readLines()
            .map { line ->
                val ints = line.split("").filter { c -> c.toIntOrNull() != null }
                "${ints[0]}${ints[ints.size - 1]}".toInt()
            }
            .reduce { acc, num -> acc + num },
    )
}
