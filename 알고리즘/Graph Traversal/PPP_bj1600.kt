/**
 * 말은 체스 나이트처럼 움직이고 장애물을 뛰어넘을 수 있다.
 * 원숭이는 k번만 말처럼 이동할 수 있고 그 이후에는 상하좌우로만 이동할 수 있다.
 * 왼쪽 상단에서 시작하여 오른쪽 하단까지 가는 최소한의 횟수를 구하시오.
 *
 * 풀이 1
 * 1. dp를 이용한다.
 * 2. 이동방법에 제한이 있고 일방향이 아니기 때문에 dp는 사용할 수 없다.
 *
 * 풀이 2 -> 시간 초과
 * 1. bfs를 이용한다.
 * 2. 이때 말처럼 이동하는 경우 뒤로 갔다가 앞으로 가는 것이 더 효율적일 수 있다.
 *  -> 방문 기록을 만들어 한번만 방문하게 하는 방법을 사용하면 안된다.
 * 3. 기술을 쓰지 않는다고 생각할 때 모든 경로는 최단거리가 정해져 있다.
 *  -> 최단 거리는 해당 좌표로 구할 수 있고 row or col이 0이고 장애물이 있을 때만 따로 고려한다.
 * 4. 도착 지점부터 k번 말처럼 이동하여 갈 수 있는 곳 중 최단거리가 가장 짧은 곳을 선택한다.
 * 5. 갈수 없는 경우도 고려한다.
 * 6. 길이 막혀있더라도 말의 움직임으로 넘어갈 수도 있다.
 *
 * 풀이 3
 * 1. 중간에 길이 막혀있는 경우 등 모든 경우를 고려해야 하므로 완전탐색을 사용한다.
 * 2. 말의 이동으로 온 횟수도 신경써야 하기 때문에 board를 3차원으로 만들어 능력 사용 횟수별 최단거리를 기록한다.
 * */

import java.io.*
import java.util.*

class bj1600 {

    data class node(var row:Int, var col: Int, var useK: Int)

    // 총 12개의 경로
    val arrR = arrayOf(0,0,-1,1,-1,-2,-2,-1,1,2,2,1)
    val arrC = arrayOf(1,-1,0,0,-2,-1,1,2,2,1,-1,-2)
    var board = Array(200){ Array(200){ IntArray(31){-1} } }

    fun solve() {

        val br = BufferedReader(InputStreamReader(System.`in`))
        val k = br.readLine().toInt()
        var st = StringTokenizer(br.readLine())
        val w = st.nextToken().toInt()
        val h = st.nextToken().toInt()

        for (r in 0 until h) {
            st = StringTokenizer(br.readLine())
            for (c in 0 until w) {
                val tmp = st.nextToken().toInt()
                for(t in 0..k) {
                    if (tmp == 0) board[r][c][t] = 40001
                }
            }
        }

        if (board[0][0][0] == -1) {
            println(-1)
            return
        }

//        for (i in 0 until k) {
//            board[0][0][i] = 0
//        }
        board[0][0][0] = 0

        var Q = LinkedList<node>()
        Q.add(node(0, 0, 0))

        while (true) {
            if (Q.isEmpty()) break

            val now = Q.first
            Q.removeFirst()

            for (i in 0..11) {
                // 말의 이동은 사용할 수 없는 경우
                if (now.useK == k && i >= 4) break
                val tmp = (now.row + arrR[i]) to (now.col + arrC[i])
                if (tmp.first < 0 || tmp.first >= h ||
                    tmp.second < 0 || tmp.second >= w
                ) continue
                // 갈 수 없는 곳
                if (board[tmp.first][tmp.second][0] == -1) continue

                if (i <= 3) {
                    if (board[tmp.first][tmp.second][now.useK] > board[now.row][now.col][now.useK] + 1) {
                        board[tmp.first][tmp.second][now.useK] = board[now.row][now.col][now.useK] + 1
                        Q.add(node(tmp.first, tmp.second, now.useK))
                    }
                } else {
                    if (board[tmp.first][tmp.second][now.useK + 1] > board[now.row][now.col][now.useK] + 1) {
                        board[tmp.first][tmp.second][now.useK + 1] = board[now.row][now.col][now.useK] + 1
                        Q.add(node(tmp.first, tmp.second, now.useK + 1))
                    }
                }
            }
        }

        var min = 40001
        for (i in 0..k) {
            if (min > board[h-1][w-1][i]) min = board[h-1][w-1][i]
        }

        if (min == 40001) println(-1)
        else println(min)


//        // debug
//        println("debug")
//        for(t in 0..k) {
//            println("k = $t")
//            for (r in 0 until h) {
//                for (c in 0 until w) {
//                    print("${board[r][c][t]} ")
//                }
//                println()
//            }
//        }
    }
}

fun main(){
    bj1600().solve()
}