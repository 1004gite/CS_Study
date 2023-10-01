/**
 * 1. 세 점이 주어진다.
 * 2. 한 점을 추가하여 축에 평행한 직사각형으로 만든다.
 * 3. x축, y축은 각각 2개씩 동일한 숫자가 있어야 한다.
 * 4. map을 이용해 2개씩 있는지 체크
 * */

fun main() {

    val xMap = HashMap<String, Int>()
    val yMap = HashMap<String, Int>()
    System.`in`.bufferedReader().use { br ->
        repeat(3) {
            br.readLine().split(' ').let {
                xMap[it[0]] = (xMap[it[0]] ?: 0) + 1
                yMap[it[1]] = (yMap[it[1]] ?: 0) + 1
            }
        }
    }
    for(en in xMap) {
        if(en.value == 1) {
            print("${en.key} ")
            break
        }
    }
    for(en in yMap) {
        if(en.value == 1) {
            print("${en.key}\n")
            break
        }
    }
}