class O_bj1032 {
}

fun main(){

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    val arr = Array<String>(n){ br.readLine() }

    val sb = StringBuilder()
    for( index in 0 until arr[0].length){
        var char = arr[0][index]
        for(i in 1 until arr.size){
            if(char == arr[i][index]) continue
            char = '?'
            break
        }
        sb.append(char)
    }

    println(sb)
}