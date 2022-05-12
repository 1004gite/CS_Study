/* 
    n종류의 동전이 있다.
    가장 적은 동전을 사용하해서 합이 k원이 되도록 하자
    동전의 구성이 같은데 순서만 다른 것은 같은 경우이다.
    가치가 같은 동전이 여러종류 있을 수 있다.
*/

import java.util.*
import java.io.*

var dp = Array<Int>(10001, {10001})

fun main(){

    val br = BufferedReader( InputStreamReader(System.`in`) )
    val bw = BufferedWriter( OutputStreamWriter(System.`out`) )

    /* 
        val st = StringTokenizer( br.readLine() ) -> 한줄 토큰으로 읽어오기
        while( st.hasMoreTokens() ){ -> 모든 토큰 읽기
            st.nextToken().toInt()
        }
    */

    val st = StringTokenizer( br.readLine() )
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    var tmp = mutableSetOf<Int>()
    for( i in 0 until n ){
        tmp.add( br.readLine().toInt() )
    }
    val coins = tmp.toList()

    dp[0] = 0
    for (i in 1..k){
        for(c in 0 until coins.size){
            if( i - coins[c] < 0) continue
            if( dp[i] > dp[i-coins[c]]+1 ) dp[i] = dp[i-coins[c]]+1
        }
    }

    if(dp[k] == 10001) dp[k] = -1

    bw.write( dp[k].toString() + "\n" )
    bw.flush()

    br.close()
    bw.close()
}