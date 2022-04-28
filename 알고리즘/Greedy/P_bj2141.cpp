/*
일직선상에 마을들이 존재한다.
i번째 마을은 x[i]에 위치하고 A[i]명의 사람들이 산다.
이런 상황에서 우체국을 지으려 한다.
사람들이 우체국에 가는 거리의 합을 최소가 되게 하려면 우체국을 어디에 세워야 하는가

풀이 1
1. 마을 위치는 -1,000,000,000 ~ 1,000,000,000 까지로 우체국의 위치도 이 범위 안이 될 것이다.
2. 한 마을에 대한 사람들의 이동거리 = 거리 * 사람 수
3. 각 마을에서 우체국까지의 거리의 합은 항상 일정하다.
4. 어떤 위치에서 우체국을 한칸 이동하게 되면 이동한 방향의 마을들은 인원수만큼 동선이 줄어들고 반대방향의 마을은 인원수만큼 동선이 길어진다.
5. 가장 왼쪽 도시에서 출발하여 오른쪽으로 한칸씩 이동시키며 최소값을 찾는다.
    옮길 때 동선이 점점 길어지게 되면 왼쪽에 있는 사람들의 합이 많아진 것으로 더이상 검사할 필요가 없다.
6. 우체국 기준 좌측의 사람이 많아지면 오른쪽으로 한칸을 갈 때마다 동선을 손해보기 시작하고 그 전까지는 오른쪽으로 가는 것이 이득이다.
    오른쪽으로 가는 것이 이득인(오른쪽애 사람이 더 많은) 상황은 절반or절반이상 의 사람이 왼쪽에 오기 전까지이다.
    즉, 절반을 넘어가게 되는 마을의 전 마을에 우체국을 설치한다.
*/

#include <iostream>
#include <vector>
#include <algorithm>
#include <utility>

using namespace std;

bool ascend(pair<long long, long long> a, pair<long long, long long> b){
    return a.first < b.first;
}

int main(void){
    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    cin >> n;
    vector< pair<long long, long long> > v(n); // 마을 위치 ,사람 수
    long long total = 0;
    for(int i = 0; i < n; i++){
        cin >> v[i].first >> v[i].second;
        total += v[i].second;
    }
    sort(v.begin(), v.end(), ascend);

    int index = 0;
    if(total & 1) total++; // 홀수인 경우 몫+1 이 정 가운데이므로 total+1을 해서 가운데를 맞춰준다.
    total /= 2;
    while(total > 0){
        total -= v[index].second;
        index++;
    }

    printf("%lld\n",v[index-1].first);
}