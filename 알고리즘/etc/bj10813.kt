package etc

fun main() {

    // 입력이 최대 100개이므로 빠른 입출력을 위해 bufferedReader 사용
    val br = System.`in`.bufferedReader()
    // 한줄 입력을 받아 공백 기준으로 배열화 시키는 부분을 지역함수로 구성
    fun readToArr() = br.readLine().split(' ').map{ it.toInt() }

    // n, m
    val input = readToArr()

    // index로 초기 값 설정
    val buckets = IntArray(input[0]+1){ it }
    fun swap(a: Int, b: Int, arr: IntArray) {
        val tmp = arr[a]
        arr[a] = arr[b]
        arr[b] = tmp
    }

    // m번 반복
    repeat(input[1]) {
        val c = readToArr()
        swap(c[0],c[1],buckets)
    }
    br.close()

    val sb = StringBuilder()
    for(i in 1 until buckets.size) {
        sb.append(buckets[i]).append(' ')
    }

    // 마지막 공백 제거
    sb.dropLast(1)
    print(sb.toString())
}