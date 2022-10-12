/*
* 3의 배수 판정법 -> 모든자리의 수의 합이 3의 배수이다.
* */
class O_bj10610 {

    fun solve(){
        val str = readln()
        val info = IntArray(10)
        var sum = 0
        str.forEach {
            val now = it.code-'0'.code
            info[now]++
            sum += now
            sum %= 3
        }

        if(sum != 0 || info[0] == 0) println(-1)
        else{
            val sb = StringBuilder()
            for(i in 9 downTo 0){
                repeat(info[i]){ sb.append(i) }
            }
            println(sb)
        }
    }
}

fun main(){
    O_bj10610().solve()
}