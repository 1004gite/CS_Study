class bj2941 {
}

/*
č	c=
ć	c-
dž	dz=
đ	d-
lj	lj
nj	nj
š	s=
ž	z=
* */

fun main(){

    val br = System.`in`.bufferedReader()
    val str = br.readLine()
    var result = 0

    var i = 0
    while(true){
        if(i == str.length-1) {
            result++
            break
        }
        else if(i >= str.length) break
        when(str[i]){
            'c'->{ if(str[i+1] == '=' || str[i+1] == '-' ) i++ }
            'd'->{
                if(str[i+1] == '-') i++
                else if(i < str.length-2 && str[i+1] == 'z' && str[i+2] == '=') i += 2
            }
            'l'->{ if(str[i+1] == 'j') i++ }
            'n'->{ if(str[i+1] == 'j') i++ }
            's'->{ if(str[i+1] == '=') i++ }
            'z'->{ if(str[i+1] == '=') i++ }
            else ->{}
        }

        result++
        i++
    }
    println(result)

}