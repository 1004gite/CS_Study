import java.util.ArrayList;
import java.util.HashSet;

class Solution {

    StringBuilder sb = new StringBuilder();
    ArrayList<Character> list = new ArrayList();
    HashSet<Integer> hSet = new HashSet();

    public int solution(String numbers) {

        for(char c: numbers.toCharArray()) list.add(c);

        for(int i = 0; i < list.size(); i++){
            if(list.get(i) == '0') continue;
            char now = list.get(i);
            list.remove(i);
            dfs(now);
            list.add(i,now);
        }

        return hSet.size();
    }

    public void dfs(char num){
        // 소수인지 검사
        sb.append(num);
        int tmp = Integer.parseInt(sb.toString());
        boolean isPrime = true;
        for(int n = 2; n*n <= tmp; n++){
            if(tmp%n == 0){
                isPrime = false;
                break;
            }
        }
        if(isPrime && tmp != 1) hSet.add(tmp);

        for(int i = 0; i < list.size(); i++){
            char now = list.get(i);
            list.remove(i);
            dfs(now);
            list.add(i,now);
        }

        sb.deleteCharAt(sb.length()-1);
    }
}