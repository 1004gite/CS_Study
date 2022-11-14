import java.util.*;

class Solution {

    // 모든 출발점에 대해 dp를 기록한다.
    // dp에는 각 지점까지 갈 수 있는 최소 ins를 저장한다.
    // 최대지점에 도착하면 멈춘다.(그대로 다시 돌아온다고 생각한다.)
    // 다른 출구로 가지 못한다.
    
    /*
    1. 다익스트라 이용
    2. 가장 처음 업데이트가 가장 최단경로이게 한 후 업데이트 되어있다면 고려하지 않는 방법이 제일 효율적임
    */

    class Edge{
        int n; int cost;
        public Edge(int n, int cost){
            this.n = n; this.cost = cost;
        }
    }
    class Node{
        boolean isSummit = false;
        ArrayList<Edge> near = new ArrayList<Edge>();
    }

    Node[] nodes;
    int minInten = Integer.MAX_VALUE;
    int minSummit = Integer.MAX_VALUE;
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

        nodes = getNodes(n, paths);
        for(int s: summits) nodes[s].isSummit = true;

        int[] dp = new int[n+1];
        for(int i = 1; i < n+1; i++) dp[i] = Integer.MAX_VALUE;
        // 도착지, 예상 비용
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a,b)->{return a[1]-b[1];});
        for(int g: gates){
            dp[g] = 0;
            for(Edge edge: nodes[g].near){
                queue.add(new int[] {edge.n, edge.cost});
            }
        }
        while(true){
            if(queue.isEmpty()) break;
            int[] now = queue.remove();
            if(minInten < now[1]) break;
            if(dp[now[0]] < Integer.MAX_VALUE) continue;
            dp[now[0]] = now[1];
            if(nodes[now[0]].isSummit){
                if( minInten == dp[now[0]] ) {
                    if (now[0] < minSummit) minSummit = now[0];
                }
                else{
                    // 봉우리이면서 더 작음
                    minSummit = now[0];
                    minInten = dp[now[0]];
                }
                continue;
            }
            for(Edge edge: nodes[now[0]].near){
                // next은 다음에 갈 node의 번호이다.
                // max는 이번 경로로 갈 때 업데이트 될 값이다.
                int next = edge.n;
                if(dp[next] < Integer.MAX_VALUE) continue;
                int max = Math.max(dp[now[0]], edge.cost);
                queue.add(new int[] {next, max});
            }
        }

        int[] answer = {minSummit,minInten};
        return answer;
    }

    Node[] getNodes(int n, int[][] paths){
        Node[] nodes = new Node[n+1];
        for(int i = 0; i < nodes.length; i++) nodes[i] = new Node();
        for(int[] path: paths) {
            int a = path[0];
            int b = path[1];
            int cost = path[2];
            nodes[a].near.add(new Edge(b, cost));
            nodes[b].near.add(new Edge(a, cost));
        }
        return nodes;
    }
}