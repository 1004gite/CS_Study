class Solution {
    fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {

        val info = IntArray(n+1){1}.also {arr ->
            lost.forEach { arr[it]-- }
            reserve.forEach { arr[it]++ }
            arr[0] = 0
        }

        for(i in 1 until info.size){
            if(info[i] < 2) continue
            if(i-1 > 0 && info[i-1] == 0){
                info[i-1]++
                info[i]--
            }else if(i+1 < info.size && info[i+1]==0){
                info[i+1]++
                info[i]--
            }
        }

        var answer = 0

        info.forEach { if(it > 0) answer++ }

        return answer
    }
}