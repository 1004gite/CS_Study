import java.util.*
import kotlin.math.*

/**
 * 상자의 크기가 주어진다.
 * 왼쪽 상자가 오른쪽 상자보다 크면 오른쪽 상자에 넣을 수 있다.
 * 넣을 수있는 상자의 최대 개수를 구하라
 *
 * 풀이 1
 * 1. 넣을 수 있는 최대 개수를 업데이트하며 진행한다.
 * 2. 이때
 * */
class bj1965 {

    fun solve(){
        val n = readln().toInt()
        val st = StringTokenizer(readln())
        val info = IntArray(n){ st.nextToken().toInt() }
        val dp = IntArray(n)

        for(i in 1 until dp.size){

            for(j in i-1 downTo 0){
                // 더 크면 먹을 수 없다.
                if(info[i] <= info[j]) continue
                dp[i] = max(dp[i], dp[j]+1)
            }
        }

        println( dp.maxOrNull()!!+1 )

    }

}

fun main(){
    bj1965().solve()
}