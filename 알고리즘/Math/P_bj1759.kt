import java.util.StringTokenizer

class bj1759 {
}

/**
 * 암호는 서로 다른 L개의 소문자로 구성되어 있다.
 * 최소 한개의 모음(aeiou)가 들어있다.
 * 최소 2개의 자음이 들어있다.(^aeiou)
 * 사전순으로 증가하는 순서이다.
 * c개의 알파벳 목록이 주어졌을 때 가능한 암호의 경우를 모두 구하라
 *
 * 1. 사전순으로 정렬한다.
 * 2. 순서는 정해져 있기 때문에 c개중 L개를 뽑는다.
 *
 * */

val check = BooleanArray(26).apply {
    this['a'.code-'a'.code] = true
    this['e'.code-'a'.code] = true
    this['i'.code-'a'.code] = true
    this['o'.code-'a'.code] = true
    this['u'.code-'a'.code] = true
}

val resultSb = StringBuilder()
var maxLen = 0
fun dfs(sb: StringBuilder,index: Int, list: CharArray){
    sb.append(list[index])
    if(sb.length == maxLen){
        val str = sb.toString()
        sb.deleteCharAt(sb.lastIndex)
        var c1 = 0
        var c2 = 0
        for(c in str){
            if(check[c.code-'a'.code]) c1++
            else c2++
        }
        if(c1 < 1 || c2 < 2) return
        resultSb.append(str).append('\n')
        return
    }

    for(i in index+1 until list.size){
        dfs(sb,i,list)
    }
    sb.deleteCharAt(sb.lastIndex)
}

fun main(){

    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    maxLen = st.nextToken().toInt()
    val C = st.nextToken().toInt()

    st = StringTokenizer(br.readLine())
    val alphabets = CharArray(C){ st.nextToken()[0] }
    alphabets.sort()

    val sb = StringBuilder()
    for(i in 0..C-maxLen) dfs(sb, i, alphabets)

    print(resultSb)
}