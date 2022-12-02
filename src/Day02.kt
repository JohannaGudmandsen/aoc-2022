import java.io.File
import kotlin.collections.*

fun getForTask1(game:String):Int{
    when (game) {
        "A X" -> return 1 + 3
        "A Y" -> return 2 + 6
        "A Z" -> return 3 + 0
        "B X" -> return 1 + 0
        "B Y" -> return 2 + 3 
        "B Z" -> return 3 + 6
        "C X" -> return 1 + 6
        "C Y" -> return 2 + 0
        "C Z" -> return 3 + 3
        else -> {
            return 0
        }
    }
}

fun getForTask2(game:String):Int{
    when (game) {
        "A X" -> return 0 + 3
        "A Y" -> return 3 + 1
        "A Z" -> return 6 + 2
        "B X" -> return 0 + 1
        "B Y" -> return 3 + 2
        "B Z" -> return 6 + 3
        "C X" -> return 0 + 2
        "C Y" -> return 3 + 3
        "C Z" -> return 6 + 1
        else -> {
            return 0
        }
    }
}

fun Day02() {
    val gameSchedule = File("src/input/day02.txt").readLines()

    val task1 = gameSchedule.map { it -> getForTask1(it) }.sum()
    val task2 = gameSchedule.map { it -> getForTask2(it) }.sum()

    println("Day 2 Task 1: $task1")
    println("Day 2 Task 2: $task2")
}