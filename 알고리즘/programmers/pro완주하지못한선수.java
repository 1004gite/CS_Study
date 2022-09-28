import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {

        if(participant.length == 1) return participant[0];
        
        HashMap<String, Integer> hashmap = new HashMap<>();
        for(String name: completion){
            if(hashmap.get(name) == null){
                hashmap.put(name, 1);
            }
            else{
                hashmap.replace(name, hashmap.get(name)+1);
            }
        }

        for(String name: participant){
            if(hashmap.get(name) == null) {
                return name;
            }
            else if(hashmap.get(name) == 1){
                hashmap.remove(name);
            }
            else{
                hashmap.replace(name, hashmap.get(name)-1);
            }
        }
        
        return "";
    }
    public String solution2(String[] participant, String[] completion) {

        if(participant.length == 1) return participant[0];
        
        ArrayList<String> sortedP = new ArrayList<>(Arrays.asList(participant));
        sortedP.sort(String.CASE_INSENSITIVE_ORDER);
        ArrayList<String> sortedC = new ArrayList<>(Arrays.asList(completion));
        sortedC.sort(String.CASE_INSENSITIVE_ORDER);
        
        for(int i = 0; i < completion.length; i++){
            if( !sortedC.get(i).equals(sortedP.get(i)) ) return sortedP.get(i);
        }

        return sortedP.get(sortedP.size()-1);
    }
}