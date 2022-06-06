/**
 * N*N 크기의 땅이 있고 각 한칸은 각 나라이다.
 * 각 나라에는 A[r][c]명이 살고 있다.
 * 국경선을 공유하는 두 나라의 인구수 차이가 L명 이상 R명 이하라면 국경선을 열어 연합 상태가 된다.(여러 나라가 연결될 수 있음)
 * 하루가 지나면 각 칸의 인구수는 (연합의 인구수)/(연합 칸의 개수)가 된다. (소숫점은 버린다.)
 * 그 후 국경선을 다시 닫는다.
 * 인구 이동은 몇일동안 발생하는가
 *
 * 풀이 1 -> 틀렸습니다.
 * 1. 인구 이동은 2000회 이하이고 땅의 최대 크기는 2500이다.
 * 2. 국경을 열 때 연합에 들어가게 된다.
 *      연합들은 lsit에 따로 묶어 저장하고 연합 번호는 list의 번호로 한다. // 연합이 없으면 -1
 * 3. 연합의 인구, 연합의 번호를 표현하는 배열을 만든다.
 *      연합끼리 묶어놓은 list를 만든다.
 * 4. 국경을 검사할 떄는 오른쪽,아래쪽만 검사한다.
 *      왼쪽 위 연합을 기준으로 연합 번호를 생성할 수 있다.
 *
 * 풀이 2 -> 틀렸습니다.
 * 1. 풀이 1은 같은 좌표가 두번 들어갈 수 있다.
 * 2. 또, 오른쪽을 고려한 후에 아래쪽을 고려하면 먼저 고려한 쪽은 최신 정보로 고려하지 않았을 수 있다.
 *
 * 풀이 3
 * 1. 풀이 2번을 사용하면 두 node가 모두 다른 연합에 속해있는 상태로 국경이 열려야 하는 상황이 발생할 수 있다.
 * 2. 어떤 node가 연합에 속하게 된다면 알림을 주어 연합에 속한 node 주변을 검사하는 식으로 변경한다.
 */

import java.io.*
import java.util.*
import kotlin.math.*

class bj16234 {

    data class Point(var row: Int, var col: Int)

    var land = Array(50) { IntArray(50) }
    var union = Array(50) { IntArray(50) { -1 } }
    var unionList = mutableListOf<MutableList<Point>>()

    val arrR = intArrayOf(0, 0, -1, 1)
    val arrC = intArrayOf(1, -1, 0, 0)

    var n = 0
    var L = 0
    var R = 0


    fun solve() {

        val br = BufferedReader(InputStreamReader(System.`in`))
        var st = StringTokenizer(br.readLine())

        n = st.nextToken().toInt()
        L = st.nextToken().toInt()
        R = st.nextToken().toInt()

        for (r in 0 until n) {
            st = StringTokenizer(br.readLine())
            for (c in 0 until n) {
                land[r][c] = st.nextToken().toInt()
            }
        }

        var count = 0
        setUnion()
        while (unionList.isNotEmpty()) {
            // 연합국 인구이동
            for (u in unionList) {
                var totalPerson = 0
                for (country in u) {
                    totalPerson += land[country.row][country.col]
                }
                val mean = totalPerson / u.size
                for (country in u) {
                    land[country.row][country.col] = mean
                }
            }
            count++
            resetUnion()
            setUnion()
        }

        print("$count\n")
    }

    fun setUnion() {
        for (r in 0 until n) {
            for (c in 0 until n) {
                // 이미 고려되었음
                if (union[r][c] != -1) continue
                // 상하좌우의 인원차를 확인
                for (i in 0..3) {
                    val nowR = r + arrR[i]
                    val nowC = c + arrC[i]
                    if (nowR > n - 1 || nowR < 0 || nowC > n - 1 || nowC < 0) continue
                    val diff = abs(land[r][c] - land[nowR][nowC])
                    if (diff in L..R) {
                        // 국경을 허무는 경우를 발견
                        updateBFS(r, c)
                        break
                    }
                }
            }
        }

    }

    // 국경을 허무는 경우의 좌표가 입력으로 들어온다.
    // 가능한 모든 국경을 bfs로 탐색하며 연결한다.
    fun updateBFS(row: Int, col: Int) {
        var queue: Queue<Point> = LinkedList<Point>()
        val nowNum = unionList.size
        unionList.add(arrayListOf(Point(row, col)))
        union[row][col] = nowNum

        queue.add(Point(row, col))
        while (queue.isNotEmpty()) {
            val now = queue.element()
            queue.remove()

            for (i in 0..3) {
                val nowR = now.row + arrR[i]
                val nowC = now.col + arrC[i]
                if (nowR < 0 || nowR > n - 1 || nowC < 0 || nowC > n - 1) continue
                if (union[nowR][nowC] != -1) continue //이미 고려함
                val diff = abs(land[now.row][now.col] - land[nowR][nowC])
                if (diff < L || diff > R) continue
                union[nowR][nowC] = nowNum
                unionList[nowNum].add(Point(nowR, nowC))
                queue.add(Point(nowR, nowC))
            }
        }
    }

    fun resetUnion() {
        unionList.clear()
        union = Array(50) { IntArray(50) { -1 } }
    }
}

fun main() {
    bj16234().solve()
}