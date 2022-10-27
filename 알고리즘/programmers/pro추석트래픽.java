// 초당 최대 처리량을 return 한다.
// 날짜 정보는 모두 동일하기 때문에 고려하지 않는다. 이때 00:00:00 근처에 끝난 시간들에 대해 시작시간을 유의하자
// 정보를 시작 시간을 기준으로 sort한다.

import java.util.*;

class Solution {
    public int solution(String[] lines) {

        // 이미 끝나는 시간을 기준으로 sort 되어있다.
        double[][] timeLine = new double[lines.length][2];
        for(int i = 0; i < lines.length; i++){
            timeLine[i] = getTimeNum(lines[i]);
        }
//        // 시작시간 기준 오름차순 정렬
//        Arrays.stream(timeLine).sorted((double[] a, double[] b) -> {
//            if(a[0]-b[0] > 0.0) return 1;
//            return 0;
//        });

        int max = 0;
        for( int i = 0; i < timeLine.length; i++){
            // 1초간격 안쪽에 있는 애들을 counting 한다.
            int count = 0;
            int index = i;
            double maxTime = timeLine[i][1]+1.0;
            while(true){
                if(index >= timeLine.length) break;
                // 시작 시간이 지금 target의 끝시간+1 보다 크면 탈락이다.
                if(timeLine[index][0] < maxTime) count++;
                index++;
            }

            if(max < count) max = count;
        }

        return max;
    }

    public double[] getTimeNum(String line){
        String[] arr = line.split(" ");
        String[] endTime = arr[1].split(":");
        double[] result = new double[2];
        result[1] += Double.parseDouble(endTime[0])*60*60;
        result[1] += Double.parseDouble(endTime[1])*60;
        result[1] += Double.parseDouble(endTime[2]);
        result[0] = result[1] - Double.parseDouble(arr[2].substring(0,arr[2].length()-1)) + 0.001;

        result[1]=Math.ceil(result[1]*1000)/1000;
        result[0]=Math.ceil(result[0]*1000)/1000;

        return result;
    }
}