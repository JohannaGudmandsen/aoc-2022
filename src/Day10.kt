import java.io.File
import kotlin.collections.*


fun drawPixel(spritePos:Int, x:Int, image:ArrayList<Char>):Int{
    if (spritePos == x || spritePos + 1 == x || spritePos - 1 == x){
        image.add('#')
    }
    else{
        image.add('.')
    }

    var newSpritePos = spritePos + 1
    if (newSpritePos > 39){
        newSpritePos = 0
    }

    return newSpritePos
}

fun checkCycle(cycle:Int, x:Int):Int{
    if (cycle == 20 || cycle == 60 || cycle == 100 
    || cycle == 140 || cycle == 180 || cycle == 220){
        return cycle*x
    }

    return 0
}

fun Day10() {
    val input = File("src/input/day10.txt").readLines()

    var cycle = 1
    var x = 1
    var signalStrengthSum = 0
    var image = ArrayList<Char>()
    var spritePos = 0

    for (line in input){
        var instruction = line.split(" ")
        var function = instruction[0]
        cycle++
        spritePos = drawPixel(spritePos, x, image)
        signalStrengthSum += checkCycle(cycle, x)

        if (function.startsWith("add")){
            spritePos = drawPixel(spritePos, x, image)

            cycle++
            var value = instruction[1].toInt()
            x += value
            signalStrengthSum += checkCycle(cycle, x)
        }
    }

    println("Day 10 Task 1: $signalStrengthSum") // 12740
    println("Day 10 Task 2: ")
    println(image.subList(0, 40))
    println(image.subList(40, 80))
    println(image.subList(80, 120))
    println(image.subList(120, 160))
    println(image.subList(160, 200))
    println(image.subList(200, 240))
}