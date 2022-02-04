/*
회의들의 시작시간과 끝나는 시간이 주어진다.
시간이 겹치지 않으면서 가장 많은 회의를 진행할 때의 최대 회의수를 구하자.
이떄 어떤 회의의 끝과 다른 회의의 시작이 같아도 문제 없다.

풀이 1 -> 시간초과
1. 각 회의마다 겹치는 회의의 개수를 구한다.
    더 빨리 시작한 회의 기준 끝나는 시간보다 빨리 시작한다면 회의가 겹친다.
2. 겹치는 회의가 가장 많은 회의부터 제거한다.

풀이 2
1. 겹치는 회의의 개수를 찾는 과정에서 시간 복잡도가 많이 소요되었다. O(N*N)
2. 시작시간, 끝 시간을 개별의 인자로 두고 오름차순으로 sort하여 순서를 따진다.
3. sort된 배열을 지나면서 해당 미팅번호에 index정보를 기입한다.
    이때 시간이 같은 경우를 고려하여 사간이 같다면 열기의 경우 가장 빠른 시간을
    닫기의 경우 가장 느린 시간을 기입한다.
*/

#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>

using namespace std;


struct meeting{
    int start, end;
    bool valid;
};

struct unit{
    bool isOpen;
    int time, num;
};

bool ascend(unit a, unit b){
    return a.time < b.time;
}

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    cin >> n;

    vector< unit > times(n*2);
    vector<meeting> meetings(n);

    for(int i = 0; i < n; i++){
        times[i*2].isOpen = true;
        times[i*2].num = i;
        cin >> times[i*2].time;

        times[i*2+1].isOpen = false;
        times[i*2+1].num = i;
        cin >> times[i*2+1].time;

        meetings[i].valid = true;
    }
    sort(times.begin(), times.end(), ascend);

    // 시작 index 처리
    int tmptime = times[0].time, tmpindex = 0; //실제 시간, 정렬된 index
    for(int i = 0; i < n*2; i++){
        if(!times[i].isOpen) continue;
        if(tmptime != times[i].time){
            // 다른 시간에 시작하는 경우만 업데이트
            tmptime = times[i].time;
            tmpindex = i;
        }
        meetings[times[i].num].start = tmpindex;
        // printf("(%d) start index is %d\n",tmptime,tmpindex);
    }
    // 끝 index 처리
    tmptime = times[2*n-1].time, tmpindex = 2*n-1; //실제 시간, 정렬된 index
    for(int i = 2*n-1; i >= 0; i--){
        if(times[i].isOpen) continue;
        if(tmptime != times[i].time){
            // 다른 시간에 시작하는 경우만 업데이트
            tmptime = times[i].time;
            tmpindex = i;
        }
        meetings[times[i].num].end = tmpindex;
    }

    while(1){
        
        int max = 0, num; // 최대 겹치는 수, 해당 회의번호
        for(int i = 0; i < n; i++){
            if(!meetings[i].valid) continue;
            vector<bool> tmp(n,false);
            for(int j = meetings[i].start; j <= meetings[i].end; j++){
                if(times[j].num == i) continue;
                if(!meetings[j].valid) continue;
                tmp[times[j].num] = true;
            }
            int count = 0;
            for(int j = meetings[i].start; j <= meetings[i].end; j++){
                if(tmp[j]) count++;
            }

            if( max < count){
                max = count;
                num = i;
            }
        }
        if(max == 0) break;

        meetings[num].valid = false;
    }
    int count = 0;
    for(int i = 0; i < n; i++){
        if(meetings[i].valid) count++;
        // printf("%d's meeting (%d, %d), is %s\n",i,meetings[i].start,meetings[i].end, meetings[i].valid? "OK" : "NO");
    }
    printf("%d\n",count);
}