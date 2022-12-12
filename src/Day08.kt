import java.io.File
import kotlin.collections.*

fun getViewingScore(height:Int, trees:List<Int>):Int{
    var viewingScore = 0
    for (tree in trees){
        viewingScore++
        if (tree >= height){
            break;
        }
    }

    return viewingScore;
}

fun Day08() {
    val input = File("src/input/day08.txt").readLines()
    var forest = input.map { treeLine -> treeLine.map { tree -> Integer.parseInt("" + tree) }}

    var task1 = 0
    var task2 = 0
    for (i in 0..forest.size-1 step 1){
        var treeLine = forest[i]
        for (j in 0..treeLine.size-1 step 1){
            val treeHeight = treeLine[j]
            
            // Check right
            var treesToRight = treeLine.subList(j+1, treeLine.size)
            var viewingscoreRight = getViewingScore(treeHeight, treesToRight)

            // Check left
            var treesToLeft = treeLine.subList(0, j).reversed()
            var viewingscoreLeft = getViewingScore(treeHeight, treesToLeft)

            // Check down
            var treesToDown = forest.subList(i+1, forest.size).map { it -> it.get(j) }
            var viewingscoreDown = getViewingScore(treeHeight, treesToDown)

            // Check up
            var treesToUp = forest.subList(0, i).map { it -> it.get(j) }.reversed()
            var viewingscoreUp = getViewingScore(treeHeight, treesToUp)

            val visible = !treesToRight.any { it -> it >= treeHeight } 
            || !treesToLeft.any { it -> it >= treeHeight }
            || !treesToUp.any { it -> it >= treeHeight }
            || !treesToDown.any { it -> it >= treeHeight }
            if (visible){
                task1++
            }
            
            var totalViewingScore = viewingscoreRight * viewingscoreLeft * viewingscoreDown * viewingscoreUp
            if (totalViewingScore > task2){
                task2 = totalViewingScore
            }
        }
    }

    println("Day 8 Task 1: $task1") //1794
    println("Day 8 Task 2: $task2") // 199272
}