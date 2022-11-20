import java.util.*

class bj1920 {
}

fun main(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    var st = StringTokenizer(br.readLine())
    val arr = IntArray(n){ st.nextToken().toInt() }.apply { sort() }
    var t = br.readLine().toInt()
    st = StringTokenizer(br.readLine())
    val sb = StringBuilder()
    while (t-- > 0){
        val target = st.nextToken().toInt()
        if(arr.binarySearch(target) < 0) sb.append('0')
        else sb.append('1')
        sb.append('\n')
    }
    print(sb)
}