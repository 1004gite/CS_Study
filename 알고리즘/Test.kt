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
}