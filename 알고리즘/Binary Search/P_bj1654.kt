import java.util.*

class bj1654 {
}

/**
 * k개의 길이가 랜덤인 랜선을 가지고 있다.
 * 동일한 길이의 랜선 n개가 필요하기 때문에 k개의 랜선을 자를 것이다.
 * 랜선을 n개 이상으로 만들면서 길이가 가장 길게 할 때 랜선의 길이는 몇인가?
 *
 * 1. 길이의 범위가 매우 크기 때문에 모든 길이에 대해 측정하는 것은 불가능하다.
 * 2. 이분탐색을 이용해 길이를 찾는다.
 * */

fun main(){
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())

    val k = st.nextToken().toInt()
    val n = st.nextToken().toLong()

    val lines = IntArray(k){br.readLine().toInt()}

    var low = 1L
    var high = (lines.maxOrNull()!!).toLong()
    var mid: Long
    while(high >= low){
        mid = (low+high)/2
        val count = getCount(lines, mid)

        // 개수가 부족하면 길이를 줄인다.
        if(count < n) high = mid-1
        // 개수가 같으면 최대값을 업데이트하고 길이를 늘려본다.
        else if(count == n){
            low = mid+1
        }
        // 개수가 더 많으면 길이를 늘린다. 개수가 완전히 같지 않을 수 있다..
        else {
            low = mid+1
        }
    }
    // 개수가 부족하면 길이를 줄인다. 즉, high가 low보다 작거나 같아지는 순간은 개수가 같거나 많아지는 최초의 순간이다.
    println(high)

}

fun getCount(lines: IntArray, len: Long): Long{
    return lines.sumOf { it / len }
}