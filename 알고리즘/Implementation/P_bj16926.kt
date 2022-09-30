import java.util.StringTokenizer
import kotlin.math.*

/*
* n행 m열의 배열이 주어지고 r번 반시계로 회전시켜야 함
* */

class bj16926 {

    // 하 우 상 좌
    val move = arrayOf(1 to 0, 0 to 1, -1 to 0, 0 to -1)
    lateinit var board: Array<IntArray>
    var rotation = 0

    fun solve(){
        val br = System.`in`.bufferedReader()
        var st = StringTokenizer(br.readLine())
        val row = st.nextToken().toInt()
        val col = st.nextToken().toInt()
        rotation = st.nextToken().toInt()
        board = Array(row){ with(StringTokenizer(br.readLine())){ IntArray(col){ this.nextToken().toInt() } } }
        val depth = min(row,col)/2
        // 직렬화시킴
        // depth는 r,c중 작은것에 의해 결정됨
        for(d in 0 until depth){
            travel(d to d, row-1-d*2, col-1-d*2)
        }

        val bw = System.out.bufferedWriter()
        for(arr in board){
            for(i in arr) bw.write("$i ")
            bw.write("\n")
        }
        bw.flush()
    }

    fun travel(start: Pair<Int,Int>, colCount: Int, rowCount: Int){
        val rota = rotation % (colCount*2+rowCount*2)
        if(rota == 0) return
        var p = start
        var moveIndex = 0
        val flatArr = mutableListOf<Int>()
        repeat(2){
            repeat(colCount){
                flatArr.add(board[p.first][p.second])
                p = p.first+move[moveIndex].first to p.second+move[moveIndex].second
            }
            moveIndex++
            repeat(rowCount){
                flatArr.add(board[p.first][p.second])
                p = p.first+move[moveIndex].first to p.second+move[moveIndex].second
            }
            moveIndex++
        }

        // 돌면서 순서를 민 후 저장
        var index = (flatArr.size-rota)% flatArr.size
        p = start
        moveIndex = 0
        repeat(2){
            repeat(colCount){
                board[p.first][p.second] = flatArr[index]
                index = (index+1)%flatArr.size
                p = p.first+move[moveIndex].first to p.second+move[moveIndex].second
            }
            moveIndex++
            repeat(rowCount){
                board[p.first][p.second] = flatArr[index]
                index = (index+1)%flatArr.size
                p = p.first+move[moveIndex].first to p.second+move[moveIndex].second
            }
            moveIndex++
        }
    }
}

fun main(){
    bj16926().solve()
}