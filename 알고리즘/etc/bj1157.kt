fun main() {

    val count = IntArray('z'-'a'+1)

    readln().forEach {
        count[it.lowercaseChar()-'a']++
    }

    val max = count.maxOf { it }
    print(if(count.count { it == max } > 1) '?' else (count.indexOf(max) + 'A'.code).toChar())
}