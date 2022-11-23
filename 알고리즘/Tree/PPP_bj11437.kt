import java.util.*

class bj11437 {
}

/*
* // 화장실이 급한 상태에서 문제를 푸는 것보다 화장실을 갔다와서 문제를 푸는게 훨씬 빠르다.
* kotlin에서 메모리값 비교는 === 이다.
* 50000개의 node가 있는 tree가 있다.
* 10000개의 쌍에 대해 가장 가까운 공통 조상을 구하라
* 1. 케이스가 많기 때문에 매번 공통 조상을 구하는 것은 비효율적이다.
* 2. 모든 조상을 node에 저장하게 되면 5만^2 이 될 수 있어 불가능하다.
* 3. 제한시간이 3초로 넉넉하기 때문에 매번 dfs를 하는 것으로 한다.
* 4. 트리상의 두 정점이 주어지지만 부모/자식 관계인지에 대해서는 언급이 없기 때문에 그래프로 생각한다.
* 5. 효율을 위해 tree를 정렬하여 부모 정보가 있는 것이 좋아보인다.
* 6. 추가적으로 depth를 알고 있다면 depth를 맞춘 후 탐색이 가능하다 -> hashSet을 사용하는 것보다 논리적, 평균적으로 시간이 적게 걸린다.
* */

val br = System.`in`.bufferedReader()
data class Node(var parent: Int, val children: MutableList<Int>, var depth: Int)
fun setTree(tree: Array<Node>, graph: Array<MutableList<Int>>, visited: BooleanArray, node: Int){
    visited[node] = true
    // 본인에 자식 node를 추가하고 자식 node의 부모를 자신으로 설정함
    for(next in graph[node]){
        if(visited[next]) continue
//        println("$next's parent is $node")
        tree[node].children.add(next)
        tree[next].parent = node
        tree[next].depth = tree[node].depth+1
        setTree(tree,graph,visited,next)
    }
}
fun getTree(): Array<Node>{
    val n = br.readLine().toInt()

    val graph = Array(n + 1) { mutableListOf<Int>() }
    repeat(n-1) {
        with(StringTokenizer(br.readLine())) {
            val a = this.nextToken().toInt()
            val b = this.nextToken().toInt()

            graph[a].add(b)
            graph[b].add(a)
        }
    }

    val tree = Array(n + 1) { Node(0, mutableListOf(), 0) }
    val visited = BooleanArray(n+1)
    setTree(tree,graph,visited, 1)

    return tree
}

fun main(){

    val tree = getTree()
    val sb = StringBuilder()
    var t = br.readLine().toInt()
    while(t-- > 0){
        val st = StringTokenizer(br.readLine())
        var a = st.nextToken().toInt()
        var b = st.nextToken().toInt()
        while(tree[a].depth > tree[b].depth) a = tree[a].parent
        while(tree[b].depth > tree[a].depth) b = tree[b].parent

        while (a != b){
            a = tree[a].parent
            b = tree[b].parent
        }
        sb.append(a).append('\n')
    }

    print(sb)
}