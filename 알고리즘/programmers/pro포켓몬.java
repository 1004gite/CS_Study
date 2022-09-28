import java.util.*;

class Solution {
    public int solution(int[] nums) {
        
        HashSet<Integer> hSet = new HashSet<>();

        for(int n: nums){
            hSet.add(n);
        }

        int size = hSet.size();
        int half = (nums.length/2);

        return size > half? half : size ;
    }
}