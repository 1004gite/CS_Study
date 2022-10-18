import java.util.*

class Solution {

    fun solutionHeap(n: Int, times: IntArray): Long {
        // 가장 적은 시간을 할당받은 사람에게 다음 사람을 할당해준다.
        var answer = 0L
        // 걸리는 시간 to 누적된 시간
        val pq = PriorityQueue<Pair<Long,Long>> { a,b ->
            // 다음걸 더했을 때 더 적은 원소가 앞으로 온다
            (a.first+a.second).compareTo(b.first+b.second)
        }
        times.forEach { pq.add(it.toLong() to 0L) }

        repeat(n){
            val less = pq.remove()
            val second = less.second+less.first
            pq.add(less.first to second)
            if(answer < second) answer = second
        }

        return answer
    }

    fun solutionBinarySearch(n: Int, times: IntArray): Long {
        var answer = 0L
        // 가장 빨리 처리하는 심사관이 0번에 있음
        times.sort()
        var min = 1L
        var max = times.last().toLong()*n.toLong()

        while( min <= max ){
            // mid만큼 시간이 걸린다고 가정한다.
            val mid = (max+min)/2
            var count = 0L
            // mid의 시간동안 총 count 만큼의 사람을 통과시킬 수 있다.
            times.forEach { count += mid/it.toLong() }

            if(count >= n){
                answer = mid
                max = mid-1
            }
            else{
                min = mid+1
            }
        }

        return answer
    }
}