class bj10994 {

    // val bw = BufferedWriter(OutputStreamWriter(System.out))
    var num = 0
    var len = 0
    val stringBuilder = java.lang.StringBuilder()

    fun solve(){
        num = readln().toInt()
        len = (num-1)*4 + 1

        for(i in 0 until num-1){
            draw(i,star)
            draw(i+1,space)
        }
        draw(num-1, star)
        for(i in num-2 downTo 0){
            draw(i+1,space)
            draw(i,star)
        }

        //bw.flush()
        print(stringBuilder)
    }

    fun draw(depth: Int, drawFun: (Int)->Unit){
        repeat(depth){
            star(1)
            space(1)
        }
        drawFun(len-depth*4)
        repeat(depth){
            space(1)
            star(1)
        }
        nextLine()
    }

    var space: (Int) -> Unit = {i -> repeat(i){
        //bw.write(" ")
        stringBuilder.append(" ")
    } }
    var star: (Int) -> Unit = {i -> repeat(i){
        //    bw.write("*")
        stringBuilder.append("*")
    } }
    var nextLine:() -> Unit = {stringBuilder.append("\n")}
    //{bw.write("\n")}
}

fun main(){
    bj10994().solve()
}