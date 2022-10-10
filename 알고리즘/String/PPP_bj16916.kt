//class bj16916{}
fun main(){
    val str = readln()
    val regex = readln()
    val failIndex = IntArray(regex.length)
    for(i in 1 until failIndex.size){
        var checkIndex = i-1
        while(true){
            if(regex[failIndex[checkIndex]] == regex[i]){
                failIndex[i] = failIndex[checkIndex]+1
                break
            }
            if(failIndex[checkIndex] == 0) break
            checkIndex = failIndex[failIndex[checkIndex]-1]
        }
    }

    var regexIndex = 0
    var strIndex = 0
    while(strIndex < str.length){
        while(regexIndex > 0){
            if(regex[regexIndex] != str[strIndex]) regexIndex = failIndex[regexIndex-1]
            else break
        }
        if(str[strIndex] == regex[regexIndex]){
            regexIndex++
            if(regexIndex == regex.length){
                println(1)
                return
            }
        }
        strIndex++
    }
    println(0)
}