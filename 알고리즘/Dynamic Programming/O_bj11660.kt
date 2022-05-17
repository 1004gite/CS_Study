/**
 * n*n 크기의 표가 있다.
 * (x1,y1) ~ (x2,y2)까지의 합을 구하자
 * 0. x,y는 좌표가 아닌 행렬이다.
 * 1. 합을 여러번 구해야 하므로 dp로 미리 값을 구해놓는다.
 * 2. 값은 누적합으로 구해놓고 적절히 뺀다.
 *  이때 누적합은 자신이 오른쪽 아래 점이 된다.
 * 3. (x1, y1)~(x2, y2)=(x2,y2) - (x2, y1-1) - (x1-1, y2) + (x1-1, y1-1)
 *      (1,1)~(x,y) = (x-1, y) + 가로누적
 * */

import java.io.*
import java.util.*

class Bj11660 {
    private var dp: Array<IntArray> = Array(1024) { IntArray(1024) { -1 } }

    fun main() {

        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.out))

        var st = StringTokenizer(br.readLine())

        val n = st.nextToken().toInt()
        val m = st.nextToken().toInt()

        // i는 행, j는 열
        for (i in 0 until n) {
            var acc = 0
            st = StringTokenizer(br.readLine())
            for (j in 0 until n) {
                var tmp = st.nextToken().toInt()
                acc += tmp
                dp[i][j] = acc
                if (i > 0) dp[i][j] += dp[i - 1][j]
            }
        }

        //(x1, y1)~(x2, y2)=(x2,y2) - (x2, y1-1) - (x1-1, y2) + (x1-1, y1-1)
        for (i in 0 until m) {
            st = StringTokenizer(br.readLine())
            val x1 = st.nextToken().toInt() - 1
            val y1 = st.nextToken().toInt() - 1
            val x2 = st.nextToken().toInt() - 1
            val y2 = st.nextToken().toInt() - 1

            var result: Int = dp[x2][y2]
            if (y1 > 0) result -= dp[x2][y1 - 1]
            if (x1 > 0) result -= dp[x1 - 1][y2]
            if (x1 > 0 && y1 > 0) result += dp[x1 - 1][y1 - 1]
            bw.write(result.toString() + "\n")
        }

        bw.flush()

        br.close()
        bw.close()

    }
}