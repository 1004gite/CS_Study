/*
* 술래는 점 n에 있고 숨는 사람은 점 k에 있다.(0~100000)
* time 1으로 x에서 1칸을 이동하거나 x*2 로 이동할 수 있다.
* 술래가 찾을 수 있는 가장 빠른 시간을 구하자
*
* 풀이 1 -> 틀렸습니다.
* 1. *2를 사용하면 금방 가까워지지만 최적해가 아닐 수 있다.
* 2. 어떤 최적해가 있을 때 그 경로에 있는 해들은 각각의 최적해이다.
*   -> dp or dijkstra
*
* 풀이 2 -> 시간초과
* 1. 뒤로 갔다가 *2 를 하는 경우가 최적해일 수 있다.
* 2. 여전히 중간 경로는 최적해를 보장하지만 순서대로 업데이트되지 않기 때문에 dijkstra를 사용한다.
*
* 풀이 3 -> 틀렸습니다.
* 1. 순간이동의 경우 0초라는 특징이 있다.
* 2. 잡히는 사람의 위치를 2로 나누어 떨어질 때까지 계속 나누어도 무관하다.
*   이때 a,b의 앞뒤관계가 바뀌어서는 안된다.
*
* 풀이 4
* 1. 풀이 3에서 잡히는 사람의 위치를 바꾸게 되면 *2 연산에 의해 결과가 달라질 수 있다.
*   저렇게 하려면 잡는 사람의 위치를 바꿔야 한다.
* 2. 시간초과가 자꾸 뜨기 때문에 priority queue를 사용해본다.
* */

import java.util.*

class bj13549 {
    val MAX = 100001
    var dijk = IntArray(MAX) { MAX }
    var visited = BooleanArray(MAX)

    var a = 0
    var b = 0

    fun solve() {
        val st = StringTokenizer(readLine())
        a = st.nextToken().toInt() // 술래
        b = st.nextToken().toInt()

        if (a > b) {
            // 술래가 더 뒤에 있으면 -1씩 가는 방법뿐이다.
            print("${a - b}\n")
            return
        }

        dijk[a] = 0
        // comparator에서 양수가 return 되면 순위가 밀린다.
        // 자리를 바꿀지 말지에 대한 응답으로 생각하면 좋을듯
        var pq: PriorityQueue<Int> = PriorityQueue { a, b ->
//            dijk[a] - dijk[b] ==> a가 더 크면 b와 자리를 바꾸게 됨
            when{
                dijk[a] > dijk[b] -> 1
                else -> -1
            }
        }
        pq.add(a)
        while (true) {
            if (pq.isEmpty()) break
            val index = pq.element()
            pq.remove()
            if (visited[index]) continue
            visited[index] = true
            // 한칸 업데이트
            val minus = index - 1
            val plus = index + 1
            if (minus >= 0 && dijk[minus] > dijk[index] + 1) {
                dijk[minus] = dijk[index] + 1
                pq.add(minus)
            }
            if (plus < MAX && dijk[plus] > dijk[index] + 1) {
                dijk[plus] = dijk[index] + 1
                pq.add(plus)
            }
            // 2배수들 업데이트
            var tmp = index
            if (tmp != 0) {
                while (true) {
                    tmp *= 2
                    if (tmp >= MAX) break
                    if (dijk[tmp] > dijk[index]) {
                        dijk[tmp] = dijk[index]
                        pq.add(tmp)
                    }
                }
            }
        }

//        for (i in 0..30) {
//            print("index: $i, val: ${dijk[i]}\n")
//        }
        print("${dijk[b]}\n")
    }
}

fun main() {
    bj13549().solve()
}