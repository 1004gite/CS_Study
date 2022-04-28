/*
N개의 회의가 있고 각각의 시작시간, 끝나는 시간이 주어진다.
모든 회의가 진행되어야 할 때 최소한의 회의실로 회의를 진행시키자.

풀이 1
1. 시작시간을 기준으로 정렬하여 먼저 시작하는 회의를 배치한다.
2. 배치가 가능하지 않다면 새로운 강의실을 할당한다.
3. 배치가 가능한지 검사시 가장 빨리 끝나는 회의실과만 비교한다. (heap 이용)
    시작시간을 기준으로 정렬했고 모든 회의가 배치되어야 하므로 어떤 회의를 배치했다면
    그 회의 이전시간에 다른 회의가 들어갈 수 있는 경우의 수는없다.
*/

#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>
#include <utility>

using namespace std;

struct cmp{
    bool operator()(int a, int b){
        return a > b;
    }
};

bool cmp_start(pair<int,int> a, pair<int,int> b){
    return a.first < b.first;
}

int main(void){
    
    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    cin >> n;
    vector< pair<int, int> > meetings(n);

    for(int i = 0; i < n; i++){
        cin >> meetings[i].first;
        cin >> meetings[i].second;
    }

    sort(meetings.begin(), meetings.end(), cmp_start);

    priority_queue<int, vector<int>, cmp> rooms;
    rooms.push(-1);

    for(int i =0; i < n; i++){
        if(rooms.top() <= meetings[i].first){
            rooms.push(meetings[i].second);
            rooms.pop();
        }
        else{
            rooms.push(meetings[i].second);
        }
    }

    printf("%lu\n",rooms.size());
}