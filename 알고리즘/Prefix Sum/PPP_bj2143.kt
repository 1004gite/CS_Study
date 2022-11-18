import java.io.*
import java.util.*

class bj2143 {
}

fun getArr(br: BufferedReader): LongArray{
    val size = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    return LongArray(size){st.nextToken().toLong()}
}

fun main(){
    val br = System.`in`.bufferedReader()

    val t = br.readLine().toLong()

    // 배열을 받고 가능한 모든 subsequence를 구한 후 각 합을 배열에 담는다.
    val a1 = getArr(br)
    val a2 = getArr(br)
    br.close()

    val map = hashMapOf<Long, Long>()
    for(i in a1.indices){
        var sum = 0L
        for(j in i until a1.size){
            sum += a1[j]
            map[sum] = 1L + (map[sum] ?: 0L)
        }
    }

    var result = 0L
    for(i in a2.indices){
        var sum = 0L
        for(j in i until a2.size){
            sum += a2[j]
            result += map[t-sum] ?: 0L
        }
    }

    println(result)

}