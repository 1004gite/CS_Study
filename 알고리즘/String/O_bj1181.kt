class bj1181 {
}

fun main(){

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val arr = Array<String>(n){ br.readLine() }
    arr.sortWith { a, b ->
        if( a.length == b.length) a.compareTo(b)
        else a.length - b.length
    }

    val bw = System.out.bufferedWriter()
    val set = hashSetOf<String>()
    arr.forEach { if(set.add(it)) bw.write("$it\n") }

    br.close()
    bw.close()
}