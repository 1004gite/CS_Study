class bj11721 {
}

fun main(){

    val sb = StringBuilder(readln())
    val start = sb.length - sb.length%10
    for( i in start downTo 10 step 10) sb.insert(i,'\n')
    sb.append('\n')
    print(sb)
}