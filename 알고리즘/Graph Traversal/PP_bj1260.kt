/*
* 그래프가 주어진다.
* dfs/bfs로 탐색하는 과정을 출력하라
* 선택지가 여러개인 경우에는 수가 작은것부터 탐색한다.
* */

package `Graph Traversal`

import java.io.*
import java.util.*

class bj1260 {

    fun solve() {

        val br = BufferedReader(InputStreamReader(System.`in`))
        var st = StringTokenizer(br.readLine())

        val n = st.nextToken().toInt()
        val m = st.nextToken().toInt()
        val start = st.nextToken().toInt()

        var nodes = Array(n + 1) { mutableListOf<Int>() }
        for (i in 0 until m) {
            st = StringTokenizer(br.readLine())
            val a = st.nextToken().toInt()
            val b = st.nextToken().toInt()
            nodes[a].add(b)
            nodes[b].add(a)
        }
        br.close()
        for (i in 1..n) {
            nodes[i].sort()
        }

        val bw = BufferedWriter(OutputStreamWriter(System.out))

        //dfs
        var visited = BooleanArray(n + 1) { false }
        var stack = Stack<Int>()
        stack.push(start)
        while (true) {
            if (stack.isEmpty()) break
            val now = stack.pop()
            if(visited[now]) continue
            bw.write("$now ")
            visited[now] = true
            for(i in (nodes[now].size-1) downTo  0){
                stack.push(nodes[now][i])
            }
        }
        bw.write("\n")

        //bfs
        visited = BooleanArray(n+1){false}
        var queue : Queue<Int> = LinkedList<Int>()
        queue.add(start)
        while(true){
            if (queue.isEmpty()) break
            val now = queue.element()
            queue.remove()
            if(visited[now]) continue
            visited[now] = true
            bw.write("$now ")
            for(i in 0 until nodes[now].size){
                queue.add(nodes[now][i])
            }
        }
        bw.write("\n")

        bw.flush()
        bw.close()
    }
}

fun main(){
    bj1260().solve()
}