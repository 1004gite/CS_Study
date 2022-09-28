import java.util.*;

class Solution {
    class pair{
        int index;
        int pri;
        pair(int i, int p){
            index = i;
            pri = p;
        }
    }
    public int solution(int[] priorities, int location) {

        var queue = new LinkedList<pair>();

        for(int i=0; i<priorities.length; i++) queue.addLast(new pair(i,priorities[i]));

        int count = 0;
        while(true){
            if(queue.isEmpty()) break;
            int max = -1;
            for(pair p: queue) if(p.pri > max) max = p.pri; 

            var tmpQ = new LinkedList<pair>();
            while(true){
                var now = queue.getFirst();

                if(now.pri == max) break;
                queue.removeFirst();
                tmpQ.addLast(now);
            }

            while(true){
                if(queue.isEmpty()) break;
                var now = queue.getFirst();
                if(now.pri != max) break;
                count++;
                if(now.index == location) return count;
                queue.removeFirst();
            }

            while(true){
                if(tmpQ.isEmpty()) break;
                var now = tmpQ.getFirst();
                tmpQ.removeFirst();
                queue.addLast(now);
            }
        }

        return -1;
    }
}