import java.util.*

class bj12865 {
}

/**
 * n개의 물건이 있고 무게w, 가치v를 가진다.
 * 가방에는 최대 k무게만큼 넣을 수 있다.
 * 넣을 수 있는 최대 가치를 구하라
 *
 * 1. greedy 하게 넣는 것이 최적의 해가 아니다.
 * 2. 완탐을 사용할 시 최악의 경우 100!의 경우의 수가 나온다.
 * 3. dp를 사용한다.
 * 4. n*k의 배열과 k의 배열을 만든다.
 * 5. k배열에는 최대 가치를 저장하고 n*k에는 그때 넣은 물건을 기록한다.
 *
 * 풀이 2 // 냅색 문제
 * 1. n*k 배열을 만든다. row는 현재 가방의 최대 무게, col은 해당 index까지 물건을 고려했을 때의 최대치를 나타낸다.
 * */

fun main(){

    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    // w to v
    val info = Array(n){
        st = StringTokenizer(br.readLine())
        st.nextToken().toInt() to st.nextToken().toInt()
    }
    val dp = Array(k+1){IntArray(n)}
    for(i in info[0].first..k){
        // 가장 처음 가방은 넣을 수 있으면 넣으면 이득
        dp[i][0] = info[0].second
    }

    for(i in 1..k){
        // i는 가방의 무게
        for(j in 1 until n){
            // j는 가방의 번호
            val w = info[j].first
            val v = info[j].second
//            // 현재 고려할 가방을 넣지 않을 경우
//            dp[i][j] = dp[i][j-1]
            // 현재 고려할 가방을 넣을 수 있는 경우 고려한다.
            if(w <= i) dp[i][j] = maxOf(dp[i][j-1], dp[i-w][j-1]+v)
            else dp[i][j] = dp[i][j-1]
        }
    }

    println(dp[k][n-1])
}