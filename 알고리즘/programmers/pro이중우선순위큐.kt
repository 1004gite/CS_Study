import java.util.*

// 역시 heap 2개를 사용하는 풀이보다 2분탐색을 이용한 풀이가 더 빨랐다. (1초이하, 1~2초)

class Solution {

    // 작은거 ~ 큰거
    val myHeap = LinkedList<Int>()

    fun solution(operations: Array<String>): IntArray {

        for(str in operations){
            with(StringTokenizer(str," ")){
                val command = this.nextToken()
                val ele = this.nextToken().toInt()
                if(command == "I") insertMyHeap(ele)
                else myRemove(ele)
            }
        }

        var answer = intArrayOf(0,0)
        if(myHeap.isNotEmpty()){
            answer[0] = myHeap.last
            answer[1] = myHeap.first
        }
        return answer
    }

    fun myRemove(num:Int){
        if(myHeap.isEmpty()) return
        if(num == 1) myHeap.removeLast()
        else myHeap.removeFirst()
    }

    fun insertMyHeap(ele: Int){

        if(myHeap.isEmpty()) {
            myHeap.addLast(ele)
            return
        }
        if(myHeap.size == 1){
            if(myHeap[0] > ele) myHeap.addFirst(ele) else myHeap.addLast(ele)
            return
        }

        var start = 0
        var mid = myHeap.size/2
        var end = myHeap.size-1

        while(true){

            if(mid > end){
                myHeap.addLast(ele)
                break
            }
            if(mid < start){
                myHeap.addFirst(ele)
                break
            }
            if(ele > myHeap[mid]) {
                start = mid+1
                mid = start+(end-start+1)/2
            }
            else if(ele < myHeap[mid]){
                end = mid-1
                mid = start+(end-start+1)/2
            }
            else {
                myHeap.add(mid, ele)
                break
            }

        }
    }

    data class element(var ele: Int, var removed: Boolean = false)

    fun solution2(operations: Array<String>): IntArray {

        var minHeap = PriorityQueue<element>{ o1,o2 -> o1.ele-o2.ele }
        var maxHeap = PriorityQueue<element>{o1,o2 -> o2.ele-o1.ele}

        operations.forEach { str ->
            with(StringTokenizer(str, " ")){
                val command = this.nextToken()
                val ele = this.nextToken().toInt()
                if(command == "I"){
                    var tmp = element(ele)
                    minHeap.add(tmp)
                    maxHeap.add(tmp)
                }
                else if(ele == 1){
                    while(true){
                        if(maxHeap.isEmpty()) break
                        val now = maxHeap.remove()
                        if(!now.removed) {
                            now.removed = true
                            break
                        }
                    }
                }
                else{
                    while(true){
                        if(minHeap.isEmpty()) break
                        val now = minHeap.remove()
                        if(!now.removed) {
                            now.removed = true
                            break
                        }
                    }
                }
            }
        }

        while(true){
            if(maxHeap.isEmpty() || !maxHeap.element().removed) break
            maxHeap.remove()
        }
        while(true){
            if(minHeap.isEmpty() || !minHeap.element().removed) break
            minHeap.remove()
        }

        return if(maxHeap.isEmpty()) intArrayOf(0,0) else intArrayOf(maxHeap.element().ele, minHeap.element().ele)
    }

    }