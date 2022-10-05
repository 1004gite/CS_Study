import kotlin.math.max

class Solution {
    fun solution(sizes: Array<IntArray>): Int {

        var dp = arrayOf(sizes[0][0],sizes[0][1]).apply { sortDescending() }

        for(arr in sizes){
            with(arr.sortedDescending()){
                dp[0] = max(dp[0],this[0])
                dp[1] = max(dp[1],this[1])
            }
        }

        return dp[0]*dp[1]
    }
}