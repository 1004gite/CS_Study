/*
트럭으로 왼쪽부터 오른쪽으로 물건을 배달한다
(출발지 마을에 도착하면 박스를 싣고 목적지 마을에 내린다)
트럭은 개수제한이 있고 박스는 목적지인 마을에서만 내린다
박스들 중 일부만 배송해도 될 때 트럭 한 대로 배송할 수 있는 최대 박스 수를 구하자

풀이 1 -> 틀렸다.
1. 출발지와 목적지가 가까울수록 유리하다
    또, 이미 받은 박스는 버릴 수 없기 때문에 트럭이 가득 차면 박스를 더 빧을 수 없다
2. 마을에 도착할 떄마다 판단하게 되면 해당 마을에서의 목적지 정보만 아는 상황이기 떄문에 전체적으로 파악해야 한다
3. 전체적으로 보면 목적지가 왼쪽일수록 유리하다
    무조건 출발지 오른쪽에 목적지가 있기 때문에 어디에서 배송을 시키든 더 빨리 박스를 내릴 수 있는 것이 유리하다
4. 박스를 일부만 받을 수 있기 때문에 가까운 목적지를 우선하여 가능한 많이 받는 것이 좋다.
5. 시뮬레이션
    -문제에서처럼 트럭을 옮겨가며 박스를 관리
        -> 출발지, 도착지 정보를 가지고 있어야 함 + 박스들의 정보를 매번 확인해 줘야 함
    -1개의 박스만 운반 가능한 트럭 c대가 있다고 가정

풀이 2
1. 도착지가 같을 경우 출발지가 더 오른쪽에 있는 박스를 우선으로 선택했다 (짧은 거리의 배달이 유리하다고 생각했음)
    이렇게 할 경우 먼저 만나는 마을을 지나치고 후에 있는 마을을 먼저 보기 떄문에 트럭에 여유가 남아도 전의 마을로 돌아갈 수 없는 상황이 생긴다.
    -> 어차피 목적지가 같다면 다음 배송에 미치는 효과가 같기 때문에 먼저 만나는 마을을 우선으로 고려한다

풀이 3
1. 수가 커지면 오답처리되는 것으로 보아 시간초과가 난 것 같다
2. 1개만 나를 수 있는 트럭 c개가 아닌 트럭 1대로 시뮬레이션한다
    이때, 싣고있는 화물들의 도착지를 기준으로 minheap을 만들어 내리는 것을 검사한다.
    -> 이미 목적지가 가까운 순으로 정렬되어 있으므로 heap을 쓸 필요는 없다 -> queue를 사용하여 순서대로 확인가능

풀이 4
1. 위의 논리대로 진행할 경우 트럭의 여유공간이 남았음에도 박스를 싣지 않는 경우가 생긴다
    ex) 공간이 남아있는데 도착지가 더빠른 박스를 먼저 고려하느라 마을을 이미 지나버린 경우
2. 도착지가 빠른 박스를 우선하여 배송하되, 지나가는 것으로 시뮬레이션 하지 않고
    출발마을에서 도착마을 전까지 트럭에 용량이 차있음만 표시해준다.
    배송 경로 중 가능한 최대 박스를 배송하고 표시한다.

*/

#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

struct info{
    int s, d, w;
};
struct box{
    int des, w;
};
bool ascend(info a, info b){
    if(a.d == b.d) return a.s < b.s; 
    return a.d < b.d;
}
int main(void){
    cin.tie(0);
    cin.sync_with_stdio(0);

    int n, c, m;
    cin >> n >> c >> m;

    vector<info> v(m);
    for(int i = 0; i < m; i++){
        cin >> v[i].s >> v[i].d >> v[i].w;
        // if(v[i].s > v[i].d) v[i].w = 0;
    }
    // v에는 배송정보가 도착지 가까운 순으로 정렬되어 있음
    sort(v.begin(), v.end(), ascend);

    long long acc = 0;
    vector<int> truck(n+1,c);
    for(int i = 0; i < m; i++){
        // 구간 내 가장 적은 남은 공간을 가지는 순간을 찾는다. (도착지에서는 내리기 때문에 고려하지 않는다. )
        int minIndex = min_element(truck.begin()+v[i].s,truck.begin()+v[i].d-1)-truck.begin();
        int possible = truck[minIndex];
        if(possible <= 0) continue;
        if(possible > v[i].w) possible = v[i].w;
        // 경로에 가능한 박스를 더함
        int length = v[i].d-v[i].s;
        for(int j = 0; j < length; j++){
            truck[v[i].s+j] -= possible;
        }
        // 배송했음으로 최종 값에 더함
        acc += possible;


        // // Debug
        // printf("%d->%d %d실음\n",v[i].s,v[i].d,possible);
        // for(int x =1 ; x < n+1; x++){
        //     printf("%d ",truck[x]);
        // }
        // printf("\n");
    }

    printf("%lld\n",acc);
}