/**
 * 오리는 quack 이라는 소리를 내며 운다.
 * 오리의 소리가 섞일 수는 있지만 알파벳 순서는 같아야 한다.
 * 울음소리가 주어졌을 때 최소 몇마리의 오리가 있는지 구하라
 *
 * 풀이 1
 * 1. 최소 오리의 수를 구해야 한다.
 * 2. 동시에 울 수는 없으므로 동시에 운 오리의 최대수를 구해야 한다.
 * 3. q가 오는 경우에는 리스트에 목록을 추가하고 k가 와서 울음이 완성되면 삭제한다.
 * 4. 알파벳은 순서대로 들어가야 하므로 글자를 저장할 필요 없이 글자의 길이만 숫자로 저장해도 무관하다.
 * 5. 길이가 5인 배열을 만들어 각 index에 해당 길이의 문자열이 몇개 있는지 기록한다.
 * */

import kotlin.math.*

class bj12933 {

    val record = IntArray(4)
    var max = 0
    var now = 0

    fun solve(){
        val str = readln()

        for(c in str){
            if(!update(c)){
                println(-1)
                return
            }
        }

        if(now != 0) println(-1)
        else println(max)
    }

    fun update(c: Char): Boolean{
        when(c){
            'q' -> {
                now++
                record[0]++
                max = max(max,now)
            }
            'u' -> {
                if(record[0] == 0) return false
                record[0]--
                record[1]++
            }
            'a' -> {
                if(record[1] == 0) return false
                record[1]--
                record[2]++
            }
            'c' -> {
                if(record[2] == 0) return false
                record[2]--
                record[3]++
            }
            else -> {
                if(record[3] == 0) return false
                record[3]--
                now--
            }
        }
        return true
    }

}

fun main(){
    bj12933().solve()
}