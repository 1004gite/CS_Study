import java.util.StringTokenizer
import kotlin.math.*

class bj1062 {
    val Char.cToIndex: Int get() = this.code-97
    val Int.indexToC: Char get() = (this+97).toChar()
    //'a','n','t','i','c'
    val learned = BooleanArray(26).apply {
        this['a'.cToIndex] = true
        this['n'.cToIndex] = true
        this['t'.cToIndex] = true
        this['i'.cToIndex] = true
        this['c'.cToIndex] = true
    }
    val candidate = BooleanArray(26)
    lateinit var words: Array<String>
    var max = 0


    fun solve(){
        val br = System.`in`.bufferedReader()
        val st = StringTokenizer(br.readLine())
        val n = st.nextToken().toInt()
        val k = st.nextToken().toInt() - 5

        if(k < 0){
            println(0)
            return
        }
        words = Array(n){ initString(br.readLine())}
        var tmp = 0
        candidate.forEach { if(it) tmp++ }
        if(tmp <= k){
            println(n)
            return
        }

        dfs(k,0)
        println(max)
    }

    fun initString(str: String): String{
        for(i in 4 until str.length-4){
            if(learned[str[i].cToIndex]) continue
            candidate[str[i].cToIndex] = true
        }
        return str
    }

    fun dfs(remain: Int, index: Int){
//        println("$remain , $index")
        if(remain == 0){
//            for(i in learned.indices){
//                if(learned[i]) print("${i.indexToC} ")
//            }
//            println()
            // 완성할 수 있는 단어 파악
            var count = 0
            for(w in words){
                if(checkWord(w)) count++
            }
            max = max(max,count)
            return
        }

        for(i in index until 26){
            if(!candidate[i]) continue
            candidate[i] = false
            learned[i] = true
            dfs(remain-1, i+1)
            candidate[i] = true
            learned[i] = false
        }
    }

    fun checkWord(str: String): Boolean{
        for(i in 4 until str.length-4){
            if(!learned[str[i].cToIndex]) return false
        }
        return true
    }
}

fun main(){
    bj1062().solve()
}