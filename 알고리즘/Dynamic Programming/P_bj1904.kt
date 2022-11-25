class bj1904 {
}
/**
 * 1, 00 두종류의 숫자만 사용할 수 있다.
 * 앞에 0이 연속해서 오면 숫자가 중복될 수 있다. -> 2진 수열이기 때문에 중복이 아니다.
 * n이 최대 1000 000으로 1칸/2칸 으로 모든 경우의 수를 따지는 것은 불가능하다.
 * dp를 사용한다
 *  1을 추가로 사용할 때 이전 경우들의 끝에 1을 붙인다고 생각한다.
 * */
fun main(){
    val n = System.`in`.bufferedReader().readLine().toInt()
    if(n == 1){
        println(1)
        return
    }
    var a = 1
    var b = 2
    for(i in 3..n){
        val tmp = (a+b)%15746
        a = b
        b = tmp
    }

    println(b)

}