import java.util.*

class bj11054 {
}

/*
* 수열이 있을 때 k번째까지는 커지고 k 이후부터는 작아지는 수열을 바이토닉 수열이라고 한다.
* 수열이 주어졌을 때 바이토닉 수열의 최대 길이를 구하라
* 1. 점점 커지는 부분과 점점 작아지는 부분의 dp를 각각 구한다. (커지는 dp에는 본인이 k일 때 본인 이전까지 점점 커지는 부분의 길이를 저장한다.)
* 2. 부분 수열과 연속부분 수열은 다른 듯 하다. 띄엄띄엄 수열 생성 가능
* */

fun main(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val arr = IntArray(n){ st.nextToken().toInt() }

    // 왼쪽에 점점 커지는 부분의 길이를 저장
    val dp = IntArray(n)
    for(i in 1 until n){
        for(j in i-1 downTo 0){
            if(arr[i] <= arr[j]) continue
            dp[i] = maxOf(dp[i], dp[j]+1)
        }
    }
//    dp.forEach { print("$it, ") }
//    println()

    // 오른쪽부터 점점 작아지는 부분의 길이를 저장
    val dp2 = IntArray(n)
    for(i in n-2 downTo 0){
        for(j in i+1 until n){
            if(arr[i] <= arr[j]) continue
            dp2[i] = maxOf(dp2[i], dp2[j]+1)
        }
    }

//    dp2.forEach { print("$it, ") }
//    println()

    var max = 0
    for(i in 0 until n){
        max = maxOf(max, dp[i]+dp2[i])
    }
    println(max+1)

}