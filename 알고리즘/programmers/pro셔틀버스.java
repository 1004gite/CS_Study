import java.util.*;

class Solution {

    // n,t 로 셔틀이 오는 시간을 알아낼 수 있다.
    // 셔틀에 시간순으로 사람들을 넣으며 시물레이션 하여 채워넣는다.
    // 가장 늦은 셔틀을 탈 때 셔틀이 꽉차지 않는 경우 셔틀의 출발 시간에 맞춰서 오면 된다.
    // 가장 늦을 셔틀이 꽉 차는 경우 가장 늦게 타는 사람보다 1분 더 일찍 와야한다.
    //

    public String solution(int n, int t, int m, String[] timetable) {

        // 셔틀 시간을 분으로 바꿔 저장한다.
        var bus = new int[n];
        for(int i = 0; i < n; i++){
            bus[i] = 9*60 + i*t;
        }

        // 크루가 도착하는 시간도 분으로 바꿔 저장한다.
        var crew = new int[timetable.length];
        for(int i = 0; i < timetable.length; i++){
            var tmp = timetable[i].split(":");
            crew[i] = Integer.parseInt(tmp[0])*60 + Integer.parseInt(tmp[1]);
        }
        Arrays.sort(crew);

        int crewIndex = 0;
        // 해당 index의 버스에 어떤 index의 crew가 타는지 기입힌다.
        var list = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < n; i++){
            list.add(new ArrayList<>());
            // i번째 버스에 탈 수 있는 사람들을 태운다.
            while(true){
                if(crewIndex >= crew.length) break;
                // 버스가 꽉차면 더이상 태울 수 없다.
                if( list.get(i).size() >= m ) break;
                // crew가 도착하지 않았으면 태울 수 없다.
                if( crew[crewIndex] > bus[i] ) break;

                // 아니라면 태우는 판정
                list.get(i).add(crewIndex);
                crewIndex++;
            }
        }

        int result = 0;
        // 가장 늦는 버스에 대해 검사한다.
        var lastBus = list.get(list.size()-1);
        if( lastBus.size() < m ) result = bus[bus.length-1];
        else {
            result = crew[lastBus.get(lastBus.size() - 1)] - 1;
        }

        return String.format("%02d:%02d",result/60,result%60);
    }
}