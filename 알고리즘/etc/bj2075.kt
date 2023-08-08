package etc

import java.util.LinkedList
import java.util.PriorityQueue

fun main() {
//    pqBruteForce()
//    pqSolve()
//    sortBruteForce()
//    mergeSort()
}

/**
 * mergeSort의 중간 단계라고 생각하고 merge Sort를 구현한다.
 * */
// 1176ms
fun mergeSort() {

    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()

    val arr = Array(n){ IntArray(n) }

    // 각 배열을 열별로 받는다.
    // 각 배열은 오름차순으로 정렬된 상태이다.
    for(i in 0 until n) {
        val tmpArr = br.readLine().split(' ').map { it.toInt() }
        for(j in 0 until n) {
            arr[j][i] = tmpArr[j]
        }
    }
    br.close()

    // queue의 크기가 1이 될떄까지 정렬을 유지하며 합친다.
    val q = LinkedList<IntArray>().apply {
        addAll(arr)
    }

    fun getMergedArr(a: IntArray, b: IntArray): IntArray {
        // 두 배열을 합치는 함수
        // 이때 정렬된 상태로 return한다.
        var indexA = 0
        var indexB = 0
        return IntArray(a.size + b.size) {
            if(indexA == a.size) b[indexB++]
            else if(indexB == b.size ) a[indexA++]
            else if(a[indexA] < b[indexB]) a[indexA++]
            else b[indexB++]
        }

    }

    while(q.size > 1) {
        val a = q.removeFirst()
        val b = q.removeFirst()

        q.addLast(getMergedArr(a,b))
    }

    print("${q.last[n*n-n]}")
}


/**
 * 내장 sort 함수를 이용해 sort 후 n번째로 큰 원소룰 출력한다.
 * */
// 1492ms
fun sortBruteForce() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()
    val arr = IntArray(n*n)

    for(i in 0 until n) {
        val tmpArr = br.readLine().split(' ').map { it.toInt() }
        for(j in 0 until n) {
            arr[n*i + j] = tmpArr[j]
        }
    }

    arr.sortDescending()

    print("${arr[n-1]}")
}


/**
 * MaxHeap에 모든 원소를 넣고 N번 뽑아낸다. (sort와 논리적으로 같다.)
 * */
// 1484ms
fun pqBruteForce() {

    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()

    // 작은 수가 top에 오게 함 (minHeap)
    val pq = PriorityQueue<Int> { a,b -> b-a }

    repeat(n) {
        val arr = br.readLine().split(' ').map { it.toInt() }
        arr.forEach { pq.add(it) }
    }
    br.close()

    repeat(n-1) {
        pq.poll()
    }

    print("${pq.peek()}")

}


/**
 * 큰 수들의 모임이라는 의미로 MinHeap을 만든다.
 * MinHeap의 크기는 n개로 크기를 제한한다.
 * 만약 더이상 넣을 수 없다면 가장 작은 것과 비교 후 큰것을 넣는다.
 * */
// 1372ms
fun pqSolve() {

    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()

    // 작은 수가 top에 오게 함 (minHeap)
    val pq = PriorityQueue<Int> { a,b -> a-b }
    fun PriorityQueue<Int>.pushWithMax(num: Int) {
        if(pq.size < n) {
            this.add(num)
        }
        else if( pq.peek() < num){
            this.poll()
            this.add(num)
        }
    }

    repeat(n) {
        val arr = br.readLine().split(' ').map { it.toInt() }
        arr.forEach { pq.pushWithMax(it) }
    }
    br.close()

    print("${pq.peek()}")

}