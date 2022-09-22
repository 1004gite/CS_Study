import java.io.BufferedWriter
import java.io.OutputStreamWriter

/**
 * 단어를 뒤집어야 한다.
 * 단어는 공백 또는 <, > 로 구분할 수 있다.
 * <> 안에 들어가 있으면 뒤집지 않는다.
 * // 문자열 관련은 앵간하면 StringBuilder가 가장 빨랐다.
 * */
class bj17413 {

    var str = ""
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var index = 0

    fun solve(){
        str = readln()

        while(true){
            if(index >= str.length) break
            if(str[index] == '<') writeTag()
            else if(str[index] == ' '){
                bw.write(" ")
                index++
                writeReverse()
            }
            else writeReverse()
        }
        bw.flush()
    }

    fun writeReverse(){
        // 띄어쓰기 전까지 혹은 < 전까지 쓴다.
//        val sb = java.lang.StringBuilder()
        var tmpIndex = index
        while(true){
            if(tmpIndex >= str.length) break
            if(str[tmpIndex] == ' ' || str[tmpIndex] == '<') break
//            sb.append(str[index])
            tmpIndex++
        }
        bw.write(str.substring(index,tmpIndex).reversed())
        index = tmpIndex
    }

    fun writeTag(){
//        val sb = java.lang.StringBuilder()
        var tmpIndex = index
        while(true){
            if(tmpIndex >= str.length) break
//            sb.append(str[index])
            if(str[tmpIndex] == '>') break
            tmpIndex++
        }
        tmpIndex++
        bw.write(str.substring(index,tmpIndex))
        index = tmpIndex
    }
}

fun main(){
    bj17413().solve()
}