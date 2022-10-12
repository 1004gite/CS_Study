// 대소문자 구분 없이 가장 많이 사용된거 -> 여러개면 ? 출력

fun main(){
    val str = readln()
    val info = IntArray('z'.code - 'a'.code + 1)
    val getNum: (Char)->(Int) = { c ->
        if(c.code >= 'a'.code) c.code - 'a'.code
        else c.code - 'A'.code
    }

    for(c in str) info[getNum(c)]++

    var index = -1
    val max = info.maxOrNull()!!
    for(i in info.indices){
        if(info[i] == max){
            if(index != -1){
                println("?")
                return
            }
            index = i
        }
    }
    println(('A'.code+index).toChar())
}