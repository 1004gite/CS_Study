import kotlin.collections.ArrayDeque

class bj5430 {

    fun solve(){
        val br = System.`in`.bufferedReader()
        val bw = System.out.bufferedWriter()
        var t = br.readLine().toInt()

        while(t-- > 0){
            val command = br.readLine()
            val num = br.readLine().toInt()
            val dq = ArrayDeque<Int>()
            val arr = with(br.readLine()) { this.substring(1,this.length-1).split(",") }
            if(num != 0) arr.forEach { dq.addLast(it.toInt()) }

            var front = true
            var err = false
            for(c in command){
                if(c == 'R') front = !front
                else{
                    if(dq.isEmpty()){
                        err = true
                        break
                    }
                    if(front) dq.removeFirst()
                    else dq.removeLast()
                }
            }

            if(err) bw.write("error\n")
            else{
                bw.write("[")
                if(front){
                    for(i in 0 until dq.size-1){
                        bw.write("${dq[i]},")
                    }
                    if(dq.isNotEmpty()) bw.write("${dq.last()}")
                }
                else{
                    for(i in dq.size-1 downTo 1){
                        bw.write("${dq[i]},")
                    }
                    if(dq.isNotEmpty()) bw.write("${dq.first()}")
                }
                bw.write("]\n")
            }
        }
        br.close()
        bw.close()
    }

}

fun main(){
    bj5430().solve()
}