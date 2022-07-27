/*
* 일직선 상의 공간에 N개의 샘터와 k채의 집이 있다. 또는 아무것도 없을 수 있다.
* 집과 가장 가까운 셈터의 거리는 해당 집의 불행도이다. 즉, 샘터가 가까울수록 불행도가 낮다.
* 집들의 불행도의 합이 최소가 되게 하라
*
* 풀이 1 -> 크기가 2억인 배열을 생성할 수 없기 때문에 불가능하다.
* 1. 샘터와 가까울수록 불행도가 낮기 때문에 가까운 곳에 우선 배치한다.
* 2. 샘터의 좌,우에 먼저 배치하는 것이 유리하다.
* 3. 샘터의 좌우가 모두 채워져 있다면 처음으로 채워진 집의 좌우가 유리하다.
* 4. 샘터들을 출발 node로 하여 범위를 1씩 넓히며 진행한다.
*
* 풀이 2
* 1. 풀이 1에서 bfs를 진행하는 대신 배열에 저장하지 않는다.
* 2. 하나의 방향으로만 나가기 때문에 방향 정보를 저장한다.
* 3. 하나의 depth마다 새로 생긴 node들 중 갈 수 없거나 겹치는 곳이 있는지 검사한다.
*   만약 겹친다면 두 node중 하나만 값을 더하고 더이상 진행하지 않는다.
*
* 풀이 3
* 1. 샘터의 위치를 sort한다.
 */

import java.io.*
import java.util.*

class bj18513 {

    // max == 0 이면 무한히 갈 수 있다.
    // 바로 막히는 node는 애초에 추가하지 않는다.
    data class node(var distance: Long, var max: Long)

    fun solve() {

        val br = BufferedReader(InputStreamReader(System.`in`))
        var st = StringTokenizer(br.readLine())
        val n = st.nextToken().toInt()
        val k = st.nextToken().toInt()

        var sam = Array<Long>(n){0}

        st = StringTokenizer(br.readLine())
        for(i in 0 until n){
            sam[i] = st.nextToken().toLong()
        }
        sam.sort()

        var nodes = LinkedList<node>()
        nodes.addLast(node(1,0))
        if(n == 1) nodes.addLast(node(1,0))
        else if(sam[0]+1 < sam[1]) nodes.addLast(node(1,(sam[1]-sam[0])/2))
        for(i in 1 until n-1){
            //왼
            if(sam[i] > sam[i-1]+1){
                var maxDiff = (sam[i]-sam[i-1])/2
                if((sam[i]-sam[i-1])%2 == 0L) maxDiff--
                if(maxDiff != 0L) nodes.addLast(node(1,maxDiff))
            }
            //오
            if(sam[i+1] > sam[i]+1) nodes.addLast(node(1,(sam[i+1]-sam[i])/2))
        }
        if(n >= 2){
            if(sam[n-1] > sam[n-2]+1){
                var maxDiff = (sam[n-1]-sam[n-2])/2
                if((sam[n-1]-sam[n-2])%2 == 0L) maxDiff--
                if(maxDiff != 0L) nodes.addLast(node(1,maxDiff))
            }
            nodes.addLast(node(1,0))
        }

        var house = 0
        var sum = 0L
        while(true){
            if(house == k) break
            var now = nodes.first
            nodes.removeFirst()
            house++
            sum += now.distance

            if(now.max > now.distance || now.max == 0L){
                now.distance++
                nodes.addLast(now)
            }
        }

        println(sum)
    }

}

fun main(){
    bj18513().solve()
}