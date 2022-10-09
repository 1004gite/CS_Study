class Solution {
    // 모든 node 방문하며 연결된 개수 세기
    var count = 0
    lateinit var visited: BooleanArray

    fun solution(n: Int, computers: Array<IntArray>): Int {

        visited = BooleanArray(n)
        for(i in 0 until n){
            if(visited[i]) continue
            visited[i] = true
            count++
            dfs(n,i, computers)
        }

        return count
    }

    fun dfs(n: Int, num: Int, computers: Array<IntArray>){
        for(com in 0..n-1){
            if(visited[com]) continue
            if(computers[num][com] == 0) continue
            visited[com] = true
            dfs(n,com,computers)
        }
    }
}