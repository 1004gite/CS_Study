class PP_pro17276 {

    fun solution(N: Int, number: Int): Int {

        var dp = Array(9){ hashSetOf<Int>() }
        var tmp = N
        repeat(8){
            dp[it+1].add(tmp)
            tmp = tmp*10 + N
        }

        for(count in 1..8){

            for( up in count-1 downTo (count+1)/2 ){
                val down = count-up
                for( a in dp[up] ){
                    for(b in dp[down]){
                        dp[count].add(a+b)
                        dp[count].add(a*b)
                        if(b != 0) dp[count].add(a/b)
                        if(a != 0) dp[count].add(b/a)
                        dp[count].add(a-b)
                        dp[count].add(b-a)
                    }
                }
            }
            if(dp[count].contains(number)) return count
        }

        return -1
    }

}