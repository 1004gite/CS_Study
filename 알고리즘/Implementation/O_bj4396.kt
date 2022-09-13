/**
 * 지뢰가 있는 곳을 누르면 게임이 끝난다.
 * 지뢰가 없는 곳을 누르면 주변 8칸에 지뢰가 몇개 있는지 알려준다.
 * 이미 열린 칸은 X로 열리지 않은 칸은 .으로 나타낸다.
 * 지뢰는 *로 지뢰가 아닌 곳은 .으로 나타낸다.
 *
 * */

import java.io.*

class bj4396 {

    // -1==폭탄, 나머지 숫자==주변 폭탄의 수
    val board = Array(10){ IntArray(10) }
    val arrR = arrayOf(1,1,1,0,0,-1,-1,-1)
    val arrC = arrayOf(-1,0,1,-1,1,-1,0,1)

    fun solve(){
        val br = BufferedReader(InputStreamReader(System.`in`))
        val n = br.readLine().toInt()

        for(r in 0 until n){
            var str = br.readLine()
            for(c in 0 until n){
                if(str[c] == '*') {
                    board[r][c] = -1
                }
            }
        }

        var bomb = false
        for(r in 0 until n){
            val str = br.readLine()
            for(c in 0 until n){
                if(str[c] == 'x'){
                    if(board[r][c] == -1) {
                        bomb = true
                        continue
                    }
                    // 주변 폭탄 개수 탐색
                    var count = 0
                    for(i in 0..7){
                        val tmpR = r+arrR[i]
                        val tmpC = c+arrC[i]

                        if(tmpR < 0 || tmpR >= n || tmpC <0 || tmpC >= n) continue
                        if(board[tmpR][tmpC] == -1) count++
                    }
                    board[r][c] = count
                }
            }
        }

        val bw = BufferedWriter(OutputStreamWriter(System.out))
        if(bomb) {
            for (r in 0 until n) {
                for (c in 0 until n) {
                    if (board[r][c] == -2) bw.write(".")
                    else if (board[r][c] >= 0) bw.write(board[r][c].toString())
                    else bw.write("*")
                }
                bw.write("\n")
            }
        }else{
            for (r in 0 until n) {
                for (c in 0 until n) {
                    if (board[r][c] >= 0) bw.write(board[r][c].toString())
                    else bw.write(".")
                }
                bw.write("\n")
            }
        }
        bw.flush()
    }
}

fun main(){
    bj4396().solve()
}