/*
센서 N개가 있다.
센서의 정보를 모으는 집중국을 k개 설치한다.
이때 집중국의 수신 범위를 최소화하자.

풀이 1
1. 센서들을 k개의 그룹으로 나누어 각각 집중국을 하나씩 할당한다고 생각한다.
2. 집중은 해당 그룹의 최대 거리만큼 수신영역이 있어야 한다.
3. 센서들을 좌표 기준으로 정렬한 후 그룹 사이의 거리가 최소가 되게 k개의 그룹으로 나눈다.
4. k개의 그룹으로 나눌때 경계선은 두 센서 사이의 거리가 먼 곳을 우선으로 한다.
5. 전체 거리에서 가장 두 센서사이가 가장 긴 거리 k-1개를 빼는것과 같다.
6. 센서 사이의 거리를 구하고 sort하면 N*NlogN이므로 거리를 구하며 heap에 바로 넣고 k개를 뽑이서 쓴다.
    이떄 최소 힙으로 구현하여 힙의 크기를 k-1로 유지한다 -> Nlogk + Klogk
*/

#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

struct cmp{
    // 최소 힙
    bool operator()(int a, int b){
        return a > b;
    }
};

bool ascend(int a, int b){
    return a < b;
}

int main(void){
    cin.tie(0);
    cin.sync_with_stdio(0);

    int n,k;
    cin >> n >> k;

    vector<int> sensor(n);
    for(int i =0; i < n; i++){
        cin >> sensor[i];
    }

    sort(sensor.begin(), sensor.end(), ascend);

    // 기지국이 1개인 경우 예외처리
    if(k == 1){
        printf("%d\n",sensor[n-1]-sensor[0]);
        return 0;
    }

    priority_queue<int, vector<int>, cmp> diff;
    for(int i = 1; i < n;i++){
        int tmp = sensor[i] - sensor[i-1];
        if(diff.size() < k-1){
            diff.push(tmp);
        }
        else if(diff.top() < tmp){
            diff.pop();
            diff.push(tmp);
        }
    }

    int total = sensor[n-1] - sensor[0];
    while(!diff.empty()){
        total -= diff.top();
        diff.pop();
    }

    printf("%d\n",total);

}