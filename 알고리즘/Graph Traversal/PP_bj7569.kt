/*
* M*N*H 크기의 3차원 박스가 있다.
* 상자 안에는 익은 토마토와 안익은 토마토, 빈칸이 있다.
* 익은 토마토의 위,아래,오른쪽,앞,뒤 는 하루가 지나면 익게 된다.
* 몇일이 지나야 토마토가 모두 익는지 구하라
*
* 풀이 1
* 1. 익은 토마토는 자신의 주변을 익게 한다.
*   즉, 어떤 토마토가 주변을 익게 하면 다음부터는 더이상 익게 할 토마토가 없다.
* 2. 바로 직전에 익은 토마토들만 고려하여 주변을 익게 한다.
* */

import java.util.*
import java.io.*

class bj7569 {

    data class Point(var height: Int, var row: Int, var col: Int)

    var box = Array(100) { Array(100) { IntArray(100) { -1 } } }

    fun solve() {

        val br = BufferedReader(InputStreamReader(System.`in`))
        var st = StringTokenizer(br.readLine())

        val m = st.nextToken().toInt()
        val n = st.nextToken().toInt()
        val h = st.nextToken().toInt()

        var list = mutableListOf<Point>()
        for (height in 0 until h) {
            for (row in 0 until n) {
                st = StringTokenizer(br.readLine())
                for (col in 0 until m) {
                    box[height][row][col] = st.nextToken().toInt()
                    if (box[height][row][col] == 1) list.add(Point(height, row, col))
                }
            }
        }

        val arrR = intArrayOf(1, -1, 0, 0, 0, 0)
        val arrC = intArrayOf(0, 0, 1, -1, 0, 0)
        val arrH = intArrayOf(0, 0, 0, 0, 1, -1)
        var day = 0
        while (true) {
            var tmp = mutableListOf<Point>()
            for (index in 0 until list.size) {
                // 이전에 익은 토마토들에 대해
                for (a in 0..5) {
                    // 6방향에 대해
                    val nowH = list[index].height + arrH[a]
                    val nowR = list[index].row + arrR[a]
                    val nowC = list[index].col + arrC[a]
                    if (nowH < 0 || nowH > h - 1 || nowR < 0 || nowR > n - 1 || nowC < 0 || nowC > m - 1) continue
                    if (box[nowH][nowR][nowC] == 0) {
                        box[nowH][nowR][nowC] = day + 1
                        tmp.add(Point(nowH, nowR, nowC))
                    }
                }
            }
            if (tmp.isEmpty()) break
            list = tmp
            day++
        }

        for (height in 0 until h) {
            for (row in 0 until n) {
                for (col in 0 until m) {
                    if (box[height][row][col] == 0) {
                        print("-1\n")
                        return
                    }
                }
            }
        }
        print("$day\n")

    }
}

fun main(){
    bj7569().solve()
}