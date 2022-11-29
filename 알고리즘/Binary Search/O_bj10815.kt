import java.util.*

class bj10815 {
}

/**
 * 숫자가 적혀있는 카드 n개가 있다.
 * m개의 정수를 줄 때 숫자카드에 있는 숫자인지 판단해라
 *
 * 1. 숫자 카드는 최대 50만개이다. 숫자의 범위는 -천만~천만이다. 또한 중복은 없다.
 *
 * */

fun main(){
    val br = System.`in`.bufferedReader()

    val list = IntArray(20000000)
    val n = br.readLine().toInt()
    with(StringTokenizer(br.readLine())){
        repeat(n){ list[this.nextToken().toInt()+10000000]++ }
    }

    val m = br.readLine().toInt()
    val sb = StringBuilder()
    with(StringTokenizer(br.readLine())){
        repeat(m){ sb.append(list[this.nextToken().toInt()+10000000]).append(' ') }
    }
    print(sb)


}

