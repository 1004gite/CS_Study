/*
1~N의 번호가 붙은 N개의 기차가 있다.
각각 20개의 좌석이 있다.
4종류의 명령이 M개 주어짐
명령을 모두 수행 후 순서대로 기차가 통과하는데 같은 승객 배치를 가지고 있으면 통과할 수 없다.
*/

import java.util.*

class bj15787{

    var n = 0
    var m = 0
    lateinit var trains: Array<BooleanArray>

    fun solve(){

        val br = System.`in`.bufferedReader()
        with(StringTokenizer(br.readLine())) {
            n = this.nextToken().toInt()
            m = this.nextToken().toInt()
        }

        trains = Array(n){ BooleanArray(20) }
        repeat(m){
            with(StringTokenizer(br.readLine())){
                command(this.nextToken().toInt(), this.nextToken().toInt()-1, if(this.hasMoreTokens()) this.nextToken().toInt()-1 else 0)
            }
        }

        val hSet = hashSetOf<String>()
        trains.forEach {
            var sb = StringBuilder()
            it.forEach { b -> if(b) sb.append(1) else sb.append(0) }
            hSet.add(sb.toString())
        }

        println(hSet.size)
    }

    fun command(order: Int, i: Int, x: Int){
        return when(order){
            1 -> { trains[i][x] = true }
            2 -> { trains[i][x] = false }
            3 -> {
                for(j in 19 downTo 1) trains[i][j] = trains[i][j-1]
                trains[i][0] = false
            }
            else -> {
                for(j in 0..18) trains[i][j] = trains[i][j+1]
                trains[i][19] = false
            }
        }
    }
}

fun main(){
    bj15787().solve()
}