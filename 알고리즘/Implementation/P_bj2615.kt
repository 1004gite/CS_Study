/**
 * 19*19 크기의 판에 오목 상황이 주어진다.
 * 두 돌중 하나의 돌만 5개거나 둘다 아닌 경우만 주어진다.
 * 딱 5개가 되어야만 이긴 것일 때 슴패를 판단하라
 *
 * 풀이 1
 * 1. 한 돌이 대각, 위, 옆 등 다양한 방향에서 5개의 일원이 될 수 있다.
 *      -> 재방문해야함
 *      -> 하지만 예를들어 왼대각에 대해 검사했다면 앞으로 왼대각에 대해 검사할 필요는 없다.
 *
 * 2. 어떤 돌에서 5개인지 판단하려면 가로, 세로, 오른대각, 왼대각을 판단해야 한다.
 * */

import java.util.*

class bj2615 {

    val board = Array(19){IntArray(19)}
    // 가로0, 세로1, 왼대각2, 오른대각3
    val check = Array(4){Array(19){BooleanArray(19)} }
    val move = arrayOf(
        arrayOf(0 to -1, 0 to 1),
        arrayOf(-1 to 0, 1 to 0),
        arrayOf(-1 to -1, 1 to 1),
        arrayOf(1 to -1, -1 to 1)
    )
    var upperLEft = 0 to 0

    fun solve(){
        val br = System.`in`.bufferedReader()
        for(r in 0 until 19){
            val st = StringTokenizer(br.readLine())
            for(c in 0 until 19){
                board[r][c] = st.nextToken().toInt()
            }
        }

        for(r in 0 until 19){
            for(c in 0 until 19){
                for(i in 0..3){
                    val result = checkBoard(r to c, i)
                    if(result != 0){
                        println(result)
                        println("${upperLEft.first+1} ${upperLEft.second+1}")
                        return
                    }
                }
            }
        }
        println(0)
    }

    fun checkBoard(p: Pair<Int, Int>, moveIndex: Int): Int{
        // 0-> 아무일 없음, 1 -> 누군가
        val s = board[p.first][p.second]
        if(s == 0 || check[moveIndex][p.first][p.second]) return 0
        check[moveIndex][p.first][p.second] = true
        upperLEft = p
        var tmp = move[moveIndex]
        var count = 1
        var r = p.first
        var c = p.second
        while(true){
            r += tmp[0].first
            c += tmp[0].second
            if(r < 0 || r >= 19 || c < 0 || c >= 19) break
            if(board[r][c] != s) break
            count++
            check[moveIndex][r][c] = true
            upperLEft = r to c
        }
        r = p.first
        c = p.second
        while(true){
            r += tmp[1].first
            c += tmp[1].second
            if(r < 0 || r >= 19 || c < 0 || c >= 19) break
            if(board[r][c] != s) break
            count++
            check[moveIndex][r][c] = true
        }


        return if(count == 5) s else 0
    }
}

fun main(){
    bj2615().solve()
}