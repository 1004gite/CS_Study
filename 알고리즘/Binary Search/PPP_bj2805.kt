import java.util.*

class bj2805 {
}

/**
 * 1. m의 값이 크기 때문에 길이에 대한 계산을 줄여야 한다.
 * */

//fun myBinarySearch(arr: LongArray, m: Long, preFix: LongArray): Long{
//    var low = 0L
//    var high = arr.last()-1
//    while(true){
//        if(low > high) break
//        val mid = (low+high)/2
//
//        val sum = getSum(arr,preFix,mid)
//        if(sum == m) return mid
//        else if(sum > m) low = mid+1
//        else high = mid-1
//    }
//    return high
//}
//
//fun getSum(arr: LongArray, preFix: LongArray, height: Long): Long{
//    var low = 0
//    var high = arr.size-1
//    var mid = 0
//    while(true){
//        if(low >= high) break
//        mid = (high+low)/2
//        if(arr[mid] <= height) low = mid+1
//        else high = mid // high는 항상 더 큰 값으로 유지
//    }
//    val minus = if(high != 0) preFix[high-1] else 0
//    return (preFix.last()-minus)-(preFix.size-high)*height
//}
//
//fun main(){
//    val br = System.`in`.bufferedReader()
//    var st = StringTokenizer(br.readLine())
//    val n = st.nextToken().toInt()
//    val m = st.nextToken().toLong()
//    st = StringTokenizer(br.readLine())
//    val arr = LongArray(n){st.nextToken().toLong()}.apply { sort() }
//    var sum = 0L
//    val preFix = LongArray(n){
//        sum += arr[it]
//        sum
//    }
//
//    println(myBinarySearch(arr,m, preFix))
//
//}

fun myBinarySearch(arr: LongArray, m: Long): Long{
    var low = 0L
    var high = arr.maxOrNull()!!-1
    while(true){
        if(low > high) break
        val mid = (low+high)/2

        val sum = getSum(arr,mid)
        if(sum < m) high = mid-1
        else low = mid+1
    }
    return high
}

fun getSum(arr: LongArray, height: Long): Long{
    var sum = 0L
    for(h in arr){
        if(h <= height) continue
        sum += h-height
    }
    return sum
}

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toLong()
    st = StringTokenizer(br.readLine())
    val arr = LongArray(n){st.nextToken().toLong()}

    println(myBinarySearch(arr,m))
}