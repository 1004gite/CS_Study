import java.util.*;

class Solution {

    int answer = 1;

    public int solution(String[][] clothes) {
        
        HashMap<String,Integer> hMap = new HashMap<>();
        for(String[] arr: clothes){
            if(hMap.containsKey(arr[1])) hMap.replace(arr[1], hMap.get(arr[1])+1);
            else hMap.put(arr[1], 1);
        }
        
        hMap.entrySet().stream().forEach( entry -> { 
            answer *= (entry.getValue()+1); 
        });

        return answer-1;
    }
}