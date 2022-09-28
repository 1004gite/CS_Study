import java.util.*;

class Solution {
    class pair{
        // 횟수, 번호
        // Minheap으로 쓸 예정
        int sum = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>( (o1,o2) -> {
            if(o1[0] == o2[0]) return o2[1]-o1[1];
            return o1[0] - o2[0];
        });

        public void insert(int[] a){
            pq.add(a);
            if(pq.size() > 2) pq.remove();
        }
    }
    public <T> int[] solution(String[] genres, int[] plays) {
        int index = 0;
        ArrayList<pair> info = new ArrayList<>();
        // pair[] info = new pair[genres.length];

        var hMap = new HashMap<String, Integer>();

        for(int i = 0; i < plays.length; i++){
            if(hMap.containsKey(genres[i])){
                int tmpIndex = hMap.get(genres[i]);
                var tmpVal = info.get(tmpIndex);
                tmpVal.sum+=plays[i];
                tmpVal.insert(new int[] {plays[i], i});
                info.set(tmpIndex, tmpVal);
            }
            else{
                hMap.put(genres[i], index);
                var tmpVal = new pair();
                tmpVal.sum = plays[i];
                tmpVal.insert(new int[] {plays[i], i});
                info.add(tmpVal);
                index++;
            }
        }

        info.sort((o1,o2) -> {return o2.sum - o1.sum;});

        var arr = new ArrayList<Integer>();
        for(pair p: info){
            var tmp = p.pq.poll()[1];
            if(p.pq.peek() != null) arr.add(p.pq.poll()[1]);
            arr.add(tmp);
        }

        int[] answer = new int[arr.size()];
        for(int i = 0; i < arr.size(); i++) answer[i] = arr.get(i);
        
        return answer;
    }

    // public static void main(String[] args){
    //     new Solution1().solution(new String[] {"classic", "pop", "classic", "classic", "pop"},new int[] {500, 600, 150, 800, 2500});
    // }
}