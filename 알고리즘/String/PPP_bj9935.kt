import java.util.*

class bj9935 {
}

fun main(){
    val str = readln()
    val boom = readln()
    if(str.length < boom.length){
        println(str)
        return
    }
    val left = ArrayDeque<Char>()
    val sb = StringBuilder(str.substring(0,boom.length))
    val right = ArrayDeque<Char>().apply {
        for(i in boom.length until str.length) addLast(str[i])
    }

    while(true){
        if(right.isEmpty()) break

        if( sb.toString() == boom ) {
            // 같아서 삭제
            sb.clear()
            // 왼쪽에서 가져올 수 있는만큼 먼저 가져옴
            while(sb.length < boom.length && left.isNotEmpty()) sb.insert(0,left.removeLast())
            // 부족하다면 오른쪽에서 가져옴
            while(sb.length < boom.length && right.isNotEmpty()) sb.append(right.removeFirst())
        }
        else {
            // sb의 처음을 left로
            left.addLast(sb.first())
            sb.deleteCharAt(0)
            // right의 처음을 sb로
            sb.append(right.removeFirst())
        }
    }

    // 현재 sb에 대한 판단
    if(sb.toString() == boom) sb.clear()
    var result = String(left.toCharArray()) + sb.toString()
    if(result.length == 0) result = "FRULA"
    println(result)
}