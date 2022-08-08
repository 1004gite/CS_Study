/**
 * 8*8 크기의 빈칸/벽으로 이루어진 체스판이 있다.
 * 행렬 기준 7,0 -> 0,7 로 이동해야 한다.
 * 벽은 1초마다 아래로 한칸씩 내려오고 가장 아래칸이라면 벽이 없어지게 된다.
 * 주인공은 주변 8칸으로 이동하거나 가만히 있을 수 있다.
 * 캐릭터가 이동한 후 벽이 움직이는데 벽이 캐릭터의 칸으로 이동하게 되면 캐릭터는 더 이상 이동할 수 없다.(끝나버린다.)
 * 캐릭터가 목표지점까지 이동할 수 있는지 구하자
 *
 * 풀이 1
 * 1. 가만히 있는 선택지가 있기 때문에 모든 상황을 기록할 수 없고 dfs도 사용할 수 없다.
 * 2. 벽이 움직이기 때문에 앞으로 갔다가 다시 되돌아가는 길도 해답이 될 수 있다.
 * 3. 벽이 모두 없어졌는데 살아남을 수 있는 경우의 수가 있다면 목표지점까지 갈 수 있다.
 *      벽이 없어지기 전에 도착할 수도 있다.
 * 4. 늦어도 8번이면 모든 벽이 없어지기 때문에 bfs를 사용한다.
 * 5. 횟수는 상관없이 위치만 중요하므로 set을 사용한다.
 * */

import java.io.*

class bj16954 {

    val arrR = arrayOf(0,0,0,1,1,1,-1,-1,-1)
    val arrC = arrayOf(0,1,-1,0,1,-1,0,1,-1)
    // false = 벽
    var board = Array(8){BooleanArray(8)}

    fun solve(){
        val br = BufferedReader(InputStreamReader(System.`in`))
        var wallCount = 0
        for(r in 0..7){
            val str = br.readLine()
            for(c in 0..7){
                if(str[c] == '.') board[r][c] = true
                else wallCount++
            }
        }

        if(!board[7][0]){
            println(0)
            return
        }

        var case = mutableSetOf<Pair<Int,Int>>()
        case.add(7 to 0)

        while(true){
            if(wallCount == 0 && case.isNotEmpty()){
                println(1)
                return
            }
            if(case.isEmpty()){
                println(0)
                return
            }
            var nextBoard = getNextBoard()
            var tmpCase = mutableSetOf<Pair<Int,Int>>()
            for(now in case){
                // 다음에 갈 경로들
                // 범위검사, 도착지로 가는지(갈 수 있는지 검사), 지금턴에 갈 수 있는지 검사, 다음턴에 없어지는지 검사
                for(i in 0..8){
                    val tmp = (now.first+arrR[i]) to (now.second+arrC[i])
                    // 범위검사
                    if(tmp.first <0 || tmp.first >= 8 ||
                            tmp.second <0 || tmp.second >= 8) continue
                    // 도착인지 검사
                    if(tmp.first == 0 && tmp.second == 7 && board[7][0]){
                        println(1)
                        return
                    }
                    // 이번턴에 갈 수 없거나 다음턴에 없어지면 생략함
                    if(!board[tmp.first][tmp.second] || !nextBoard[tmp.first][tmp.second]) continue
                    tmpCase.add(tmp)
                }
            }
            board = nextBoard
            case = tmpCase
        }
    }

    fun getNextBoard(): Array<BooleanArray>{
        var newBoard = Array(8){BooleanArray(8){true} }
        // 마지막줄은 볼 필요 없음
        for(r in 0..6){
            for(c in 0..7){
                if(!board[r][c]) newBoard[r+1][c] = false
            }
        }

        return newBoard
    }
}

fun main(){
    bj16954().solve()
}