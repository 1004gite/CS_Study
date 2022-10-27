import java.util.StringTokenizer

class bj4344 {

    fun solve(){
        val br = System.`in`.bufferedReader()
        var t = br.readLine().toInt()

        while(t-- > 0) {
            val st = StringTokenizer(br.readLine())
            val n = st.nextToken().toInt()

            // 점수의 총합은 Int를 넘지 않음
            val arr = IntArray(n) { st.nextToken().toInt() }
            val sum = arr.sum()
            val count = arr.count { it * n > sum }
            val ratio = count / n.toFloat() * 100
            println(String.format("%.3f", ratio) + "%")
        }
    }

}

fun main(){
    bj4344().solve()
}