import java.util.*

class bj14501 {
}

fun main(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    // 소요 날짜 to 보상
    val info = Array(n){
        val st = StringTokenizer(br.readLine())
        st.nextToken().toInt() to st.nextToken().toInt()
    }

    // index = 날짜, value = 최대 이득
    // 날짜를 반대로 접근한다.
    val dp = IntArray(n+1)
    for(date in n-1 downTo 0){
        // date ~ n-1일 까지 고려했을 때의 최대치
        val endDate = date + info[date].first - 1
        if(endDate >= n) dp[date] = dp[date+1] // 일을 할 수 없을 경우 이전 상태를 가져온다.
        // 일을 할 수 있는 경우 이전 상태와, 이 일을 한 상태를 비교한다.(이 일을 했을 때는 endDate까지 일을 하지 못하기 때문에 endDate+1에서 이번 일의 보상을 더한다.)
        else dp[date] = maxOf(dp[date+1], dp[endDate+1] + info[date].second)
    }
    println(dp[0])
}