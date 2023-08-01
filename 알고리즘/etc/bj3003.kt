package etc

fun main() {
    val sb = StringBuilder()
    val answer = arrayOf(1,1,2,2,2)
    readln().split(' ').forEachIndexed { index, count ->
        val diff = count.toInt() - answer[index]
        sb.append("$diff ")
    }
    sb.dropLast(1)
    print(sb.toString())
}