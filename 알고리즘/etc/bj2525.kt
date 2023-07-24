import java.util.*

class Time(var h: Int, var m: Int) {

    fun printTime() = println("$h $m")
    fun addH(value: Int){
        h += value 
        h %= 24
    }
    fun addM(value: Int) {
        m += value
        addH(m/60)
        m %= 60
    }
}

fun main(){
    val start = readln().split(' ').map{ it.toInt() }
    val spend = readln().toInt()
    val time = Time(start[0], start[1])

    time.addM(spend)
    time.printTime()
}