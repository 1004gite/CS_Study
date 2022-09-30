import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;

        var minHeap = new PriorityQueue<Integer>((o1,o2) -> { return o1-o2; });
        for(int i: scoville) minHeap.add(i);

        while(true){
            if(minHeap.isEmpty() || (minHeap.size() < 2 && minHeap.peek() < K)){
                answer = -1;
                break;
            }
            if(minHeap.peek() >= K) break;

            minHeap.add( minHeap.poll() + minHeap.poll()*2 );
            answer++;
        }

        return answer;
    }
}