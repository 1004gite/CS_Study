package String

fun main() {

    fun solve1() { // Brute force - 4860ms
        val br = System.`in`.bufferedReader()

        var n = 0
        var m = 0
        br.readLine().split(' ').let {
            n = it[0].toInt()
            m = it[1].toInt()
        }

        val s = Array<String>(n){ br.readLine() }


        var result = 0
        repeat(m){
            val str = br.readLine()
            for(tmp in s) {
                if(tmp == str) {
                    result++
                    break
                }
            }
        }
        br.close()
        print(result)
    }

    fun solve2() { // binary search - 392 ms
        val br = System.`in`.bufferedReader()

        var n = 0
        var m = 0
        br.readLine().split(' ').let {
            n = it[0].toInt()
            m = it[1].toInt()
        }

        val s = Array<String>(n){ br.readLine() }.apply {
            sort()
        }


        var result = 0
        repeat(m){
            val str = br.readLine()
            if(0 <= s.binarySearch(str)) {
                result++
            }
        }

        br.close()
        print(result)
    }

    fun solve3() { // hashMap - 356ms
        val br = System.`in`.bufferedReader()

        var n = 0
        var m = 0
        br.readLine().split(' ').let {
            n = it[0].toInt()
            m = it[1].toInt()
        }

        val s = HashMap<String, Int>().apply {
            repeat(n) {
                this[br.readLine()] = 1
            }
        }


        var result = 0
        repeat(m){
            val str = br.readLine()
            if(s.containsKey(str)) {
                result++
            }
        }

        br.close()
        print(result)
    }


//    solve1()
//    solve2()
    solve3()
}

class TestComparable: Compa