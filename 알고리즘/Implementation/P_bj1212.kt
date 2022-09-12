/**
 * 8진수를 2진수로 바꿔라
 * 8 == 2^3 이므로 8진수 1개를 2진수 3개로 바꾸어 표현하면 된다.
 * 만약 딱 떨어지지 않는 관계라면 중간에 10진수를 껴서 변환한다.
 * */
class bj1212 {

    fun solve(){
        val input = readln()
        var str = StringBuilder()

        for(c in input){
            str.append( eightToTwo(c.digitToInt()) )
        }

        var index = 0
        while(str[index] == '0' && index < str.length-1) index++

        println(str.substring(index))
    }

    fun eightToTwo(num: Int): String{
        val result = StringBuilder()
        var n = num

        repeat(3){
            result.append(n%2)
            n /= 2
        }

        return result.reverse().toString()
    }

    fun solve2(){
        val input = readln()
        val strBuilder = StringBuilder()
        for(c in input){
            strBuilder.append(hardCoding(c))
        }

        var index = 0
        while(strBuilder[index] == '0' && index < strBuilder.length-1) index++

        println(strBuilder.substring(index))
    }

    fun hardCoding(c: Char): String{
        return when(c){
            '0' -> "000"
            '1' -> "001"
            '2' -> "010"
            '3' -> "011"
            '4' -> "100"
            '5' -> "101"
            '6' -> "110"
            else -> "111"
        }
    }
}

fun main(){
    bj1212().solve()
}