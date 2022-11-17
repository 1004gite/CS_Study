package String

class bj5582 {

    fun solve(){
        val br = System.`in`.bufferedReader()
        val s1 = br.readLine()
        val s2 = br.readLine()
        br.close()

        // 행-s1, 열-s2
        val arr = Array(s1.length){IntArray(s2.length)}
        var max = 0
        for(r in s1.indices){
            for(c in s2.indices){
                if(s1[r] != s2[c]) continue
                if(r-1 >= 0 && c-1 >= 0) arr[r][c] = arr[r-1][c-1]+1
                else arr[r][c] = 1
                if(max < arr[r][c]) max = arr[r][c]
            }
        }

        println(max)
    }

}

fun main(){
    bj5582().solve()
}