import java.util.*

class bj2745 {
}

/**
 * B진법 수 N이 주어진다.
 * 이 수를 10진법으로 바꿔라
 * */

fun main(){
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())

    val n = st.nextToken()
    val b = st.nextToken().toInt()

    var result = 0
    var mul = 1
    for(i in n.length-1 downTo 0){
        result += charToInt(n[i])*mul
        mul *= b
    }

    println(result)
}

fun charToInt(c: Char): Int{
    if(c in 'A'..'Z') return 10 + c.code-'A'.code
    return c.code-'0'.code
}