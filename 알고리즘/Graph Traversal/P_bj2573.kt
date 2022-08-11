/**
 * 빙산의 높이 정보가 주어진다.
 * 빙산은 동서남북에 붙어있는 0의 개수만큼 줄어든다.
 * 빙산이 녹아 2덩이가 될 때까지의 시간을 구하라.
 * 만약 다 녹을때까지 2덩이가 되지 않으면 0을 출력한다.
 *
 * 풀이 1
 * 1. 매번 빙산을 녹이고 2덩이 이상인지 확인한다.
 * 2. 빙산의 총 개수는 10000개 이하이다.
 *  -> 빙산을 녹일 때 최대 90000개의 칸을 모두 검사하는것보다 10000개의 목록을 만들어두는게 유리할 것 같다.
 * */

import java.io.*
import java.util.*

class bj2573 {

    var row = 0
    var col = 0
    var board = Array(300){IntArray(300)}
    var list = LinkedList<Pair<Int,Int>>()

    val moveR = arrayOf(0,0,1,-1)
    val moveC = arrayOf(1,-1,0,0)

    fun solve(){

        val br = BufferedReader(InputStreamReader(System.`in`))
        var st = StringTokenizer(br.readLine())
        row = st.nextToken().toInt()
        col = st.nextToken().toInt()

        for(r in 0 until row){
            st = StringTokenizer(br.readLine())
            for(c in 0 until col){
                val tmp = st.nextToken().toInt()
                if(tmp != 0){
                    board[r][c] = tmp
                    list.add(r to c)
                }
            }
        }

        var time = 0
        while(true){
            // 처음부터 2덩이 이상인 경우도 있으므로 먼저 검사해야 한다.
            if(list.isEmpty()){
                println(0)
                return
            }
            else if(checkMess()){
                println(time)
                return
            }

            updateBoard()
            time++
        }
    }

    fun updateBoard(){
        // 녹을 때 0이 되어 다음 실행에 영향을 미치는 경우를 고려해야 한다.

        var tmpBoard = Array(300){r -> IntArray(300){ c -> board[r][c] } }
        var size = list.size

        while(size > 0){
            size--

            val now = list.first
            list.removeFirst()

            var nearWater = 0
            for(i in 0..3){
                val tmpNow = (now.first+moveR[i]) to (now.second+moveC[i])
                if(tmpNow.first <0 || tmpNow.first >= row || tmpNow.second<0 || tmpNow.second >= col) continue
                if(board[tmpNow.first][tmpNow.second] != 0) continue
                nearWater++
            }

            tmpBoard[now.first][now.second] -= nearWater
            if(tmpBoard[now.first][now.second] > 0) list.add(now)
            else tmpBoard[now.first][now.second] = 0 // 0보다 작은 경우 대비
        }
        board = tmpBoard
    }

    // 한덩이가 아니면 true를 리턴
    fun checkMess(): Boolean{
        // list의 첫번째 원소부터 bfs를 해서 연결된 덩어리의 개수를 구한다.
        // 만약 전체 덩어리개수보다 작으면 여러개의 덩어리로 나누어진 것이다.

        var Q = LinkedList<Pair<Int,Int>>()
        var visited = Array(row){BooleanArray(col)}
        Q.add(list.first)
        var size = 0

        while(true){
            if(Q.isEmpty()) break

            val now = Q.first
            Q.removeFirst()
            if(visited[now.first][now.second]) continue
            visited[now.first][now.second] = true
            size++

            for(i in 0..3){
                val tmpNow = (now.first+moveR[i]) to (now.second+moveC[i])
                if(tmpNow.first < 0 || tmpNow.first >= row || tmpNow.second<0 || tmpNow.second >= col) continue
                if(board[tmpNow.first][tmpNow.second] == 0) continue

                Q.add(tmpNow)
            }
        }

        return list.size > size
    }
}

fun main(){
    bj2573().solve()
}