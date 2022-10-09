import java.util.*

class Solution {
    var check = false
    lateinit var result: Array<String>
    fun solution(tickets: Array<Array<String>>): Array<String> {

        tickets.sortWith{ a,b ->
            if( a[0] == "ICN" ) -1
            else if(b[0] == "ICN") 1
            else a[1].compareTo(b[1])
        }

        var answer: Stack<String> = Stack()
        for( i in tickets.indices){
            if(tickets[i][0] != "ICN") break
            if(check) break
            var valid = BooleanArray(tickets.size)
            valid[i] = true
            answer.add(tickets[i][0])
            answer.add(tickets[i][1])
            dfs(tickets, answer, valid, 1)
            valid[i] = false
            answer.pop()
            answer.pop()
        }

        return result
        // return Array<String>(1){""}
    }

    fun dfs(tickets: Array<Array<String>>, answer :Stack<String>, valid: BooleanArray, used: Int){

        if(check) return
        // used가 티켓의 사이즈만큼이면 검사할 필요가 없다.
        if(used == tickets.size){
            result = answer.toTypedArray()
            check = true
            return
        }

        // 갈 수 있는 곳을 간다.
        for(i in tickets.indices){
            if(valid[i]) continue
            if( answer.last() != tickets[i][0] ) continue
            valid[i] = true
            answer.add(tickets[i][1])
            dfs(tickets, answer, valid, used+1)
            valid[i] = false
            answer.pop()
        }
    }
}