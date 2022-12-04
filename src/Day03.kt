import java.io.File
import kotlin.collections.*

fun getValueOfLowerCaseLetter(input:Char):Int{ 
    return input.code - 'a'.code + 1
}

fun getValueOfUpperCaseLetter(input:Char):Int{ 
    return input.code - 'A'.code + 27
}

fun Day03() {
    val rucksacks = File("src/input/day03.txt").readLines()

    var task1 = 0
    for (rucksack in rucksacks) {
        var compartment1 = rucksack.substring(0, rucksack.length/2)
        var compartment2 = rucksack.substring(rucksack.length/2)
        
        var commonChar = compartment1.toList()
            .intersect(compartment2.toList())
            .first()
        
        if (commonChar.isUpperCase()) {
            task1 += getValueOfUpperCaseLetter(commonChar)
        }
        else {
            task1 += getValueOfLowerCaseLetter(commonChar)
        }
    }

    var task2 = 0
    for (i in 0..rucksacks.size-1 step 3) {
        var commonChar = rucksacks[i].toList()
            .intersect(rucksacks[i+1].toList())
            .intersect(rucksacks[i+2].toList())
            .first()

        if (commonChar.isUpperCase()) {
            task2 += getValueOfUpperCaseLetter(commonChar)
        }
        else {
            task2 += getValueOfLowerCaseLetter(commonChar)
        }
    }

    println("Day 3 Task 1: $task1")
    println("Day 3 Task 2: $task2")
}