import java.io.File
import kotlin.collections.*

class Test(var divisibleBy:Int, var trueThrowsToMonkey:Int, var falseThrowsToMonkey:Int)

class Operation(var type:String, var rightValue:String, var leftValue:String)

class Monkey(var id:Int, var items:ArrayList<Long>, var op:Operation, var test:Test, var inspect:Long)

fun doOneRound(monkeys:ArrayList<Monkey>, divideWorryLevelBy3:Boolean, modWorryLevelWith:Int){
    for (monkey in monkeys){
        for (item in monkey.items){
            monkey.inspect++

            var left:Long
            var right:Long
            if (monkey.op.rightValue.all { it -> it.isDigit() }){
                right = monkey.op.rightValue.toLong()
            }
            else {
                right = item
            }

            if (monkey.op.leftValue.all { it -> it.isDigit() }){
                left = monkey.op.leftValue.toLong()
            }
            else {
                left = item
            }

            var worryLevel:Long
            if (monkey.op.type == "+"){
                worryLevel = (left + right)
            }
            else {
                worryLevel = (left * right)
            }

            if (divideWorryLevelBy3){
                worryLevel /= 3
            }
            else {
                worryLevel = worryLevel % modWorryLevelWith
            }

            if (worryLevel.mod(monkey.test.divisibleBy.toLong()) == 0.toLong()){
                var throwToMonkey = monkeys.filter { it -> it.id == monkey.test.trueThrowsToMonkey }.first()
                throwToMonkey.items.add(worryLevel)
            }
            else {
                var throwToMonkey = monkeys.filter { it -> it.id == monkey.test.falseThrowsToMonkey }.first()
                throwToMonkey.items.add(worryLevel)
            }
        }

        monkey.items = ArrayList<Long>()
    }
}

fun Day11() {
    val input = File("src/input/day11.txt").readLines()

    var monkeysTask1 = ArrayList<Monkey>()
    var monkeysTask2 = ArrayList<Monkey>()
    var modWorryLevelWith = 1

    for(i in 0..input.size-1 step 7){
        var monkeyId = input[i].filter { it.isDigit() }.toInt()

        var startingItems = input[i+1].split(":")[1].split(",").map { it.filter { it.isDigit() }.toLong() }

        var op = input[i+2].split("=")[1].split(" ")

        var divisibleBy = input[i+3].filter { it.isDigit() }.toInt()
        modWorryLevelWith = modWorryLevelWith * divisibleBy

        var ifTrueThrowToMonkey = input[i+4].filter { it.isDigit() }.toInt()
        var ifFalseThrowToMonkey = input[i+5].filter { it.isDigit() }.toInt()

        var monkeyTask1 = Monkey(
            monkeyId, 
            ArrayList<Long>(startingItems),
            Operation(op[2], op[1], op[3]),
            Test(divisibleBy, ifTrueThrowToMonkey, ifFalseThrowToMonkey),
            0)

        var monkeyTask2 = Monkey(
            monkeyId, 
            ArrayList<Long>(startingItems),
            Operation(op[2], op[1], op[3]),
            Test(divisibleBy, ifTrueThrowToMonkey, ifFalseThrowToMonkey),
            0)

        monkeysTask1.add(monkeyTask1)
        monkeysTask2.add(monkeyTask2)
    }

    for (i in 0..19 step 1){
        doOneRound(monkeysTask1, true, modWorryLevelWith)
    }

    for (i in 0..9999 step 1){
        doOneRound(monkeysTask2, false, modWorryLevelWith)
    }

    var inspectList = monkeysTask1.map { it -> it.inspect }.sortedDescending()
    var task1 = inspectList[0] * inspectList[1]

    var inspectListTask2 = monkeysTask2.map { it -> it.inspect }.sortedDescending()
    var task2:Long = inspectListTask2[0].toLong() * inspectListTask2[1].toLong()
    
    println("Day 11 Task 1: $task1") // 55216
    println("Day 11 Task 2: $task2") // 12848882750
}