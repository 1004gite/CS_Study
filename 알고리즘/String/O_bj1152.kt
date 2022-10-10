package String

class bj1152 {
}

fun main(){
    var arr = readln().split(" ")
    var result = arr.size
    if(arr[0] == "") result--
    if(arr.last() == "") result--
    println(result)
}