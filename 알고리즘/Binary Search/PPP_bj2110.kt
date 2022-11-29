import java.util.*

class bj2110 {
}

/**
 * 수직선 위에 n개의 집이 서로 다른 좌표에 있다.
 * c개의 공유기를 n개의 집에 설치할 예정이다.
 * 공유기는 한 집에 하나만 설치할 수 있다.
 * 가장 가까운 두 공유기 사이의 거리가 최대한 크게 되게 설치하려 할 때 두 공유기 사이의 거리를 구하라
 *
 * 1. 최선의 경우 (가장 먼 두집의 거리/ 공유기의 개수)가 정답이다.
 * 2. n개의 집 중에서 c개의 집을 뽑아야 한다.
 * 3. n == c라면 모든 집에 공유기를 설치해야 하기 때문에 가장 거리가 가까운 두 집이 정답이 된다.
 *      c == n-1 이라면 가장 거리가 가까운 한군데를 제외할 수 있다.
 *      이때 공통된 부분 외의 방향이 더 짧은 집의 공유기를 제거하는 것이 유리하다.
 *
 * 정답 풀이
 * 1. 위처럼 공유기를 하나씩 설치하거나 모두 설치한 후 하나씩 제거하는 방향으로 가면 조건이 까다로워 진다.
 * 2. 최대 거리를 기준으로 이분탐색을 하자
 * 3. 공유기의 최초 설치는 가장 왼쪽에서 하는 것 맞다.
 *      처음 x개를 건너뛰고 시작했을 때 앞의 공간이 낭비되므로 맨 앞에서 시작하는 케이스는 적어도 손해보지 않는다.
 * */

fun main(){
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    val house = IntArray(n){ br.readLine().toInt() }.apply { sort() }

    var high = house.last()-house.first()
    var low = 1
    while(true){
        // high가 low보다 작아지는 순간은 (low가 커져 개수를 채우지 못하게 되는 최초 순간이다, high가 작아져 딱 k개를 만들 수 있거나 k보다 적게 가능한) 경우이다. 즉, low-1이 답이다.
        if(high < low) break

        val mid = (high+low)/2
        val count = myCount(house, mid)

        if(count < k){
            // mid의 거리이상을 유지하며 설치 가능한 개수가 부족하다 -> 거리를 줄인다.
            high = mid-1
        }
        else if(count == k){
            // mid의 거리에서 딱 k개를 설치할 수 있다. -> 최대 거리를 구하기 때문에 일단 거리를 늘려본다.
            low = mid+1
        }
        else{
            // 거리를 늘린다.
            low = mid+1
        }
    }
    println(low-1)
}

fun myCount(house: IntArray, dis: Int): Int{
    var count = 1
    var pre = 0
    for(i in 1 until house.size){
        if(house[i] - house[pre] >= dis){
            pre = i
            count++
        }
    }
    return count
}