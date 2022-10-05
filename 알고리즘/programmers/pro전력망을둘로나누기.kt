import kotlin.math.*

class Solution {

    lateinit var tree: Array<MutableList<Int>>
    lateinit var visited: BooleanArray
    var count = 0

    fun solution(n: Int, wires: Array<IntArray>): Int {

        tree = Array(n+1){ mutableListOf() }
        visited = BooleanArray(n+1)

        for(arr in wires){
            tree[arr[0]].add(arr[1])
            tree[arr[1]].add(arr[0])
        }

        var answer: Int = 200

        for(arr in wires){
            answer = min(answer, getDiff(arr[0], arr[1]))
        }

        return answer
    }

    fun getDiff(a:Int, b:Int): Int{
        count = 0
        visited[b] = true
        dfs(a)
        val tmp = count
        visited[b] = false

        count = 0
        visited[a] = true
        dfs(b)
        val tmp2 = count
        visited[a] = false

        return abs(tmp - tmp2)
    }

    fun dfs(index: Int){
        count++
        visited[index] = true
        for(n in tree[index]){
            if(visited[n]) continue
            dfs(n)
        }
        visited[index] = false
    }
}