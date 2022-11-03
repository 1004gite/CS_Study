import java.util.*;
import java.io.*;


public class Main {

    public static void main(String args[]) throws Exception {

        class Info {
            long performance;

            public Info(long p) {
                performance = p;
            }

            public long calCost(long targetPerformance) {
                long diff = targetPerformance - performance;
                return diff * diff;
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        var arr = new ArrayList<Info>();
        while (n-- > 0) arr.add(new Info(Long.parseLong(st.nextToken())));
        arr.sort((o1,o2)->{return Long.compare(o1.performance,o2.performance);});


        long left = arr.get(0).performance;
        long right = 2000000000L;

        while(true){

            if(right - left <= 1) break;

            long mid = (left+right)/2;
            long totalCost = 0L;
            for(Info info: arr){
                if(mid <= info.performance) break;
                totalCost += info.calCost(mid);
                if(totalCost > b) break;
            }

            if(totalCost > b){
                right = mid;
            }
            else{
                left = mid;
            }

        }

        System.out.println(left);

    }
}