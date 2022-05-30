/**
 * RxC 크기의 판이 있고 일부 칸에는 폭탄이 있다.
 * 폭탄은 3초후에 폭팔하며 해당 칸을 포함한 상하좌우는 빈칸(폭탄이 없는 칸)이 된다.
 * 맨 처음, 일정 칸에 폭탄이 설치되어 있고 2초 후 폭탄이 설치되어 있지 않은 칸에 폭탄을 모두 설치한다.
 * 1초 후 처음 설치한 폭탄이 터진다.
 * 이제부터 1초동안은 남은 칸에 폭탄을 설치하고 1초를 기다리는 것을 반복한다.
 * 즉, 2초때 모든 판에 폭탄이 설치된 상태이고 3초때 먼저 심어진 폭탄이 터진다. 4초부터는 반복이다.
 *
 * 풀이 1
 * 1. 짝수시간이 지난 직후에는 모든 칸에 폭탄이 있다.
 * 2. 홀수 시간이 지난 직후에는 n-3초 직후에 설치한 폭탄이 터진다.
 * 3. 홀수 시간에서 n-3초전에 심은 폭탄은 n-4초때 터진 자리와 같다.
 * 4. 각 홀수 시간에는 해당 시간에 심은 폭탄의 자리를 기억한다.
 *      n초에 심은 폭탄의 자리로 n+4초에 심을 폭탄의 자리를 알 수 있다.
 * 5. dp를 이용한다.
 *
 * 풀이 2
 * 1. 3초 전 폭탄이 터진다는 것은 2번 전에 설치한 폭탄이 터진다는 의미이고
 *      이는 직전에 심은 폭탄에 영향을 주는 것을 의미한다. -> dp로 풀려면 이전꺼도 같이 업데이트 해야한다.
 * 2. 폭탄을 심은 시간으로 표현한다.
 */

import java.util.*
import java.io.*

class bj16918 {

    private var board = Array(200) { IntArray(200) }

    fun solve() {

        val br = BufferedReader(InputStreamReader(System.`in`))
        val st = StringTokenizer(br.readLine())

        val r = st.nextToken().toInt()
        val c = st.nextToken().toInt()
        val n = st.nextToken().toInt()

        // false인 곳은 폭탄이 있는 곳이다.
        for (row in 0 until r) {
            val str = br.readLine()
            for (col in 0 until c) {
                if (str[col] == '.') board[col][row] = -1
                else board[col][row] = 3 // 터질 시간을 지정
            }
        }

        val arrX = intArrayOf(0, 0, 1, -1)
        val arrY = intArrayOf(1, -1, 0, 0)
        for (i in 2..n) {
            if (i % 2 == 1) {
                // 홀수일땐 n-3초에 설치한 폭탄이 폭발한다.
                // 이때 같은 시간에 터지는 폭탄은 동시에 터지므로 사라지지 않도록 유의한다.
                // 이번꺼 주변 먼저
                for (row in 0 until r) {
                    for (col in 0 until c) {
                        if (board[col][row] != i) continue
                        for (idx in 0..3) {
                            val nowX = col + arrX[idx]
                            val nowY = row + arrY[idx]
                            if (nowX < 0 || nowX > c-1 || nowY < 0 || nowY > r-1) continue
                            if(board[nowX][nowY] == i) continue
                            board[nowX][nowY] = -1
                        }
                    }
                }
                // 이번꺼
                for (row in 0 until r) {
                    for (col in 0 until c) {
                        if (board[col][row] != i) continue
                        board[col][row] = -1
                    }
                }
            } else {
                // 짝수일 땐 판이 모두 채워진다.
                for (row in 0 until r) {
                    for (col in 0 until c) {
                        // 터질 시간을 지정
                        if (board[col][row] == -1) board[col][row] = i + 3
                    }
                }
            }
        }

        val bw = BufferedWriter(OutputStreamWriter(System.out))
        for (row in 0 until r) {
            for (col in 0 until c) {
                if (board[col][row] != -1) bw.write("O")
                else bw.write(".")
//                bw.write(board[col][row].toString())
            }
            bw.write("\n")
        }
        bw.flush()
    }
}

fun main() {
    bj16918().solve()
}