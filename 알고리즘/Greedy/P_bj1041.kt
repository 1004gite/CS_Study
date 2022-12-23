import java.util.StringTokenizer

/**
 * 특정 6개의 수가 쓰여져있는 주사위 n^3개가 주어진다.
 * 이 주사위들로 정육면체를 만들어 땅에 놓을 때 보이는 5면의 수의 합이 최소가 되게 하라
 *
 * 풀이 1
 * 1. 모서리, 꼭짓점과 연관된 부분을 제외한 부분들은 가장 작은 수만 보이게 놓을 수 있다.
 * 2. 꼭짓점이 아닌 모서리의 경우 {ea, ab, bf, fe, ...} 등의 조합이 강제당한다.
 *      이때, 바닥에 있는 꼭짓점은 두 면만 보이기 때문에 모서리로 취급한다.
 * 3. 꼭짓점의 경우 3 면이 강제당한다.
 * 4. 보이는 전체 부분의 개수는 n^2*5 이다.
 *      그중 꼭짓점에 관련된 면은 3*4 이다.
 *      모서리에만 관련된 면은 2*(n-1)*4 + 2*(n-2)*4 이다.
 * */

class bj1041 {
}

//fun getMinTwo(arr: LongArray): Long{
//    // 마주보는 면을 제외하고 한가지 선택 가능
//    var min = arr[0]+arr[1]
//    val exception = arrayOf(5,4,3,2,1,0)
//    for(i in 0 until 6){
//        for(j in 0 until 6){
//            if(j == i || j == exception[i]) continue
//            min = minOf(min, arr[i]+arr[j])
//        }
//    }
//    return min
//}
//
//fun getMinThree(arr: LongArray): Long{
//    // 마주보는 면을 제외하고 마주보는 관계가 아닌 두 면을 선택
//    val exception = arrayOf(5,4,3,2,1,0)
//    var min = arr[0]+arr[1]+arr[2]
//    for(i in 0 until 6){
//        for(j in 0 until 6){
//            if(i == j || exception[i] == j) continue
//            for(z in 0 until 6){
//                if(z == i || z == exception[i] || z == j || z == exception[j]) continue
//                min = minOf(min,arr[i]+arr[j]+arr[z])
//            }
//        }
//    }
//    return min
//}

fun main(){

    val n = readln().toLong()
    val st = StringTokenizer(readln())

    val dice = LongArray(6){ st.nextToken().toLong() }
    if(n == 1L){
        var max = dice[0]
        var sum = 0L
        for(i in 0 until 6){
            max = maxOf(max, dice[i])
            sum += dice[i]
        }
        println(sum - max)
        return
    }
//    보이는 전체 부분의 개수는 n^2*5 이다.
//    그중 꼭짓점에 관련된 면은 3*4 이다.
//    모서리에만 관련된 면은 2*(n-1)*4 + 2*(n-2)*4 이다.
    val three = 4L
    val two = (n-1L)*4L + (n-2L)*4L
    val one = (n-2L)*(n-1L)*4L + (n-2L)*(n-2L)

    val mins = LongArray(3)
    mins[0] = minOf(dice[0],dice[5])
    mins[1] = minOf(dice[1],dice[4])
    mins[2] = minOf(dice[2],dice[3])
    mins.sort()

    println(three*mins.sum() + two*(mins[0]+mins[1]) + one*mins[0])

//    println( three*getMinThree(dice) + two*getMinTwo(dice) + one*dice.minOrNull()!! )
}