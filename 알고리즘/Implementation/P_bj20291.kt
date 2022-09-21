import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 * 파일을 확장자별 정리한 후 몇개씩 있는지 알아야 함
 * 확장자를 사전순으로 정렬하여 출력한다.
 *
 * 풀이 1
 * 1. 파일의 확장자는 hashSet으로 중복을 검사
 * 2. 개수를 저장해야 하므로 hashMap이 더 적당하다.
 * 3. 마지막에 sort하여 출력한다.
 * */

class bj20291 {

    fun solve(){
        val br = BufferedReader(InputStreamReader(System.`in`))
        val n = br.readLine().toInt()

        val hashMap = hashMapOf<String, Int>()

        repeat(n){
            val str = br.readLine().split(".")[1]

            val tmp = hashMap[str]
            if(tmp == null) hashMap[str] = 1
            else hashMap[str] = tmp+1
        }

        val bw = BufferedWriter(OutputStreamWriter(System.out))
        for(pair in hashMap.toSortedMap()){
            bw.write(pair.key+" ${pair.value}\n")
        }
        bw.flush()
    }
}

fun main(){
    bj20291().solve()
}