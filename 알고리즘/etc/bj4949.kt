import java.util.*

fun main () {
    System.`in`.bufferedReader().use { br ->
        val sb = StringBuilder()

        for(str in br.readLines()) {
            if(str == ".") break
            val stack = LinkedList<Char>()
            for(c in str) {
                // 열리는 괄호는 stack에 넣는다.
                if(c == '(' || c == '[') stack.addLast(c)
                // 닫히는 괄호들은 종류에 따라 올바른 문자열인지 판단한 후,
                // 올바르지 않다면 stack에 'x'를 넣어 표시하고 반복문을 탈출한다.
                else if (c == ')' || c == ']') {
                    // 와야 할 열리는 괄호를 의미
                    val targetOpen = if(c == ')') '(' else '['
                    if(stack.isEmpty() || stack.last != targetOpen) {
                        // 이전 열린 괄호가 없는 경우 || 올바르지 않은 열린 괄호가 온 경우
                        stack.addLast('x')
                        break
                    }
                    // 올바른 경우라면 열리는 괄호를 stack에서 제거
                    stack.removeLast()
                    // 상단 if문에서 검사할 때 last 대신 removeLast를 사용하면 이 부분을 생략할 수 있지만
                    // 가독성을 위해 그러지 않겠다.
                }
            }

            sb.append(if(stack.isEmpty()) "yes\n" else "no\n")
        }

        print(sb.toString())
    }
}