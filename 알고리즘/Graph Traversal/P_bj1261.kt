/*
* N*M 크기의 미로가 있다.
* 미로는 빈방/벽 으로 이루어져 있고 벽은 부셔야지만 이동할 수 있다.
* 상하좌우 1칸을 이동할 수 있다.
* (1,1)에서 (N,M)으로 이동해야 할 때 부셔야 하는 벽의 최소 개수를 구하라.
*
* 풀이 1
* 1. 길찾기 문제이지만 벽을 부수는 것을 고려해야 하므로 백트래킹은 적절하지 않다.
* 2. dp를 이용한다. index는 방의 좌표이고 value는 부셔야 하는 벽의 최소 개수이다.
* 3. dp를 이용할 때 자신을 업데이트하면 아직 안가본 길을 고려할 수 없고 주변을 업데이트하면 블록을 여러번 고려하게 된다.
*
* 풀이 2 -> pq에 넣고 업데이트 될때마다 빼주면 시간초과, while문 한번마다 검사하면 메모리 초과가 난다.
* 1. 다익스트라를 이용한다.
* 2. 벽이 있는 관계는 간선을 1로보고 나머지는 0으로 본다.
*
* 풀이 3
* 1. pq대신 방문 기록을 사용항 최소 node를 구한다.
* */

import java.util.*
import java.io.*

class bj1261 {
    data class Node(var row: Int, var col: Int)

    // 0,0 -> n-1,m-1
    private var board = Array(100) { IntArray(100) }
    private var dijk = Array(100) { IntArray(100) { 10001 } }
    private var visited = Array(100){BooleanArray(100)}
    fun solve() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        var st = StringTokenizer(br.readLine())

        val n = st.nextToken().toInt() //가로
        val m = st.nextToken().toInt() //세로

        for (col in 0 until m) {
            val str = br.readLine()
            for (row in 0 until n) {
                if (str[row] == '0') continue //빈방이면 표시 안함
                board[row][col] = 1
            }
        }

        var index = Node(0,0)
        dijk[0][0] = 0

        while (index.row != -1) {

            val r = index.row
            var c = index.col
            visited[r][c] = true

            // 상
            if (c > 0 && dijk[r][c] + board[r][c - 1] < dijk[r][c - 1]) {
                dijk[r][c - 1] = dijk[r][c] + board[r][c - 1]
            }
            // 하
            if (c < m - 1 && dijk[r][c] + board[r][c + 1] < dijk[r][c + 1]) {
                dijk[r][c + 1] = dijk[r][c] + board[r][c + 1]
            }
            // 좌
            if (r > 0 && dijk[r][c] + board[r - 1][c] < dijk[r - 1][c]) {
                dijk[r - 1][c] = dijk[r][c] + board[r - 1][c]
            }
            // 우
            if (r < n - 1 && dijk[r][c] + board[r + 1][c] < dijk[r + 1][c]) {
                dijk[r + 1][c] = dijk[r][c] + board[r + 1][c]
            }

            index = getMinimun(n,m)
        }
        print(dijk[n-1][m-1].toString()+"\n")
    }

    fun getMinimun(n:Int,m:Int): Node{
        var node = Node(-1,-1)
        var value = 10001
        for( x in 0 until n){
            for( y in 0 until m){
                if(visited[x][y]) continue
                if(value > dijk[x][y]){
                    node = Node(x,y)
                    value = dijk[x][y]
                }
            }
        }
        return node
    }

}

fun main() {
    bj1261().solve()
}