import java.util.*;

/*
최대한 많이 겹치는 곳에 설치하는 것이 유리
경우에 따라서는 적당한 곳에 나누는 것이 유리할 수 있음
들어오기 시작한 지점을 기준으로 sort 후 가장 늦게 들어온 차량부터 포함되게 한다.
*/

class Solution {

    class Pair{
        int start;
        int end;
        public Pair(int s, int e){ start = s; end = e; }
    }

    public int solution(int[][] routes) {

        ArrayList<Pair> info = new ArrayList();
        for(int[] arr: routes) info.add(new Pair(arr[0],arr[1]));

        // 시작이 느린 것이 뒤로 가게 한다.
        info.sort((a,b) -> { return a.start - b.start; });

        var stack = new Stack<Integer>();
        stack.push( info.get(info.size()-1).start );
        for(int i = info.size()-1; i >= 0; i--){
            // stack의 top에 cctv의 위차가 있다. -> 포함되지 않으면 시작점을 넣는다.
            int cctv = stack.peek();
            Pair now = info.get(i);
            if( now.start <= cctv && now.end >= cctv ) continue;
            stack.push(now.start);
        }

        return stack.size();
    }
}