package etc

import java.util.LinkedList

fun main() {

    data class Top(val height: Int, val num: Int)

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val tops = br.readLine().split(' ').mapIndexed { index, s -> Top(s.toInt(), index+1) }

    val stack = LinkedList<Top>().apply {
        // 수신하는 탑이 없을 경우 0을 출력해야 한다.
        // 최대 높이의 0번 탑을 가상으로 세워 0이 출력되게 한다.
        addLast(Top(Int.MAX_VALUE,0))
    }

    fun push(top: Top): Int{
        // 본인의 레이저를 수신 가능한 탑을 찾을때까지 pop한 후 push한다.
        // 수신 가능한 탑의 번호를 return한다.
        // 가상의 거대한 탑을 넣었기 때문에 수신 가능한 탑이 없는 경우는 고려하지 않아도 무관하다.
        while (stack.last.height <= top.height) {
            stack.removeLast()
        }
        val resultNum = stack.last.num
        stack.addLast(top)
        return resultNum
    }

    val sb = StringBuilder()
    tops.forEach {
        val result = push(it)
        sb.append("$result ")
    }

    print(sb.toString())
}