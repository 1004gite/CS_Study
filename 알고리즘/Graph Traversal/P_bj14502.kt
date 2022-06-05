/*
* 크기가 N*M인 직사각형이 {빈칸,벽,바이러스}로 이루어져 있다.
* 바이러스는 인접한 빈 칸으로 모두 퍼질 수 있다.
* 벽을 3개 세워서 안전 영역을 최대한 크게 하자.
*
* 풀이 1
* 0. 직사각형의 최대 크기가 8*8로 크지 않다.
* 1. 벽을 세워서 나눌 수 있는 공간을 찾아야 한다.
* 2. 바이러스가 있는 공간이 적을수록 좋다.
* 3. 벽을 세울 수 있는 모든 경우의 수를 고려하며 바이러스를 확산시킨다.
* */

import java.io.*
import java.util.*

class bj14502 {

    data class Point(var row: Int, var col: Int)

    private var board = Array(8) { IntArray(8) { 1 } }
    var virusPoint = mutableListOf<Point>()
    val arrR = intArrayOf(0,0,1,-1)
    val arrC = intArrayOf(1,-1,0,0)
    var n = 0
    var m = 0

    fun solve() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        var st = StringTokenizer(br.readLine())

        n = st.nextToken().toInt() //세로
        m = st.nextToken().toInt() //가로
        var space = 0

        for (row in 0 until n) {
            st = StringTokenizer(br.readLine())
            for (col in 0 until m) {
                board[row][col] = st.nextToken().toInt()
                if (board[row][col] == 2) {
                    virusPoint.add(Point(row,col))
                } else if (board[row][col] == 0) space++
            }
        }

        // 모든 경우의 수
        var min = 100
        for (a in 0 until (n * m)-2) {
            val aR = a / m
            val aC = a % m
            if (board[aR][aC] != 0) continue
            board[aR][aC] = 1
            for (b in a+1 until (n * m)-1) {
                val bR = b / m
                val bC = b % m
                if (board[bR][bC] != 0) continue
                board[bR][bC] = 1
                for (c in b+1 until n * m) {
                    val cR = c / m
                    val cC = c % m
                    if (board[cR][cC] != 0) continue
                    board[cR][cC] = 1
                    // 바이러스 시뮬레이션
                    val virusCount = simulate()
                    if( virusCount < min) min = virusCount
                    board[cR][cC] = 0
                }
                board[bR][bC] = 0
            }
            board[aR][aC] = 0
        }

        // 벽을 세운 공간 3개도 빼줘야함
        print("${space-min-3}\n")
    }

    // 현 상태에서 바이러스가 침식한 공간을 리턴
    fun simulate(): Int {
        var count = 0
        var queue : Queue<Point> = LinkedList<Point>()
        var tmpBoard = getCopiedArr(board)
        for(p in virusPoint){
            queue.add(p)
        }

        while(queue.isNotEmpty()){
            val now = queue.element()
            queue.remove()

            //상하좌우
            for( i in 0..3){
                val nowR = now.row+arrR[i]
                val nowC = now.col+arrC[i]
                if(nowR<0 || nowR > n-1 || nowC < 0 || nowC > m-1) continue
                if(tmpBoard[nowR][nowC] != 0) continue
                tmpBoard[nowR][nowC] = 2
                queue.add(Point(nowR,nowC))
                count++
            }
        }

        return count
    }

    private fun getCopiedArr(target : Array<IntArray>) : Array<IntArray>{
        var result = Array(8){IntArray(8)}
        for( i in target.indices){
            result[i] = target[i].clone()
        }
        return result
    }

}

fun main(){
    bj14502().solve()
}