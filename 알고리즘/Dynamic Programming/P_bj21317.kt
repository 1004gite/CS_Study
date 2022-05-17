/**
 * n개의 돌이 있고 1번째 돌부터 마지막 돌까지 가야 한다.
 * 작은점프 - 1칸을 이동하고 돌마다 쓰이는 에너지가 다르다
 * 큰점프 - 2칸을 이동하고 돌마다 쓰이는 에너지가 다르다.
 * 매우 큰 점프 - 3칸을 이동하고 에너지를 k 소비한다. 또, 단 한번만 쓸 수 있다.
 *
 * 풀이
 * 1. dp를 이용한다
 * 2. 매우 큰 점프 없이 dp를 업데이트 한다.
 * 3. 업데이트된 dp에서 매우 큰 점프를 사용하는 경우를 고려하여 업데이트한다.
 *      고려시 큰 점프를 사용하지 않는 경우부터 1~17번째 index에서 사용하는 것까지 비교힌다.
 * */

import java.io.*
import java.util.*

class Bj21317 {

    var dp = IntArray(21) { 100001 }

    data class mPair(var first: Int, var second: Int)

    fun main() {

        val br = BufferedReader(InputStreamReader(System.`in`))

        val n = br.readLine().toInt()

        // inedx번째 돌에서 (작은점프, 큰점프)를 하는데 드는 비용
        var energe = Array(n) { mPair(0, 0) }

        var st: StringTokenizer
        for (i in 1 until n) {
            st = StringTokenizer(br.readLine())
            energe[i].first = st.nextToken().toInt()
            energe[i].second = st.nextToken().toInt()
        }

        val k = br.readLine().toInt()
        var min = 100001

        for (case in 0..17) {
            dp = IntArray(21) { 100001 }
            dp[1] = 0
            for (i in 1..n - 2) {
                if (i == case) {
                    if (dp[i] + k < dp[i + 3]) dp[i + 3] = dp[i] + k
                    continue
                }
                if (dp[i] + energe[i].first < dp[i + 1]) dp[i + 1] = dp[i] + energe[i].first
                if (dp[i] + energe[i].second < dp[i + 2]) dp[i + 2] = dp[i] + energe[i].second
            }
            // n-1 -> n 만 따로
            if (dp[n - 1] + energe[n - 1].first < dp[n]) dp[n] = dp[n - 1] + energe[n - 1].first

            if (min > dp[n]) min = dp[n]
        }

        print(min.toString() + "\n")
    }
}