class Solution {
    fun solution(board: Array<IntArray>, skill: Array<IntArray>): Int {

        val preFix = Array(board.size){ IntArray(board[0].size) }
        skill.forEach{ prefix(preFix, it) }
        update(board, preFix)

        preFix.forEach {
            it.forEach { print("$it, ") }
            println()
        }
        board.forEach {
            it.forEach { print("$it, ") }
            println()
        }

        var answer: Int = 0
        board.forEach{ answer += it.count{ it > 0} }
        return answer
    }

    fun update(board: Array<IntArray>,preFix: Array<IntArray>){
        // 오른쪽으로 업데이트
        for(r in 0 until preFix.size){
            var sum = 0
            for(c in 0 until preFix[0].size){
                sum += preFix[r][c]
                preFix[r][c] = sum
            }
        }
        // 아래로 업데이트
        for(c in 0 until preFix[0].size){
            var sum = 0
            for(r in 0 until preFix.size){
                sum += preFix[r][c]
                preFix[r][c] = sum
            }
        }

        // 적용
        for(r in board.indices){
            for(c in board.indices){
                board[r][c] += preFix[r][c]
            }
        }

    }

    fun prefix(preFix: Array<IntArray>, skill: IntArray){
        val a = if(skill[0] == 1) -1*skill[5] else skill[5]
        val b = a*-1
        preFix[skill[1]][skill[2]] += a
        if(checkRange(skill[1],skill[4]+1,preFix)) preFix[skill[1]][skill[4]+1] += b
        if(checkRange(skill[3]+1, skill[2],preFix)) preFix[skill[3]+1][skill[2]] += b
        if(checkRange(skill[3]+1, skill[4]+1,preFix)) preFix[skill[3]+1][skill[4]+1] += a
    }

    fun checkRange(r: Int, c: Int, arr: Array<IntArray>): Boolean{
        return !(r < 0 || r >= arr.size || c < 0 || c >= arr[0].size)
    }
}
