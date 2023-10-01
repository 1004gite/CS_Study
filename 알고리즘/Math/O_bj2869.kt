/**
 * 1. v미터를 올라야 한다.
 * 2. A미터를 오를 수 있으나, 정상에 오르지 못하면 B미터를 떨어진다.
 * 3. 총 몇번만에 올라갈 수 있는지 구하라
 * 4. 마지막 날에 a만큼 올라가기 때문에 마지막 전날에는 v-a보다 크거나 같게 올라오면 된다.
 * -> v-a보다 크거나 같게 (a-b)만큼으로 오르는 날 + 1이 정답이다.
 * */

fun main() {
    System.`in`.bufferedReader()
        .readLine()
        .split(' ', limit = 100)
        .map { it.toLong() }
        .let { (a,b,v) ->
            val target = v-a
            val oneDay = a-b
            val result = (target/oneDay).plus(if(target%oneDay == 0L) 1L else 2L)
            print(result)
        }
}