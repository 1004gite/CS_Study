import java.util.*;

class Solution {
    public String solution(int[] numbers) {

        var list = new ArrayList<String>();
        for(int i =0; i < numbers.length; i++) list.add( Integer.toString(numbers[i]) );
        list.sort((a,b) -> { return (b+a).compareTo(a+b); });

        if(list.get(0).equals("0")) return "0";
        var sb = new StringBuilder();
        for(String n: list){
            sb.append(n);
        }

        return sb.toString();
    }
}