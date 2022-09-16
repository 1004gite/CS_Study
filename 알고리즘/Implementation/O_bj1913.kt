import java.io.BufferedWriter
import java.io.OutputStreamWriter

/**
 * 연속된 자연수를 달팽이 모양으로 출력하고 특정 수의 좌표를 출력하라
 *
 * 풀이 1
 * 1. 왼쪽 상단부터 출발하여 달팽이 모양으로 수를 저장한다.
 * */

class bj1913 {

    // 하 -> 우 -> 상 -> 좌
    val moveR = arrayOf(1,0,-1,0)
    val moveC = arrayOf(0,1,0,-1)

    fun solve(){
        val n = readln().toInt()
        val special = readln().toInt()

        val board = Array(n){IntArray(n)}
        var num = n*n
        var p = 0 to 0
        var moveIndex = 0

        while(num > 0){
//            println("${p.first}, ${p.second} ")
            board[p.first][p.second] = num

            num--
            var tmpNext = p.first+moveR[moveIndex] to p.second+moveC[moveIndex]
            if( (tmpNext.first < 0 || tmpNext.first >= n || tmpNext.second < 0 || tmpNext.second >= n) ||
                (board[tmpNext.first][tmpNext.second] != 0)){
                // 범위를 벗어나거나 이미 업데이트 된 부분이면 방향을 바꾼다.
                moveIndex = (moveIndex+1)%4
                tmpNext = p.first+moveR[moveIndex] to p.second+moveC[moveIndex]
            }

            p = tmpNext
        }

        val bw = BufferedWriter(OutputStreamWriter(System.out))
        var point = 0 to 0
        for(r in 0 until n){
            for(c in 0 until n){
                if(board[r][c] == special) point = r to c
                bw.write("${board[r][c]} ")
            }
            bw.write("\n")
        }

        bw.write("${point.first+1} ${point.second+1}\n")
        bw.flush()
    }
}

fun main(){
    bj1913().solve()
}