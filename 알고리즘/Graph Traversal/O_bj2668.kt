/**
 * N칸짜리 배열이 2줄 있다.
 * 1번째 줄에서 수들을 뽑아 집합을 만든다.
 * 이때 대응하는 아래줄 집합도 같아야 한다.
 * 최대한 많이 뽑는 것이 목표이다.
 *
 * 풀이 1
 * 1. 위아래가 서로 교차되는 index는 무조건 뽑는다.
 * 2. 어떤 수 x부터 시작하여 다음 수는 x에 대항하는 index의 2번째 줄로 결정한다.
 * 3. 중간에 순환이 발생한디면 해당 수부터 시작하는 순환은 뽑을 수 있다.
 * */

import java.io.*


class bj2668 {

    fun solve(){
        val br = BufferedReader(InputStreamReader(System.`in`))
        val n = br.readLine().toInt()

        var arr = IntArray(n+1)
        for(i in 1 until n+1){
            arr[i] = br.readLine().toInt()
        }

        var circle = mutableListOf<Int>()
        for( start in 1 until n+1){
            var visited = BooleanArray(n+1)

            var index = start
            while(true){
                visited[index] = true
                val next = arr[index]
                if(visited[next]){
                    // 순환 발생
                    circle.add(next)
//                    println("$next is added")
                    break
                }
                index = next
            }
        }

        var result = mutableListOf<Int>()
        var visited = BooleanArray(n+1)
        for(c in circle){
            var index = c
            while(true){
                if(visited[index]) break
                visited[index] = true
                result.add(index)
                index = arr[index]
            }
        }

        result.sort()
        val bw = BufferedWriter(OutputStreamWriter(System.out))
        bw.write("${result.size}\n")
        for(r in result){
            bw.write("$r\n")
        }
        bw.flush()
    }
}

fun main(){
    bj2668().solve()
}