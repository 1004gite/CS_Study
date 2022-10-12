
class O_bj1259 {
}

fun main(){

    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var str = br.readLine()

    while(str != "0"){
        var check = true
        for(i in 0..str.length/2){
            if(str[i] != str[str.length-i-1]){
                check = false
                break
            }
        }
        bw.write( if(check) "yes\n" else "no\n" )

        str = br.readLine()
    }

    br.close()
    bw.close()
}