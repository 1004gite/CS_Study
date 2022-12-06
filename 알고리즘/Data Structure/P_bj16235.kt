import java.util.*

class bj16235 {
}

/**
 * n*n 크기의 땅이 있다.
 * 모든 칸에는 양분이 5 있다.
 * m개의 나무를 심었다.
 * 봄 - 자신의 나이만큼 양분을 먹고 나이가 1 증가한다. 이때, 같은 칸에 여러 나무가 있다면 나이가 어린 나무가 우선해서 양분을 먹는다.
 * 만약 양분이 부족하다면 죽는다.
 * 여름 - 봄에 죽은 나무가 양분으로 변한다. 죽은 나무의 나이/2 가 양분으로 들어간다.
 * 가을 - 나이가 5의 배수인 나무가 번식한다. 인접한 8개의 칸에 나무가 생긴다.
 * 겨울 - 각 칸에 입력으로 주어지는 만큼의 양분을 추가한다.(칸마다 입력이 다름)
 *
 * k년이 지난 후 살아남은 나무의 수를 출력한다.
 *
 * 풀이 1
 * 0. 기본적으로 시뮬레이션을 돌리는 방향으로 간다.
 * 1. 봄에 일어나는 동작이 시간이 가장 많이 걸릴 것 같다.
 * 2. linkedList를 이용한다.
 * */

data class Tree(var age: Int = 1, var next: Tree?, var pre: Tree?)
class Land{
    private var trees: Tree = Tree(-1,null,null)
    private var dead: Tree = Tree(-1,null,null)
    var energy = 5

    fun getTreeCount(): Int{
        var count = 0
        var now = trees.next
        while(true){
            if(now == null) break
            count++
            now = now.next
        }
        return count
    }

    fun insertTree(age: Int){

        var now = trees

        while(true){
            // 다음이 마지막이면 그냥 마지막에 넣는다.
            if(now.next == null) break
            // 다음 나무가 지금 넣으려는 나무보다 나이가 많으면 그 앞에 넣는다.
            if(now.next!!.age >= age) break

            now = now.next!!
        }

        // now의 next를 지금 삽입하는 나무로 만든다.
        val newTree = Tree(age,now.next,now)
        now.next?.pre = newTree
        now.next = newTree

    }

    fun addEnergy(n: Int){
        energy += n
    }

    fun getFiveCount(): Int{
        var count = 0
        var now = trees.next
        while(true){
            if(now == null) break
            if(now.age % 5 == 0) count++
            now = now.next
        }
        return count
    }

    fun addBaby(n: Int){
        val first = trees.next
        var baby = Tree(1,null,trees)
        trees.next = baby
        repeat(n-1){
            val newBaby = Tree(1,null,baby)
            baby.next = newBaby
            baby = newBaby
        }
        baby.next = first
        first?.pre = baby
    }

    fun summer(){
        var now = dead.next
        while(true){
            if( now == null) break
            energy += now.age/2
            now = now.next
        }
        dead.next = null
    }

    fun spring(){

        var now = trees.next

        while(true){
            if(now == null) break
            // 양분이 부족하다면
            if(now.age > energy){
                // now부터는 죽은 나무가 된다.
                now.pre!!.next = null
                dead.next = now
                break
            }
            // 양분이 충분하다면
            energy -= now.age
            now.age++
            now = now.next
        }
    }
}

fun main(){

    val arrR = arrayOf(-1,-1,-1,0,0,1,1,1)
    val arrC = arrayOf(-1,0,1,-1,1,-1,0,1)

    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    // 겨울마다 받을 양분
    val A = Array(n){
        st = StringTokenizer((br.readLine()))
        IntArray(n){ st.nextToken().toInt() }
    }

    val board = Array(n){Array<Land>(n){ Land() }}
    repeat(m){
        st = StringTokenizer(br.readLine())
        board[st.nextToken().toInt()-1][st.nextToken().toInt()-1].insertTree(st.nextToken().toInt())
    }

    repeat(k){
        for(r in 0 until n) for( c in 0 until n) board[r][c].spring()
        for(r in 0 until n) for( c in 0 until n) board[r][c].summer()
        for(r in 0 until n){
            for(c in 0 until n){
                val count = board[r][c].getFiveCount()
                if(count == 0 ) continue
                for(i in 0..7){
                    val nR = r+arrR[i]
                    val nC = c+arrC[i]
                    if(nR < 0 || nR >= n || nC < 0 || nC >= n ) continue
                    board[nR][nC].addBaby(count)
                }
            }
        }

        for(r in 0 until n) for( c in 0 until n) board[r][c].addEnergy(A[r][c])

//        // debug
//        for(r in 0 until n){
//            for( c in 0 until n){
////                print("${board[r][c].getTreeCount()} ")
//                print("${board[r][c].energy} ")
//            }
//            println()
//        }
//        println()

    }

    println(board.sumOf { it.sumOf { it.getTreeCount() } })
}