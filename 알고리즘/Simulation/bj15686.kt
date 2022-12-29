package Simulation

import java.util.Deque
import java.util.LinkedList
import java.util.StringTokenizer
import kotlin.math.*

/**
 * n*n 크기의 판이 있다.
 * 각 칸은 {0빈칸, 1집, 2치킨집} 중 하나이다.
 * 치킨 거리: 집과 가장 가까운 치킨집 사이의 거리 (행렬차의 절대값)
 * 도시의 치킨 거리: 모든 집의 치킨 거리의 합
 *
 * 치킨집을 m개가 되게 줄일 것이다.
 * 이때 도시의 치킨 거리가 최소가 되게 하라.
 *
 * 풀이 1
 * 1. 집을 기준으로 모든 치킨집까지의 거리를 구한다. (플로이드 워샬)
 * 2. 판의 최대크기는 250, 치킨집의 최대 개수는 13으로 크지 않다.
 *
 * 풀이 2 -> 시간 초과
 * 1. 제거했을 때 손해가 가장 적은 치킨집을 없애는 것이 효율적이다.
 * 2. 이때, 선택되는 치킨집끼리 영향을 받을 수 있다.
 * 3. -> 어차피 매번 검사해야 한다면 greedy하게 풀 수 없다.
 * 4. 유클리드 거리만 따지면 되기 떄문에 2차원 배열에 저장할 필요는 없다.
 * 5. 좌표만 기록 후 이용한다.
 *
 * 풀이 3 -> 시간초과 -> 불필요한 경우의 수 백트래킹으로 해결
 * 1. 집과 치킨집 사이의 거리를 최초 1회 구해놓고 계속 사용할 수 있다.
 *
 * 풀이 4
 * 1. 풀이 3으로 통과하긴 함
 * 2. 다른 더 빠른 풀이를 보니 조합을 구하는 부분과 min값 갱신 부분을 나누어 놓았음
 * 3. linkedList에 붙이고 떼는 부분에서 시간이 많이 걸리는 것 같음
 *
 * -> linkedList 방식에서 booleanArray에 표시하는 방식으로 바꿨더니 시간이 거의 1/10으로 줄었다!!
 * */

class bj15686 {

    val chicken = mutableListOf<Pair<Int,Int>>()
    val house = mutableListOf<Pair<Int,Int>>()
    lateinit var distance: Array<IntArray> // 집을 기준으로 모든 치킨집까지의 거리를 저장
    lateinit var selected: BooleanArray
    var m = 0
    var n = 0
    var min = Int.MAX_VALUE

    fun solve(){
        val br = System.`in`.bufferedReader()
        var st = StringTokenizer(br.readLine())

        n = st.nextToken().toInt()
        m = st.nextToken().toInt()

        for(r in 0 until n){
            st = StringTokenizer(br.readLine())
            for(c in 0 until  n){
                when(st.nextToken()){
                    "1" -> { house.add(r to c)}
                    "2" -> { chicken.add(r to c) }
                }
            }
        }
        setDistance()
        selected = BooleanArray(chicken.size)

        // m개를 선택하는 모든 조합에 대해 고려
        // dfs로 m개 선택
        dfs(0,0)

        println(min)
    }

    fun setDistance(){
        distance = Array(house.size){ h ->
            IntArray(chicken.size){ c ->
                abs(house[h].first-chicken[c].first) + abs(house[h].second-chicken[c].second)
            } }
    }

    fun dfs(index: Int, len: Int){
        if(len == m){
            // 거리 측정 후 return
            val tmp = IntArray(house.size){ Int.MAX_VALUE }
            for(i in selected.indices){
                if(selected[i]){
                    for(h in house.indices){
                        tmp[h] = minOf(tmp[h], distance[h][i])
                    }
                }
            }
            min = minOf(min, tmp.sum())
            return
        }

        if( m > len + chicken.size-index) return // 개수를 채우는게 불가능함
        for(i in index until chicken.size){
            selected[i] = true
            dfs(i+1, len+1)
            selected[i] = false
        }
    }

}

fun main(){
    bj15686().solve()
}