import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

/**
 *
 * 못풀었다.. 다음에 도전하려 한다.
 *
 * 좌표평면에 n개의 원이 있고 모든 원은 교점이 존재하지 않는다.( 교점이 없게 원 내부에 존재할 수는 있다.)
 * 임의의 두 원을 골라 내부에서 내부로 이동해야 한다. (좌표평면도 중심이 0,0이고 반지름이 무한한 원으로 친다)
 * 이때 같은 원은 한번만 통과하게 해야 한다.
 * 이 조건에서 가장 많은 원을 통과하게 할때 통과하는 원의 수를 구하여라
 *
 * 풀이 1
 * 1. 문제에서는 이동경로가 직선이어야 한다는 말이 없지만 테스트 케이스로 미뤄보아 직선 경로여야 하는 것 같다...
 * 2. 어떤 원에 포함되는 원을 깊게 위치한다고 할 때 가장 깊게 위치한 원을 선택하는 것이 유리하다.
 *      하지만 깊이가 깊은 두 원을 선택했을 때 같은 원들에 포함된다면 손해를 볼 수 있다.
 * 3. 두 원중 1개는 가장 깊이가 깊은 원을 고르는 것이 무조건 유리하다.
 * 4. 가장 깊이가 깊은 원을 출발 node로 잡고 모든 node에 대해 완전탐색을 한다.
 * 5. 모든 원에 대해 자신이 속한 원의 정보를 저장한다.
 *      -> 이동할때 거치는 원의 개수는 각 원이 속한 원의 개수들 중 겹치지 않는 원의 개수이다.
 * */
class bj22946 {

    data class Info(var x:Int, var y:Int, var r: Int)
    var n = 0
    var circles = Array(5000){Info(-1,-1,-1)}
    var include = Array(5000){ mutableListOf<Int>() }

    fun solve(){
        val br = BufferedReader(InputStreamReader(System.`in`))

        n = br.readLine().toInt()
        circles[0].x = 0
        circles[0].y = 0
        circles[0].r = 2000000 // 모든 원은 +=1001000을 넘지 않기 때문에 충분히 큰 크기로 0번째 원을 정의한다.
        for(i in 1..n){
            val st = StringTokenizer(br.readLine())
            circles[i].x = st.nextToken().toInt()
            circles[i].y = st.nextToken().toInt()
            circles[i].r = st.nextToken().toInt()
        }

        for(i in 1..n){
            val now = circles[i]
            // 지금 원의 반지름이 더 작아야 더 안에 있는 원일 가능성이 있다.
            // 중심 사이의 거리가 (큰 원의 반지름-작은 원의 반지름)보다 작으면 큰 원이 작은 원을 포함한다.
            // 중심 사이의 거리의 최대값은 2000000이다.
            for(j in 0 until i){
                if(now.r <= circles[j].r) continue

            }
            for(j in i+1..n){

            }
        }
    }
}

fun main(){
    bj22946().solve()
}