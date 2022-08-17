/**
 * 미로를 탈출해야 한다. (미로의 가장자리로 가면 탈출할 수 있다.)
 * 불은 매 분마다 4방향으로 확산된다.
 * 주인공은 매 분마다 4방향중 한 방향으로 이동할 수 있다.
 *
 * 풀이 1
 * 1. 주인공을 사방으로 이동시키며 불의 위치를 업데이트한다.
 * 2. 이때 벽을 부수는 등의 변수가 없으므로 bfs를 사용한다.
 *
 * 불이 난 위치가 1개라는 언급은 없기 때문에 불이 난 위치는 0개일 수도 여러개일 수도 있다는 점을 고려해야 한다.
 */

import java.io.*
import java.util.*


class bj4179 {

    val moveR = arrayOf(0,0,1,-1)
    val moveC = arrayOf(1,-1,0,0)

    fun solve(){
        val br = BufferedReader(InputStreamReader(System.`in`))
        val st = StringTokenizer(br.readLine())

        val row = st.nextToken().toInt()
        val col = st.nextToken().toInt()

        // 0갈수있는곳,-1불,-2벽
        val max = row*col+1
        var board = Array(row){IntArray(col){max} }

        var QF = LinkedList<Pair<Int,Int>>()
        var QJ = LinkedList<Pair<Int,Int>>()
        for(r in 0 until row){
            val str = br.readLine()
            for(c in 0 until col){
                if(str[c] == '#') board[r][c] = -2
                else if(str[c] == 'F'){
                    QF.add(r to c)
                    board[r][c] = -1
                }
                else if(str[c] == 'J'){
                    QJ.add(r to c)
                    board[r][c] = 0
                }
            }
        }

        var visited = Array(row){BooleanArray(col)}

        while(true){
            // 아직 탈출하지 못했는데 더이상 갈 곳이 없는 경우 탈출할 수 없다.
            if(QJ.isEmpty()) break

            // 다음 턴에 불이 있는 공간은 갈 수 없기때문에 지훈이의 위치를 먼저 업데이트하고
            // 불이 온다면 -1로 덮어씌운다.
            // 불을 먼저 업데이트 하면 다음턴에 올 불때문에 가능한 위치가 불가능해 지는 경우가 생김
            var tmpQJ = LinkedList<Pair<Int,Int>>()
            while (true){
                if(QJ.isEmpty()) break

                val nowJ = QJ.first
                QJ.removeFirst()

                if(visited[nowJ.first][nowJ.second]) continue
                visited[nowJ.first][nowJ.second] = true
                // 지훈이 간 후 불로 뒤덮힌 곳은 고려하지 않음
                if(board[nowJ.first][nowJ.second] == -1) continue

                for(i in 0..3){
                    val tmpJ = nowJ.first+moveR[i] to nowJ.second+moveC[i]
                    if (tmpJ.first < 0 || tmpJ.first >= row || tmpJ.second < 0 || tmpJ.second >= col){
                        // 탈출
                        println(board[nowJ.first][nowJ.second]+1)
                        return
                    }
                    if(board[tmpJ.first][tmpJ.second] < 0) continue
                    if(board[tmpJ.first][tmpJ.second] > board[nowJ.first][nowJ.second]+1) {
                        board[tmpJ.first][tmpJ.second] = board[nowJ.first][nowJ.second] + 1
                        tmpQJ.add(tmpJ)
                    }
                }
            }
            QJ = tmpQJ

            // 불 위치 업데이트
            var tmpQF = LinkedList<Pair<Int,Int>>()
            while(true) {
                if(QF.isEmpty()) break
                val nowF = QF.first
                QF.removeFirst()

                for (i in 0..3) {
                    val tmpF = nowF.first + moveR[i] to nowF.second + moveC[i]
                    if (tmpF.first < 0 || tmpF.first >= row || tmpF.second < 0 || tmpF.second >= col) continue
                    // 이미 불이 번졌거나 벽인 곳은 고려하지 않음
                    if (board[tmpF.first][tmpF.second] < 0) continue
                    board[tmpF.first][tmpF.second] = -1
                    tmpQF.add(tmpF)
                }
            }
            QF = tmpQF

//            //debug
//            println("debug-==-=-=-=-=")
//            for(arr in board){
//                for(ele in arr) print(ele)
//                println()
//            }
        }

        println("IMPOSSIBLE")
    }
}

fun main(){
    bj4179().solve()
}