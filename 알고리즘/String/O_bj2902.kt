class bj2902 {
}

fun main(){

    val sb = StringBuilder()
    readln().split("-").forEach {
        sb.append(it[0])
    }

    println(sb)
}