import java.io.File
import kotlin.collections.*

class Position(var X:Int, var Y:Int)

fun moveTail(head:Position, tail:Position){
    if (head.X > tail.X + 1){
        tail.X++
        if (head.Y > tail.Y){
            tail.Y++
        }
        else if (head.Y < tail.Y){
            tail.Y--
        }
    }
    else if (head.X < tail.X - 1){
        tail.X--
        if (head.Y > tail.Y){
            tail.Y++
        }
        else if (head.Y < tail.Y){
            tail.Y--
        }
    }
    else if (head.Y > tail.Y + 1){
        tail.Y++
        if (head.X > tail.X){
            tail.X++
        }
        else if (head.X < tail.X){
            tail.X--
        }
    }
    else if (head.Y < tail.Y - 1 ){
        tail.Y--
        if (head.X > tail.X){
            tail.X++
        }
        else if (head.X < tail.X){
            tail.X--
        }
    }
}

fun Day09() {
    val input = File("src/input/day09.txt").readLines()

    var head = Position(500,500)
    var tailTask1 = Position(500,500)
    
    var tailsTask2 = ArrayList<Position>()
    tailsTask2.add(Position(500, 500))
    tailsTask2.add(Position(500, 500))
    tailsTask2.add(Position(500, 500))
    tailsTask2.add(Position(500, 500))
    tailsTask2.add(Position(500, 500))
    tailsTask2.add(Position(500, 500))
    tailsTask2.add(Position(500, 500))
    tailsTask2.add(Position(500, 500))
    tailsTask2.add(Position(500, 500))

    var visitedPositionsTask1 = ArrayList<String>()
    var visitedPositionsTask2 = ArrayList<String>()

    for(line in input){
        var instruction = line.split(" ")
        var direction = instruction[0]
        var times = instruction[1].toInt()

        for(i in 0..times-1 step 1){
            if (direction == "R"){
                head.X++
            }
            else if (direction == "L"){
                head.X--
            }
            else if (direction == "U"){
                head.Y++
            }
            else { // "D"
                head.Y--
            }

            moveTail(head, tailTask1)
            visitedPositionsTask1.add(tailTask1.X.toString() + tailTask1.Y.toString())

            var prevTail = head
            for(tailTask2 in tailsTask2){
                moveTail(prevTail, tailTask2)
                prevTail = tailTask2
            }

            visitedPositionsTask2.add(tailsTask2.last().X.toString() + tailsTask2.last().Y.toString())
       }
    }

    var task1 = visitedPositionsTask1.distinct().count()
    var task2 = visitedPositionsTask2.distinct().count()

    println("Day 9 Task 1: $task1") // 6563
    println("Day 9 Task 2: $task2") // 2653
}