import java.util.*

class bj1520 {
}

/**
 * 각 칸의 높이가 주어진 지도가 주어진다.
 * 0,0 -> n,m으로 이동하는데 높이가 더 낮은 곳으로만 이동할 수 있다.(0 < n,m <= 500)
 * 이동 가능한 모든 경우의 수를 구하라
 * 1. dfs로 모든 경로를 탐색하게 되면 경우의 수가 매우 커진다.
 * 2. dp로 본인으로 올 수 있는 경로의 수를 저장하며 간다.
 * 3. 이떄 최단경로가 아니기 때문에 한방향으로만 dp를 누적하면 모든 경우의 수가 고려되지 않을 수 있다.
 * 4. 플로이드 워셜 비슷하게 변화가 없을때까지 업데이트한다.
 * */

/**
 * 정석 풀이는 dfs와 dp를 이용하는 방법이다.
 * dp를 -1로 초기화한 후 dfs로 n,m까지 탐색을 시작한다.
 * 이때 m,n에서 1을 return하고 각 dfs는 갈 수 있는 모든 dfs를 한 후 return되는 합을 dp에 저장한다.
 * 도중에 dp가 -1이 아니라면 이미 메모제이션이 있기 때문에 그 값을 그대로 사용한다.
 * */

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val myI = { st.nextToken().toInt()}

    val row = myI()
    val col = myI()

    val map = Array(row){
        st = StringTokenizer(br.readLine())
        IntArray(col){
            myI()
        }
    }

    val arrR = arrayOf(0,0,1,-1)
    val arrC = arrayOf(1,-1,0,0)

    // 0,0에서 r,c까지 가는 경우의 수
    val dp = Array(row){ IntArray(col)}
    // 출발지에서의 2방향만 미리 업데이트한다.
    if(col >= 2 && map[0][0] > map[0][1]) dp[0][1] = 1
    if(row >=2 && map[0][0] > map[1][0]) dp[1][0] = 1

    var end = true
    while(true){

        for(r in 0 until row){
            for(c in 0 until col){
                // 0,0을 제외하고 본인으로 올 수 있는 길들 + 1을 했을 때 변화가 있다면 업데이트한다.
                // 높은 곳에서 낮은 곳으로만 갈 수 있기 때문에 무한루프에 빠질 일은 없다.
                var sum = 0
                for(i in 0..3){
                    // tmpR,tmpC가 더 높아야 r,c로 올 수 있다.
                    val tmpR = r + arrR[i]
                    val tmpC = c + arrC[i]
                    if(tmpR < 0 || tmpR >= row || tmpC < 0 || tmpC >= col) continue
                    if(map[r][c] >= map[tmpR][tmpC]) continue
                    sum += dp[tmpR][tmpC]
                }

                if(sum > dp[r][c]){
                    end = false
                    dp[r][c] = sum
                }
            }
        }

//        for(r in 0 until row){
//            println()
//            for(c in 0 until col){
//                print("${dp[r][c]}, ")
//            }
//        }
//        println()

        if(end) break
        end = true
    }

    println(dp.last().last())
}