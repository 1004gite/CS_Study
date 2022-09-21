/**
 * 움직이는 시간은 직교좌표 거리만큼 걸린다.
 * 키를 누를는 시간은 1초가 걸린다.
 * 각 손가락은 자음, 모음을 담당한다.
 *
 * 풀이 1
 * 1. 움직이는 시간이 겹칠 수 있다.
 * 2. 손가락이 미리 가있을 수는 있지만 먼저 누를 수는 없다.
 * 3. 문제 조건에 성우는 두 손을 동시에 움직일 수 없다고 나와 있다...
 *      괜히 더 어렵게 풀었다...
 * */

import java.util.*
import kotlin.math.abs

class bj20436 {

    val keyboard = arrayOf(
        arrayOf('q','w','e','r','t','y','u','i','o','p'),
        arrayOf('a','s','d','f','g','h','j','k','l'),
        arrayOf('z','x','c','v','b','n','m')
    )
    val leftList = arrayOf('q','w','e','r','t','a','s','d','f','g','z','x','c','v')
    val rightList = arrayOf('y','u','i','o','p','h','j','k','l','b','n','m')

    fun solve(){

        var st = StringTokenizer(readln())
        var left = Finger(st.nextToken()[0])
        var right = Finger(st.nextToken()[0])

        var str = readln()

        var time = 0
        for(c in str){
            time += if(leftList.contains(c)) left.updateDistance(c) else right.updateDistance(c)
            // 누르는 시간
            time++
        }

        println(time)
    }

    inner class Finger(var key: Char){
        var point = findPoint(key)

        fun findPoint(c: Char): Pair<Int,Int> {
            for(i in keyboard.indices){
                val index = keyboard[i].indexOf(c)
                if(index != -1){
                    return i to index
                }
            }
            return -1 to -1
        }

        fun updateDistance(target: Char): Int{
            val tmp = findPoint(target)
            val distance = abs(tmp.first-point.first) + abs(tmp.second-point.second)
            point = tmp
            key = target
            return distance
        }
    }
}

fun main(){
    bj20436().solve()
}