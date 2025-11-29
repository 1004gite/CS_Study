import kotlin.math.*

class bj9663 {
}

// 한 열에 1개
// 한 행에 1개를 놓을 수 있다.

var result = 0
var ern = 0
val board = IntArray(15)

fun main(){
    n = readln().toInt()

    if(n == 1){
        println(1)
        return
    }

    for(c in 0 until n){
        board[0] = c
        dfs(1)
    }

    println(result)
}

fun dfs(r: Int){

    for(c in 0 until n){
        // col에 넣을 수 있는지 검사 후 넣는다.
        if(!check(r, c)) continue
        board[r] = c

        if(r == n-1) result++
        else dfs(r+1)
    }

}

fun check(r: Int, c: Int): Boolean{
    // r,c에 넣을 수 있는지 검사
    // 대각선 및 같은 col을 검사
    for(i in 0 until r){
        if(board[i] == c) return false
        if(i-board[i] == r-c) return false
        if((r-i)+c == board[i]) return false
    }

    return true
}