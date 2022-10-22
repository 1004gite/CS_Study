import java.util.Arrays
import java.util.LinkedList
import java.util.Queue
import kotlin.math.*

class Solution {

    val moveR = arrayOf(1,-1,0,0)
    val moveC = arrayOf(0,0,-1,1)

    fun solution(game_board: Array<IntArray>, table: Array<IntArray>): Int {
        // 블럭들은 1인곳이 블럭이 있는 곳으로 표시된다.
        val boardBlocks = getBlocks(game_board, 1)
        val tableBlocks = getBlocks(table, 0)

        val used = BooleanArray(tableBlocks.size)
        var answer: Int = 0
        for( bIndex in boardBlocks.indices){
            for( tIndex in tableBlocks.indices){
                if(used[tIndex]) continue
                if(checkAllRotation(boardBlocks[bIndex],tableBlocks[tIndex])){
                    tableBlocks[tIndex].forEach { answer += it.count { it == 1 } }
                    used[tIndex] = true
                    break
                }
            }
        }

        return answer
    }

    fun checkAllRotation(a: Array<IntArray>,b: Array<IntArray>): Boolean{
        // a를 4방향으로 돌려가며 비교한다.
        var block = a
        if(check2Arr(block,b)) return true
        repeat(3){
            // 비교
            block = rotatedBlock(block)
            if(check2Arr(block,b)) return true
        }
        return false
    }

    fun check2Arr(a: Array<IntArray>,b: Array<IntArray>): Boolean{
        // 두 배열이 같은지 비교
        if(a.size != b.size || a[0].size != b[0].size) return false
        for( i in a.indices)
            for(j in a[0].indices) if(a[i][j] != b[i][j]) return false

        return true
    }

    fun rotatedBlock(target: Array<IntArray>): Array<IntArray>{
        // 90도 회전시켜줌
        val tmpRow = target.size-1
        return Array(target[0].size){r -> IntArray(target.size){c -> target[tmpRow-c][r]} }
    }

    // check이면 방문하지 않는다.
    fun getBlocks(target: Array<IntArray>, check: Int): Array<Array<IntArray>>{
        var result = mutableListOf<Array<IntArray>>()

        for(r in target.indices){
            for(c in target[0].indices){
                if(target[r][c] == check) continue
                result.add(makeBlock(target,r to c, check))
            }
        }
        return result.toTypedArray()
    }

    fun makeBlock(target: Array<IntArray>,start: Pair<Int,Int>, check: Int): Array<IntArray>{
        // bfs를 돌며 시작점, 끝점을 받아 블럭을 만들어 준다.
        val tmpArr = Array(target.size){target[it].copyOf()}
        var rMax = start.first
        var rMin = start.first
        var cMax = start.second
        var cMin = start.second

        val queue: Queue<Pair<Int,Int>> = LinkedList()
        queue.add(start)
        while(true){
            if(queue.isEmpty()) break

            val now = queue.remove()
            if(tmpArr[now.first][now.second] == check) continue
            tmpArr[now.first][now.second] = check
            rMax = max(rMax, now.first)
            rMin = min(rMin, now.first)
            cMax = max(cMax, now.second)
            cMin = min(cMin, now.second)

            for(i in 0..3){
                val next = now.first+moveR[i] to now.second+moveC[i]
                if(next.first < 0 || next.first >= tmpArr.size || next.second < 0 || next.second >= tmpArr[0].size) continue
                if(tmpArr[next.first][next.second] == check) continue
                queue.add(next)
            }
        }
        // 좌표를 구한 상태임
        // 구한 좌표로 실제 배열에 체크하며 진행
        val result = Array(rMax-rMin+1){IntArray(cMax-cMin+1)}
        queue.add(start)
        while(true){
            if(queue.isEmpty()) break

            val now = queue.remove()
            if(target[now.first][now.second] == check) continue
            target[now.first][now.second] = check
            result[now.first-rMin][now.second-cMin] = 1

            for(i in 0..3){
                val next = now.first+moveR[i] to now.second+moveC[i]
                if(next.first < 0 || next.first >= tmpArr.size || next.second < 0 || next.second >= tmpArr[0].size) continue
                if(target[next.first][next.second] == check) continue
                queue.add(next)
            }
        }

        return result
    }
}