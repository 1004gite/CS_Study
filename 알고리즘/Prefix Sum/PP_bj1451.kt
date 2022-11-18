import java.util.*

class bj1451 {
}
/**
 * n*m 크기의 수가 직사각형에 n*m개 써있다.
 * 직사각형을 겹치지 않게 3등분 해야 한다.
 * 각각의 칸은 작은 하나의 직사각형에 포함되어야 한다.
 * 각각의 직사각형은 적어도 하나의 숫자를 포함해야 한다.
 * 각 직사각형이 포함하는 숫자의 합을 직사각형의 수로 볼때 직사각형의 수들의 곱이 최대가 되게 하라
 * 최대 넓이는 250이다.
 *
 * 1. 첫번째 직사각형은 0,0에서 시작한다고 본다.
 * 2. 가로가 꽉 차지 않은 경우
 *  -> 두번째 사각형으로 가로를 꽉 채운다, 이때 세로는 첫번째 사각형과 같거나 꽉 차야 한다.
 * 3. 가로가 꽉 찬 경우
 *  -> 두번째 사각형은 세번째 사각형과 가로 or 세로를 나눠먹는다.
 * */

fun getPrefix(): Array<IntArray>{
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val row = st.nextToken().toInt()
    val col = st.nextToken().toInt()

    val board = Array(row){
        val str = br.readLine()
        IntArray(col){str[it].digitToInt() }
    }
    br.close()

    for(r in 1 until row) board[r][0] = board[r][0] + board[r-1][0]
    for(c in 1 until col) board[0][c] = board[0][c] + board[0][c-1]
    for(r in 1 until row){
        for(c in 1 until col){
            board[r][c] += board[r][c-1] + board[r-1][c] - board[r-1][c-1]
        }
    }

    return board
}

fun getSum(preFix: Array<IntArray>, start: Pair<Int,Int>, end: Pair<Int,Int>): Long{
    var result = preFix[end.first][end.second]

    val minus1 = start.first-1 to end.second
    val minus2 = end.first to start.second-1
    val plus = start.first-1 to start.second-1
    if(checkRange(preFix,minus1)) result -= preFix[minus1.first][minus1.second]
    if(checkRange(preFix,minus2)) result -= preFix[minus2.first][minus2.second]
    if(checkRange(preFix,plus)) result += preFix[plus.first][plus.second]

    return result.toLong()
}

fun checkRange(preFix: Array<IntArray>, p: Pair<Int,Int>): Boolean{
    return !(p.first < 0 || p.first >= preFix.size || p.second < 0 || p.second >= preFix[0].size)
}

fun main(){

    val prefix = getPrefix()

    var max = 0L

    // 첫번째 직사각형의 가로, 세로가 모두 남는 경우
    for(r in 0 until prefix.size-1){
        for(c in 0 until prefix[0].size-1){
            val first = getSum(prefix, 0 to 0, r to c)
            // 두번째는 가로가 꽉차게끔 만들어지고 세로는 1과 같거나 꽉찬다.
            var second = getSum(prefix, 0 to c+1, r to prefix[0].size-1)
            var third = getSum(prefix, r+1 to 0, prefix.size-1 to prefix[0].size-1)
            max = maxOf(max, (first*second*third))

            second = getSum(prefix, 0 to c+1, prefix.size-1 to prefix[0].size-1)
            third = getSum(prefix, r+1 to 0, prefix.size-1 to c)
            max = maxOf(max, (first*second*third))
        }
    }

    // 첫번째 직사각형이 가로를 모두 차지한 경우
    for(r in 0 until prefix.size-2){
        val first = getSum(prefix, 0 to 0, r to prefix[0].size-1)

        // 가로를 나눠먹는다.
        for(c in 0 until prefix[0].size-1){
            val second = getSum(prefix, r+1 to 0, prefix.size-1 to c)
            val third = getSum(prefix, r+1 to c+1, prefix.size-1 to prefix[0].size-1)

            max = maxOf(max, (first*second*third))
        }

        // 세로를 나눠먹는다.
        for(row in r+1 until prefix.size-1){
            val second = getSum(prefix, r+1 to 0, row to prefix[0].size-1)
            val third = getSum(prefix, row+1 to 0, prefix.size-1 to prefix[0].size-1)

            max = maxOf(max, (first*second*third))
        }
    }

    // 첫번째 직사각형이 세로를 모두 차지한 경우
    for(c in 0 until prefix[0].size-2){
        val first = getSum(prefix, 0 to 0, prefix.size-1 to c)

        // 가로를 나눠먹는다.
        for(col in c+1 until prefix[0].size-1 ){
            val second = getSum(prefix, 0 to c+1, prefix.size-1 to col)
            val third = getSum(prefix, 0 to col+1, prefix.size-1 to prefix[0].size-1)

            max = maxOf(max, (first*second*third))
        }

        // 세로를 나눠먹는다.
        for(r in 0 until prefix.size-1){
            val second = getSum(prefix, 0 to c+1, r to prefix[0].size-1)
            val third = getSum(prefix, r+1 to c+1, prefix.size-1 to prefix[0].size-1)

            max = maxOf(max, (first*second*third))
        }
    }

    println(max)
}