
/**
 * 1칸짜리 정사각형을 변이 맞닿는 조건에서 이어붙인 형태를 폴리오미노 라고 한다.
 * n*m 크기의 보드가 있고 각 칸에 숫자가 있다.
 * 폴리노미오를 놓았을 때 놓아진 부분의 숫자의 합이 최대가 되게 하고 그때의 합을 구하라
 *
 * 풀이 1
 * 1. 폴리오미노는 4번을 상하좌우로 이동하는 그래프 탐색으로 구할 수 있다.
 * 2. 모든 점을 출발점으로 잡게 되면 겹치는 형태가 많을 것이다.
 * 3. 폴리오미노가 될 수 있는 형태는 방향에 따라 각기 다른 형태로 볼 때 2 1 4 2 4 가지이다.
 * 4. 13*4* 500*500 == 1000만번 이하 정도이므로 모든 경우를 탐색할 수 있다.
 * */

import java.util.*

class bj14500 {

    fun solve(){
        val br = System.`in`.bufferedReader()
        var st = StringTokenizer(br.readLine())

        val n = st.nextToken().toInt()
        val m = st.nextToken().toInt()

        val board = Array(n){
            st = StringTokenizer(br.readLine())
            IntArray(m){ st.nextToken().toInt() }
        }

        var max = 0
        with(
            arrayOf(booleanArrayOf(true, true, true, true))
        ){
            max = maxOf(max, getMaxSum(board, this))
            max = maxOf(max, getMaxSum(board, getRotated(this)))
        }

        with(
            arrayOf(booleanArrayOf(true, true),booleanArrayOf(true, true))
        ){
            max = maxOf(max, getMaxSum(board, this))
        }


        with(
            arrayOf(booleanArrayOf(true, false),booleanArrayOf(true, false),booleanArrayOf(true, true))
        ){
            var now = this
            max = maxOf(max, getMaxSum(board, now))
            max = maxOf(max, getMaxSum(board, getMirror(now)))
            repeat(3) {
                now = getRotated(now)
                max = maxOf(max, getMaxSum(board, now))
                max = maxOf(max, getMaxSum(board, getMirror(now)))
            }
        }


        with(
            arrayOf(booleanArrayOf(true, false),booleanArrayOf(true, true),booleanArrayOf(false, true))
        ){
            var now = this
            max = maxOf(max, getMaxSum(board, now))
            max = maxOf(max, getMaxSum(board, getMirror(now)))
            repeat(3) {
                now = getRotated(now)
                max = maxOf(max, getMaxSum(board, now))
                max = maxOf(max, getMaxSum(board, getMirror(now)))
            }
        }


        with(
            arrayOf(booleanArrayOf(true,true, true),booleanArrayOf(false,true,false))
        ){
            var now = this
            max = maxOf(max, getMaxSum(board, now))
            max = maxOf(max, getMaxSum(board, getMirror(now)))
            repeat(3) {
                now = getRotated(now)
                max = maxOf(max, getMaxSum(board, now))
                max = maxOf(max, getMaxSum(board, getMirror(now)))
            }        }

        println(max)
    }

    // 좌우대칭
    // 회전할때마다 좌우대칭 할것이기 때문에 위아래 대칭도 고려됨
    fun getMirror(target: Array<BooleanArray>): Array<BooleanArray>{
        val targetCMax = target[0].size-1
        val result = Array(target.size){ r ->
            BooleanArray(target[0].size){ c ->
                target[r][ targetCMax - c]
            }
        }
        return result
    }

    fun getRotated(target: Array<BooleanArray>): Array<BooleanArray>{
        val targetRMax = target.size-1
        val result = Array(target[0].size){ r ->
            BooleanArray(target.size){ c ->
                // 결과에서 r이 늘어가는 것은 기존 c가 늘어나는 것
                // 결과에서 c가 늘어나는 것은 기존 r이 줄어드는 것
                target[targetRMax-c][r]
            }
        }
        return result
    }

    // 주어진 모양을 board에 모두 대입하면서 최대값 반환
    fun getMaxSum(board: Array<IntArray>, shape: Array<BooleanArray>): Int{
        var max = 0

        for(r in 0..board.size - shape.size){
            for(c in 0..board[0].size-shape[0].size){
                // r,c를 board의 시작점으로
                var now = 0
                for(sR in shape.indices){
                    for(sC in shape[0].indices){
                        if(!shape[sR][sC]) continue
                        now += board[sR+r][sC+c]
                    }
                }

                max = maxOf(max, now)
            }
        }

        return max
    }

}

fun main(){
    bj14500().solve()
}