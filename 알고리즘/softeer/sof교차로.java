import java.util.*;
import java.io.*;


public class Main
{
    class Wait{
        int index;
        int time;
        char type;
        public Wait(int t, char ty, int i){
            time = t;
            type = ty;
            index = i;
        }
    }
    // a->d, b->a, c->b, d->c 를 확인하여 차가 있으면 1초를 기다린다.
    void solve() throws Exception{
        ArrayList<Queue<Integer>> roadList = new ArrayList<>();
        for(int i = 0; i < 4; i++) roadList.add(new LinkedList<>());

        var qWait = new ArrayList<Wait>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] record = new int[n];
        for(int i = 0; i < record.length; i++) record[i] = -1;

        while(n-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            qWait.add(new Wait(t, st.nextToken().charAt(0), qWait.size()));
        }
        qWait.sort((o1,o2)->{ return o1.time-o2.time; });

        int time = qWait.get(0).time;
        int index = 0;
        // 0->3, 1->0, 2->1, 3->2
        int[] checkList = new int[] {3,0,1,2};

        while(true){
            // 현재 시간에 도착한 차량을 도로에 세운다.
            // 대기열이 하나도 없는 상황이라면 대기열을 땡긴다.
            if(roadList.get(0).isEmpty()&&roadList.get(1).isEmpty()&&
                    roadList.get(2).isEmpty()&&roadList.get(3).isEmpty() && index < qWait.size()){
                time = qWait.get(index).time;
            }
            while (true){
                if( index >= qWait.size()) break;
                Wait now = qWait.get(index);
                if(now.time != time) break;
                switch (now.type){
                    case 'A': roadList.get(0).add(now.index); break;
                    case 'B': roadList.get(1).add(now.index); break;
                    case 'C': roadList.get(2).add(now.index); break;
                    default: roadList.get(3).add(now.index); break;
                }
                index++;
            }

            var tmpList = new ArrayList<Integer>();
            for(int i = 0; i < 4; i++){
                if(roadList.get(i).isEmpty()) continue;
                // 지나갈 수 없음
                if(!roadList.get(checkList[i]).isEmpty()) continue;
                tmpList.add(i);
            }
            if(tmpList.isEmpty()) break;
            for(int i: tmpList){
                record[roadList.get(i).remove()] = time;
            }
            time++;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(var t: record){
            bw.append(Integer.toString(t)+"\n");
        }
        bw.flush();
    }

    public static void main(String args[]) throws Exception {
        new Main().solve();
    }
}