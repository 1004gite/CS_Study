/*
* 정사각형 모양의 지도가 있다.
* 1은 집이 있는 곳을 나타내고 연결된 집들은 하나의 단지이다.
* 각 단지별 집의 개수를 오름차순으로 출력하자
*
* 풀이 1
* 1. 집이고 주변에 단지로 지정된 건물이 있으면 해당 단지 번호를붙인다.
* 2. 집인데 주변에 단지로 지정된 집이 없으면 새로운 번호를 붙인다.
* 3. 위,아래가 모두 붙어있는데 다른 단지로 표현되어 있다면 두 단지가 같음을 명시한다.
*
* 풀이2
* 1. 집인데 단지가 아닌 곳을 발견하면 주변을 모두 단지로 표시한다.
* */

import java.util.*
import java.io.*

class bj2667 {

    data class Index(var x: Int, var y: Int)

    var board = Array(25) { BooleanArray(25) }
    var isChecked = Array(25) { BooleanArray(25) }
    val xArr = intArrayOf(0, 0, 1, -1)
    val yArr = intArrayOf(1, -1, 0, 0)
    var n = 0

    fun solve() {

        val br = BufferedReader(InputStreamReader(System.`in`))
        n = br.readLine().toInt()

        for (r in 0 until n) {
            val str = br.readLine()
            for (c in 0 until n) {
                if (str[c] == '1') board[c][r] = true
            }
        }

        var list = mutableListOf<Int>()
        for (r in 0 until n) {
            for (c in 0 until n) {
                if (isChecked[c][r]) continue
                if(!board[c][r]) continue
                list.add(makeGroup(Index(c, r)))
            }
        }

        list.sort()
        print(list.size.toString() + "\n")
        for (result in list) {
            print(result.toString() + "\n")
        }
    }

    //bfs
    fun makeGroup(start: Index): Int {
        var Q: Queue<Index> = LinkedList<Index>()
        Q.add(start)
        isChecked[start.x][start.y] = true
        var count = 0
        while (Q.isNotEmpty()) {
            val now = Q.element()
            val x = now.x
            val y = now.y
            Q.remove()
            count++

            for (i in 0..3) {
                val nowX = x + xArr[i]
                val nowY = y + yArr[i]
                if (nowX > n - 1 || nowX < 0 || nowY < 0 || nowY > n - 1) continue
                if(!board[nowX][nowY]) continue
                if(isChecked[nowX][nowY]) continue
                isChecked[nowX][nowY] = true
                Q.add(Index(nowX, nowY))
            }
        }
        return count
    }

}

fun main() {
    bj2667().solve()
}