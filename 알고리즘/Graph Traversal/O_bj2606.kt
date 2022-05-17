package `Graph Traversal`

/*
* 1번 노드에서 시작하여 갈 수 있는 노드의 개수를 구하는 문제
* */

import java.io.*
import java.util.*

class bj2606 {

    fun solve(){

        val br = BufferedReader(InputStreamReader(System.`in`))

        val n = br.readLine().toInt()
        val p = br.readLine().toInt()

        var nodes = Array(n+1) { mutableListOf<Int>() }

        var st : StringTokenizer
        for(i in 0 until p){
            st = StringTokenizer(br.readLine())
            // 상하관계가 없는 그래프이기 때문에 둘 다 저장해야 함
            val a = st.nextToken().toInt()
            val b = st.nextToken().toInt()
            nodes[a].add(b)
            nodes[b].add(a)
        }
        br.close()

        var queue : Queue<Int> = LinkedList<Int>()
        var visited = BooleanArray(n+1){false}
        var result = 0

        queue.add(1)
        visited[1] = true
        while(true){
            if(queue.isEmpty()) break
            val index = queue.element()
            queue.remove()
            result ++
            for(i in 0 until nodes[index].size){
                val tmpNode = nodes[index][i]
                if(visited[tmpNode]) continue
                visited[tmpNode] = true
                queue.add(tmpNode)
            }
        }

        print("${result-1} \n")
    }
}

fun main(){
    bj2606().solve()
}