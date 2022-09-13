import java.io.*
import java.util.*

/**
 * 5*5의 판에 1~25의 숫자가 있다.
 * 풀이 1
 * 1. row, col, 대각(2) 개의 관점에서 볼때 수를 5개 얻으면 원빙고이다.
 * 2. 각번호에 행렬, 대각 정보를 저장한 후 사회자가 부를 때마다 빙고 후보의 개수를증가시킨다.
 */
class bj2578 {

    // 5행 ,5열, 왼대각, 오른대각
    val count = IntArray(12)
    var numInfo = Array(26){ mutableListOf<Int>() }

    fun solve(){
        val br = BufferedReader(InputStreamReader(System.`in`))

        for(r in 0..4){
            val st = StringTokenizer(br.readLine())
            for(c in 0..4){
                val tmp = st.nextToken().toInt()
                numInfo[tmp].add(r)
                numInfo[tmp].add(c+5)
                if(r == c) numInfo[tmp].add(10)
                if(r == (4-c)) numInfo[tmp].add(11)
            }
        }

        var binggo = 0
        for(r in 0..4){
            val st = StringTokenizer(br.readLine())
            for(c in 0..4){
                val tmp = numInfo[st.nextToken().toInt()]
                for(n in tmp){
                    count[n]++
                    if(count[n] == 5) binggo++
                    if(binggo == 3){
                        println(r*5 + c + 1)
                        return
                    }
                }
            }
        }
    }
}

fun main(){
    bj2578().solve()
}