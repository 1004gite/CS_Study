/**
 * n*m 크기의 판이 있고 1은 벽을 의미하고 0은 이동할 수 있는 공간을 의미한다.
 * 왼쪽 상단에서 오른쪽 하단으로 이동해야 한다.
 * 최단경로로 이동해야 하고 벽은 1개까지 부술 수 있다.
 * 최단경로의 길이를 구할 때 출발, 도착점도 1번으로 친다.
 *
 * 풀이 1
 * 1. 벽을 부쉈는지에 대한 정보를 가진 채로 bfs를 한다.
 *  -> 이 경우 벽을 부순 경우와 부수지 않은 경우가 섞여서 불가능하다.
 *  -> 배열을 2개 만들어 한쪽은 벽을 부수지 않은 경우를 업데이트하고 한쪽은 벽을 부순 후의 경우를 업데이트한다.
 *  -> 이미 업데이트 된 값도 도중에 다시 업데이트 될 수 있으므로 더이상 최소값이 업데이트 되지 않을 때까지 반복한다.
 * 2. 벽을 한번 파괴한 후의 배열을 업데이트 할 때 부서진 벽인지 원래 벽인지 알 수 없으므로 벽의 정보는 다른 배열에 따로 저장한다.
 *  -> 두번째 배열에서 이동할 때는 벽으로는 갈 수 없다.
 * */

import java.io.*
import java.util.*

class bj2206 {

    val moveR = arrayOf(0,0,1,-1)
    val moveC = arrayOf(1,-1,0,0)

    fun solve(){
        val br = BufferedReader(InputStreamReader(System.`in`))
        var st = StringTokenizer(br.readLine())
        val row = st.nextToken().toInt()
        val col = st.nextToken().toInt()

        // -1은 벽을 의미한다.
        var isWall = Array(row){BooleanArray(col)}
        val max = row*col+1
        var board = Array(2){Array(row){IntArray(col){max} } }
        for(r in 0 until row){
            val str = br.readLine()
            for(c in 0 until col){
                // 모든 칸을 거쳐서 가는 경우 n*m번이 소요되므로 n*m+1로 초기화한다.
                if(str[c] == '1') isWall[r][c] = true
            }
        }

        var Q = LinkedList<Triple<Int,Int,Int>>()
        board[0][0][0] = 1 // 시작점도 1칸으로 고려하여 초기화
        Q.add(Triple(0,0,0))

        while(true){
            if(Q.isEmpty()) break
            val now = Q.first
            Q.removeFirst()

            for(i in 0..3){
                val tmp = Triple(now.first, now.second+moveR[i], now.third+moveC[i])
                if(tmp.second < 0 || tmp.second >= row || tmp.third <0 || tmp.third >= col) continue

                if(isWall[tmp.second][tmp.third]){
                    if(tmp.first == 1) continue
                    if(board[1][tmp.second][tmp.third] > board[0][now.second][now.third]+1){
                        board[1][tmp.second][tmp.third] = board[0][now.second][now.third]+1
                        Q.add(Triple(1,tmp.second,tmp.third))
                    }
                }
                else{
                    if(board[tmp.first][tmp.second][tmp.third] > board[now.first][now.second][now.third]+1){
                        board[tmp.first][tmp.second][tmp.third] = board[now.first][now.second][now.third]+1
                        Q.add(Triple(tmp.first,tmp.second,tmp.third))
                    }
                }
            }
        }

//        // debug
//        println("==debug 0==")
//        for(r in 0 until row){
//            for(c in 0 until col){
//                print("${board[0][r][c]} ")
//            }
//            println()
//        }
//        println("==debug 1==")
//        for(r in 0 until row){
//            for(c in 0 until col){
//                print("${board[1][r][c]} ")
//            }
//            println()
//        }

        val min = if(board[0][row-1][col-1] < board[1][row-1][col-1]) board[0][row-1][col-1]
            else board[1][row-1][col-1]

        if(min == max) println(-1)
        else println(min)
    }
}

fun main(){
    bj2206().solve()
}