/**
 *
 * */

fun main() {
    var (n,k) = readln().split(' ').map { it.toInt() }
    var result = 1

    for(i in 1..n) {
        if(n%i == 0) k--
        if(k == 0) {
            result = i
            break
        }
    }

    print(if(k ==0) result else 0)
}