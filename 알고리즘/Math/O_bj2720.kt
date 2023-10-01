import java.io.BufferedReader

/*
    1. 거스름돈은 항상 5.0 이하
    2. 동전의 개수는 최소가 되어야 한다.
    3. 0.25 0.1 0.05 0.01 종류의 동전이 있다.
    4. 입력은 센트로 들어오고 100센트는 1달러이다.
    5. 가장 큰 단위부터 주었을 때 예외 케이스가 있는지 생각해본다
    -> 0.25를 제외하면 더 작은 수로 더 큰수를 만들 수 있다.
    -> 0.25의 경우에도 0.1을 먼저 사용하는 경우가 이득인 경우는 없다.
*/

open class Coin(private val value: Int) {
    fun getDivide(money: Int): Int = money/value
    fun getRest(money: Int): Int = money%value
}



val Quarter = Coin(25)
val Dime = Coin(10)
val Nickel = Coin(5)
val Penny = Coin(1)

fun main() {

    val br = System.`in`.bufferedReader()
    fun BufferedReader.getInt() = br.readLine().toInt()
    val sb = StringBuilder()

    repeat(br.getInt()) {
        var money = br.getInt()
        sb.append(Quarter.getDivide(money)).append(' ')
        money = Quarter.getRest(money)
        sb.append(Dime.getDivide(money)).append(' ')
        money = Dime.getRest(money)
        sb.append(Nickel.getDivide(money)).append(' ')
        money = Nickel.getRest(money)
        sb.append(money).append(' ').append('\n')
    }
    br.close()
    print(sb.toString())
}