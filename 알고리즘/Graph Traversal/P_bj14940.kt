/**
 * 지도와 목표지점이 주어진다.
 * 가로,세로만 이동할 수 있을 때 모든 지점에 대해 목표지점까지의 거리를 구하라
 * 갈수 있는 땅과 갈수 없는 땅이 있다.
 *
 * 풀이 1
 * 1. 목표 지점에서 출발하여 거리를 계산한다.
 * 2. 간선의 값이 모두 1이기 때문에 bfs로 탐색한다.
 */

import java.io.*
import java.util.*

class bj14940 {
    data class Point(var row: Int, var col: Int)

    var board = Array(1000) { IntArray(1000) }
    var dp = Array(1000) { IntArray(1000) { Int.MAX_VALUE } }
    var visited = Array(1000) { BooleanArray(1000) }

    val arrR = intArrayOf(0, 0, -1, 1)
    val arrC = intArrayOf(1, -1, 0, 0)

    fun solve() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        var st = StringTokenizer(br.readLine())

        val R = st.nextToken().toInt()
        val C = st.nextToken().toInt()

        var start: Point = Point(0, 0)
        for (r in 0 until R) {
            st = StringTokenizer(br.readLine())
            for (c in 0 until C) {
                board[r][c] = st.nextToken().toInt()
                if (board[r][c] == 2) start = Point(r, c)
            }
        }

        dp[start.row][start.col] = 0
        var queue: Queue<Point> = LinkedList()
        queue.add(start)
        visited[start.row][start.col] = true

        while (true) {
            if (queue.isEmpty()) break
            val now = queue.element()
            queue.remove()

            for (i in 0..3) {
                val nowR = now.row + arrR[i]
                val nowC = now.col + arrC[i]
                if (nowR < 0 || nowR >= R || nowC < 0 || nowC >= C) continue
                if(board[nowR][nowC] == 0) continue
                if(visited[nowR][nowC]) continue
                visited[nowR][nowC] = true
                dp[nowR][nowC] = dp[now.row][now.col]+1
                queue.add(Point(nowR,nowC))
            }
        }

        val bw = BufferedWriter(OutputStreamWriter(System.out))
        for(r in 0 until R){
            for(c in 0 until C){
                var str = "${dp[r][c]} "
                if(board[r][c] == 0) str = "0 "
                else if(dp[r][c] == Int.MAX_VALUE) str = "-1 "
                bw.write(str)
            }
            bw.write("\n")
        }
        bw.flush()
    }
}

fun main() {
    bj14940().solve()
}