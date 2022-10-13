
class O_10988 {
}

fun main(){
    val str = readln()

    for(i in 0..str.length/2){
        if(str[i] != str[str.length-i-1]){
            println(0)
            return
        }
    }
    println(1)
}