package etc

import java.util.*

class MyStack {

    // -4:(  -3:[  -2:)  -1:]  -5:err
    private val stack = LinkedList<Int>()

    fun getAnswer(): Int{
        // 숫자 외의 문자가 남아있다면 잘못된 String으로 0을 출력한다.
        var sum = 0
        stack.forEach {
            if(isDigit(it).not()) return 0
            sum += it
        }

        return sum
    }

    // 도중에 잘못된 문자열임이 판단되면 false를 반환한다.
    fun push(charValue: Char): Boolean {
        val value = convertIfLetter(charValue)
        if(isOpen(value)) {
            // 열리는 괄호는 그냥 넣는다.
            stack.addLast(value)
        } else {
            // 닫히는 괄호는 숫자가 아닌 char가 나올때까지 뺀 후 판단.
            val tmpStack = LinkedList<Int>()
            while(true) {
                if(stack.isEmpty()) return false
                if( isDigit(stack.last).not() ) break
                tmpStack.addLast(stack.removeLast())
            }
            // 유효한지 판단
            if(checkValid(stack.last, value).not()) return false
            // 적용할 값
            val applyValue = if(value == -1) 3 else 2
            // 기존 값
            val origin = if(tmpStack.isEmpty()) 1 else tmpStack.sum()

            // 열린 괄호를 제거 후 값 삽입
            stack.removeLast()
            stack.addLast(applyValue*origin)
        }

        return true
    }

    private fun convertIfLetter(value: Char): Int {
        return when(value) {
            '(' -> -4
            '[' -> -3
            ')' -> -2
            ']' -> -1
            else -> value.digitToIntOrNull() ?: -5
        }
    }

    private fun isDigit(value: Int): Boolean {
        return value >= 0
    }

    private fun isOpen(value: Int): Boolean {
        return value <= -3
    }

    private fun checkValid(open: Int, close: Int): Boolean {
        return (open == -4 && close == -2) || (open == -3 && close == -1)
    }

}

fun main() {
    val str = readln()
    val myStack = MyStack()
    str.forEach { char ->
        val check = myStack.push(char)
        if(check.not()) {
            println(0)
            return
        }
    }
    println(myStack.getAnswer())
}