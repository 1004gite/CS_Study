import java.util.*
class Solution {

    data class info(var now: String, var count: Int)

    fun solution(begin: String, target: String, words: Array<String>): Int {
        if(!words.contains(target)) return 0
        val visited = BooleanArray(words.size).apply{
            if(words.contains(begin)) this[words.indexOf(begin)] = true
        }
        val queue: Queue<info> = LinkedList<info>().apply{add(info(begin,0))};

        while(true){
            if(queue.isEmpty()) break
            val now = queue.poll()

            for(i in words.indices){
                if(visited[i]) continue
                if(checkConvert(now.now, words[i])){
                    if(words[i] == target) return now.count+1
                    queue.add(info(words[i],now.count+1))
                    visited[i] = true
                }
            }
        }

        return 0
    }

    fun checkConvert(now: String, next: String): Boolean{
        var count = 0
        for(i in now.indices){
            if(now[i] != next[i]) count++
            if(count >= 2) return false
        }
        if(count == 0) return false
        return true
    }
}