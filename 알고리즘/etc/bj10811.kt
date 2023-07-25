package etc

import java.util.LinkedList


fun main() {
    val br = System.`in`.bufferedReader()
    // 한줄을 입력받아 공백을 기준으로 List<Int>로 반환
    fun getInput() = br.readLine().split(' ').map { it.toInt() }

    val input = getInput()
    val n = input[0]
    val m = input[1]

    // n+1크기 intArray에 각 값을 본인의 index로 세팅
    // index는 위치, value는 바구니의 번호를 의미
    val arr = IntArray(n+1){ it }

    // start~end를 역순으로 바꾸는 함수들
    fun reverse1(start: Int, end: Int) {
        // 172ms
        // IntArray 기본 확장함수 이용
        arr.reverse(start, end+1)
    }
    fun reverse2(start: Int, end: Int) {
        // 144ms
        // stack 이용
        val stack = LinkedList<Int>()
        for(i in start..end) {
            stack.addLast(arr[i])
        }
        for(i in start downTo end) {
            arr[i] = stack.removeLast()
        }
    }
    fun reverse3(start: Int, end: Int) {
        // 144ms
        // 배열을 전부 deepCopy한 후 값을 적용하는 방식 이용
        val tmpArr = arr.copyOf()
        for(i in 0..end-start) {
            arr[start+i] = tmpArr[end-i]
        }
    }

    repeat(m) {
        val tmp = getInput()
//        reverse1(tmp[0], tmp[1])
//        reverse2(tmp[0], tmp[1])
        reverse3(tmp[0], tmp[1])
    }

    // 정답 출력
    val sb = StringBuilder()
    for(i in 1 until arr.size) {
        sb.append(arr[i]).append(' ')
    }
    sb.dropLast(1)
    print(sb.toString())
}