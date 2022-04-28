/*
회의들의 시작시간과 끝나는 시간이 주어진다.
시간이 겹치지 않으면서 가장 많은 회의를 진행할 때의 최대 회의수를 구하자.
이떄 어떤 회의의 끝과 다른 회의의 시작이 같아도 문제 없다.

풀이 1 -> 시간초과
1. 각 회의마다 겹치는 회의의 개수를 구한다.
    더 빨리 시작한 회의 기준 끝나는 시간보다 빨리 시작한다면 회의가 겹친다.
2. 겹치는 회의가 가장 많은 회의부터 제거한다.

==>겹치는 회의의 개수를 찾는 과정에서 시간 복잡도가 많이 소요되었다. O(N*N)
    -> 한 회의의 겹치는 개수를 찾기 위하는데 최대 O(N*N)이 소요되고 최대 N개의 회의에 대해 검사를 진행한다.
    -> 위의 과정을 최대 N-1번 반복한다.

풀이 2
1. 어떤 행동을 하는 동안 다른 행동을 할 수 없고 최대한 많은 행동을 해야하는 문제 -> Greedy로 접근
2. Greedy로 접근할 때는 현재 어떤 행동이 진행중일 떄 다음 행동에 가장 유리한 조건을 생각하자
3. 회의가 진행중일 때 빠르게 끝난다면 다음 회의가 시작하기에 유리하다.
    만약 동시에 끝난다면 시작시간이 느릴수록 유리하다.
*/

#include <iostream>
#include <vector>
#include <algorithm>
#include <utility>

using namespace std;

bool ascend(pair<int,int> a, pair<int,int> b){
    if(a.second == b.second) return a.first < b.first;
    return a.second < b.second;
}

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    cin >>n;

    vector< pair<int,int> > meetings(n);

    for(int i =0; i < n; i++){
        cin >> meetings[i].first;
        cin >> meetings[i].second;
    }

    sort(meetings.begin(), meetings.end(), ascend);

    int count = 0;
    int end = -1; // 최근 회의의 끝나는 시간
    for(int i = 0; i < n; i++){
        if(meetings[i].first >= end){
            count++;
            end = meetings[i].second;
        }
    }

    printf("%d\n",count);
}
