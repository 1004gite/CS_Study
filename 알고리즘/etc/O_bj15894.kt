/**
 * 1. 한변의 길이가 1인 정사각형이 무한히 있다.
 * 2. 각 층에는 각 층수만큼의 정사각형이 있다.
 * 3. n층까지 있을 때 둘레를 구하라
 * 4. 위, 아래에서 봤을 때 둘레는 가장 긴 층수 만큼이다
 * 5. 양옆에서 봤을 때 층수만큼의 길이이다.
 * -> 총 둘레는 n*4이다.
 * 6. n은 10억까지 올 수 있다.
 * -> Long 사용
 * */

fun main() {
    print(readln().toLong()*4L)
}