import kotlin.math.*

class bj9251 {

    // dp로 접근
    /*
    * 3가지 경우의 수가 있음
    * // i,j -> 해당하는 위치의 값
    * 1. 행 or 열을 늘린다고 봄 -> 문자 하나를 추가 -> 이 경우 i,j 가 같아도 이전에 소모되었다고 판단하여 +1하지 않는다.
    * 2. 행,열에 하나씩 각각 추가되었다고 봄 -> 이 경우 i-1,j-1에서 i,j가 같다면 +1 아니면 +0을 한 후 판단한다.
    *   -> 이때 같지 않으면 1번에서 이미 최대값이 고려되어 있을것임
    *  */

    fun solve(){

        val a = readln()
        val b = readln()

        // a가 세로롤 쓰여있는 형테
        val dp = Array(a.length){ IntArray(b.length)}
        // row,col 한줄식 미리 설정
        if(a[0] == b[0]) dp[0][0] = 1
        for( i in 1 until a.length) if(dp[i-1][0] == 1 || a[i] == b[0]) dp[i][0] = 1
        for( i in 1 until b.length) if(dp[0][i-1] == 1 || a[0] == b[i]) dp[0][i] = 1

        for(r in 1 until a.length){
            for(c in 1 until b.length){

                // 위
                var max = dp[r-1][c]
                // 왼쪽
                max = max(max, dp[r][c-1])
                // 대각
                if(a[r] == b[c]) max = max(max, dp[r-1][c-1]+1)

                dp[r][c] = max
            }
        }

        println(dp[a.length-1][b.length-1])

    }
}

fun main(){
    bj9251().solve()
}