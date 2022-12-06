import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class bj1005 {
}

/**
 * 건물을 짓는 순서가 매번 주어진다.
 * 건설시간이 있다.
 * 건물의 개수 n, 규칙 k개, 목표 건물 번호 w
 *
 * 풀이 1
 * 1. 각 건물에 선행조건을 기록한다.
 * 2. 방향이 있는 그래프 느낌이 될 것이다.
 * 3. 본인으로 오는 간선의 정보+가는 간선의 정보를 기록한다.
 * 4. dp에는 본인이 끝날때까지 걸리는 시간을 기록한다. 이때 선행 node 중 가장 시간이 긴것을 선택한다.
 * 5. 이때 다음 검사할 node 후보는 확정난 node의 가는 node들이고 모든 node가 완성 되었을때만 검사한다.
 * */

fun main(){

    val br = System.`in`.bufferedReader()
    val t = br.readLine().toInt()

    val sb = StringBuilder()

    repeat(t){

        var st = StringTokenizer(br.readLine())
        val n = st.nextToken().toInt()
        val k = st.nextToken().toInt()

        st = StringTokenizer(br.readLine())
        // 본인을 짖는데 걸리는 시간
        val info = IntArray(n){ st.nextToken().toInt() }
        // 본인에서 갈 수 있는 node를 가르킨다.
        val graph = Array(n){ mutableListOf<Int>() }
        // 위상을 나타낸다.
        val degree = IntArray(n)
        repeat(k){
            st = StringTokenizer(br.readLine())
            val a = st.nextToken().toInt()-1
            val b = st.nextToken().toInt()-1
            graph[a].add(b)
            degree[b]++
        }
        val dp = IntArray(n){ -1 }
        val w = br.readLine().toInt()-1

        val queue: Queue<Int> = LinkedList()
        for(i in graph.indices){
            if(degree[i] == 0){
                queue.add(i)
                dp[i] = info[i]
            }
        }

        while(true){

            if(queue.isEmpty()) break

            // now는 degree가 0이고 지금 이전까지의 업데이트로 값이 확정되었다.
            val now = queue.remove()
            if(now == w){
                sb.append(dp[w]).append('\n')
                break
            }

            // now에서 갈 수 있는 node들을 업데이트하고 위상이 0이 된다면 queue에 추가한다.
            for(i in graph[now]){
                dp[i] = maxOf(dp[i], dp[now]+info[i])
                degree[i]--
                if(degree[i] == 0) queue.add(i)
            }

        }

    }

    print(sb)
}