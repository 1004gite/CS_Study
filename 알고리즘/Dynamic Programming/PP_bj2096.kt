/**
 * n줄에 0~9의 숫자가 적혀 있다.
 * n줄을 모두 내려가는데 좌우 한칸까지만 움직이며 내려갈 수 있다.
 * 내려가며 얻을 수 있는 최대, 최소 점수를 구하여라
 *
 * 풀이 1
 * 1. 이동함에 따라 다음에 갈 수 있는 경로가 달라지기 때문에 중간해가 최적해의 일부라고 볼 수 없다.
 * 2. 이번에 내려온 결과때문에 다음 줄에서 최대 수를 고를 수 없게될 수도 있기 때문 -> 모든 경우 고려해야함
 * 3. 하지만 현재 줄까지의 최대값은 이전 모든 경우의 수를 고려하기 떄문에 유효하다.
 * 4. 완전탐색을 하면 적어도 2^100000의 경우의 수가 나오므로 불가능하다.
 * 5. dp를 이용한다.
 */

class bj2096 {
    fun solve(){
        val br = System.`in`.bufferedReader()
        val n = br.readLine().toInt()
        val info = Array(n){ br.readLine().split(" ").map { it.toInt() } }

        var dp = Array(3){ info[0][it] }
        for(i in 1 until info.size){
            val tmp = Array(3){ info[i][it] }
            tmp[0] += maxOf(dp[0],dp[1])
            tmp[1] += maxOf(dp[0],dp[1],dp[2])
            tmp[2] += maxOf(dp[1],dp[2])
            dp = tmp
        }
        print(dp.maxOrNull())

        dp = Array(3){ info[0][it] }
        for(i in 1 until info.size){
            val tmp = Array(3){ info[i][it] }
            tmp[0] += minOf(dp[0],dp[1])
            tmp[1] += minOf(dp[0],dp[1],dp[2])
            tmp[2] += minOf(dp[1],dp[2])
            dp = tmp
        }
        println(" ${dp.minOrNull()}")
    }

}

fun main() = bj2096().solve()