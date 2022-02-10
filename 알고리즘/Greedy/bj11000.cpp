/*
N개의 강의가 있고 S에 시작하여 T에 끝난다.
최소한의 강의실을 사용하여 모든 수업을 가능하게 하자.

풀이 1 ==>시간초과
1. 최소한의 강의실만 사용하기 위해 한 강의실에서 최대한 많은 강의가 진행되어야 한다.
2. 한 강의실 기준 가능한 최대의 강의를 배치 후 남는 강의들은 다른 강의실에 똑같은 방법으로 배치한다.
    -> a강의실에서 배정한 후 시간이 겹친 강의들을 b강의실로 배정 시도한다.
    -> b강의실에서도 겹치는 강의가 나오면 다음 강의실을 배정한다.
    (이떄 b강의실에서도 겹치는 강의를 a강의실의 강의 몇개와 바꿀 수 있는데
        a강의실에서 내려온 강의들은 b강의실 배정 예정인 강의들과 겹쳐서 분리되었었기 때문에 또 강의실이 겹친다.)
3. 강의실 하나에 대해 최대한 많은 강의를 배정하기 위해 greedy하게 접근한다.
    어떤 강의가 배정되었다면 그 강의의 끝나는 시간이 빨라야 더 많은 강의가 올 수 있다.
4. 끝나는 시간순으로 정렬 후 배치한다
    끝시간을 기준으로 정렬하기 떄문에 회의시간이 길던 짧던 끝나기 전에 시작하는 강의와는 같은 강의실을 쓸 수 없다.
    따라서 끝시간이 같다면 강의 시간이 긴 강의를 먼저 배정하여 다음 강의실 배정을 유리하게 한다.

풀이 2 ==> 틀렸습니다.
1. 강의실을 하나씩 돌며 배정이 완료될때까지 반복하지 않고
    겹치는 강의는 바로 가능한 강의실 혹은 새로운 강의실을 만들어 넣는다.

풀이 3
1. 이전의 풀이들의 경우 어떤 강의실에 겹치지 않게 할당 가능하면 바로 그 강의실에 할당하는 식으로 돌아간다.
    만약 2개 이상의 강의실에 할당이 가능하다면 이전강의와 지금강의사이의 간격이 최대한 짧은 강의실을 골라 배정한다.
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <utility>

using namespace std;

bool cmp(pair<long long, long long> a, pair<long long, long long> b){
    if(a.second == b.second) return a.first < b.first;
    return a.second < b.second;
}

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    cin >> n;

    vector< pair<long long, long long> > lec(n);

    for(int i = 0; i < n; i++){
        cin >> lec[i].first >> lec[i].second;        
    }

    sort(lec.begin(), lec.end(), cmp);

    vector<long long> rooms; //가장 마지막 강의의 끝나는 시간을 보유한다.

    for(int i = 0; i < n; i++){
        int room = -1, max = -1;
        for(int j = 0; j < rooms.size(); j++){
            // 할당이 가능할 때 이전 강의가 가장 늦게 끝나는 강의실에 할당하여 비는 시간이 적게 한다.
            if(rooms[j] <= lec[i].first && max < rooms[j]){
                room = j;
            }
        }
        if(room == -1){
            // 가능한 강의실이 없으면 새로운 강의실 생성
            rooms.push_back(lec[i].second);
        }
        else{
            // 가장 늦게 끝나는 강의실에 할당
            rooms[room] = lec[i].second;
        }
    }

    printf("%ld\n",rooms.size());
}