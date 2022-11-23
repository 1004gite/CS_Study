import java.util.*

class bj11404 {
}

fun main(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val m = br.readLine().toInt()

    val dp = Array(n+1){ s ->
        LongArray(n+1){ e-> if(s == e) 0 else Long.MAX_VALUE  }
    }

    repeat(m){
        with(StringTokenizer(br.readLine())){
            val start = this.nextToken().toInt()
            val end = this.nextToken().toInt()
            val cost = this.nextToken().toLong()

            if(dp[start][end] > cost) dp[start][end] = cost
        }
    }

    var check = true
    while(true){

        for(i in 1..n){
            for(j in 1..n){
                // i > k > j
                for(k in 1..n){
                    if(dp[i][k] == Long.MAX_VALUE || dp[k][j] == Long.MAX_VALUE) continue
                    if(dp[i][j] > dp[i][k] + dp[k][j]){
                        check = false
                        dp[i][j] = dp[i][k] + dp[k][j]
                    }
                }
            }
        }

        if(check) break
        check = true
    }

    val sb = StringBuilder()
    for(i in 1..n){
        for(j in 1 until n){
            sb.append(if(dp[i][j] == Long.MAX_VALUE) 0 else dp[i][j]).append(" ")
        }
        sb.append(if(dp[i][n] == Long.MAX_VALUE) 0 else dp[i][n]).append("\n")
    }
    print(sb)
}