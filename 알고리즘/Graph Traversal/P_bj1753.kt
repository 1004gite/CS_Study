import java.util.PriorityQueue
import java.util.StringTokenizer

class bj1753 {
}

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val v = st.nextToken().toInt()
    var e = st.nextToken().toInt()

    val k = br.readLine().toInt() // 시작점의 번호
    val nodes = Array(v+1){ mutableListOf<Pair<Int,Int>>() } //목적지,비용
    while(e-- > 0){
        st = StringTokenizer(br.readLine())
        nodes[st.nextToken().toInt()].add(st.nextToken().toInt() to st.nextToken().toInt())
    }

    val dp = IntArray(v+1){ Int.MAX_VALUE }.apply { this[k] = 0 }
    // 목적지, 도착했을 때의 비용
    val pq = PriorityQueue<Pair<Int,Int>>{ o1, o2 ->
        o1.second-o2.second
    }
    for(edge in nodes[k]) pq.add(edge)

    while(true){
        if(pq.isEmpty()) break

        val now = pq.remove()
        if(dp[now.first] != Int.MAX_VALUE) continue
        dp[now.first] = now.second

        nodes[now.first].forEach{
            val cost = dp[now.first]+it.second
            if(cost < dp[it.first]) pq.add( it.first to cost )
        }
    }

    with(StringBuilder()){
        for(i in 1 until dp.size){
            this.append(if(dp[i] == Int.MAX_VALUE) "INF" else dp[i]).append("\n")
        }
        print(this)
    }
}