import java.util.StringTokenizer

class bj2437 {
}

/**
 * 무게가 양의 정수인 저울추 n개가 주어진다.
 * 같은 무게가 여러개 있을 수 있다.
 * 이 저울추들로 만들 수 없는 무게의 최솟값을 구하라.
 *
 * 풀이 1
 * 1. 오름차순으로 정렬한다.
 * 2. 각 무게는 만들 수 있다.
 * 3. 어떤 무게를 만들 수 있는 경우의 수는 여러가지가 될 수 있다
 * 4. 가장 작은 수부터 이용하여 작은 숫자부터 만들어가기 시작한다.
 *      연속적으로 수를 만들 수 있다면 다음 수 a를 사용하면 연속수 +a까지 만들 수 있다.
 *      이때, 1~a-1까지 만들어지지 않았으면 중간에 빈 칸이 무조건 생기게 된다.
 *
 * */

fun main(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    val st = StringTokenizer(br.readLine())
    val arr = IntArray(n){ st.nextToken().toInt() }
    arr.sort()

    if(arr[0] > 1){
        println(1)
        return
    }
    var high = arr[0]
    for(i in 1 until arr.size){
        if(high < arr[i]-1){
            println(high+1)
            return
        }
        high += arr[i]
    }

    println(high+1)
}