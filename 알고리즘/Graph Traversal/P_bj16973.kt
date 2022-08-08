/**
 * n*m 크기의 판에 H*W크기의 직사각형이 있다.
 * 직사각형을 특정 좌표까지 이동시켜야 할 때 가장 최단 횟수를 구하여라
 * 이때, 판은 빈칸 또는 벽으로 이루어져 있고 직사각형은 벽을 뚫고 지나갈 수 없다.
 *
 * 풀이 1
 * 1. 초기 직사각형은 벽에 겹치지 않게 주어진다.
 * 2. 좌표를 이동할 때 왼쪽 상단을 기준으로 하고 테두리를 검사하여 갈 수 있는 곳인지 판단한다.
 * 3. bfs를 사용하여 업데이트한다.
 * */

import java.io.*
import java.util.*

class bj16973 {

    val moveR = arrayOf(1,-1,0,0)
    val moveC = arrayOf(0,0,-1,1)

    fun solve(){

        val br = BufferedReader(InputStreamReader(System.`in`))
        var st = StringTokenizer(br.readLine())
        val n = st.nextToken().toInt()
        val m = st.nextToken().toInt()

        // 벽은 -2로 표시하고 나머지는 횟수를 나타낸다.
        // 벽은 아니나 갈 수 없는 곳은 -1로 표시한다.
        var board = Array(n){IntArray(m){-1} }
        for(r in 0 until n){
            st = StringTokenizer(br.readLine())
            for(c in 0 until m){
                if(st.nextToken().toInt() == 1){
                    board[r][c] = -2
                }
            }
        }

        st = StringTokenizer(br.readLine())
        val h = st.nextToken().toInt()
        val w = st.nextToken().toInt()
        val start = (st.nextToken().toInt()-1) to (st.nextToken().toInt()-1)
        val end = (st.nextToken().toInt()-1) to (st.nextToken().toInt()-1)

        var visited = Array(n){BooleanArray(m)}
        board[start.first][start.second] = 0
        var Q = LinkedList<Pair<Int,Int>>()
        Q.add(start)

        while(true){
            if(Q.isEmpty()) break
            val now = Q.first
            Q.removeFirst()
            if(visited[now.first][now.second]) continue
            visited[now.first][now.second] = true

            for(i in 0..3){
                // 양끝 좌표를 구한 뒤 이동할 수 있는지 판단한다.
                val tmpLT = (now.first+moveR[i]) to (now.second+moveC[i])
                val tmpRB = (tmpLT.first+h-1) to (tmpLT.second+w-1)
                // 양 끝점이 판 안에 있는지 우선 검사
                if(tmpLT.first < 0 || tmpLT.first >= n || tmpLT.second < 0 || tmpLT.second >= m ||
                    tmpRB.first < 0 || tmpRB.first >= n || tmpRB.second < 0 || tmpRB.second >= m) continue
                // 이미 왔던 곳은 업데이트 하지 않는다.
                if(visited[tmpLT.first][tmpLT.second]) continue
                // 이동했을 때 벽에 걸리는 부분이 있는지 검사
                var impossible = false
                for(r in tmpLT.first..tmpRB.first){
                    // 세로로 내려가면서 검사
                    if(board[r][tmpLT.second] == -2 || board[r][tmpRB.second] == -2){
                        impossible = true
                        break
                    }
                }
                if(impossible) continue
                for (c in tmpLT.second..tmpRB.second) {
                    // 가로로 검사
                    if (board[tmpLT.first][c] == -2 || board[tmpRB.first][c] == -2) {
                        impossible = true
                        break
                    }
                }
                if(impossible) continue

                // 이동할 수 있는 곳임
                board[tmpLT.first][tmpLT.second] = board[now.first][now.second]+1
                Q.add(tmpLT)
            }
        }

//        // debug
//        for(r in 0 until n){
//            for( c in 0 until m){
//                print("${board[r][c]} ")
//            }
//            println()
//        }
        println(board[end.first][end.second])
    }
}

fun main(){
    bj16973().solve()
}