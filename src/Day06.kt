import java.io.File
import kotlin.collections.*

fun Day06() {
    val input = File("src/input/day06.txt").readText()

    var task1 = 0
    for (i in 0..input.length-1 step 1){
        var seq = input.subSequence(i, i+4)
        if (seq.toSet().size == 4){
            task1 = i+4
            break;
        }
    }

    var task2 = 0
    for (i in 0..input.length-1 step 1){
        var seq = input.subSequence(i, i+14)
        if (seq.toSet().size == 14){
            task2 = i+14
            break;
        }
    }

    println("Day 6 Task 1: $task1") // 1953
    println("Day 6 Task 2: $task2") // 2301
}