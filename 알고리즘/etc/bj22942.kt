import java.util.LinkedList

fun main(){

    data class CircleInfo(val circleNum: Int = -1, val isStart: Boolean = false)

    fun getCircleInfos(): Array<CircleInfo>? {
        val br = System.`in`.bufferedReader()
        val n = br.readLine().toInt()
        // 같은 x좌표 검사 및 시작점, 끝점으로 정보 변환
        // 만약 내접, 외접 케이스가 있다면 null을 반환
        return Array(2020000){ CircleInfo() }.apply {
            for(i in 0 until n) {
                val info = br.readLine().split(' ').map { it.toInt() }
                // -좌표는 나타낼 수 없으므로 100만씩 더해서 표현한다.
                // 반지름도 고려하여 10000을 추가로 더한다.
                val start = info[0]-info[1] + 1010000
                val end = info[0]+info[1] + 1010000
                if(this[start].circleNum != -1) return null
                if(this[end].circleNum != -1) return null

                this[start] = CircleInfo(i, true)
                this[end] = CircleInfo(i, false)
            }
        }
    }

    // 시작, 끝점으로 변환된 배열을 받는다.
    // 외접, 내접의 경우가 있어 null이 반환된 경우 "No"를 출력하고 종료한다.
    val circleInfos = getCircleInfos() ?: run {
        println("NO")
        return
    }

    val stack = LinkedList<CircleInfo>()
    for( cInfo in circleInfos ) {
        if(cInfo.circleNum == -1) continue
        if(cInfo.isStart) {
            stack.addLast(cInfo)
        } else {
            val top = stack.removeLast()
            // 가장 최근에 열린 원과 매칭되지 않는 경우 교점이 2개 생긴 경우임
            if(top.circleNum != cInfo.circleNum) {
                println("NO")
                return
            }
            // 매칭되는 경우 계속 진행
        }
    }

    // 모든 검사를 통과한 경우 YES
    println("YES")
}