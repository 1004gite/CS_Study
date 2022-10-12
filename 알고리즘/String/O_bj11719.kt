class bj11719 {
}

fun main(){

    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var str = br.readLine()

    while (str != null){
        bw.write(str)
        bw.write("\n")
        str = br.readLine()
    }

    br.close()
    bw.close()

}