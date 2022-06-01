/**
 * M*N 크기의 상자에 익은 토마토와 안익은 토마토가 섞여있다.(빈칸이 있을 수 있다.)
 * 하루가 지나면 익은 토마토의 상,하,좌,우가 익게 된다.
 * 몇일이 지나여 모든 토마토가 다 익는지 구하라
 *
 * 풀이 1 -> 시간초과
 * 1. 날짜별로 시뮬레이션한다.
 *
 * 풀이 2
 * 1. 익은 날짜를 저장한다.
 * 2. 시간이 지나면 익은 토마토 자신 주변의 토마토는 모두 익게 되므로 주변을 더 익게할 수 없다.
 *      즉, 어제 익은 토마토만 고려하여 주변을 익게한다.
 * 3. 매번 모든 판을 검사하지 않고 다음에 검사할 목록을 유지한다.
 */

import java.io.*
import java.util.*

class bj7576 {

    data class Point(var row:Int, var col:Int)

    private var box = Array(1000) { IntArray(1000) { -1 } }

    fun solve() {

        val br = BufferedReader(InputStreamReader(System.`in`))
        var st = StringTokenizer(br.readLine())

        val m = st.nextToken().toInt() // 가로
        val n = st.nextToken().toInt() // 세로

        var list = mutableListOf<Point>()
        for (r in 0 until n) {
            st = StringTokenizer(br.readLine())
            for (c in 0 until m) {
                box[r][c] = st.nextToken().toInt()
                if(box[r][c] == 1) list.add(Point(r,c))
            }
        }

        var count = 0
        val arrR = intArrayOf(0, 0, -1, 1)
        val arrC = intArrayOf(1, -1, 0, 0)
        while (true) {
            var tmpList = mutableListOf<Point>()
            for(index in 0 until list.size) {
                val now = list[index]
                for (i in 0..3) {
                    val nowR = now.row + arrR[i]
                    val nowC = now.col + arrC[i]
                    if (nowR < 0 || nowR > n - 1 || nowC < 0 || nowC > m - 1) continue
                    // 주변에 익은게 있는지 확인 후 아직 안익었으면 익힌다.
                    if (box[nowR][nowC] == 0) {
                        box[nowR][nowC] = count + 1
                        tmpList.add(Point(nowR,nowC))
                    }
                }
            }
            if(tmpList.size == 0) break
            list = tmpList
            count++
        }

        // 값 검사
        for (r in 0 until n) {
            for (c in 0 until m) {
                if(box[r][c] == 0){
                    print("-1\n")
                    return
                }
            }
        }

        print("$count\n")
    }
}

fun main() {
    bj7576().solve()
}