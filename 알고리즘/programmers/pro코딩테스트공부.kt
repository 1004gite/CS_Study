import kotlin.math.*

class Solution {
    // 코딩력, 알고력을 1 올리는데 시간이 1 든다.
    // 풀 수 있는 문제를 풀어 보상으로 알고,코딩력을 올린다.
    // 같은 문제를 여러번 풀 수 있다.
    // 모든 문제를 풀 수 있는 알고,코딩력을 얻는 시간을 구하라

    // 다익스트라를 쓰면 한번에 업데이트가 가능한가?
    fun solution(alp: Int, cop: Int, problems: Array<IntArray>): Int {
        // problem - 알고,코딩,알고보상,코딩보상,드는시간
        var maxAlp = 0
        var maxCop = 0
        problems.forEach {
            maxAlp = max(it[0],maxAlp)
            maxCop = max(it[1], maxCop)
        }

        val checkP: (Int, Int)-> Boolean = { r,c ->  !(r < 0 || r > maxAlp || c < 0 || c > maxCop)}

        // 각 좌표에 드는 시간을 적는다. alp,cop
        val dp = Array(maxAlp+1){IntArray(maxCop+1){Int.MAX_VALUE} }
        for(r in 0..alp){
            if(r > maxAlp) break
            for(c in 0..cop){
                if(c > maxCop) break
                dp[r][c] = 0
            }
        }

        for(r in 0..maxAlp){
            for(c in 0..maxCop){
                // 해당 좌표에서 가능한 동작을 한 후 좌표에 업데이트
                // 이때 이미 더 작은 값이 있으면업데이트 하지 않는다.
                if(checkP(r+1,c)) dp[r+1][c] = min(dp[r+1][c], dp[r][c]+1)
                if(checkP(r,c+1)) dp[r][c+1] = min(dp[r][c+1], dp[r][c]+1)
                for(pro in problems){
                    if(r < pro[0] || c < pro[1]) continue
                    val tmpR = if(r+pro[2] > maxAlp) maxAlp else r+pro[2]
                    val tmpC = if(c+pro[3] > maxCop) maxCop else c+pro[3]
                    val cost = pro[4]
                    dp[tmpR][tmpC] = min(dp[tmpR][tmpC], dp[r][c]+cost)
                }
            }
        }

        return dp[maxAlp][maxCop]
    }
}