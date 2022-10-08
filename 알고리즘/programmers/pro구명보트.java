import java.util.*;

// 같은 풀이인데 LinkedList는 효율성이 떨어지고 Deque는 효율성에 통과했다.
// LinkedList는 앞뒤에서 빼도 index를 사용하는 것이 아닌가 추측한다.

class Solution {
    //    public int solution(int[] people, int limit) {
//
//        LinkedList<Integer> list = new LinkedList<>();
//        for(int i: people) list.addLast(i);
//        list.sort((a,b) -> {return b-a;}); // 오름차순
//
//        if(list.getLast() + list.get(list.size()-2) > limit) return people.length;
//        int answer = 0;
//
//        while(true){
//            if(list.size() <= 1){
//                answer += list.size();
//                break;
//            }
//            if(list.getFirst() + list.getLast() <= limit) list.removeLast();
//            list.removeFirst();
//            answer++;
//        }
//
//        return answer;
//    }
    public int solution(int[] people, int limit) {

        Arrays.sort(people);
        Deque<Integer> list = new LinkedList<>();
        for (int i : people) list.addLast(i);

        int answer = 0;
        while (true) {
            if (list.size() <= 1) {
                answer += list.size();
                break;
            }
            if (list.getFirst() + list.getLast() <= limit) list.removeFirst();
            list.removeLast();
            answer++;
        }

        return answer;
    }
}




