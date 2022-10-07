import kotlin.math.*

class Solution {

    val getMinChange: Char.()->Int = {min(this.code-'A'.code, 'Z'.code-this.code+1)}
    var len = 0

    fun solution(name: String): Int {
        var answer = 0
        len = name.length
        // 문자를 바꾸는 횟수 추가
        name.forEach { answer += it.getMinChange() }
        if(answer == 0) return 0 // 모두 'A'인 경우
        if(!name.toCharArray().contains('A')) return answer+len-1

        var min = Int.MAX_VALUE
        for(right in 0 until len){
            // r만큼 갔다가 온 상황
            // right+1 부터 len-1까지 최소 index만큼 추가로 간다.
            var minLeft = len + right // 갔다가 다시 올 필요가 없는 경우 right만큼 돌아온것도 빠지게 한다.
            for(left in right+1 until len){
                if(name[left] != 'A'){
                    minLeft = left
                    break
                }
            }
            min = min(min, right*2 + (len-minLeft))
        }

        for(left in 1 until len){
            // left만큼 갔다가 다시 0으로 온 상황
            // left-1 downTo 1까지 중 가장 큰 index만큼 가야함
            var maxRight = left*-1
            for(right in len-left-1 downTo 1){
                if(name[right] != 'A'){
                    maxRight = right
                    break
                }
            }
            min = min(min, left*2 + maxRight)
        }

        return answer + min
    }
}