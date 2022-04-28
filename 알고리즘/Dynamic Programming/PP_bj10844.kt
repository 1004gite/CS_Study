/*
인접한 모든 수의 차가 1이면 계단수이다.
길이가 n인 계단수는 총 몇개인지 구하라.
0으로 시작될 수는 없다.

풀이 1
1. dp를 이용한다.
2. 길이가 끝이 5이고 길이가 n-1인 계단수는 끝이 4or6이고 길이가 n인 계단수가 될 수 있다.
3. 각 index는 계단수의 길이를 의미하고 value는 9개이다.
    각각의 value는 1~9로 끝나는 계단수의 개수를 의미한다.
4. mod연산의 특성상 중간 피연산자에 mod연산을 미리할 수 있다.
*/

import java.util.*

var mod : Int = 1000000000
var dp = Array<IntArray>(101){ IntArray(10){ 0 } }

fun main() = with(System.`in`.bufferedReader()) {

    // 띄어쓰기 단위로 받아오는 경우
    // val st = StringTokenizer(readLine())

    // while (st.hasMoreTokens()) {
    //     arr.add(st.nextToken().toInt())
    // }
    
    var n = readLine().toInt()

    for( i in 1..9){
        dp[1][i] = 1
    }

    for( i in 2..100){
        dp[i][0] = addWithMod(dp[i][0], dp[i-1][1])
        for( j in 1..8){
            dp[i][j] = addWithMod(dp[i][j], dp[i-1][j-1])
            dp[i][j] = addWithMod(dp[i][j], dp[i-1][j+1])
        }
        dp[i][9] = addWithMod(dp[i][9], dp[i-1][8])
    }

    var result = 0;
    for( i in 0..9){
        result = addWithMod(result, dp[n][i])
        // println(dp[n][i])
    }
    
    println(result.toString())
}

fun addWithMod(a : Int,b : Int) : Int{
    if( (a+b) >= mod)
        return a+b-mod
    else
        return a+b
}