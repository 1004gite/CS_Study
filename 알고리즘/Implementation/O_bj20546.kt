import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

/**
 * 1. 매수, 매도는 항상 최대치로 이루어진다.
 * 2. 현재 소유한 주식이 3일째 상승하면 전량 매도한다. (가격 유지는 상승이 아니다.)
 * 3. 3일 연속 가격이 하락하면 해당 주식을 전향 매수한다. (가격 유지는 하락이 아니다.)
 *
 * */
class bj20546 {

    fun solve(){
        val br = BufferedReader(InputStreamReader(System.`in`))

        val money = br.readLine().toInt()
        val j = Jun(money,0)
        val s = Sung(money,0)
        val st = StringTokenizer(br.readLine())

        repeat(14){
            val price = st.nextToken().toInt()
            j.update(price)
            s.update(price)
        }

        val totalJ = j.money + j.stock*s.pre
        val totalS = s.money + s.stock*s.pre

        if(totalJ > totalS) println("BNP")
        else if(totalJ < totalS) println("TIMING")
        else println("SAMESAME")
    }

    class Jun(var money: Int, var stock: Int = 0){
        fun update(price: Int){
            stock += money/price
            money %= price
        }
    }

    class Sung(var money: Int, var stock: Int = 0){
        var pre = -1
        var countUp = 0
        var countDown = 0

        fun update(price: Int){
            if(pre == -1){
                pre = price
                return
            }

            if(pre > price){
                // 하락
                countDown++
                countUp = 0
            }
            else if(pre < price){
                countUp++
                countDown = 0
            }
            else {
                countUp = 0
                countDown = 0
            }
            pre = price

            if(countUp == 3) sell(price)
            if(countDown == 3) buy(price)
        }

        fun buy(price: Int){
            stock += money/price
            money %= price
        }

        fun sell(price: Int){
            money += price*stock
            stock = 0
        }
    }
}

fun main(){
    bj20546().solve()
}