import kotlin.math.*
import java.util.LinkedList
import java.util.StringTokenizer

class bj14888 {

    lateinit var arr: IntArray
    lateinit var oper: IntArray
    var n = 0
    var max = Int.MIN_VALUE
    var min = Int.MAX_VALUE

    fun solve(){
        val br = System.`in`.bufferedReader()
        n = br.readLine().toInt()
        // 수열 입력
        arr = with(StringTokenizer(br.readLine())){
            IntArray(n){ this.nextToken().toInt()}
        }
        // 초기상태 입력
        oper = with(StringTokenizer(br.readLine())){
            IntArray(4){this.nextToken().toInt()}
        }

        update(arr[0], 1)

        print("$max\n$min\n")
    }

    fun update(num:Int, index: Int){
        if(index == n){
            max = max(max,num)
            min = min(min,num)
            return
        }

        for(i in 0..3){
            if(oper[i] == 0) continue
            oper[i]--
            when(i){
                0 -> update(num+arr[index],index+1)
                1 -> update(num-arr[index], index+1)
                2 -> update(num*arr[index], index+1)
                else -> update(num/arr[index], index+1)
            }
            oper[i]++
        }
    }
}

fun main(){
    bj14888().solve()
}
