import java.util.*

class bj1806 {
}

fun main(){
    val br = System.`in`.bufferedReader()

    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val s = st.nextToken().toInt()

    st = StringTokenizer(br.readLine())
    val arr = IntArray(n){st.nextToken().toInt()}

    var start = 0
    var end = 0
    var sum = arr[0]
    var min = Int.MAX_VALUE
    while(true){
        if(sum >= s){
            min = minOf(min, end-start+1)
            sum -= arr[start]
            start++
        }
        else if(end == n-1) break
        else {
            end++
            sum += arr[end]
        }
    }

    println(if(min != Int.MAX_VALUE) min else 0)
}