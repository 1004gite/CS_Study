/*
* node가 n개 있고 node는 신뢰관계를 형성할 수 있다.
* a가 b를 신뢰하면 b만 해킹해도 a가 같이 해킹된다.
*
* 풀이 1 -> 시간초과
* 1. 자신을 많이 신뢰할수록 많은 컴퓨터를 해킹할 수 있다.
* 2. 자신을 신뢰하는 컴퓨터 많은 신뢰를 받는 것도 중요하다
* 3. 어떤 node a가 b를 신뢰한다면 b는 a보다 값이 크다.
* 4. 트리로 표현한 후 다른 node를 따르지 않는 node들만 검사한다.
*.
* 풀이 2 -> 자신을 신뢰할 수 있는 node가 겹치면 중복해서 셈 -> 순환하는 경우, 겹치는 경우 고려해야함 -> 기각
* 1. 새로운 node가 들어올 때마다 모든 조상과 자신을 업데이트한다.
* 2. 자식이 생겼을 경우 자식의 누적합 +1을 누적합에 추가한다.
* 3. 부모가 생겼을 경우 본인의 누적합 +1을 부모에 추가한다.
*
* 풀이 3
* 0. 인터넷을 참조했는데 풀이 1과 논리는 같았다. -> 비효율적인 부분을 검사하자
* 1. 자식을 가지는 것이 아니라 부모정보를 가지게?
* 2. 변수 선언 후 {}로 초기화하는 부분에서의 소요 -> 배열 선언시의 기본값을 참고하자
*       + 기존 배열을 fill하는거보다 새로 만드는게 조금 더 빠르다.
* */

package `Graph Traversal`

import java.io.*
import java.util.*

class bj1325 {

    fun solve() {

        val br = BufferedReader(InputStreamReader(System.`in`))
        var st = StringTokenizer(br.readLine())

        val n = st.nextToken().toInt()
        val m = st.nextToken().toInt()

        // 각 노드는 자식 정보를 가지고 있다.
        var nodes = Array(n+1) { mutableListOf<Int>() }

        for (i in 0 until m) {
            st = StringTokenizer(br.readLine())
            var a = st.nextToken().toInt()
            var b = st.nextToken().toInt()
            nodes[b].add(a)
//            nodes[a].haveParent = true
        }
        br.close()

        var max = -1
        var list = IntArray(n+1) //0으로 초기화가 기본값임
        for( i in 1..n){
//            if(nodes[i].haveParent) continue
            list[i] = getSum(i,nodes)
            if(max < list[i]) max = list[i]
        }

        val bw = BufferedWriter(OutputStreamWriter(System.out))
        for( i in 1..n ){
            if(max == list[i]) bw.write("$i ")
        }
        bw.write("\n")
        bw.flush()
        bw.close()
    }

    private fun getSum(start : Int, nodes : Array<MutableList<Int>>) : Int{
        var count = 0
        var visited = BooleanArray(nodes.size) // false 초기화가 기본값임
        var queue : Queue<Int> = LinkedList<Int>()
        queue.add(start)
        visited[start] = true
        count ++
        while(true){
            if(queue.isEmpty()) break
            val now = queue.element()
            queue.remove()
            for( node in nodes[now]){
                if(visited[node]) continue
                visited[node] = true
                count ++
                if(nodes[node].isNotEmpty()) queue.add(node)
            }
        }
        return count
    }
}
fun main(){
    bj1325().solve()
}