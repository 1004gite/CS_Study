class Solution {

    var now = 0
    var answer = 0

    fun solution(numbers: IntArray, target: Int): Int {
        dfs(0, numbers, target)
        return answer
    }

    fun dfs(index: Int, numbers: IntArray, target: Int){
        if(index == numbers.size){
            if(now == target) answer++;
            return
        }

        now += numbers[index]
        dfs(index+1, numbers, target)
        now -= numbers[index]

        now -= numbers[index]
        dfs(index+1, numbers, target)
        now += numbers[index]
    }
}