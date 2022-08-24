import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

/**
 * 학생들은 같이하고 싶은 학생 한명을 지목한다.(본인을 지목할 수 있다.)
 * 학생들이 여럿 연결된다면 여러명은 한 팀이 된다.(모두 같은 팀이 될 수 있다.)
 * 하지만 학생들이 지목해 순환이 만들어지는 경우만 팀이 된다.
 * 팀이 되지 못한 학생들의 수를 구하여라
 *
 * 풀이 1
 * 1. 순환이 만들어진다면 그 사람들은 팀이 된다.
 * 2. dfs를 통해 팀이 된 사람들을 표시한다.
 * */


class bj9466 {

//    fun solve(){
//        val br = BufferedReader(InputStreamReader(System.`in`))
//        var t = br.readLine().toInt()
//
//        while(t > 0){
//            t--
//            val n = br.readLine().toInt()
//            var arr = IntArray(n+1)
//            val st = StringTokenizer(br.readLine())
//            for(i in 1..n){
//                arr[i] = st.nextToken().toInt()
//            }
//
//            // 0 아직 고려하지 않음, 1 팀이 될 수 없음, 2 팀이 있음
//            var hasTeam = IntArray(n+1)
//            for(i in 1..n){
//                if(hasTeam[i] != 0) continue
//
//                var visited = BooleanArray(n+1)
//                var now = i
//                // 다시 본인으로 돌아와야 팀이 된 것임
//                while(true){
//                    if(visited[now]){
//                        // now부터 순환이 발생해 팀이 결성되었음
//                        // i에서 now 전까지는 팀이 결성되지 않음
//                        var tmp = i
//                        while(true){
//                            if(tmp == now) break
//                            hasTeam[tmp] = 1
//                            tmp = arr[tmp]
//                        }
//                        while(true){
//                            // 팀이 결성된 사람들
//                            hasTeam[tmp] = 2
//                            tmp = arr[tmp]
//                            if(tmp == now) break
//                        }
//                        break
//                    }
//                    if(hasTeam[now] != 0){
//                        // i부터 now 전까지 팀이 될 수 없음
//                        var tmp = i
//                        while(true){
//                            if(tmp == now) break
//                            hasTeam[tmp] = 1
//                            tmp = arr[tmp]
//                        }
//                        break
//                    }
//
//                    visited[now] = true
//                    now = arr[now]
//                }
//            }
//
//            var count = 0
//            for(i in 1..n){
//                if(hasTeam[i] == 1) count++
//            }
//
//            println(count)
//        }
//    }

    fun solve2(){
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.out))
        var t = br.readLine().toInt()

        while(t > 0){
            t--
            val n = br.readLine().toInt()
            var arr = IntArray(n+1)
            val st = StringTokenizer(br.readLine())
            for(i in 1..n){
                arr[i] = st.nextToken().toInt()
            }

            // 어차피 한번 방문한 node는 팀 구성이 가능하건 가능하지 않건 결정은 된다.
            // 또한 방문이 가능하건 가능하지 않건 이미 방문한 node를 원하면 팀이 구성될 수 없다.
            // 때문에 목록을 따로 유지하지 않고 방문 기록과 팀을 구성할 수 없는 인원만 기록한다.
            var count = 0
            var visited = BooleanArray(n+1)
            for(i in 1..n){
                if(visited[i]) continue

                var now = i
                // 다시 본인으로 돌아와야 팀이 된 것임
                while(true){
                    if(visited[now]){
                        // i ~ now 전까지는 team에 들어갈 수 없음
                        var tmp = i
                        while(true){
                            if(tmp == now) break
                            count++
                            tmp = arr[tmp]
                        }
                        break
                    }

                    visited[now] = true
                    now = arr[now]
                }
            }

            bw.write("$count\n")
        }
        bw.flush()
    }
}

fun main(){
    bj9466().solve2()
}