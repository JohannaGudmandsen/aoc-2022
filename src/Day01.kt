import java.io.File
import java.util.regex.*
import kotlin.collections.*
import kotlin.text.split

fun Day01() {
    val snacksPerPersons = File("src/input/day01.txt").readText().split("\r\n\r\n")

    var calorieMapSorted =
            snacksPerPersons.map { it.split("\r\n").map { it.toInt() }.sum() }.sortedDescending()

    val task1 = calorieMapSorted.first()
    val task2 = calorieMapSorted.take(3).sum()

    println("Task 1: $task1")
    println("Task 2: $task2")
}