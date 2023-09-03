import java.util.ArrayList
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

fun main() {

    val stack = mutableListOf<Int>()
    // 삽입
    val push = measureNanoTime {
        repeat(100000) {
            stack.add(1)
        }
    }
    val pop = measureNanoTime {
        repeat(100000) {
            stack.removeLast()
        }
    }

    println("== ${stack.javaClass.name} ==")
    print("push: $push, pop: $pop\n\n")

//    val stackLinkedList = LinkedList<Int>()
//
//    // 삽입
//    stackLinkedList.add(1)
//
//    // 삭제
//    stackLinkedList.removeLast()
//
//    stackLinkedList.isEmpty()
//    val stackMutableList = mutableListOf<Int>()
//    // 삽입
//    stackMutableList.add(1)
//
//    // 삭제
//    stackMutableList.removeLast()
//
//    stackMutableList.isEmpty()
//    val stackDeque = ArrayDeque<Int>()
//    // 삽입
//    stackDeque.add(1)
//
//    // 삭제
//    stackDeque.removeLast()
//
//    stackDeque.isEmpty()
}

//import java.io.BufferedReader
//import java.io.BufferedWriter
//import java.io.InputStream
//import java.io.InputStreamReader
//import java.io.OutputStreamWriter
//import java.util.Scanner
//class Test {
//}

//fun main(){
////    // "test string"
////    val str1: String? = readLine()
////
////    // "0 1 2 4"
////    val str2: String = readln()
////    val arr = str2.split(' ')
////
////    // "test String"
////    println(str1)
////    // "0/ 1/ 2/ 4/"
////    arr.forEach {
////        print("$it/ ")
////    }
//
////    val scanner = Scanner(System.`in`)
////    // "1 2 3 4"
////    val str1 = scanner.nextLine()
////    // "1 2 3 4"
////    println(str1)
////
////
////    while(scanner.hasNext()) {
////        val nextInt = scanner.nextInt()
////        print("$nextInt/ ")
////    }
//
////    import java.io.*
////
////
////    val br1 = BufferedReader(InputStreamReader(System.`in`))
////    val str1 = br1.readLine()
////    val bw1 = BufferedWriter(OutputStreamWriter(System.out))
////    bw1.write(str1)
////    bw1.flush()
////    bw1.close()
////
////    val br2 = System.`in`.bufferedReader()
////    br2.readLine()
////    val bw2 = System.out.bufferedWriter()
////    bw2.write(str1)
////    bw2.flush()
////    bw2.close()
//
//
//}