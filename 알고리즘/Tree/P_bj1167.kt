import java.util.*

class bj1167 {
}

/*
* 트리의 지름 (임의의 두 node를 선택했을 때 거리가 가장 먼 부분)
* 1. 두 node가 root를 기준으로 다른 subtree에 있다면 두 node는 각 subtree 중 root에서 가장 먼 node이다.
* 2. 두 node가 같은 subtree에 있다면 둘중 하나는 root에서 가장 긴 node 이다. (공통된 부분까지는 같고 갈라질 때 간선이 가장 크기 때문)
* 3. root에서 가장 먼 node를 찾고 해당 node에서 가장 먼 node를 찾는다.
*
* ** 갈 수 있는 모든 node에 대한 정보가 주어지는데 children에 대한 정보만 주어진다고 생각해서 중복된 edge를 추가해서 느렸다.
* */

var n = 0
fun getGraph(): Array<Pair<Int,MutableList<Pair<Int,Int>>>>{
    val br = System.`in`.bufferedReader()
    n = br.readLine().toInt()

    // node번호 to 이웃(목적지 to 비용)(그래프로 친다.)
    val nodes = Array(n+1){ it to mutableListOf<Pair<Int,Int>>() }
    repeat(n){
        val st = StringTokenizer(br.readLine())
        val num = st.nextToken().toInt()

        var now = st.nextToken().toInt()
        while(now != -1){
            val cost = st.nextToken().toInt()
            nodes[num].second.add(now to cost)
            //nodes[now].second.add(num to cost)

            now = st.nextToken().toInt()
        }
    }
    br.close()
    return nodes
}

var max = 0
var maxNode = 0
fun dfsMax(graph: Array<Pair<Int,MutableList<Pair<Int,Int>>>>, visited: BooleanArray, node: Int, sum: Int){
//    print("$node ->")
    if(max < sum){
        max = sum
        maxNode = node
    }
    visited[node] = true
    for(edge in graph[node].second){
        if(visited[edge.first]) continue
        dfsMax(graph, visited, edge.first, sum+edge.second)
    }
}

fun main(){

    val graph = getGraph()
    var visited = BooleanArray(n+1)
    dfsMax(graph, visited,1, 0 )
//    println()

    max = 0
    visited = BooleanArray(n+1)
    dfsMax(graph, visited, maxNode, 0)
//    println()

    println("$max")
}