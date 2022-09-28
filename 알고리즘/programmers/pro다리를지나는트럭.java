import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {

        int truckIndex = 0;
        int sum = 0;
        var bridge = new LinkedList<Integer>();
        for(int i=0; i < bridge_length;i++) bridge.addLast(0);

        int answer = 0;
        while(true){
            if(truckIndex == truck_weights.length) break;
            answer++;
            sum -= bridge.getFirst();
            bridge.removeFirst();
            if(sum + truck_weights[truckIndex] <= weight){
                bridge.addLast(truck_weights[truckIndex]);
                sum += truck_weights[truckIndex];
                truckIndex++;
            }
            else bridge.addLast(0);
        }

        return answer+bridge_length;
    }
}