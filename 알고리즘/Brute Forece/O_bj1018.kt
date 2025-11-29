package `Brute Forece`

/**
 * 문제
 * 지민이는 자신의 저택에서 MN개의 단위 정사각형으로 나누어져 있는 M×N 크기의 보드를 찾았다. 어떤 정사각형은 검은색으로 칠해져 있고, 나머지는 흰색으로 칠해져 있다. 지민이는 이 보드를 잘라서 8×8 크기의 체스판으로 만들려고 한다.
 * 체스판은 검은색과 흰색이 번갈아서 칠해져 있어야 한다. 구체적으로, 각 칸이 검은색과 흰색 중 하나로 색칠되어 있고, 변을 공유하는 두 개의 사각형은 다른 색으로 칠해져 있어야 한다. 따라서 이 정의를 따르면 체스판을 색칠하는 경우는 두 가지뿐이다. 하나는 맨 왼쪽 위 칸이 흰색인 경우, 하나는 검은색인 경우이다.
 * 보드가 체스판처럼 칠해져 있다는 보장이 없어서, 지민이는 8×8 크기의 체스판으로 잘라낸 후에 몇 개의 정사각형을 다시 칠해야겠다고 생각했다. 당연히 8*8 크기는 아무데서나 골라도 된다. 지민이가 다시 칠해야 하는 정사각형의 최소 개수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N과 M이 주어진다. N과 M은 8보다 크거나 같고, 50보다 작거나 같은 자연수이다. 둘째 줄부터 N개의 줄에는 보드의 각 행의 상태가 주어진다. B는 검은색이며, W는 흰색이다.
 *
 * 출력
 * 첫째 줄에 지민이가 다시 칠해야 하는 정사각형 개수의 최솟값을 출력한다.
 *
 * 1. 50*50 크기의 판이 있음
 * 2. 좌상단의 좌표와 시작할 색을 정하면 나머지 부분에서 색칠해야 할 횟수를 구할 수 있음
 * */

fun main() {
    val (n,m) = readln().split(" ").map { it.toInt() }
    val board = mutableListOf<List<Char>>().apply {
        repeat(n) {
            add(readln().toCharArray().toList())
        }
    }.toList()

    var min = Int.MAX_VALUE
    for(r in 0..n-8) for(c in 0..m-8) {
        min = minOf(getCount(r,c,'B' ,board), min)
        min = minOf(getCount(r,c,'W' ,board), min)
    }

    print(min)
}

fun getCount(startR: Int, startC: Int, startColor: Char, board: List<List<Char>>): Int {
    fun getRevertColor(color: Char) = if(color == 'B') 'W' else 'B'

    var count = 0
    for(r in startR until startR+8) {
        val rowStartColor = if(r%2 == startR%2) startColor else getRevertColor(startColor)
        for(c in startC until startC+8) {
            val currentColor = if(c%2 != startC%2) rowStartColor else getRevertColor(rowStartColor)
            if(currentColor != board[r][c]) count++
        }
    }

    return count
}