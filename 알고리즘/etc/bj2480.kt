fun main() {
    val dice = IntArray(7)

    readln().split(' ').forEach {
        dice[it.toInt()]++
    }

    var maxIndex = 1
    for(i in 2..6) {
        if(dice[i] >= dice[maxIndex]) maxIndex = i
    }

    val answer = when(dice[maxIndex]) {
        1 -> maxIndex * 100
        2 -> 1000 + maxIndex*100
        3 -> 10000 + maxIndex*1000
        else -> 0
    }

    println(answer)
}