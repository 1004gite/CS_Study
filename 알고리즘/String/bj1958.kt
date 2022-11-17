class bj1958 {
}

fun main(){
    // 각각 특정 index에서 3 문자열의 char가 같으면 공통 문자열의 일부로 볼 수 있다.
    // 만약 각 index-1에서도 같았다면 해당 좌표 +1 길이를 가질 것이다.
    // subsequence이기 때문에 문자를 뛰어넘어가며 계산해야 한다.
    val str1 = " "+readln()
    val str2 = " "+readln()
    val str3 = " "+readln()

    val dp = Array(str1.length){ Array(str2.length){ IntArray(str3.length) } }
    var max = 0
    for(a in 1 until str1.length){
        for(b in 1 until str2.length){
            for(c in 1 until str3.length){
                if(str1[a] != str2[b] || str1[a] != str3[c] || str2[b] != str3[c]){
                    dp[a][b][c] = maxOf(dp[a-1][b][c], dp[a][b-1][c], dp[a][b][c-1])
                }
                else dp[a][b][c] = dp[a - 1][b - 1][c - 1] + 1
                max = maxOf(max, dp[a][b][c])
            }
        }
    }
    println(max)
}