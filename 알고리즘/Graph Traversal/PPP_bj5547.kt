/*
* 각 칸이 육각형인 판이 주어지고 회색 칸과 하얀 칸이 있다.
* 회색 칸의 테두리의 합을 구하라
* 이때 둘러쌓인 안쪽은 고려하지 않는다.
*
* 풀이 1
* 1. 인접한 칸이 있으면 1면은 외부에 노출되지 않는다.
* 2. 순환하는 부분이 있다면 안쪽은 면의 길이를 고려하지 않는다.
* 3. 하얀 칸들에 대해 bfs를 돌아 외부와 연결되지 않는다면 내부 벽으로 판단한다.
*   이때, 테두리에 있는 빈공간을 출발 node로 보고 진행한다.
* 4. 회색 칸들에 대해 bfs를 돌면서 외벽의 길이를 구한다.
* */

import java.io.*
import java.util.*

class bj5547 {

    data class Point(var row: Int, var col: Int)

    var board = Array(100) { BooleanArray(100) }
    var inPlace = Array(100) { BooleanArray(100) { true } }
    var visited = Array(100) { BooleanArray(100) }

    // 홀수줄을 위함
    val arrROdd = intArrayOf(0, 0, -1, -1, 1, 1)
    val arrCOdd = intArrayOf(-1, 1, -1, 0, -1, 0)

    // 짝수줄을 위함
    val arrREven = intArrayOf(0, 0, -1, -1, 1, 1)
    val arrCEven = intArrayOf(-1, 1, 0, 1, 0, 1)

    fun solve() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        var st = StringTokenizer(br.readLine())

        val w = st.nextToken().toInt()
        val h = st.nextToken().toInt()

        for (row in 0 until h) {
            st = StringTokenizer(br.readLine())
            for (col in 0 until w) {
                val tmp = st.nextToken().toInt()
                if (tmp == 1) {
                    board[row][col] = true
                }
            }
        }

        checkInplace(h, w)
        val result = computeResult(h,w)
        print("$result\n")
    }

    fun computeResult(h: Int, w: Int) : Int {
        var count = 0

        for( row in 0 until h){
            for( col in 0 until w){
                if(!board[row][col]) continue
                if(visited[row][col]) continue
                visited[row][col] = true

                var arrR = arrREven
                var arrC = arrCEven
                if (row % 2 != 0) {
                    arrR = arrROdd
                    arrC = arrCOdd
                }
                for (i in 0..5) {
                    val nowR = row + arrR[i]
                    val nowC = col + arrC[i]
                    if(nowR <0 || nowR > h-1 || nowC<0 || nowC>w-1){
                        // 외벽임
                        count++
                        continue
                    }
                    if(!board[nowR][nowC] && !inPlace[nowR][nowC]){
                        count++
                        continue
                    }
                }
            }
        }

        return count
    }

    fun checkInplace(h: Int, w: Int) {
        var list = mutableListOf<Point>()
        // 빈 공간 중 내부공간 체크
        for (row in 0 until h) {
            if (!board[row][0]) list.add(Point(row, 0))
            if (!board[row][w - 1]) list.add(Point(row, w - 1))
        }
        for (col in 1 until w - 1) {
            if (!board[0][col]) list.add(Point(0, col))
            if (!board[h - 1][col]) list.add(Point(h - 1, col))
        }

        //리스트를 돌면서 외부와 연결된 공간 확인
        while (true) {
            if (list.isEmpty()) break
            val now = list.removeAt(0)
            inPlace[now.row][now.col] = false

            var arrR = arrREven
            var arrC = arrCEven
            if (now.row % 2 != 0) {
                arrR = arrROdd
                arrC = arrCOdd
            }
            for (i in 0..5) {
                val nowR = now.row + arrR[i]
                val nowC = now.col + arrC[i]
                if (nowR < 0 || nowR > h - 1 || nowC < 0 || nowC > w - 1) continue
                if (board[nowR][nowC]) continue
                if (visited[nowR][nowC]) continue
                visited[nowR][nowC] = true
                list.add(Point(nowR, nowC))
            }
        }
    }
}

fun main(){
    bj5547().solve()
}