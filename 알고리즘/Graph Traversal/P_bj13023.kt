/**
 * 0~n-1 번이 있다.
 * 친구 관계가 있다.
 * 친구 관계가 순환하는 경우가 있는지 구하여라
 *
 * 풀이 1
 * 1. 한명씩 친구관계를 따라간다.
 * 2. 5번의 연결이 발생하는지 확인한다.
 * 3. 친구 괸계는 순서를 고려해야 한다. ex) 1->2 != 2->1
 *
 * 풀이 2
 * 1. 관계가 여러개일 수 있음을 고려하자
 * */

import java.io.*
import java.util.*

class bj13023 {

    data class node(var index: Int, var set: MutableSet<Int>)

    fun solve(){
        val br = BufferedReader(InputStreamReader(System.`in`))
        var st = StringTokenizer(br.readLine())

        val n = st.nextToken().toInt()
        val m = st.nextToken().toInt()

        var relation = Array(n){ mutableListOf<Int>() }
        for(i in 0 until m){
            st = StringTokenizer(br.readLine())
            val a = st.nextToken().toInt()
            val b = st.nextToken().toInt()
            relation[a].add(b)
            relation[b].add(a)
        }

        for(i in 0 until n){
            // 출발점을 i로 하여 dfs
            var stack = LinkedList<node>()
            for(r in relation[i]){
                stack.add(node(r, mutableSetOf()))
            }

            while(true){
                if(stack.isEmpty()) break
                val now = stack.last
                stack.removeLast()

                if(now.set.size == 5){
                    println(1)
                    return
                }

                // 이번 친구를 더한다.
                // 이때 이번 친구가 이미 있으면 순환이 발생하므로 더할 수 없다.
                if(now.set.add(now.index)){
                    for(r in relation[now.index]){
                        stack.add(node(r,now.set.toMutableSet()))
                    }
                }
//                if(stack.isNotEmpty())
//                    println("i = $i, last = ${stack.last.index}, size = ${stack.size}")
            }
        }

        println(0)
    }
}

fun main(){
    bj13023().solve()
}