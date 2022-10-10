package String

class bj11720 {
}
fun main(){
    readln()
    var sum = 0
    for(c in readln()){
        sum += (c.code - '0'.code)
    }
    println(sum)
}