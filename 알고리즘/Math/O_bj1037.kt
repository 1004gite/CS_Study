import java.util.StringTokenizer

class bj1037 {

    // 문제에 모든 약수가 주어진다고 했다.
    // 최개 * 최소

    fun solve(){
        val br = System.`in`.bufferedReader()
        val n = br.readLine().toInt()

        var max = Int.MIN_VALUE
        var min = Int.MAX_VALUE

        with(StringTokenizer(br.readLine())){
            repeat(n){
                val now = this.nextToken().toInt()
                if( max < now) max = now
                if( min > now) min = now
            }
        }

        println(min*max)
    }
}

fun main(){
    bj1037().solve()
}