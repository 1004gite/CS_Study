class O_bj1439 {
}

fun main(){
    val info = IntArray(2)

    with(readln()){
        var pre = '3'
        for(c in this){
            if(pre == c) continue
            pre = c
            info[pre.code - '0'.code]++
        }
    }
    if(info.sum() == 1) println(0)
    else println( if(info[0] > info[1]) info[1] else info[0] )
}