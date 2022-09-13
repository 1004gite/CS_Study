/**
 * N개의 정수 중 최소 최대값을 구하라
 * */

import java.io.*
import java.util.*
import kotlin.math.*

class bj20053 {

    fun solve(){
        val br = BufferedReader(InputStreamReader(System.`in`))
        var t = br.readLine().toInt()

        while(t > 0){
            t--

            val n = br.readLine().toInt()
            var max = -1000001
            var min = 1000001
            val st = StringTokenizer(br.readLine())
            repeat(n){
                val tmp = st.nextToken().toInt()
                max = max(max, tmp)
                min = min(min,tmp)
            }

            println("$min $max")
        }
    }
}

fun main(){
    bj20053().solve()
}