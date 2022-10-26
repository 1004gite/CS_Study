import java.util.StringTokenizer

/**
 * 1. 근처에 다시 쓰이는 플러그는 남겨두는 것이 유리하다
 * 2. 다음에 쓰이는 시간을 저장하여 가장 나중에 쓰이는 플러그를 뽑는 것이 유리하다.
 * 3. 구멍이 3개일 때 3번 안에 다시 쓰이는 플러그는 뽑지 않는 것이 유리하다.
 * 4. 3번 안에 모두 쓰인다면 그 다음 3번씩 검사한다.
 * */

class bj1700 {
    fun solve(){

        val br = System.`in`.bufferedReader()
        var st = StringTokenizer(br.readLine())

        val n = st.nextToken().toInt()
        val k = st.nextToken().toInt()

        st = StringTokenizer(br.readLine())
        val arr = IntArray(k){ st.nextToken().toInt() }

        var count = 0
        val plug = hashSetOf<Int>()
        for( i in arr.indices){
            if( plug.contains(arr[i]) ) continue
            if(plug.size < n){
                plug.add(arr[i])
                continue
            }

            // 플러그를 뽑아야 하는 상황
            val valid = hashSetOf<Int>()
            // 현재 arr[i-1], arr[i-2], arr[i-3] 번째 플러그가 꽂혀있다.
            // arr[i-1], arr[i-2], arr[i-3] 번째 플러그 중 가장 늦게 나오는 플러그를 뽑는다.
            for( j in i+1 until arr.size){
                if(valid.size >= n-1) break
                if( plug.contains(arr[j]) ) valid.add(arr[j])
            }

            // valid에 없는 플러그를 뽑는디.
            for(p in plug){
                if(valid.contains(p)) continue
                plug.remove(p)
                count++
                break
            }
            plug.add(arr[i])
        }

        println(count)
    }
}

fun main(){
    bj1700().solve()
}