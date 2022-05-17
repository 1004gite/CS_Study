/**
 * n개의 돌이 있고 1번에서 n번 돌로 가야한다. Ai는 i번 돌의 값이다.
 * i 돌에서 j 돌로 갈 때 (j-i)*(1+ |Ai-Aj|)
 * 한칸 이동하는 것보다 여러칸을 한번에 가는 에너지가 더 작을 수 있다.
 * 한번에 쓸 수 있는 최대 에너지는 k이다.
 *
 * 풀이
 * 1. 해당 index까지 올 수 있는지 검사하여 기록한다.
 *      하나의 경우라도 올 수 있으면 ok이다.
 * 2. 중간에 갈 수 없는 곳이 있어도 모두 검사한다.
 * */

import java.io.*
import java.util.*
import kotlin.math.*

class Bj22869 {
    var dp = BooleanArray(5001) { false }
    var A = IntArray(5001) { 0 }

    fun main() {

        val br = BufferedReader(InputStreamReader(System.`in`))

        var st = StringTokenizer(br.readLine())

        val n = st.nextToken().toInt()
        val k = st.nextToken().toInt()

        st = StringTokenizer(br.readLine())
        for (i in 1..n) {
            A[i] = st.nextToken().toInt()
        }
        br.close()

        dp[1] = true
        for (i in 2..n) {
            // (j-i)*(1+ |Ai-Aj|)
            for (j in 1 until i) {
                if (!dp[j]) continue
                if ((i - j) * (1 + (A[j] - A[i]).absoluteValue) <= k) {
                    dp[i] = true
                    break
                }
            }
        }

        if (dp[n]) print("YES\n")
        else print("NO\n")
    }
}