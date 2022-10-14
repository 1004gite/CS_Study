import java.util.StringTokenizer
import kotlin.math.*

class O_bj1120 {
}

fun main(){
    val st = StringTokenizer(readln())
    val a = st.nextToken()
    val b = st.nextToken()

    var min = Int.MAX_VALUE
    for( index in 0..b.length-a.length ){
        val tmp = b.substring(index, index+a.length)
//        print("$tmp, ")
        var count = 0
        for(i in tmp.indices){
            if(tmp[i] != a[i]) count++
        }
        min = min(min, count)
    }
    println(min)
}