import java.util.StringTokenizer

class bj1764 {
}

fun main(){

    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val st = StringTokenizer(br.readLine())

    val set = hashSetOf<String>()
    repeat(st.nextToken().toInt()){ set.add(br.readLine()) }

    val result = mutableListOf<String>()
    repeat(st.nextToken().toInt()){
        br.readLine()?.let { if(set.contains(it)) result.add(it) }
    }
    result.sort()
    bw.write("${result.size}\n")
    result.forEach { bw.write("$it\n") }

    br.close()
    bw.close()
}