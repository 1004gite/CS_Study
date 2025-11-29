
fun main() {
    val list = readln().split(" ").map { it.toLong() }
    val a = (list[0]+list[3])
    val b = (list[1]+list[4])
    val c = list[2]+list[5]
    fun check(x: Long, y: Long): Boolean = list[0]*x + list[1]*y == list[2] && list[3]*x + list[4]*y == list[5]

    for(x in -999L..999L) for(y in -999L..999L) {
        if(check(x,y)) {
            print("$x $y")
            return
        }
    }
}