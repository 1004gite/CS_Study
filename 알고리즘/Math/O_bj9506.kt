/**
 * 1. 본인을 제외한 약수의 합 == 본인   인 수를 완전수라 한다.
 * 2. n이 완전수인지 판단하라
 * */

fun main() {
    System.`in`.bufferedReader().use { br ->
        val sb = StringBuilder()
        while (true) {
            val n = br.readLine().toInt()
            if(n == -1) break

            val tmpSb = StringBuilder()
            var sum = 0
            for(i in 1 until n) {
                if(n % i == 0) {
                    sum += i
                    tmpSb.append("$i + ")
                }
                if(sum > n) break
            }

            if(sum == n){
                sb.append("$n = ").append(tmpSb.dropLast(3)).append('\n')
            } else {
                sb.append("$n is NOT perfect.\n")
            }
        }
        print(sb.toString())
    }
}