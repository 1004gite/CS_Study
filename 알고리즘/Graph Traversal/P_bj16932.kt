/**
 * 0,1 로 이루어진 n*m크기의 판이 주어진다.
 * 배열의 칸 하나에 있는 수를 변경해서 1이 연결된 부분의 크기가 최대가 되게 하자
 *
 * 풀이 1
 * 1. 모든 연결된 1들에 대해 탐색하여 숫자를 연결된 크기로 바꾼다.
 * 2. 그 후 모든 0에 대해 1로 바꾸었을 때 상하좌우에 연결되는 그룹이 있는지 확인한다.
 * 3. 이런 방식으로 하면 ㄷ으로 생간 그룹의 경우 크기가 같은 서로 다른 그룹으로 오인할 수 있다.
 *
 * 풀이 2
 * 1. 그룹을 나누고 해당 그룹을 그룹의 번호로 업데이트한다.
 * 2. 이때 그룹번호는 2부터 시작하며 info라는 배열의 그룹번호 index에 크기 정보를 저장한다.
 * */

import java.io.*
import java.util.*

class bj16932 {

    var info = mutableListOf<Int>().apply {
        add(-1)
        add(-1)
    }
    var board = Array(1000) { IntArray(1000){-1} }
    var row = 0
    var col = 0
    val moveR = arrayOf(0,0,1,-1)
    val moveC = arrayOf(1,-1,0,0)

    fun solve() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        var st = StringTokenizer(br.readLine())

        row = st.nextToken().toInt()
        col = st.nextToken().toInt()

        for (r in 0 until row) {
            st = StringTokenizer(br.readLine())
            for(c in 0 until col){
                board[r][c] = st.nextToken().toInt()
            }
        }

        // 그룹 정보 업데이트
        for (r in 0 until row) {
            for(c in 0 until col){
                if(board[r][c] == 1) updateGroup(r to c)
            }
        }

        // 0인것을 1로 바꿨을 때 시뮬레이션
        var max = 0
        for (r in 0 until row) {
            for(c in 0 until col){
                if(board[r][c] != 0) continue

                // 상하좌우에 그룹이 있는지 확인
                // 중복된 그룹은 더하지 않기 위함
                var set = mutableSetOf<Int>()
                for(i in 0..3){
                    val next = (r+moveR[i]) to (c+moveC[i])
                    if(next.first < 0 || next.first >= row || next.second < 0 || next.second >= col) continue
                    if(board[next.first][next.second] == 0 ) continue
                    set.add(board[next.first][next.second])
                }

                var sum = 1
                set.stream().forEach { sum += info[it] }
                if(max < sum){
                    max = sum
//                    println("updated $r,$c - $sum")
                }
            }
        }

        println(max)
    }

    fun updateGroup(start: Pair<Int,Int>){
        val groupNum = info.size
        var Q = LinkedList<Pair<Int,Int>>()
        Q.add(start)
        var size = 0

        while(true){
            if(Q.isEmpty()) break
            val now = Q.first
            Q.removeFirst()
            if(board[now.first][now.second] != 1) continue //이미 방문하여 업데이트 한 것임

            board[now.first][now.second] = groupNum
            size++

            for(i in 0..3){
                val next = (now.first+moveR[i]) to (now.second+moveC[i])
                if(next.first < 0 || next.first >= row || next.second < 0 || next.second >= col) continue
                if(board[next.first][next.second] == 1){
                    Q.add(next)
                }
            }
        }

        info.add(size)
    }
}

fun main(){
    bj16932().solve()
}