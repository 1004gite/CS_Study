class bj3071 {
}

/**
 * 10진수를 특별한 3진수로 바꾼다.
 * 각 자리수는 3^n을 의미하고 1,0,-1을 곱할 수 있다.
 *
 * 1. n자리이고 모든 자리가 1이라면 3^n-1 + 3^n-2 + ... + 1 == 3(3^n-2 + 3^n-3 + ... + 1) + 1 == 3^n-1 + 3(n-1)
 * 2. 9 이전에는 3,1이 있다. -> 9-4 ~ 9+4 를 만들 수 있다.
 *      27 이전에는 9,3,1이 있다. -> 27-13 ~ 27+13을 만들 수 있다.
 *      즉 +-(n/2)까지 만들 수 있다.
 * */

val threeSquare = LongArray(31).apply {
    this[0] = 1L
    for(i in 1..30) this[i] = this[i-1]*3L
}

fun setNum(num: Long, result: CharArray){
    if(num == 0L) return
    // x번째 자리가 1이라서 3^(x-1) == y의 값을 갖는다고 하자
    // 이때 +-y/2 안에 num이 들어간다면 해당 값을 선택한다.
    var target = num
    if(num < 0) target *= -1L
    var tmp = 0
    while(true){
        val now = threeSquare[tmp]
        val half = now/2L
        if(target in now-half..now+half) break
        tmp++
    }

    var next = num

    if(num < 0){
        result[tmp] = '-'
        next += threeSquare[tmp]
    }
    else{
        result[tmp] = '1'
        next -= threeSquare[tmp]
    }
//    print("(${result[tmp]})${threeSquare[tmp]} -> ")

    setNum(next, result)
}

fun main(){
    val br = System.`in`.bufferedReader()
    val t = br.readLine().toInt()

    val sb = StringBuilder()

    repeat(t){

        val num = br.readLine().toLong()
        val result = CharArray(30){'x'}
        setNum(num, result)
//        print("\n\n\n")

        // 1||- 이전의 x는 고려하지 않음
        // 1||- 이후의 x는 0으로 취급함
        var index = result.size-1
        while(index > 0 && result[index] == 'x') index--
        if(index == -1) sb.append('0')
        for(i in index downTo  0){
            if(result[i] == 'x') sb.append('0')
            else sb.append(result[i])
        }
        sb.append('\n')
    }

    print(sb)
}