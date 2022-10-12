class bj1316 {
}

fun main(){

    val br = System.`in`.bufferedReader()
    var n = br.readLine().toInt()
    var result = 0
    while (n-- > 0){
        val set = hashSetOf<Char>()
        val str = br.readLine()!!
        var check = true
        var pre = str[0]
        set.add(str[0])
        for(c in str) {
            if (c == pre) continue
            if (!set.add(c)) {
                // 그룹이 아님
                check = false
                break
            }
            pre = c
        }
        if(check) result++
    }
    println(result)
}