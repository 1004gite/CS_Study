import java.util.*
import kotlin.math.*

class Solution {

    /*
    * y 좌표를 내림차순으로 정렬하여 부모 node 가 먼저 오게 한다.
    * x 좌표를 기준으로 tree 를 구현한다.
    * 정렬하지 않고 pq를 사용한다.
    * 2진 트리이기 때문에 좌,우 하나의 child 만 가질 수 있다.
    *    -> 가장 왼쪽 node 를 먼저 상위 level 의 가장 왼쪽 node 의 left 가 되도록 한다.
    * 이때 부모 node 의 후보가 여러개가 될 수있는데 x값이 더 가까운 쪽에 붙여야 문제가 없다.
    *
    * 틀림 -> 조상의 좌표에 의해 subtree 조건이 맞지 않는 경우가 생기는 것 같음
    * -> 자식을 가질 수 있는 범위 정보를 가지고 다닌다.
    * -> ex) 부모의 right라면 해당 node는 부모의 x보다 작은 범위에 자식을 가질 수 없음
    *   -> 또한 부모가 자식을 가질 수 있는 범위도 넘을 수 없음
    * */

    data class Node(var x: Int, var y: Int, var num: Int, var right: Node?, var left: Node?, var parent: Node? , var minX: Int, var maxX: Int)
    val answer = Array(2){ mutableListOf<Int>() }

    fun solution(nodeinfo: Array<IntArray>): Array<IntArray> {
        val pq = PriorityQueue<Node>{ a,b ->
            if(a.y == b.y) a.x - b.x// x좌표가 작은것이 앞에 오게한다.
            else{
                b.y - a.y
            }
        }
        for( i in nodeinfo.indices){
            val now = nodeinfo[i]
            pq.add(Node(now[0],now[1],i+1,null,null,null, 0, 100000))
        }

        // tree 생성
        val root = pq.remove()
//        val candidate = mutableListOf<Node>().apply { add(root) }// 자식을 받을 수 있는 애들
        val candidate = LinkedList<Node>().apply { add(root) }
        while(true){
            if(pq.isEmpty()) break

            val now = pq.remove()
            var parent = candidate.first
            for( tmp in candidate){
                if( tmp.y <= now.y ) continue
                if( tmp.minX > now.x || tmp.maxX < now.x ) continue
                if( tmp.right != null && tmp.x < now.x) continue
                if( tmp.left != null && tmp.x > now.x ) continue

                parent = tmp
            }

            // 결정한 parent로 설정 후 parent의 자식이 꽉 차면 후보에서 제외
            now.parent = parent
            if(parent.x > now.x){
                parent.left = now
                now.maxX = parent.x -1
                now.minX = parent.minX
            }
            else{
                parent.right = now
                now.minX = parent.x+1
                now.maxX = parent.maxX
            }
            if( parent.right != null && parent.left != null ) candidate.remove(parent)

            // now도 후보에 등록
            candidate.add(now)
        }

        dfs_pre(root)
        dfs_post(root)

        return Array(2){ answer[it].toIntArray() }
    }

    fun dfs_pre(node: Node){
        answer[0].add(node.num)
        node.left?.let { dfs_pre(it) }
        node.right?.let{ dfs_pre(it) }
    }

    fun dfs_post(node: Node){
        node.left?.let { dfs_post(it) }
        node.right?.let{ dfs_post(it) }
        answer[1].add(node.num)
    }
}