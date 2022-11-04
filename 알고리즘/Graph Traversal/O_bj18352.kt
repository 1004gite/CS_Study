import java.io.*
import java.util.*

class Solve{
    var n = 0
    var m = 0
    var k = 0
    var start = 0
    fun solve(){
        val info = getInfo()
        val dp = IntArray(n+1){Int.MAX_VALUE}.apply{ this[start] = 0 }

        val queue: Queue<Int> = LinkedList<Int>().apply{ add(start) }
        while(true){
            if(queue.isEmpty()) break
            val now = queue.remove()

            for( node in info[now] ){
                if(dp[node] != Int.MAX_VALUE) continue
                dp[node] = dp[now]+1
                queue.add(node)
            }
        }

        val bw = System.out.bufferedWriter()
        for( i in dp.indices ) if(dp[i] == k) bw.write( "$i\n" )
        bw.flush()
        bw.close()
    }

    fun getInfo(): Array<MutableList<Int>>{
        val br = System.`in`.bufferedReader()
        var st = StringTokenizer(br.readLine())
        n = st.nextToken().toInt()
        m = st.nextToken().toInt()
        k = st.nextToken().toInt()
        start = st.nextToken().toInt()

        val info = Array(n+1){mutableListOf<Int>()}
        repeat(m){
            st = StringTokenizer(br.readLine())
            val a = st.nextToken().toInt()
            val b = st.nextToken().toInt()

            // 단방향 도로임
            info[b].add(a)
        }

        return info
    }
}

fun main(){
    Solve().solve()
}