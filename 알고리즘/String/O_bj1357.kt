import java.util.*

// String 관련은 StringBuilder가 가장 빠르다.

class O_bj1357 {
}

fun main(){
    with(StringTokenizer(readln())){
        val s1 = StringBuilder(this.nextToken()).reversed().toString().toInt()
        val s2 = StringBuilder(this.nextToken()).reversed().toString().toInt()
        println(StringBuilder((s1+s2).toString()).reversed().toString().toInt())
    }
}