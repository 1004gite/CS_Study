class Solution {
    fun solution(citations: IntArray): Int {

        citations.sortDescending()
        for(i in citations.indices){
            if(i+1 <= citations[i]) continue
            return i
        }

        return citations.size
    }
}