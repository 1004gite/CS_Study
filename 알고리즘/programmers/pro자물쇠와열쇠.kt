import java.util.LinkedList

class Solution {
    /**
     * 열쇠로 자물쇠의 빈 곳을 딱 맞게 채우면 자물쇠를 열 수 있다.
     * 열쇠는 회전이 가능하다
     * 열쇠의 튀어나온 부분은 남아도 괜찮으나 자물쇠는 빈칸이 남아서는 안된다.
     * 열쇠를 돌려가며 4개의 열쇠를 만든다.
     * */
    fun solution(key: Array<IntArray>, lock: Array<IntArray>): Boolean {

        var mKey = key
        if(check(mKey, lock)) return true
        repeat(3){
            mKey = getRotatedKey(mKey)
            if(check(mKey, lock)) return true
        }

        return false
    }

    fun getRotatedKey(key: Array<IntArray>): Array<IntArray>{
        return Array(key[0].size){ r -> IntArray(key.size){ c ->
            // r,c -> (colSize-c-1),(r)
            key[key[0].size-c-1][r]
        } }
    }

    fun check(key: Array<IntArray>, lock: Array<IntArray>): Boolean{
        // r,c 는 lock에 key를 넣을 때 key의 오른쪽 상단 index와(0,0) 매칭된다.
        for(r in 0-key.size-1 until lock.size){
            for(c in 0-key[0].size-1 until lock[0].size){

                // 매칭했을 때 열쇠가 닿지 않는 lock 영역에 0이 있으면 불가능하다.
                // (r,c) ~ (r+keySize-1),(c+key[0]Size-1) 사이의 범위외의 범위
                var endTrigger = false
                for(lockR in 0 until lock.size){
                    if(endTrigger) break
                    for(lockC in 0 until lock[0].size){
                        if(endTrigger) break
                        if( lockR in r until r+key.size && lockC in c until c+key[0].size){
                            // lock과 key의 값이 다르면 통과
                            // key가 매칭되는 index는 lockR-r, lockC-c이다
                            if(lock[lockR][lockC] != key[lockR-r][lockC-c]) continue
                            endTrigger = true
                        }
                        else{
                            // lock이 0이면 탈락
                            if(lock[lockR][lockC] == 1) continue
                            endTrigger = true
                        }
                    }
                }

                if(!endTrigger) return true
            }
        }

        return false
    }
}