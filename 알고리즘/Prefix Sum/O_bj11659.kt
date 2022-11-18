package `Prefix Sum`

import java.util.*

class bj11659 {
}

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    var t = st.nextToken().toInt()

    st = StringTokenizer(br.readLine())
    var sum = 0
    val arr = IntArray(n){
        sum += st.nextToken().toInt()
        sum
    }

    val bw = System.out.bufferedWriter()
    while(t-- > 0){
        st = StringTokenizer(br.readLine())
        val s = st.nextToken().toInt()-1
        val e = st.nextToken().toInt()-1

        var result = arr[e]
        if(s != 0) result -= arr[s-1]
        bw.write("$result\n")
    }

    br.close()
    bw.flush()
    bw.close()
}


/*
5 3
5 4 3 2 1
1 3
2 4
5 5
* */