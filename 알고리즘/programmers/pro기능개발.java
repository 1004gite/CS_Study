import java.util.*;

class Solution {
    class pair {
        public pair(int p, int s){
            this.progress = p;
            this.speed = s;
        }
        int progress;
        int speed;

        public void update(){
            if(progress < 100) progress += speed;
        }
    }
    public int[] solution(int[] progresses, int[] speeds) {

        var queue = new LinkedList<pair>();
        for(int i =0; i < progresses.length; i++){
            queue.addLast(new pair(progresses[i],speeds[i]));
        }

        var result = new ArrayList<Integer>();
        var t = 0;
        while(true){
            if(queue.isEmpty()) break;

            for(int i =0; i < queue.size(); i++){
                var tmp = queue.getFirst();
                queue.removeFirst();
                tmp.update();
                queue.addLast(tmp);
            }
            
            int count = 0;
            while(true){
                if(queue.isEmpty()) break;
                var now = queue.getFirst();
                if(now.progress < 100) break;
                queue.removeFirst();
                count++;
            }
            if(count > 0 ) result.add(count);
            
        }

        int[] answer = new int[result.size()];
        for(int i =0; i < result.size(); i++){
            answer[i] = result.get(i);
        }
        return answer;
    }
}