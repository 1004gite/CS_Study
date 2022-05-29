/*
* N*M크기의 리로가 있고 0은 이동할 수 없는 칸이다.
* 1,1 -> n,m으로 가는 최단 경로를 구하라
*
* 풀이 1
* // 한쪽 방향으로만 가면 dp로도 가능하지만 모든 방향이 가능하면 dp로 모든 경우의 수를 고려할 수 없다.
* // backTracking은 중간에 다시 업데이트 되는 값이 더 작을 수 있다.
* 1. 다익스트라
* */

import java.io.*
import java.util.*

class bj2178 {

    private var board = Array(100) { IntArray(100) }
    private var visited = Array(100) { BooleanArray(100) }
    private var dijk = Array(100) { IntArray(100) { 10001 } }
    var h = 0
    var w = 0

    data class Node(var x: Int, var y: Int)

    fun solve() {

        val br = BufferedReader(InputStreamReader(System.`in`))
        var st = StringTokenizer(br.readLine())

        h = st.nextToken().toInt()
        w = st.nextToken().toInt()

        for (r in 0 until h) {
            val str = br.readLine()
            for (c in 0 until w) {
                if (str[c] == '1') board[c][r] = 1
            }
        }

        var index = Node(0, 0)
        dijk[0][0] = 1
        val xAdd = intArrayOf(0, 0, -1, 1)
        val yAdd = intArrayOf(-1, 1, 0, 0)
        while (index.x != -1) {
            for (i in 0 until 4) {
                val nowX = index.x + xAdd[i]
                val nowY = index.y + yAdd[i]
                if (nowX < 0 || nowX > w - 1 || nowY < 0 || nowY > h - 1) continue
                if (board[nowX][nowY] == 0) continue
                if (dijk[index.x][index.y] + 1 < dijk[nowX][nowY]) dijk[nowX][nowY] = dijk[index.x][index.y] + 1
            }
            visited[index.x][index.y] = true
            index = getMin()
        }

        print(dijk[w - 1][h - 1].toString() + "\n")
    }

    fun getMin(): Node {
        var index = Node(-1, -1)
        var value = 10001
        for (y in 0 until h) {
            for (x in 0 until w) {
                if (visited[x][y]) continue
                if (board[x][y] == 0) continue
                if (dijk[x][y] < value) {
                    index = Node(x, y)
                    value = dijk[x][y]
                }
            }
        }
        return index
    }
}

fun main() {
    bj2178().solve()
}