import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int[] solution(int[] answers) {

        int[] ptn1 = {1,2,3,4,5};
        int[] ptn2 = {2,1,2,3,2,4,2,5};
        int[] ptn3 = {3,3,1,1,2,2,4,4,5,5};

        int[][] patterns = {ptn1,ptn2,ptn3};
        int[] result = {0,0,0};

        int[] indexes = {0,0,0};

        for(int a: answers){
            // 세 사람에 대해
            for(int i=0; i <3; i++){
                // 정답 확인 후 인덱스 업데이트
                if(a == patterns[i][indexes[i]]) result[i]++;
                indexes[i] = (indexes[i]+1)%patterns[i].length;
            }
        }

        int max = Arrays.stream(result).max().getAsInt();
        var answer = new ArrayList<Integer>();
        for(int i = 0; i < result.length; i++){
            if(result[i] == max) answer.add(i+1);
        }

        int[] a = new int[answer.size()];
        for( int i =0; i < answer.size(); i++ ) a[i] = answer.get(i);

        return a;
    }
}