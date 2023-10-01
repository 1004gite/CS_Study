/**
 * 1. 막대 a,b,c를 가지고 있다.
 * 2. 각 막대의 길이를 줄일 수 있다.
 * 3. 둘레의 길이가 최대가 되게 삼각형을 만든다.
 * 4. 가장 긴변이 짧은 두 변의 합보다 짧아야 한다.
 * -> 가장 긴 변이 짧은 두 변의 합보다 크면 1 작게 줄이고 아니면 그대로 사용한다.
 * */

fun main() {
    val arr = System.`in`.bufferedReader().readLine()
        .split(' ')
        .map { it.toInt() }
        .sorted()

    var result = arr[0] + arr[1]
    result += if(result > arr[2]) arr[2] else result-1

    print(result)
}