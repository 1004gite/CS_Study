/*
* 연속된 일정을 같은 직사각형 안에 포함시켜야 한다.
*
* */

import java.util.*
import kotlin.math.*

class bj20207 {

    fun solve(){
        val br = System.`in`.bufferedReader()
        val n = br.readLine().toInt()
        val list = Array(n){IntArray(2)}
        val info = IntArray(366)
        repeat(n){
            with(StringTokenizer(br.readLine())){
                val s = this.nextToken().toInt()
                val e = this.nextToken().toInt()
                list[it][0] = s
                list[it][1] = e
                for(i in s..e) info[i]++
            }
        }
        list.sortWith{ a,b ->
            if(a[0] == b[0]) b[1]-a[1]
            else a[0]-b[0]
        }

        val getSum = { start: Int,end: Int ->
            info.copyOfRange(start,end+1).maxOrNull()!!*(end-start+1)
        }

        var result = 0
        var end = -1
        var start = 0
        for(arr in list){
            if(arr[0] <= end+1){
                // 연속됨
                end = max(end, arr[1])
            }
            else{
//                if(end-start > 0)
                if(end != -1)
                    result += getSum(start,end)
                // 새로 시작
                start = arr[0]
                end = arr[1]
            }
        }
//        if(end-start > 0)
            result += getSum(start,end)
        println(result)
    }
}

fun main(){
    bj20207().solve()
}