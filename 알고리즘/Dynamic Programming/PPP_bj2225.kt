import java.util.*

class bj2225 {
}

/*
* 0~n 까지의 수를 중복 가능하게 k개 선택한다.
* k개의 합이 n이 되는 경우의 수를 1,000,000,000으로 나눈 나머지를 출력한다.
* 이때 순서가 다르면 다른 경우의 수로 본다.
* 1. 각 숫자를 한번씩만 이용하여 만들 수 있는 경우의 수는 각 자신으로 1개씩이다.
* 2. row는 사용한 숫자의 개수를 의미한다. col은 합을 의미한다.
* 3. 마지막에 l을 붙인다고 할 때 l은 0~col까지의 범위가 가능하고 이때 row-1의 col-l의 값을 모두 더해준다.
* */

fun main(){
    val st = StringTokenizer(readln())
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    val dp = Array(k+1){ IntArray(n+1) }
    for(c in 0..n) dp[1][c] = 1

    for(r in 2..k){
        for(c in 0..n){
            // r개를 사용할 거고 합은 c가 될 것이다.
            for(l in 0..c){
                // 이때 마지막에는 무조건 l이 오게 한다.
                dp[r][c] = (dp[r][c]+dp[r-1][c-l])%1000000000
            }
        }
    }

    println(dp[k][n])
}