fun main() {
    var max = 0
    var index = 0 to 0

    val br = System.`in`.bufferedReader()
    for(i in 0 until 9) {
        val numbers = br.readLine().split(' ').map { it.toInt() }
        for(j in 0 until 9){
            if(max < numbers[j]) {
                max = numbers[j]
                index = i to j
            }
        }
    }
    br.close()

    print("$max\n${index.first+1} ${index.second+1}")
}