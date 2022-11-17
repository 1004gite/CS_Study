package String

class bj12904 {
}

fun main(){
    /*
    * s2의 끝이 A이면 A를 뺄 수 있다
    * s2의 끝이 B이면 B를 빼고 뒤집을 수 있다.
    * // StringBuilder.drop은 drop된 charSequence를 return하고 sb의 내용이 바뀌진 않는다.
    * */
    val s1 = readln()
    val s2 = StringBuilder(readln())

    while(s2.length > s1.length){
        if(s2.last() == 'A') s2.deleteCharAt(s2.lastIndex)
        else{
            s2.deleteCharAt(s2.lastIndex)
            s2.reverse()
        }
    }

    if(s1 == s2.toString()) println(1)
    else println(0)

}