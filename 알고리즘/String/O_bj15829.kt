
class O_bj15829 {
}

fun main(){
    val mod = 1234567891
    val r = 31L
    val myCode: Char.()->Long = { (this.code - 'a'.code + 1).toLong() }
    val myPow: Long.(Int)->Long = { p ->
        var now = 1L
        repeat(p){
            now *= this
            now %= mod
        }
        now
    }

    readln()
    var result = 0L
    val str = readln()
    for(i in str.indices){
        result += str[i].myCode() * r.myPow(i)
        result %= mod
    }

    println(result)
}