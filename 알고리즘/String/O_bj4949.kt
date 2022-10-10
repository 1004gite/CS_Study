import java.util.*
//class bj4949{}
fun main(){

    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var str = br.readLine()

    while(str != "."){
        var stack = Stack<Char>()
        var check = true
        for(c in str){
            if(c == '(' || c == '[') stack.add(c)
            else if(c == ')'){
                if(stack.isEmpty() || stack.pop() != '('){
                    check = false
                    break
                }
            }
            else if(c == ']'){
                if(stack.isEmpty() || stack.pop() != '[') {
                    check = false
                    break
                }
            }
        }
        if(check && stack.isEmpty()) bw.write("yes\n")
        else bw.write("no\n")
        str = br.readLine()
    }

    bw.flush()
}