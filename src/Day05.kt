import java.io.File
import kotlin.collections.*

class StackDefinition (var name:String, var indexInLine:Int, var stack: ArrayDeque<Char>)

fun Day05() {
    val inputs = File("src/input/day05.txt").readLines()

    var stackDefinition = ArrayList<StackDefinition>()
    for (input in inputs){
        if (!input.contains("""[""")){
            val trimmedInput = input.trim()
            var stackInputs = trimmedInput.split("   ")
            for (stackInput in stackInputs){
                stackDefinition.add(StackDefinition(stackInput, input.indexOf(stackInput), ArrayDeque<Char>()))
            }

            break;
        }
    }
    
    for (input in inputs){
        if (input.contains("""[""")){
            for (stack in stackDefinition){
                val stackChar = input[stack.indexInLine]
                if (stackChar.isUpperCase()){
                    stack.stack.add(stackChar)
                }
            }
        }
        else {
            break;
        }
    }

    var stacks = ArrayList<ArrayDeque<Char>>()
    var stacks2 = ArrayList<ArrayDeque<Char>>()
    
    for (stack in stackDefinition){
        stacks.add(ArrayDeque<Char>(stack.stack))
        stacks2.add(ArrayDeque<Char>(stack.stack))
    }

    for (i in 10..inputs.size-1 step 1){
        var input = inputs[i].split(" ")
        var moveNumber = input[1].toInt()
        var stackFromTask1 = stacks[input[3].toInt()-1]
        var stackToTask1 = stacks[input[5].toInt()-1]
        var stackFromTask2 = stacks2[input[3].toInt()-1]
        var stackToTask2 = stacks2[input[5].toInt()-1]

        var taken = ArrayList<Char>(stackFromTask2.take(moveNumber))
        for (j in 0..moveNumber-1 step 1){
            if (stackFromTask1.size > 0){
                stackToTask1.addFirst(stackFromTask1.first())
                stackFromTask1.removeFirst()
            }
            
            if (stackFromTask2.size > 0){
                stackToTask2.addFirst(taken.last())
                stackFromTask2.remove(taken.last())
                taken.removeLast()
            }
        }
    }

    var task1 = stacks.map { it -> it.first() }.joinToString("")
    var task2 = stacks2.map { it -> it.first() }.joinToString("")
    
    println("Day 5 Task 1: $task1") // HNSNMTLHQ
    println("Day 5 Task 2: $task2") // RNLFDJMCT
}