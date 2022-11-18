import java.util.*

class bj10986 {
}

fun main(){
    // nC2 = n(n-1)/2
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    st = StringTokenizer(br.readLine())
    val arr = IntArray(m)
    var sum = 0
    repeat(n){
        sum += st.nextToken().toInt()
        sum %= m
        arr[sum]++
    }

    var result = arr[0].toLong()
    for(i in arr){
        if(i == 0) continue
        result += i.toLong()*(i.toLong()-1)/2
    }

    println(result)
}