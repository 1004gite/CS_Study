import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

/**
 * n*n 크기의 교실이 있고 n*n명의 학생이 있다.
 * 각 학생들은 번호가 있고 4명의 선호하는 학생이 있다.
 * 상하좌우를 인접하다고 한다.
 * 1 주어진 순서대로 학생들의 자리를 정한다.
 * 2 비어있는 칸 중에서 인접한 칸에 선호하는 학생이 가장 많은 자리로 자리를 정한다.
 * 3 같은 조건이 여러개라면 인접한 칸 중 빈칸이 많은 곳을 우선으로 설정한다.
 * 4 같은 조건의 칸이 많다면 행,열 순으로 번호가 작은 곳을 우선 할당한다.
 * 자리 배치가 끝난 후 학생들의 만족도를 구한다.
 * // 인접한 칸에 있는 선호하는 학생의 수 0-0, 1-1, 2-10, 3-100, 4-1000
 *
 * 풀이 1
 * 1. 문제 그대로 학생들을 배치시킨 후 시뮬레이션한다.
 * 2. 조건을 검사하는 과정이 너무 막연해 막혔다.
 *
 * 풀이 2
 * 1. 자리 배치를 위해 알아야 하는 정보를 먼저 정의하자
 *      {인접한 칸에 좋아하는 학생이 몇명 있는지, 비어있는 칸이 몇개 있는지, 행, 열}의 정보가 있으면 자리를 지정할 수 있다.
 * 2.
 * */
class bj21608 {

    data class Student(var num: Int, var like: MutableList<Int>)
    data class Point(var row: Int, var col: Int, var likeFriend:Int, var emptyCount: Int)

    val arrR = arrayOf(0,0,1,-1)
    var arrC = arrayOf(1,-1,0,0)

    fun solve() {
        val br = BufferedReader(InputStreamReader(System.`in`))

        val n = br.readLine().toInt()

        // 학생들이 좋하하는 학생 리스트
        val info = Array(n * n) { Student(it, mutableListOf()) }
        // 이 배열은 마지막 만족도 측정을 위함임
        // index는 학생의 번호를 의미한다.
        val forSatisfy = Array(n*n+1){ mutableListOf<Int>() }

        for (i in 0 until n * n) {
            val st = StringTokenizer(br.readLine())
            val now = st.nextToken().toInt()
            info[i].num = now

            for (j in 0..3) {
                val tmp = st.nextToken().toInt()
                info[i].like.add(tmp)
                forSatisfy[now].add(tmp)
            }
        }

        // 자리를 기준으로 학생을 배치
        var room = Array(n) { IntArray(n) }
        for (i in 0 until n*n) {
            var candidate = mutableListOf<Point>()
            for(r in 0 until n){
                for(c in 0 until n){
                    if(room[r][c] != 0) continue // 이미 배치되어 있는 자리는 고려하지 않는다.
                    var likeF = 0
                    var emptyC = 0
                    for(j in 0..3){
                        var tmpR = r+arrR[j]
                        var tmpC = c+arrC[j]

                        if(tmpR < 0 || tmpR >= n || tmpC < 0 || tmpC >= n) continue
                        if(room[tmpR][tmpC] == 0) emptyC++
                        else if(info[i].like.contains(room[tmpR][tmpC])) likeF++
                    }
                    candidate.add(Point(r,c,likeF,emptyC))
                }
            }
            candidate.sortWith{ a,b ->
                // 1번조건 = 주변에 선호하는 사람이 많은 경우
                // 주변에 선호하는 사람이 더 많으면 바꾸지 않음
                if(a.likeFriend > b.likeFriend) -1
                else if(a.likeFriend < b.likeFriend) 1
                else{
                    if(a.emptyCount > b.emptyCount) -1
                    else if(a.emptyCount < b.emptyCount) 1
                    else {
                        if(a.row < b.row) -1
                        else if(a.row > b.row) 1
                        else{
                            if(a.col < b.col) -1
                            else 1
                        }
                    }
                }
            }

            // 자리를 배치한다.
            val result = candidate[0]
            room[result.row][result.col] = info[i].num
        }

        // 자리 배치가 모두 끝난 후 만족도를 조사한다.
        var sum = 0
        for(r in 0 until n){
            for(c in 0 until n){
                var count = 0
                for(j in 0..3){
                    var tmpR = r+arrR[j]
                    var tmpC = c+arrC[j]
                    if(tmpR < 0 || tmpR >= n || tmpC < 0 || tmpC >= n) continue
                    if(forSatisfy[room[r][c]].contains(room[tmpR][tmpC])){
                        if(count == 0) count++
                        else count *= 10
                    }
                }
                sum += count

            }
        }

        println("$sum")

//        for(r in 0 until n) {
//            for (c in 0 until n) {
//                print("${room[r][c]} ")
//            }
//            println()
//        }
    }
}

fun main() {
    bj21608().solve()
}