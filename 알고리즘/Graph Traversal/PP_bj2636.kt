/**
 * 판의 가장자리는 비어있다.
 * 공기와 직접 닿는 부분은 1시간 후 없어진다.
 * 내부의 구명은 공기가 닿지 않고 밖과 연결되어야 닿는 판정이다.
 * 치즈가 모두 목아 없어지는 시간과 완전히 없어지기 한 시간 전의 개수를 구하라
 *
 * 풀이 1
 * 1. 내부 공간과 외부 공간을 구분한다.
 * 2. 치즈가 없어질 때마다 외부,내부 공간이 연결되는지 확인힌다.
 * 3.
 * */

import java.io.*
import java.util.*

class bj2636 {

    val arrR = arrayOf(0, 0, 1, -1)
    val arrC = arrayOf(1, -1, 0, 0)
    var board = Array(100) { IntArray(100) { -1 } }
    var row = 0
    var col = 0

    fun solve() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        var st = StringTokenizer(br.readLine())
        row = st.nextToken().toInt()
        col = st.nextToken().toInt()

        var total = 0
        for (r in 0 until row) {
            st = StringTokenizer(br.readLine())
            for (c in 0 until col) {
                val tmp = st.nextToken().toInt()
                board[r][c] = tmp
                if (tmp == 1) total++
            }
        }
        for (c in 0 until col) {
            board[0][c] = 2
            board[row - 1][c] = 2
        }
        for( r in 0 until row){
            board[r][0] = 2
            board[r][col-1] = 2
        }



        var pre = total
        var count = 0
        // 다음에 없어질 치즈는 3으로 표시 후 일괄 삭제
        while (true) {
            if(total == 0) break
            pre = total
            updateSpace()

            // 한바퀴 돌기
            for (r in 0 until row) {
                for (c in 0 until col) {
                    if (board[r][c] != 1) continue
                    for (i in 0..3) {
                        val tmp = (r + arrR[i]) to (c + arrC[i])
                        if (tmp.first < 0 || tmp.first >= row ||
                            tmp.second < 0 || tmp.second >= col) continue
                        if (board[tmp.first][tmp.second] == 2) {
                            board[r][c] = 3
                            break
                        }
                    }
                }
            }

            // 3으로 표시된 부분 2로 변경
            for (r in 0 until row) {
                for (c in 0 until col) {
                    if(board[r][c] == 3){
                        total--
                        board[r][c] = 2
                    }
                }
            }

            count++
        }

        println(count)
        println(pre)
    }

    fun updateSpace() {
        for (r in 0 until row) {
            for (c in 0 until col) {
                if (board[r][c] != 2) continue
                var Q = LinkedList<Pair<Int, Int>>()
                Q.add(r to c)
                while (true) {
                    if (Q.isEmpty()) break
                    val now = Q.first
                    Q.removeFirst()

                    for (i in 0..3) {
                        val tmp = (now.first + arrR[i]) to (now.second + arrC[i])
                        if (tmp.first < 0 || tmp.first >= row ||
                            tmp.second < 0 || tmp.second >= col) continue
                        if (board[tmp.first][tmp.second] == 0) {
                            Q.add(tmp.first to tmp.second)
                            board[tmp.first][tmp.second] = 2
                        }
                    }

                }
            }
        }
    }
}

fun main() {
    bj2636().solve()
}