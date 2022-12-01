class Test {
}

fun main(){
    val mutableList = mutableListOf<Int>().apply {
        add(1)
    }
    val hashSet = hashSetOf<Int>()
    hashSet.add(1)
    val mutableMap = mutableMapOf<String, Pair<Int,Int>>()
    mutableMap.put("mapTest", 1 to 2)

    val lazyVal: Pair<Int,Int> by lazy { 2 to 3 }

    // Pair의 주석을 읽어보면 비교시 모든 값이 같으면 true를 반환한다고 적혀 있음
    val setTest = mutableSetOf<Pair<Int,Int>>()
    setTest.add(1 to 2)
    setTest.add(1 to 2)
    println(setTest.size)
}