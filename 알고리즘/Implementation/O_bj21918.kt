import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

/**
 * 1은 전구가 켜져있음을 뜻하고 0은 꺼져있음을 뜻한다.
 *
 * */
class bj21918 {

    var n = 0
    var m = 0
    val state = BooleanArray(4001)

    fun solve(){
        val br = BufferedReader(InputStreamReader(System.`in`))
        var st = StringTokenizer(br.readLine())
        n = st.nextToken().toInt()
        m = st.nextToken().toInt()
        st = StringTokenizer(br.readLine())
        for(i in 1..n){
            if(st.nextToken() == "1") state[i] = true
        }

        repeat(m){
            st = StringTokenizer(br.readLine())
            val j = st.nextToken().toInt()
            val a = st.nextToken().toInt()
            val b = st.nextToken().toInt()

            when(j){
                1 -> job1(a,b)
                2 -> job2(a,b)
                3 -> job3(a,b)
                else -> job4(a,b)
            }
        }

        val bw = BufferedWriter(OutputStreamWriter(System.out))
        for(i in 1..n){
            if(state[i]) bw.write("1 ")
            else bw.write("0 ")
        }
        bw.flush()
    }

    fun job1(a:Int, b:Int){
        state[a] = (b == 1)
    }
    fun job2(a:Int, b:Int){
        for(i in a..b) state[i] = !state[i]
    }
    fun job3(a:Int, b:Int){
        for(i in a..b) state[i] = false
    }
    fun job4(a:Int, b:Int){
        for(i in a..b) state[i] = true
    }


}

fun main(){
    bj21918().solve()
}