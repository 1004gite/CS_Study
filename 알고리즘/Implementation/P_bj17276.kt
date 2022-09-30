import java.util.Arrays
import java.util.StringTokenizer

/**
 * -x 도 == 360-x도 이다.
 * x도 == x도 % 360이다.
 *
 * */

class bj17276 {

    fun solve(){
        val br = System.`in`.bufferedReader()
        var t = br.readLine().toInt()
        val bw = System.out.bufferedWriter()

        while(t > 0){
            t--
            var st = StringTokenizer(br.readLine())
            val n = st.nextToken().toInt()
            val mid = n/2
            var jump = st.nextToken().toInt().let {
                var result = it / 45
                result %= 8
                if(result < 0) result+= 8
                result
            }

            val board = Array(n){ with(StringTokenizer(br.readLine())){ IntArray(n){ this.nextToken().toInt() } } }
            val result = Array(n){ board[it].copyOf() }
            for(d in 1..n/2){
                val positionArr = getIndexArr(mid,d)
                var index= 0
                for(i in jump..7){
                    result[positionArr[i].first][positionArr[i].second] =
                        board[positionArr[index].first][positionArr[index].second]
                    index++
                }
                for(i in 0 until jump){
                    result[positionArr[i].first][positionArr[i].second] =
                        board[positionArr[index].first][positionArr[index].second]
                    index++
                }
            }
            result.forEach{arr ->
                arr.forEach { bw.write("$it ") }
                bw.write("\n")
            }
        }
        bw.flush()
    }

    fun getIndexArr(m: Int, d: Int): Array<Pair<Int,Int>>{
        return Array(8){0 to 0}.apply {
            this[0] = m-d to m-d
            this[1] = m-d to m
            this[2] = m-d to m+d
            this[3] = m to m+d
            this[4] = m+d to m+d
            this[5] = m+d to m
            this[6] = m+d to m-d
            this[7] = m to m-d
        }
    }
}

fun main(){
    bj17276().solve()
}