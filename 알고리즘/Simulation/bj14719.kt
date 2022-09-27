import java.util.StringTokenizer

class Bj14719 {

    var h = 0
    var w = 0
    var result = 0
    lateinit var arr: IntArray

    fun solve(){
        val br = System.`in`.bufferedReader()
        with(StringTokenizer(br.readLine())){
            h = this.nextToken().toInt()
            w = this.nextToken().toInt()
        }
        arr = with(StringTokenizer(br.readLine())){
            IntArray(w){ this.nextToken().toInt() }
        }

        for(i in 1..h){
            result += getWater(i)
        }

        println(result)
    }

    fun getWater(height: Int): Int{
        // 해당 높이 중 갖힌 공간이 몇갠지
        var total = 0
        var start = 0
        while(true){
            if(start >= w) break
            if(arr[start] >= height) break
            start++
        }
        var tmp = 0
        while(start < w){
            if(arr[start] >= height){
                total += tmp
                tmp = 0
            }
            else tmp++

            start++
        }

        return total
    }
}

fun main(){
    Bj14719().solve()
}