/**
 * 세 각의 크기가 모두 60이면, Equilateral
 * 세 각의 합이 180이고, 두 각이 같은 경우에는 Isosceles
 * 세 각의 합이 180이고, 같은 각이 없는 경우에는 Scalene
 * 세 각의 합이 180이 아닌 경우에는 Error
 * */

fun main() {

    val hashMap = hashMapOf<Int, Int>()
    var sum = 0
    System.`in`.bufferedReader().use { br ->
        repeat(3) {
            val angle = br.readLine().toInt()
            sum += angle
            hashMap[angle] = 1
        }
    }

    if(sum != 180) {
        print("Error")
        return
    }
    when (hashMap.size){
        1 -> print("Equilateral")
        2 -> print("Isosceles")
        else -> print("Scalene")
    }
}