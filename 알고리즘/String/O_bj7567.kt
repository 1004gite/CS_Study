
class O_bj7567 {
}

fun main(){
    val str = readln()
    var sum = str.length*5 + 5
    for( i in 1 until str.length) if(str[i-1] != str[i]) sum += 5

    println(sum)
}