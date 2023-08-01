import java.math.BigDecimal


fun main() {

    val gradeTable = hashMapOf<String, Int>().apply {
        put("A+", 45)
        put("A0", 40)
        put("B+", 35)
        put("B0", 30)
        put("C+", 25)
        put("C0", 20)
        put("D+", 15)
        put("D0", 10)
        put("F", 0)
    }

    var totalGrade = 0
    var totalhak = 0

    System.`in`.bufferedReader().use { br ->
        repeat(20) {
            br.readLine().split(' ').let {
                val grade = gradeTable[it[2]] ?: return@let
                val hak = it[1][0].digitToInt()

                totalGrade += grade*hak
                totalhak += hak
            }
        }
    }
    print(totalGrade.toDouble()/totalhak/10)
}