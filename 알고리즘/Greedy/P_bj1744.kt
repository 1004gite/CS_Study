/**
 * 길이가 n인 수열이 있다.
 * n개의 수 중 2개가 한 쌍으로 되게 묶을 수 있다. 또는 묶지 않아도 된다.
 * 쌍이 된 수는 곱연산 후 합연산, 나머지는 합연산을 할때 최대 합을 구하라
 *
 * 풀이 1
 * 1. 쌍을 만들 때 큰 수끼리 묶는 것이 이득이 크다.
 * 2. 0,1은 묶는 것보다 더하는 것이 이득이다.
 * 3. 음수도 큰것 두개를곱해 짝수로 만들 수 있다.
 * 4. 남는 음수가 있다면 0과 곱하여 없앨 수 있다.
 * */

class bj1744 {



}

fun main(){
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()
    val arr = IntArray(n){ br.readLine().toInt() }
    arr.sort()

    var sum = 0
    var index = 0
    // 음수 구간
    while(true){
        // 2개 이하로 남아있지 않으면 정지
        if(index >= n-1) break
        // 둘다 0 이하가 아니면 정지
        if(arr[index] > 0 || arr[index+1] > 0 ) break
        sum += arr[index]*arr[index+1]
        index += 2
    }
    if(index < n && arr[index] <= 0){
        sum += arr[index]
        index++
    }
    while(true){
        if(index >= n || arr[index] > 1) break
        sum += arr[index]
        index++
    }
    var upIndex = n-1
    while(true){
        if(upIndex-1 < index) break
        sum += arr[upIndex]*arr[upIndex-1]
        upIndex -= 2
    }
    if(upIndex == index) sum += arr[index]

    println(sum)
}