import java.util.*;

class Solution {

    class disk{
        disk(int start, int remain){
            insertTime = start;
            this.remain = remain;
        }
        int insertTime = 0;
        int remain = 0;
    }

    public int solution(int[][] jobs) {
        int answer = 0;
        Arrays.sort(jobs, (o1, o2) -> { return o1[0]-o2[0]; });
        var minHeap = new PriorityQueue<disk>( (o1,o2) -> { return o1.remain-o2.remain; } );

        int index = 0;
        disk nowDisk = null;
        int now = 0;

        while(true){
            if(minHeap.isEmpty() && index == jobs.length && nowDisk == null) break;
            // 들어온 disk 삽입
            while(true){
                if(index == jobs.length) break;
                if(jobs[index][0] > now) break;
                minHeap.add(new disk(now,jobs[index][1]));
                index++;
            }

            if(nowDisk != null && nowDisk.remain == 0) {
                answer += now-nowDisk.insertTime;
                nowDisk = null;
            }
            if(nowDisk == null) nowDisk = minHeap.poll();
            if(nowDisk != null) nowDisk.remain--;


            now++;
        }

        return answer/ jobs.length;
    }
}