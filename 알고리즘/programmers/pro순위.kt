import java.util.LinkedList
import java.util.Queue

class Solution {

    class Person{
        val win = hashSetOf<Int>()
        val loose = hashSetOf<Int>()
    }

    fun solution(n: Int, results: Array<IntArray>): Int {

        // 모든 선수와의 승패가 정해지면 순위를 매길 수 있다.
        // 다른 선수들과의 경기로 내 승퍄가 정해질 수도 있다.

        // 0알수없음, 1승, -1패
        val info  = Array(n+1){ Person() }
        for(arr in results){
            val queue: Queue<Pair<Int,Int>> = LinkedList()
            queue.add(arr[0] to arr [1])

            while(true){
                if(queue.isEmpty()) break

                val now = queue.remove()

                if(info[now.first].win.add(now.second)){
                    // second가 이긴 사람들 추가
                    for(win in info[now.second].win) queue.add( now.first to win )
                }
                if(info[now.second].loose.add(now.first)){
                    // first가 진 사람들 추가
                    for(loose in info[now.first].loose) queue.add(loose to now.second)
                }
            }
        }

        var answer = 0
        for(tmp in info) if(tmp.win.size + tmp.loose.size + 1 == n) answer++
        return answer
    }
}