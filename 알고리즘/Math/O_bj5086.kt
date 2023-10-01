/**
 * 1. 첫번째 수가 두번째 숫자의 약수이다. -> a*x = b -> b%a = 0 (b > a)
 * 2. 첫번째 수가 두번째 숫자의 배수이다. -> b*x = a -> a%b = 0 (a > b)
 * 3. 첫번째 숫자가 두번째 숫자의 약수와 배수 모두 아니다 -> !(1 || 2)
 * */

fun main() {

    val sb = StringBuilder()

    fun checkFactor(a: Int,b: Int): Boolean {
        if(a > b) return false
        if(b%a != 0) return false
        sb.append("factor\n")
        return true
    }
    fun checkMultiple(a: Int,b: Int): Boolean {
        if(b > a) return false
        if(a%b != 0) return false
        sb.append("multiple\n")
        return true
    }
    fun check(a: Int,b: Int) {
        if(checkFactor(a,b)) return
        if(checkMultiple(a,b)) return
        sb.append("neither\n")
    }

    System.`in`.bufferedReader().use { br ->
        while (true) {
            val (a,b) = br.readLine().split(' ', limit = 2).map { it.toInt() }
            if(a == 0 && b == 0) break
            check(a,b)
        }
    }

    print(sb.toString())

}