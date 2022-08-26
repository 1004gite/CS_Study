/**
 * 간선의 값이 모두 1인 그래프가 주어진다.
 * a에서 출발해 b를 거쳐 다시 a로 와야 한다.
 * 이때 가는 경로를 먼저 최단거리와 사전순으로 선택하고 그 후 오는 경로도 같은 원리로 선택한다.
 *
 * 풀이 1
 * 1. bfs를 통해 최단경로를 찾는다.
 * 2. 이때 경로를 저장하며 진행하고 중간에 최단경로가 나와도 같은 depth를 모두 마칠때까지 진행한다.
 *      -> 이렇게 하면 모든 경로에 대한 list를 보유해야 하므로 리스크가 크다.
 *      -> 항상 숫자가 낮은것부터 Queue에 넣어서 depth다음으로는 숫자가 작은지를 고려하게 만든다.
 *      -> 근데 이렇게하면 오는 경로를 알 수가 없기 때문에 경로는 유지한다.
 * 3. 출발 node가 정해져 있기 때문에 depth를 따질 수 있다.
 * 4. 이렇게 하면 너무 복잡해지고 1초의 시간제한을 맞출 수 없을 것 같다. -> 역시나 시간초과..
 *
 * 풀이 2
 * 1. 풀이 1에서 간선의 정보를 받고 정렬을 하면 경로를 찾을 때 숫자가 작은 경로부터 우선 탐색할 수 있고 사전순을 검사하는 과정을 줄일 수 있다.
 * */

import java.io.*
import java.util.*

class bj22868 {

    data class info(var depth: Int, var near: MutableList<Int>)
    var n = 0
    var m = 0
    var s = 0
    var e = 0
    var root = Array(10001){info(10001, mutableListOf())}

    fun solve(){

        val br = BufferedReader(InputStreamReader(System.`in`))
        var st = StringTokenizer(br.readLine())
        n = st.nextToken().toInt()
        m = st.nextToken().toInt()

        for(i in 0 until m){
            st = StringTokenizer(br.readLine())
            val x = st.nextToken().toInt()
            val y = st.nextToken().toInt()
            root[x].near.add(y)
            root[y].near.add(x)
        }
        root.forEach { it.near.sort() }
        st = StringTokenizer(br.readLine())
        s = st.nextToken().toInt()
        e = st.nextToken().toInt()

        root[s].depth = 0
        val sToE = getShortPath(s,e)
        // 이미 지나간 경로는 다시 갈 수 없게 depth를 -1로 업데이트
        // s,e의 depth를 업데이트
        root.forEach { it.depth = 10001 }
        for(tmp in sToE){
            root[tmp].depth = -1
        }
        root[e].depth = 0
        val eToS = getShortPath(e,s)

        println(sToE.size+eToS.size)
    }

    fun getShortPath(start: Int, end: Int): MutableList<Int>{
        // s -> e 경로 중 최단경로를 return 한다.
        // 최단경로가 여러개이면 사전순으로 가장 빠른 경로를 return 한다.

        var Q = LinkedList<MutableList<Int>>()
        Q.add(mutableListOf<Int>().apply { add(start) }) // 출발 depth
        while(true){
//            val hasE = LinkedList<MutableList<Int>>()
            val tmpQ = LinkedList<MutableList<Int>>()
            // 이번 차례의 depth들에 대해서 모두 조사
            while(true){
                if(Q.isEmpty()) break
                val now = Q.first
                Q.removeFirst()

                // 갈 수 있는 주변에 대해 모두 간다.
                // now에는 경로들이 저장되어 있으므로 마지막이 현재 node이다.
                for(next in root[now.last()].near){
//                    if(next == end) hasE.add(now.toMutableList().apply { add(next) })
                    if(next == end) return now
                    if(  root[next].depth <= root[now.last()].depth) continue
                    val tmp = now.toMutableList().apply { add(next) }
                    tmpQ.add(tmp)
                }

                // depth 업데이트
                for(tmp in tmpQ){
                    root[tmp.last()].depth = root[now.last()].depth+1
                }
            }

            // 간선의 정보는 sort되어 있으므로 사전순으로 다시 찾을 필요가 없다.
//            if(hasE.isNotEmpty()){
//                // 사전순 검사 후 result에 넣고 break
//                result = hasE.first
//                hasE.removeFirst()
//                while(true){
//                    if(hasE.isEmpty()) break
//                    val now = hasE.first
//                    hasE.removeFirst()
//
//                    for(i in 0 until result.size){
//                        if(result[i] == now[i]) continue
//                        else if(result[i] > now[i]) {
//                            result = now
//                            break
//                        }
//                        else break
//                    }
//                }
//                break
//            }
            Q = tmpQ
        }

//        return mutableListOf()
    }

}

fun main(){
    bj22868().solve()
}