/**
 * 간선의 정보가 있는 트리의 지름을 구하는 문제
 *
 * 풀이 1
 * 1. 트리의 지름을 구하는 것은 leafNode 2개를 선택하는 것과 같다.
 * 2. leafNode 2개를 선택했을 때 지름은 공통조상을 만나야 알 수 있다.
 *
 * 풀이 2
 * 1. 저번에도 같은 문제를 못풀었는데 이번에도 못풀었다.
 * 2. 트리의 지름은 가장 먼 양 끝점이다.
 *      즉, 어떤 node에서 출발하여 만나게 되는 점 중 가장 긴 점은 트리의 지름이 되는 점 중 1개이다.
 * 3. 지름점 중 한개를 찾았다면 다른 해당 점에서 가장 먼 점을 찾는다.
 * */

import java.io.*
import java.util.*

class bj1967 {

    fun solve(){
        val br = BufferedReader(InputStreamReader(System.`in`))
        val n = br.readLine().toInt()

        // 이웃의 index, 간선정보
        var tree = Array(n+1){ mutableListOf<Pair<Int,Int>>() }
        for(i in 0 until n-1){
            val st = StringTokenizer(br.readLine())
            val p = st.nextToken().toInt()
            val c = st.nextToken().toInt()
            val value = st.nextToken().toInt()
            tree[p].add(c to value)
            tree[c].add(p to value)
        }

        // node 번호, 누적값
        var stack = LinkedList<Pair<Int,Int>>()
        var visited = BooleanArray(n+1)
        stack.add(1 to 0)
        visited[1] = true
        var max = -1
        var maxIndex = -1
        while(true){
            if(stack.isEmpty()) break
            val now = stack.last
            stack.removeLast()
            if(max < now.second){
                max = now.second
                maxIndex = now.first
            }
            // 순환이 없기때문에 목록을 넣을때 visited를 검사해도 재방문이 없음
            for(node in tree[now.first]){
                if(visited[node.first]) continue
                visited[node.first] = true
                stack.add(node.first to (now.second+node.second))
            }
        }

        // node 번호, 누적값
        stack = LinkedList<Pair<Int,Int>>()
        visited = BooleanArray(n+1)
        stack.add(maxIndex to 0)
        visited[maxIndex] = true
        max = -1
        while(true){
            if(stack.isEmpty()) break
            val now = stack.last
            stack.removeLast()
            if(max < now.second) max = now.second

            for(node in tree[now.first]){
                if(visited[node.first]) continue
                visited[node.first] = true
                stack.add(node.first to (now.second+node.second))
            }
        }

        println(max)
    }
}

fun main(){
    bj1967().solve()
}