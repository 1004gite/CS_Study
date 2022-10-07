import java.util.*

class Solution {

    val charList = arrayOf('A','E','I','O','U')
    val sb = StringBuilder()
    var answer = 0
    var w: String = ""
    var check = false

    fun solution(word: String): Int {
        w = word
        dfs()
        return answer
    }

    fun dfs(){
        if(sb.toString() == w) check
        if(check) return
        if(sb.length == 5) return
        for(c in charList){
            if(check) break
            answer++
            sb.append(c)
            dfs()
            sb.deleteCharAt(sb.lastIndex)
        }

    }
}