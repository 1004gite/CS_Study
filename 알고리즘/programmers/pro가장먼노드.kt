import java.util.LinkedList
import java.util.Queue

class Solution {
    fun solution(n: Int, edge: Array<IntArray>): Int {

        // 갈 수 있는 node의 정보
        val info = Array(n+1){ mutableListOf<Int>() }
        edge.forEach {
            info[it[0]].add(it[1])
            info[it[1]].add(it[0])
        }
        // 업데이트된 길이
        val dp = IntArray(n+1){-1}
        dp[1] = 0

        // 간선의 정보가 모두 1이기 때문에 bfs로 할 수 있다.
        val queue: Queue<Int> = LinkedList<Int>().apply { add(1) }

        while(true){
            if(queue.isEmpty()) break
            val now = queue.remove()

            for(next in info[now]){
                if(dp[next] != -1) continue
                dp[next] = dp[now]+1
                queue.add(next)
            }
        }

        val max = dp.maxOrNull()!!

        var answer = 0
        dp.forEach { if(it == max) answer++ }

        return answer
    }
}