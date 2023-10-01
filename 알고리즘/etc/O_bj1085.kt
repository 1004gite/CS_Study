/**
 * 1. (0,0) (w,h) 에 두 꼭짓점이 있는 직사각형이 좌표 위에 있다.
 * 2. (x,y)에서 직사각형의 경계선까지 가는 거리의 최솟값을 구하라
 * 3. 수평, 수직 각각 최소값을 구하여 비교한다.
 * */

fun main() {
    readln().split(' ').map { it.toInt() }
        .let { (x,y,w,h) ->
            print(arrayOf(x,y,w-x,h-y).minOf { it })
        }
}