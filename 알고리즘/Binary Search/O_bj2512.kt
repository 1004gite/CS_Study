import java.util.StringTokenizer

/**
 * 총 예산과 요청들이 있음
 * 1. 예산 안에서 모든 요청을 처리할 수 있는 경우 모두 처리해준다.
 * 2. 불가능하다면 특정한 정수 상한액을 계산하여 최대 상한액만큼 배정한다.
 * 3. 이때 상한액은 총 지급 금액이 최대가 되도록 내가 잡아야 한다.
 *
 * 요청의 수는 10000개 이하이다.
 * 총 예산은 10억 이하이다.
 *
 * 풀이 1
 * 1. 예산을 넘지 않으면 합을 출력한다.
 * 2. 예산을 넘으면 적절 상한액을 찾는다.
 * 3. 상한액은 1~10만 사이다.
 * 4. 각 요청 비용이 다르기 때문에 매번 상한액에 대해 값을 계산해야 한다.
* */

class bj2512 {

    fun solve(){
        val br = System.`in`.bufferedReader()
        val n = br.readLine().toInt()
        val st = StringTokenizer(br.readLine())
        val list = IntArray(n){ st.nextToken().toInt() }
        val total = br.readLine().toInt()
        br.close()

        if(getSumWithLimit(list,100000) <= total) {
            println(list.maxOrNull())
            return
        }

        var low = 1
        var high = 100000
        fun getMid(): Int = (high+low)/2 + 1 // 언더바운드를 구하기 떄문에 딱 나누어 떨어지지 않으면 더 큰쪽을 고려
        while(low < high){ // low==high이면 low가 언더바운드임
            // low bound를 찾는다.
            // 즉, low는 유효한 값이 유지되도록 한다. -> mid가 가능할 때 low를 mid까지만 올린다.
            val mid = getMid()
            val sum = getSumWithLimit(list, mid)
            if(sum <= total){
                low = mid
            } else {
                high = mid-1
            }
        }

        // 상한액이 필요한 상황이라면 상한액이 최대값이 될 것임
        println(low)
    }

    fun getSumWithLimit(list: IntArray, limit: Int): Int =
        list.sumOf { if(limit < it) limit else it }
}

fun main(){
    bj2512().solve()
}