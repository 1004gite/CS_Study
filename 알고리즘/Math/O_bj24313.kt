package Math

fun main() {
    val (a1, a0) = readln().split(" ").map { it.toInt() }
    val c = readln().toInt()
    val n0 = readln().toInt()

    if (c < a1 || (c - a1) * n0 < a0) print(0)
    else print(1)
}