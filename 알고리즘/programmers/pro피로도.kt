import kotlin.math.*

class Solution {

    lateinit var visited: BooleanArray
    var max = 0

    fun solution(k: Int, dungeons: Array<IntArray>): Int {

        visited = BooleanArray(dungeons.size)

        dfs(k,dungeons, 0)
        return max
    }

    fun dfs(k:Int, dungeons: Array<IntArray>, count:Int){
        var check = true
        for(i in dungeons.indices){
            if(visited[i]) continue
            if(k < dungeons[i][0]) continue
            visited[i] = true
            dfs(k-dungeons[i][1], dungeons, count+1)
            check = false
            visited[i] = false
        }

        if(check) max = max(max, count)
    }
}