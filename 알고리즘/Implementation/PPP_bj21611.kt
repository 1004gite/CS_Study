/**
 * n은 항상 홀수이고 크기가 n*n인 격자가 있다.
 * 행렬은 (1,1) 에서 시작하며 마법사는 ((n+1)/2, (n+1)/2) 에 있다.
 * 칸과 칸 사이는 벽으로 막혀있을 수도 있다.
 * 상어가 있는 칸을 제외한 칸에는 1,2,3번 구슬 중 하나가 들어갈 수 있고 연속하는 칸에 같은 번호의 구슬이 있으면 연속한 구슬이라고 한다.
 * 마법을 사용하기 위해 d,s(방향,거리)를 정한다.
 * d는 (1,2,3,4)의 값을 가질 수 있고 순 (위,아래,좌,우) 이다.
 * 1. 마법사는 해당 방향으로 거리가 s이하인 모든 칸에 있는 구슬을 파괴한다.
 * 구슬이 파괴되면 빈 칸이 되고 벽은 파괴되지 않는다.
 * 2. 어떤 칸의 번호가 1 작은 곳이 빈칸이고 해당 칸에 구슬이 있으면 구슬을 1 작은 칸으로 이동시킨다.
 * 3. 4개 이상 연속하는 구슬이 있으면 구슬은 폭발한다.
 *      -> 2,3의 과정을 변화가 없을 때까지 반복해야 한다.
 * 4. 그룹은 연속된 구슬들의 모임이고 하나의 그룹은 두개의 구슬로 변한다. (그룹에 있는 구슬의 수, 그룹을 이루는 구슬의 번호)
 *      구슬은 그룹의 순서대로 1번칸부터 들어가고 칸을 넘어가는 구슬들은 없어진다.
 *
 * 풀이 1
 * 1. 칸의 번호로 좌표를 알 수 있고 좌표로도 칸의 번호를 알 수 있다.
 * 2. 빈칸에 밀어넣는 과정보다 마법을 시뮬레이션 하는 과정이 좀 더 복잡할 것 같아서 행렬을 기준으로 한다.
 * 3. 빈칸에 밀어넣는 과정은 {가장 번호가 낮은 빈칸, 지금 보는 칸}으로 twoPointer를 사용한다.
 * 4. 편의를 위해 번호에 해당하는 좌표를 미리 저장해놓고 사용한다.
 * */

import java.io.*
import java.util.*

class bj21611 {

    val boardNum = Array(49*49){-1 to -1}
    var board = Array(49){IntArray(49)}
    val directionArr = arrayOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
    var n = 0
    var halfN = 0
    var nSquare = 0

    var explode = arrayOf(0,0,0,0)

    fun solve(){

        val br = BufferedReader(InputStreamReader(System.`in`))
        var st = StringTokenizer(br.readLine())

        n = st.nextToken().toInt()
        halfN = n/2
        nSquare = n*n
        var m = st.nextToken().toInt()

        setBoardNum()
//        debug2()

        for(r in 0 until n){
            st = StringTokenizer(br.readLine())
            for(c in 0 until n){
                board[r][c] = st.nextToken().toInt()
            }
        }

        // 처음부터 4개 이상 연속한 구슬이 없다는 조건은 있는데 중간에 빈 공간이 없다는 조건은 없다.
        while(true){
//            debug("beforeUpdate")
            updateBoard()
//            debug("afterUpdate")
            if(!simulateExplosion()) break // 폭발이 없으면 그만
        }
        while(m > 0){
            m--
            st = StringTokenizer(br.readLine())
            val d = st.nextToken().toInt() - 1
            val s = st.nextToken().toInt()

//            debug("beforeMagic")
            magic(d,s)
//            debug("afterMagic")

            while(true){
                updateBoard()
                if(!simulateExplosion()) break // 폭발이 없으면 그만
            }

//            debug("beforeGrouping")

            updateByGroup()

        }

        println( explode[1] + 2*explode[2] + 3*explode[3] )
    }

    fun updateByGroup(){
        // 규칙에 따라 같은 그룹들을 변환
        val linkedList = LinkedList<Int>()
        var now = 1
        var sequence = mutableListOf<Int>()
        while(true){
            if(now >= nSquare) break
            val p = boardNum[now]
            if(board[p.first][p.second] == 0){
                if(sequence.isEmpty()) break
                val preP = boardNum[sequence.last()]
                // 번호가 달라지는 경우
                linkedList.add(sequence.size)
                if(linkedList.size >= nSquare) break
                linkedList.add(board[preP.first][preP.second])
                break
            }
            else if(sequence.isEmpty()) sequence.add(now)
            else{
                val preP = boardNum[sequence.last()]
                if(board[p.first][p.second] != board[preP.first][preP.second]){
                    // 번호가 달라지는 경우
                    linkedList.add(sequence.size)
                    if(linkedList.size >= nSquare) break
                    linkedList.add(board[preP.first][preP.second])
                    if(linkedList.size >= nSquare) break
                    sequence.clear()
                }
                sequence.add(now)
            }
            now++
        }

        for(i in 0 until linkedList.size){
//            if( i + 1 >= nSquare ) break
            val p = boardNum[i+1]
            board[p.first][p.second] = linkedList[i]
        }

        for(i in linkedList.size+1 until nSquare){
            val p = boardNum[i]
            board[p.first][p.second] = 0
        }
    }

