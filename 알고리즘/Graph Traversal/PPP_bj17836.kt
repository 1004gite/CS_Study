/*
* N*M 크기의 성이 있고 각 칸은 빈칸 또는 벽이다. 벽은 통과할 수 없다.
* 상하좌우로 이동할 수 있고 T칸 이하로 이동하여 목표 위치까지 가야 한다.
* 성 어딘가에 있는 무기를 찾게 되면 벽을 무시할수 있다.
* 최단 시간을 출력하라, 만약 T시간이 넘는다면 Fail을 출력하라
*
* 풀이 1 -> 틀렸습니다.
* 1. dijkstra를 사용할 때 중간에 무기를 찾아도 그 전에 확정된 경로들은 최단경로임이 보장된다.
*   bfs로만 하면 무기를 찾은 다음 업데이트를 보장할 수 없다.
* 2. 중복해서 업데이트되는 경우는 많지 않을 것 같아서 priorityQueue를 사용한다.
* 3. 무기 정보를 들고 다닌다.
*
* 풀이 2 -> 틀렸습니다.
* 1. 조금 돌아가도 무기를 가지고 가는것이 유리할 때가 있다.
*   이때 무기를 가진 주변이 이미 업데이트 되었다면 무기를 가지고 가는 경우는 고려되지 않는다.
* 2. 무기를 가질 떄의 방문기록과 무기가 없을 때의 방문기록을 따로 관리한다.
*
* 풀이 3
* 1. 무기를 얻었을 때 업데이트되는 경로만 탐색에 넣게 되면 비효율적이라도 무기가 있어야만 갈 수 있는 길을 갈 수 없다.
* 2. 무기를 얻은 이후의 값을 따로 관리한다.
*   -> 무기를 얻으면 가장 가깝게 이동할 수 있으므로 무기를 얻은 즉시 값을 계산할 수 있다.
*
* 풀이 4
* 1. 간선의 비용이 모두 1이기 때문에 dijk을 안써도 될것 같다.
* -> 깊이가 기준이기 때문에 어차피 가까운 node를 먼저 방문하게 된다.
* -> 시간이 훤씬 빨랐다.
* */

import java.io.*
import java.util.*
import kotlin.math.min

class bj17836 {

    data class mPoint(var row: Int, var col: Int)

    var n = 0
    var m = 0
    var t = 0
    var board = Array(100) { IntArray(100) }
    var dijk = Array(100) { IntArray(100) { Int.MAX_VALUE } }
    var visited = Array(100) { BooleanArray(100) }
    val arrR = intArrayOf(0, 0, 1, -1)
    val arrC = intArrayOf(1, -1, 0, 0)

    fun solve() {

        val br = BufferedReader(InputStreamReader(System.`in`))
        var st = StringTokenizer(br.readLine())

        n = st.nextToken().toInt() - 1
        m = st.nextToken().toInt() - 1
        t = st.nextToken().toInt()

        for (r in 0..n) {
            st = StringTokenizer(br.readLine())
            for (c in 0..m) {
                board[r][c] = st.nextToken().toInt()
            }
        }
        // a가 더 크면 바꾼다.
//        var pq: PriorityQueue<mPoint> = PriorityQueue { a, b ->
//            dijk[a.row][a.col] - dijk[b.row][b.col]
//        }
        var q : Queue<mPoint> = LinkedList()
        if (board[0][0] == 2) {
            if (m + n > t) print("Fail\n")
            else print("${m + n}\n")
            return
        }
        q.add(mPoint(0, 0))
        dijk[0][0] = 0
        var weaponDistance = Int.MAX_VALUE
        while (true) {
            if (q.isEmpty()) break
            val now = q.element()
            q.remove()
            visited[now.row][now.col] = true
            if (board[now.row][now.col] == 2){
                // 무기까지의 최단거리 결정됨
                weaponDistance = m+n-now.row-now.col + dijk[now.row][now.col]
            }
            // 상하좌우
            for (i in 0..3) {
                val nowR = now.row + arrR[i]
                val nowC = now.col + arrC[i]
                if (nowR < 0 || nowR > n || nowC < 0 || nowC > m) continue
                if (visited[nowR][nowC]) continue
                if (board[nowR][nowC] == 1) continue
                if (dijk[nowR][nowC] > dijk[now.row][now.col] + 1) {
                    dijk[nowR][nowC] = dijk[now.row][now.col] + 1
                    q.add(mPoint(nowR, nowC))
                }
            }

        }
        var result = min(weaponDistance, dijk[n][m])
        if (result > t) print("Fail\n")
        else print("$result\n")

//        for(r in 0..n){
//            for(c in 0..m){
//                print("${dijk[r][c]} ")
//            }
//            println()
//        }
    }
}

fun main() {
    bj17836().solve()
}