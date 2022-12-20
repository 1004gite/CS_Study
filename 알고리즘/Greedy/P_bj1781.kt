import java.util.PriorityQueue
import java.util.StringTokenizer

/**
 * 문제, 보상, 문제별 데드라인이 주어진다.
 * 문제를 푸는데는 1이 소요된다.
 * 받을 수 있는 최대 컵라면 수를 출력하라
 *
 * 풀이 1
 * 1. 시간이 겹친다면 더 많은 보상이 있는 문제를 선택한다.
 * 2. 보상이 많은 문제부터 선택하는 것이 유리하다.
 * 3. 어떤 문제를 선택할때 가능한 가장 뒤에서부터 하는 판정이 유리하다.
 *
 * 풀이 2
 * 1. 데드라인 오름차순으로 정렬한다.
 * 2. 마지막 데드라인날에는 해당 날에 데드라인인 문제만 풀 수 있다.
 * 3. 즉, 그 날에 있는 문제 중 가장 이득인 것을 푼다.
 * 4. 그 후 그 전날에 풀 수 있는 문제들과 함께 가장 효율적인 문제를 선택한다.
 * 5. pq를 사용한다.
 * */

class bj1781 {
}

fun main(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    // 데드라인 to 보상
    val arr = Array<Pair<Int, Int>>(n){
        val st = StringTokenizer(br.readLine())
        st.nextToken().toInt() to st.nextToken().toInt()
    }

    // 데드라인 기준 내림차순
    arr.sortByDescending { it.first}

    val pq = PriorityQueue<Pair<Int,Int>>(){ o1,o2 ->
        // 보상 기준 maxHeap
        o2.second - o1.second
    }

    var sum = 0
    var date = arr[0].first
    var index = 0
    while(date > 0){
        // date까지 pq에 넣음
        while(true){
            if(index >= arr.size) break
            if(arr[index].first < date) break
            pq.add(arr[index])
            index++
        }

        if(pq.isNotEmpty()){
            sum += pq.remove().second
        }

        date--
    }

    println(sum)
}