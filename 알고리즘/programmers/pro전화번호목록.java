import java.util.*;
import java.util.regex.Pattern;

class Solution {
    public boolean solution_timeout(String[] phone_book) {

        for(int i = 0; i < phone_book.length; i++){
            
            String pattern = "^" + phone_book[i] + ".*$";

            for(int j = 0; j < phone_book.length; j++){
                if( i == j ) continue;
                if(Pattern.matches(pattern, phone_book[j])) return false;
            }
        }

        return true;
    }

    public boolean solution(String[] phone_book) {

        Arrays.sort(phone_book, Comparator.naturalOrder());

        for(int i = 0; i < phone_book.length-1; i++){
            if(phone_book[i+1].startsWith(phone_book[i])) return false;
        }

        return true;
    }
}