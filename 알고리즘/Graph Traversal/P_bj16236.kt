import java.util.*

class Solve{
    /**
    큰 물고기가 있으면 지나갈 수 없음
    같은 크기면 지나갈 수만 있음
    작은 크기면 먹을 수 있음
    상하좌우로 이동 가능하다
    0. 먹을 수 있는 물고기가 없으면 종료한다.
    1. 먹을 수 있는 물고기가 1마리면 그 물고기를 먹으러 간다
    2. 먹을 수 있는 물고기가 여러마리면 가장 가까운 물고기를 먹는다.
    거리가 같은 물고기가 있다면 가장 위, 가장 왼쪽 물고기를 먹는다.
    3. 물고기 자신의 크기만큼 먹으면 크기가 1 증가한다.
    4. 몇초 동안 물고기를 먹으러 다닐 수 있는지 검사한다.
     */
    class Shark(){
        var col = 0
        var row = 0
        var size = 2
        var ate = 0
        fun eat(){
            if(++ate == size){
                size++
                ate = 0
            }
        }
    }

    class Feed{
        var distance = 50000
        var row = -1
        var col = -1
        fun setFeed(d:Int,r:Int,c: Int){
            distance = d
            row = r
            col = c
        }
    }

    data class Point(val row: Int, val col: Int, val distance: Int)

    val moveR = arrayOf(0,0,1,-1)
    val moveC = arrayOf(1,-1,0,0)
    private val shark = Shark()
    var n = 0
    private val board: Array<IntArray> = getBoard()

    fun solve(){
        var time = 0
        while(true){
            val feed = bfs()

            if(feed.row == -1) break
//            println("${feed.row}, ${feed.col}")
            time += feed.distance
            board[shark.row][shark.col] = 0
            board[feed.row][feed.col] = 9
            shark.eat()
            shark.row = feed.row
            shark.col = feed.col
        }

        println(time)
    }

    fun bfs(): Feed{
        val feed = Feed()
        val visited = Array(n){BooleanArray(n)}
        val queue: Queue<Point> = LinkedList<Point>().apply { add(Point(shark.row,shark.col,0)) }

        while(true) {
            if(queue.isEmpty()) break
            val point = queue.remove()
//            println("${point.row},${point.col}")
            val distance = point.distance
            val row = point.row
            val col = point.col

            if(visited[row][col]) continue
            visited[row][col] = true
            if (feed.distance < distance) continue

            val now = board[row][col]
            if (now != 0 && now != 9 && now < shark.size) {
                // 먹을 수 있는 먹이
                if (feed.distance > distance) feed.setFeed(distance, row, col)
                else if (feed.distance == distance) {
                    // 행, 렬 순으로 비교
                    if (feed.row > row) feed.setFeed(distance, row, col)
                    else if (feed.row == row && feed.col > col) feed.setFeed(distance, row, col)
                }
            }

            for (i in 0 until 4) {
                val tmpR = row + moveR[i]
                val tmpC = col + moveC[i]

                if (tmpR < 0 || tmpR >= n || tmpC < 0 || tmpC >= n) continue
                if (visited[tmpR][tmpC]) continue
                if (board[tmpR][tmpC] > shark.size) continue

                queue.add(Point(tmpR,tmpC,distance+1))
            }
        }

        return feed
    }

    fun getBoard(): Array<IntArray>{
        val br = System.`in`.bufferedReader()
        n = br.readLine().toInt()
        val result = Array(n){r ->
            with(StringTokenizer(br.readLine())){
                IntArray(n){c ->
                    val tmp = this.nextToken().toInt()
                    if(tmp == 9){
                        shark.row = r
                        shark.col = c
                    }
                    tmp
                }
            }
        }
        br.close()
        return result
    }
}

fun main(){
    Solve().solve()
}