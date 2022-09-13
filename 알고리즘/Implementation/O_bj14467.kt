import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

/**
 * 소의 위치를 N번 관찰한다 (소의 번호, 위치)
 * 소의 번호는 1~10 이다.
 * 소의 위치는 0 또는 1이다.
 * 소가 최소 몇번 위치를 이동했는지 알아보자
 * */
class bj14467 {

    fun solve(){
        var br = BufferedReader(InputStreamReader(System.`in`))

        val count = IntArray(11)
        val pre = IntArray(11){-1}
        var n = br.readLine().toInt()
        while(n > 0){
            n--
            val st = StringTokenizer(br.readLine())
            val num = st.nextToken().toInt()
            val direction = st.nextToken().toInt()

            if(pre[num] == -1){
                pre[num] = direction
                continue
            }

            if(pre[num] == direction) continue

            pre[num] = direction
            count[num]++
         }

        println(count.sum())
    }
}

fun main(){
    bj14467().solve()
}