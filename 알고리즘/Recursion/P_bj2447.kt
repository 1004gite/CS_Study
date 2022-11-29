class bj2447 {
}

/*
* 별찍기 문제
* 3의 제곱수인 n이 주어진다.
* n이 3이면 가운데가 빈 정사각형 모양이다.
* n이 9이면 n이 3인 모양을 1개의 별로 보고 똑같이 정사각형을 만들면 된다.
* */

fun printStar(r: Int, c: Int, n: Int, board: Array<CharArray>){
    if(n == 3){
        // r,c에서 시작해서 9*9 사각형 그리기
        for(row in 0 until 3){
            for(col in 0 until 3){
                board[r+row][c+col] = '*'
            }
        }
        board[r+1][c+1] = ' '
        return
    }
    // n/3 * n/3을 하나의 별으로 생각한 별 그리기
    val tmp = n/3
    for(cm in 0 until 3) printStar(r, c+cm*tmp,tmp,board)
    printStar(r+tmp, c,tmp,board);     printStar(r+tmp, c+2*tmp,tmp,board)
    for(cm in 0 until 3) printStar(r+2*tmp, c+cm*tmp,tmp,board)

}

fun main(){

    val n = readln().toInt()
    val board = Array(n){CharArray(n){' '} }
    printStar(0,0,n,board)

    val sb = StringBuilder()
    for(r in 0 until n){
        for(c in 0 until n){
            sb.append(board[r][c])
        }
        sb.append('\n')
    }
    print(sb)
}