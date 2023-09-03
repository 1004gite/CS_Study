import java.util.PriorityQueue

data class MyNum(val value: Int, var valid: Boolean = true)

// 유효한 MyNum을 지우는 확장함수
// 유효하지 않은 MyNum은 제거한다.
// 제거한 값을 return한다.
// 비어있다면 null을 return한다.
fun PriorityQueue<MyNum>.myRemove(): MyNum? {
    while ( true ) {
        if(this.isEmpty()) return null
        val top = this.remove()
        if(top.valid) return top
    }
}

fun main() {

    val br = System.`in`.bufferedReader()
    val sb = StringBuilder()

    // testCase의 개수만큼 반복
    repeat(br.readLine().toInt()) {


        // 최소 최대 heap 선언
        val maxHeap = PriorityQueue<MyNum>{ a,b -> b.value - a.value }
        val minHeap = PriorityQueue<MyNum>{ a,b -> a.value - b.value }

        // 명령의 개수만큼 반복
        repeat(br.readLine().toInt()) {

            val tmp = br.readLine().split(' ')
            val command = tmp[0][0]
            val num = tmp[1].toInt()

            if(command == 'I') {
                // 두 힙에 같은 객체를 모두 삽입
                val myNum = MyNum(num)
                maxHeap.add(myNum)
                minHeap.add(myNum)
            } else if(num < 0) {
                // 최솟갑 삭제 및 유효성 false로 설정
                minHeap.myRemove()?.valid = false
            } else {
                maxHeap.myRemove()?.valid = false
            }

        }

        val max = maxHeap.myRemove()
        val min = minHeap.myRemove()

        if(max == null)  sb.append("EMPTY\n")
        else sb.append("${max?.value} ${min?.value}\n")
    }
    br.close()
    print(sb.toString())
}