    fun magic(d: Int, s: Int){
        var p = halfN to halfN
        val move = directionArr[d]
        for(i in 0 until s){
            p = p.first+move.first to p.second+move.second
            if(p.first <0 || p.first >= n || p.second<0 || p.second >= n) break
            board[p.first][p.second] = 0
        }
    }

    fun updateBoard(){
        // 빈칸이 있으면 땡기는 함수
        var blank = -1
        var now = 1
        // 첫 빈칸을 찾아나간다.
        while(true){
            if(now >= nSquare) break
            val p = boardNum[now]
            if(board[p.first][p.second] == 0){
                blank = now
                break
            }
            now++
        }
        // 당길 수 있는 부분이 있는지 검사한다.
        while(true){
            if(now >= nSquare) break
            val p = boardNum[now]
            if(board[p.first][p.second] != 0){
                // 빈칸이 아니면 당기고 업데이트한다.
                val blankP = boardNum[blank]
                board[blankP.first][blankP.second] = board[p.first][p.second]
                board[p.first][p.second] = 0
                blank++
            }
            now++
        }
    }

    fun simulateExplosion(): Boolean{
        // 이 함수는 모든 구슬을 당긴 후에 실행되므로 구슬 중간에 0이 들어가는 일은 없다.
        var changed = false
        // 4개 이상 연속하는 구슬이 있으면 폭발시킨 후 업데이트한다.
        // 폭발이 없었으면 false를 return한다.
        var now = 1
        var sequence = mutableListOf<Int>()
        while(true){
            if(now >= nSquare) break
            val p = boardNum[now]
            if(board[p.first][p.second] == 0){
                // 지금까지 쌓인거 판단 후에 그만해야함
                if(sequence.size >= 4){
                    val preP = boardNum[sequence.last()]
                    // 4개 이상인 경우터진 개수 업데이트
                    explode[board[preP.first][preP.second]] += sequence.size
//                        println("${p.first},${p.second} is diff ${preP.first},${preP.second}")
//                        debug("beforeExplode")
                    for(tmp in sequence){
                        val tmpP = boardNum[tmp]
                        board[tmpP.first][tmpP.second] = 0
                    }
//                        debug("afterExplode")
                    changed = true
                }
                break
            }
            // pre와 같은 숫자가 아니면 4개 넘게 연속이었는지 판단 후 업데이트
            else if(sequence.isEmpty()) sequence.add(now)
            else {
                val preP = boardNum[sequence.last()]
                if(board[p.first][p.second] != board[preP.first][preP.second]){
                    if(sequence.size >= 4){
                        // 4개 이상인 경우터진 개수 업데이트
                        explode[board[preP.first][preP.second]] += sequence.size
//                        println("${p.first},${p.second} is diff ${preP.first},${preP.second}")
//                        debug("beforeExplode")
                        for(tmp in sequence){
                            val tmpP = boardNum[tmp]
                            board[tmpP.first][tmpP.second] = 0
                        }
//                        debug("afterExplode")
                        changed = true
                    }
                    sequence.clear()
                }
                sequence.add(now)
            }
            now++
        }
        return changed
    }

    fun setBoardNum(){
        // 번호 세팅
        val moveArr = arrayOf(0 to 1, 1 to 0, 0 to -1, -1 to 0)
        var moveIndex = 0 // 처음에는 가로로 한칸 가는것을 의미
        var index = 0 to 0
        var num = nSquare-1
        while(true){
            if(num == 0 ) break //0번은 한가운데로 상어가 있는 곳
            boardNum[num] = index
//            println("${index.first}, ${index.second} = $num")
            num--
            // 다음 index 설정
            var tmpR = index.first+moveArr[moveIndex].first
            var tmpC = index.second+moveArr[moveIndex].second
            // 범위를 벗어나서나 이미 방문한 곳인 경우 방향을 바꾼다
            if( (tmpR<0 || tmpR>=n || tmpC<0 || tmpC>=n) || board[tmpR][tmpC] == -1)
                moveIndex = (moveIndex+1)%4
//            println("${index.first}, ${index.second}")
            board[index.first][index.second] = -1 // board를 초기화하기 전에 방문 기록용으로 사용
            index = index.first+moveArr[moveIndex].first to index.second+moveArr[moveIndex].second
        }
    }

    fun debug(str: String){
        println(str)
        for(r in 0 until n){
            for(c in 0 until n){
                print("${board[r][c]} ")
            }
            println()
        }
        println()
    }

    fun debug2(){
        println("boardNum")
        for(i in 1..nSquare){
            println("${boardNum[i].first}, ${boardNum[i].second} = $i")
        }
        println()
    }

}

fun main(){
    bj21611().solve()
}