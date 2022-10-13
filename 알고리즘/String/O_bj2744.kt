class O_bj2744 {
}

fun main(){
    println( readln().let {
        val tmp = 'a'.code - 'A'.code
        val sb = StringBuilder()
        it.forEach { c ->
            if(c.code >= 'a'.code) sb.append((c.code-tmp).toChar())
            else sb.append((c.code+tmp).toChar())
        }
        sb
    } )
}