import java.util.*

class Solution {
    val max = 100000
    fun solution(n: Int, costs: Array<IntArray>): Int {

        val info = Array(n){ mutableListOf<Pair<Int,Int>>() }
        for(arr in costs){
            info[arr[0]].add(arr[1] to arr[2])
            info[arr[1]].add(arr[0] to arr[2])
        }

        // 도달하기 위해 선택한 간선의 비용(누적이 아님)
        val dp = IntArray(n){ max }.apply{ this[0] = 0 }
        // 현재노드 to 선택간선번호
        val pq = PriorityQueue<Pair<Int,Int>>{ a,b ->
            // 간선의 비용이 작은것 우선
            info[a.first][a.second].second - info[b.first][b.second].second
        }.apply{ for(i in info[0].indices) add(0 to i) }

        while(true){

            if(pq.isEmpty()) break
            val now = pq.poll();
            // 간선의 정보가 작은것부터 업데이트한다.
            // 이때 이미 업데이트 되어있으면 무시한다.
            val targetLine = info[now.first][now.second]
            if(dp[targetLine.first] != max) continue
            dp[targetLine.first] = targetLine.second
            // targetLine.first에서 갈 수 있는 경로들을 더한다.
            for(i in info[targetLine.first].indices){
                if(dp[info[targetLine.first][i].first] != max) continue
                pq.add( targetLine.first to i )
            }

        }

        return dp.sum()
    }
}