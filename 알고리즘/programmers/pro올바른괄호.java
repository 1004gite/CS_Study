import java.util.*;

class Solution {
    boolean solution(String s) {

        var stack = new LinkedList<Character>();

        for(char c: s.toCharArray()){
            if(c == '(') stack.addLast(c);
            else if( stack.isEmpty() ) return false;
            else stack.removeLast();
        }

        if(!stack.isEmpty()) return false;
        
        return true;
    }
}