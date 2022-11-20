import java.util.*

class bj11724 {
}

fun dfs(nodes: Array<MutableList<Int>>, n: Int, visited: BooleanArray){
    visited[n] = true

    for(i in nodes[n]){
        if(visited[i]) continue
        dfs(nodes,i,visited)
    }
}

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt()
    var m = st.nextToken().toInt()

    val nodes = Array(n+1){ mutableListOf<Int>() }
    val visited = BooleanArray(n+1)
    while (m-- > 0){
        st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        nodes[a].add(b)
        nodes[b].add(a)
    }

    var count = 0
    for(i in 1 until nodes.size){
        if(visited[i]) continue
        count++
        dfs(nodes,i,visited)
    }

    println(count)
}