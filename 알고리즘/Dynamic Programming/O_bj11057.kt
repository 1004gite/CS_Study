class bj11057 {
}

/**
 * 길이가 n이고 수가 같거나 증가하는 수열의 개수를 구하라
 * 1. 2차원 dp를 이용한다.
 * 2. row는 길이를 의미하고, col은 마지막에 오는 숫자를 의미한다.
 * 3. value는 개수를 의미한다.
 * */

fun main(){
    val n = readln().toInt()

    val dp = Array(n+1){IntArray(10)}
    for(i in 0..9) dp[1][i] = 1

    for(r in 2..n){
        dp[r][0] = 1
        for(c in 1..9){
            dp[r][c] = (dp[r][c] + dp[r-1][c]+dp[r][c-1])%10007
        }
    }

    var result = 0
    dp[n].forEach{ result = (result + it) % 10007 }
    println(result)
}