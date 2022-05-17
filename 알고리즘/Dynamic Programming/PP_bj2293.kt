/**
 * 가치가 서로 다른 n가지 동전이 있다.
 * 중복 사용이 가능하게 k원을 만들 수 있는 경우의 수를 구하자.
 * 동전의 구성이 같은데 순서만 다른 경우는 같은 경우이다.
 *
 * 풀이 1
 * 1. 가치가 n원이 되는 경우의 수는 가치가 (n-x원이 되는 경우의 수 +1)과 같다.
 *      이때 x는 동전의 모든 종류이다.
 * x. 2원에는 {1원+1원/ 2원}의 경우가 고려되어 있다.
 *      3월을 고려할 때 1원에서 2원을 더하는 것을 고려하면
 *          2원에서 1원을 더하는 것을 고려하는 것과 겹치게 된다.
 * 풀이 2
 * 1. n-c원에서 c원짜리 동전을 사용하면 n원의 가치를 만들 수 있다.
 * 2. 이때 경우의 수는 dp[n-c]를 dp[n]에 더하게 된다.
 * 3. 풀이 1 에서는 값을 기준으로 모든 coin들을 고려했기 때문에 다음 값에서 이전에 고려했던 coin이 고려될 수 있다.
 * 4. 풀이 2 에서는 coin을 기준으로 값을 업데이트 하기 때문에 한번 고려된 coin은 중복해서 사용되지 않는다.
 *      ex) 1원짜리로 만들 수 있는 경우를 모두 고려한 다음 2원짜리를 고려할 때
 *          (1,1,2) (1,2,1)같은 경우는 나오지 않는다.
 *          -> 1원짜리가 모두 고려되고 2원짜리를 고려하기 때문에 전자만 나온다.
 * */

import java.util.*
import java.io.*

class Bj2293 {
    var dp = IntArray(10001) { 0 }

    fun main() {

        val br = BufferedReader(InputStreamReader(System.`in`))

        var st = StringTokenizer(br.readLine())

        val n = st.nextToken().toInt()
        val k = st.nextToken().toInt()

        var coins = IntArray(n)
        for (i in 0 until n) {
            coins[i] = br.readLine().toInt()
        }
        br.close()

        dp[0] = 1
        for (i in 0 until n) {
            for (j in coins[i]..k) {
                if (j - coins[i] < 0) continue
                dp[j] += dp[j - coins[i]]
            }
        }

        print("${dp[k]} \n")
    }
}