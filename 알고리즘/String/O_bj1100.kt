class bj1100 {
}

fun main(){

    val br = System.`in`.bufferedReader()
    var sum = 0

    repeat(4){
        with(br.readLine()){
            for(i in 0 until 8 step 2) if(this[i] == 'F') sum++
        }
        with(br.readLine()){
            for(i in 1 until 8 step 2) if(this[i] == 'F') sum++
        }
    }

    println(sum)
}