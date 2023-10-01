/**
 * Equilateral :  세 변의 길이가 모두 같은 경우
 * Isosceles : 두 변의 길이만 같은 경우
 * Scalene : 세 변의 길이가 모두 다른 경우
 * */

fun main() {

    val sb = StringBuilder()

    System.`in`.bufferedReader().use { br ->
        while (true) {
            br.readLine().split(' ').map { it.toInt()}
                .let {
                    val max = it.maxOf { it }
                    if(max == 0) return@use
                    if(max*2 >= it.sum()) {
                        sb.append("Invalid\n")
                        return@let
                    }
                    it.map { it to 1 }.let { (a,b,c) ->
                        when(hashMapOf(a,b,c).size) {
                            1 -> sb.append("Equilateral")
                            2 -> sb.append("Isosceles")
                            else -> sb.append("Scalene")
                        }
                        sb.append('\n')
                    }
                }
        }
    }

    print(sb.toString())

}