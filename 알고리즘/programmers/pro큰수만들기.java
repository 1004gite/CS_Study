import java.util.*;

class Solution {
    public String solution(String number, int k) {

        Deque<Integer> list = new LinkedList<>();
        Deque<Integer> result = new LinkedList<>();
        for(int i = 0; i < number.length(); i++){
            list.addLast(number.charAt(i) - '0');
        }

        while(true){
            if(k == 0) break;
            if(list.isEmpty()) break;
            // 하나를 빼고 넣는다.
            // 넣기 전에 본인보다 작은 놈을 모두 빼고 넣는다.
            int now = list.pollFirst();
            while(true){
                if(result.isEmpty()) break;
                if(result.getLast() >= now) break;
                result.pollLast();
                k--;
                if(k == 0) break;
            }
            result.addLast(now);
        }

        StringBuilder answer = new StringBuilder();
        for(int i: result) answer.append(i);
        for(int i: list) answer.append(i);
        return answer.length() > (number.length()-k) ? answer.substring(0, number.length()-k): answer.toString();
    }
}