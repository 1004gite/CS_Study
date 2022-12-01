import java.util.*

class bj11657 {
}

/**
 * n개의 도시 있고 m개의 버스가 있다.
 * 버스는 {시작도시,도착도시,걸리는 시간} 으로 나타낸다.
 * 걸리는 시간은 양수, 음수, 0 이 모두 될 수 있다.
 * 1번 도시에서 출발해 나머지 도시로 가는 가장 빠른 시간을 구하라
 * 음수 순환이 발생하면 -1만 출력한다.
 * 음수 순환이 없고 어떤 도시로 가는 경로가 없다면 해당 도시에만 -1을 출력한다.
 *
 * 1. 음수 간선이 존재하기 때문에 밸만-포드 사용
 * 2.
 * */

fun main(){

    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    val max = Long.MAX_VALUE
    val dp = LongArray(n+1) { max }
    dp[1] = 0L

    // {시작, 끝, 비용}을 담는 트리플
    val edge = Array(m){
        st = StringTokenizer(br.readLine())
        Triple(st.nextToken().toInt(), st.nextToken().toInt(), st.nextToken().toLong())
    }

    // 모든 간선에 대해 업데이트를 n-1번 진행함 (1번 node에서 마지막 node까지 일자 형식일 때가 worst인데 이때 n-1번으로 갈 수 있음)
    repeat(n-1){
        for(e in edge){
            val start = e.first
            if(dp[start] == max) continue
            val end = e.second
            val cost = e.third
            dp[end] = minOf(dp[end], dp[start]+cost )
        }
    }

    for(e in edge){
        val start = e.first
        val end = e.second
        val cost = e.third
        if(dp[start] == max) continue
        if(dp[end] > dp[start]+cost) {
            println(-1)
            return
        }
    }

    val sb = StringBuilder()
    for(i in 2..n) sb.append(if(dp[i] == max) -1 else dp[i]).append('\n')
    print(sb)
}