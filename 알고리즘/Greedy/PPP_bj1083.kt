import java.util.StringTokenizer

/**
 * 크기가 n인 배열 A가 있고 모든 원소는 중복이 없다.
 * 이 배열을 sort할 때 swqp만 가능하다.
 * swap은 최대 S번 가능하다.
 * 내림차순으로 sort하되 S번 안에 불가능하다면 앞의 숫자가 먼저 앞에 나오게 한다.
 *
 * 풀이 1
 * 1. 사전순으로 늦게 나오려면 가장 앞의 숫자를 크게 만드는 것을 우선 해야한다.
 * 2. 즉, i in 1..n-1에서 시작하여 0번째 원소까지 내림차순 정렬한다.
 * 3. n은 최대 50이므로 n^2 풀이가 가능하다.
 *
 * 풀이 2
 * 1. s번 swap이 가능하다는 뜻은 최대 s번위에서까지 가져올 수 있다는 얘기다.
 * */

class bj1083 {
}

fun main(){
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val arr = IntArray(n){ st.nextToken().toInt() }
    var s = br.readLine().toInt()

    for(i in 0 until n){
        // i번째 원소를 선택
        if(s == 0) break
        var index = i
        for(j in i+1 until n){
            if(j-i > s) break // 이 이상 멀면 바꿀 수 없음
            if(arr[index] < arr[j]){
                index = j
            }
        }
        if(index == i) continue // 이미 범위 내의 최대값임
        // 한칸씩 뒤로 민 후 index번째는 i에 넣음
        // s를 업데이트함
        s -= (index-i)
        val tmp = arr[index]
        for(j in index downTo i+1) arr[j] = arr[j-1]
        arr[i] = tmp
    }

    val sb = StringBuilder()
    arr.forEach { sb.append(it).append(' ') }
    print(sb.append('\n'))
}