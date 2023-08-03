fun main() {
    System.`in`.bufferedReader().use { br ->
        val n: Int
        val m: Int
        br.readLine().split(' ').let {
            n = it[0].toInt()
            m = it[1].toInt()
        }

        val arr = Array(n){ IntArray(m) }
        for(i in 0 until n) {
            val str = br.readLine().split(' ')
            for(j in 0 until m) {
                arr[i][j] = str[j].toInt()
            }
        }

        val sb = StringBuilder()
        for(i in 0 until n) {
            val str = br.readLine().split(' ')
            for(j in 0 until m) {
                sb.append(arr[i][j] + str[j].toInt()).append(' ')
            }
        }
        print(sb.toString())
    }
}