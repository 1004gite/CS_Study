/**
 * N개의 카드(P)에 수가 적혀있다
 * D1~DN 은 1~N 까지의 수가 순서 상관없이 한개씩 들어있다.
 * Di는 P(Di)의 값을 i번째러 가지고 오는 것을 의미한다.
 *
 * */

import java.util.*

class bj22858 {

    fun solve(){
        val br = System.`in`.bufferedReader()
        var st = StringTokenizer(br.readLine())

        val n = st.nextToken().toInt()
        val k = st.nextToken().toInt()

        var cards = IntArray(n+1)
        val d = IntArray(n+1)

        st = StringTokenizer(br.readLine())
        for(i in 1..n){
            cards[i] = st.nextToken().toInt()
        }

        st = StringTokenizer(br.readLine())
        for(i in 1..n){
            d[i] = st.nextToken().toInt()
        }

        repeat(k){
            val tmp = IntArray(n+1)
            for(i in 1..n){
                tmp[ d[i] ] = cards[i]
            }
            cards = tmp
        }

        val sb = StringBuilder()
        for(i in 1 until n){
            sb.append("${cards[i]} ")
        }
        sb.append("${cards[n]}\n")
        print(sb)
    }
}

fun main(){
    bj22858().solve()
}