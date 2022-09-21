/**
 * 연속적인 자연수 번호가 붙어있는 스위치가 있다.
 * 1은 켜짐, 0은 꺼짐을 의미한다.
 * 남: 스위치 번호가 자신의 번호의 배수이면 그 스위치의 상태를 바꾼다.
 * 여: 받은 수로부터 대칭되는 번호의 수가 같은지 비교한다. 다를때까지 바꾼다.
 * 출력은 20개 단위로 한다.
 * */

import java.io.*
import java.util.*

class bj1244 {

    val switch = BooleanArray(101)
    var n = 0

    fun solve(){
        val br = BufferedReader(InputStreamReader(System.`in`))
        n = br.readLine().toInt()
        var st = StringTokenizer(br.readLine())
        for(i in 1..n){
            val now = st.nextToken()
            if(now == "1") switch[i] = true
        }

        val t = br.readLine().toInt()
        repeat(t){
            st = StringTokenizer(br.readLine())
            val isMale = if(st.nextToken() == "1") true else false
            val num = st.nextToken().toInt()
            if(isMale){
                // 남학생
                var index = num
               while(true){
                   if(index > n) break
                   switch[index] = !switch[index]
                   index += num
               }
            }
            else{
                // 여학생
                switch[num] = !switch[num]
                var count = 1
                while(true){
                    val a = num+count
                    val b = num-count
                    if(a > n) break
                    if(b < 1) break
                    if(switch[a] != switch[b]) break

                    switch[a] = !switch[a]
                    switch[b] = !switch[b]
                    count++
                }
            }
        }
        val bw = BufferedWriter(OutputStreamWriter(System.out))
        var index = 1
        while(true){
            if(index > n) break
            bw.write( if(switch[index]) "1" else "0" )
            bw.write(if((index % 20 == 0))  "\n" else " ")
            index++
        }
        bw.flush()
    }
}

fun main(){
    bj1244().solve()
}