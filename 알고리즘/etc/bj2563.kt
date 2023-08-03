fun main() {
    val arr = Array(100){BooleanArray(100)}
    System.`in`.bufferedReader().use { br ->
        repeat(br.readLine().toInt()) {
            // 열, 행
            val index = br.readLine().split(' ').map { it.toInt() }
            for(i in 0 until 10) {
                for(j in 0 until 10) {
                    arr[index[1]+i][index[0]+j] = true
                }
            }
        }
    }
    print(arr.sumOf { it.count  { it } })
}