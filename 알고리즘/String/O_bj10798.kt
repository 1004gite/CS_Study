
class O_bj10798 {
}

fun main(){
    val arr = Array<String>(5){ readln() }
    val max = arr.maxOf { it.length }
    var index = 0
    while(true){
        if(index >= max) break
        for(s in arr){
            if(index < s.length) print(s[index])
        }
        index++
    }
    println()
}