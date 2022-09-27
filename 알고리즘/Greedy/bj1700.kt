import java.util.StringTokenizer
import kotlin.math.*

class bj1700 {
    fun solve(){
        var st = StringTokenizer(readln())
        val n = st.nextToken().toInt()
        val k = st.nextToken().toInt()
        st = StringTokenizer(readln())
        val order = IntArray(k){st.nextToken().toInt()}

        if(n >= k){
            println(0)
            return
        }

        val hSet = hashSetOf<Int>()
        var index = 0
        while(index < n){
            if(hSet.contains(order[index])) continue
            hSet.add(order[index])
            index++
        }
        var count = 0
        while(index < k){
//            hSet.forEach { print("$it ") }
//            println()
            if(hSet.contains(order[index])) {
                index++
                continue
            }
            var tmp = index+1
            val tmpSet = hSet.toHashSet()
            while(true){
                if(tmp == k){
                    hSet.remove(tmpSet.first())
                    hSet.add(order[index])
                    count++
                    break
                }
                tmpSet.remove(order[tmp])
                if(tmpSet.size == 1){
//                    println("remove ${order[tmp]} for ${order[index]}")
                    hSet.remove(tmpSet.first())
                    hSet.add(order[index])
                    count++
                    break
                }
                tmp++
            }
            index++
        }

        println(count)
    }
}

fun main(){
    bj1700().solve()
}