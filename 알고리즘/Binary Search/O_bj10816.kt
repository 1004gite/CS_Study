import java.util.*

class bj10816 {
}

/**
 * 상근이는 숫자 n개를 가지고 있다.
 * m개의 수가 주어질 때 상근이는 주어진 수에 해당하는 수를 몇개 가지고 있는지 구하
 * 1. 수의 범위는 2천만으로 모든 수에 대해 배열을 만들어 체크하는 방식은 어렵다.
 * 2. 숫자 카드의 개수는 50만으로 hashmap을 사용하는 것이 가능할 것 같다.(int 50만개 == 200만B == 2000KB == 2MB...)
 * */

fun main(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val hashMap = hashMapOf<Int,Int>()
    var st = StringTokenizer(br.readLine())
    repeat(n){
        val now = st.nextToken().toInt()
        hashMap[now] = (hashMap[now] ?: 0) + 1
    }

    val m = br.readLine().toInt()
    val sb = StringBuilder()
    st = StringTokenizer(br.readLine())
    repeat(m){
        sb.append(hashMap[st.nextToken().toInt()] ?: 0).append(' ')
    }

    print(sb)
}