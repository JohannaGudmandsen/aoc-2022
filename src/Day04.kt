import java.io.File
import kotlin.collections.*

fun Day04() {
    val assignmentPairs = File("src/input/day04.txt").readLines()

    var task1 = 0
    var task2 = 0
    for (assignmentPair in assignmentPairs){
        var assignments = assignmentPair.split(',')
        var assignmentElf1 = assignments[0].split('-').map { it -> it.toInt() }
        var assingmentElf2 = assignments[1].split('-').map { it -> it.toInt() }

        var a = assignmentElf1[0]
        var b = assignmentElf1[1]
        var x = assingmentElf2[0]
        var y = assingmentElf2[1]

        if ((a <= x && b >= x) || (a <= y && b >= y)
        || (x <= a && y >= a) || (x <= b && y >= b) )
        {
            task2++
        }

        if ((a <= x && b >= y) || (x <= a && y >= b)) {
            task1++
        }
    }

    println("Day 4 Task 1: $task1")
    println("Day 4 Task 2: $task2")
}