class bj1427 {
}

fun main(){

    val info = IntArray(10)
    readln().forEach { info[it.code-'0'.code]++ }

    val sb = StringBuilder()
    for( i in 9 downTo 0){
        repeat(info[i]){ sb.append(i) }
    }
    print(sb)
}