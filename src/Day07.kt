import java.io.File
import kotlin.collections.*

class Node(var Name: String, var Parent:Node?, var Children:ArrayList<Node>, var Size:Int)

fun getNodeSize(node:Node, size:Int):Int{
    // File
    if (node.Size !=  0){
        return node.Size
    }
    // Directory
    return node.Children.map { it -> getNodeSize(it, size) }.sum() + size
}

fun Day07() {
    val input = File("src/input/day07.txt").readLines()

    var rootNode : Node = Node("/", null, ArrayList<Node>(), 0)
    var nodeList = ArrayList<Node>()
    nodeList.add(rootNode)

    var currentNode = rootNode
    for (line in input){
        var command = line.split(" ")

        if (line.startsWith("$ cd ..")){
            currentNode = currentNode.Parent!!
        }
        else if (line.startsWith("$ cd") && command[2] != "/"){
            var newNode = Node(command[2], currentNode, ArrayList<Node>(), 0)
            currentNode.Children.add(newNode)
            nodeList.add(newNode)
            currentNode = newNode
        }
        else if (command[0].all { it -> it.isDigit() }){
            currentNode.Children.add(Node(command[1], currentNode, ArrayList<Node>(), command[0].toInt()))
        }
    }

    val nodeSizes = nodeList.map { it -> getNodeSize(it, 0) }

    val task1 = nodeSizes.filter { it -> it <= 100000 }.sum()

    val unused = 70000000 - getNodeSize(rootNode, 0)
    val required = 30000000 - unused
    val task2 = nodeSizes.filter { it -> it >= required }.min()

    println("Day 7 Task 1: $task1") // 1477771
    println("Day 7 Task 2: $task2") // 3579501
}