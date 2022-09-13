import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * 1~30 번이 있다.
 * 입력으로 들어오지 않은 수를 작은 번호부터 한줄에 하나씩 출력한다.
 * */
class bj5597 {
}

fun main(){
    val exist = BooleanArray(31)
    val br = BufferedReader(InputStreamReader(System.`in`))

    repeat(28){
        exist[ br.readLine().toInt() ] = true
    }

    for(i in 1..30){
        if(exist[i]) continue
        println(i)
    }
}