import java.util.*

class bj1012 {
}

val arrX = arrayOf(1,-1,0,0)
val arrY = arrayOf(0,0,1,-1)

fun dfs(board: Array<IntArray>, visited: Array<BooleanArray>, p: Pair<Int, Int>){
    if(visited[p.first][p.second]) return
    visited[p.first][p.second] = true

    for(i in 0..3){
        val n = p.first+arrX[i] to p.second+arrY[i]
        if(n.first < 0 || n.first >= board.size || n.second < 0 || n.second >= board[0].size) continue
        if(board[n.first][n.second] == 0) continue
        dfs(board, visited, n)
    }
}

fun main(){

    val br = System.`in`.bufferedReader()
    var t = br.readLine().toInt()

    val bw = System.out.bufferedWriter()
    while (t-- > 0){
        var st = StringTokenizer(br.readLine())
        val w = st.nextToken().toInt()
        val h = st.nextToken().toInt()
        var k = st.nextToken().toInt()

        val board = Array(w){IntArray(h)}
        while(k-- > 0){
            st = StringTokenizer(br.readLine())
            board[st.nextToken().toInt()][st.nextToken().toInt()] = 1
        }

        var count = 0
        val visited = Array(w){BooleanArray(h)}
        for(x in 0 until w){
            for(y in 0 until h){
                if(board[x][y] == 0 || visited[x][y]) continue
                count++
                dfs(board,visited,x to y)
            }
        }

        bw.write("$count\n")
    }
    bw.flush()
}