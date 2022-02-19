/*
x축 위에 N개의 마을이 있다.
i번쨰 마을은 X[i]에 위치하며, A[i]명의 사람이 산다.
우체국을 설치하는데 각 사람들의 동선이 최소가 되게 설치하자.

풀이 1
1. 사람이 많은 곳에 가까울수록 유리하다.
2. 어떤 방향으로 우체국을 옮길 때 사람이 많은 방향으로 옮기면 이득을 본다.
3. 오른쪽에서 왼쪽으로 옮겨간다고 생각할 때 왼쪽의 사람들이 오른쪽보다 많기 전까지 옮긴다.
    동선이 같을 경우 가장 왼쪽 위치를 선택하기 때문에 왼쪽부터 검사한다.
4. 어떤 마을을 지나면 왼쪽이 반 이상이 된다면 그 마을에 설치한다
    -마을을 지났을 때 반이 넘으면 손해보기 시작
    -마을을 지났을 때 딱 반반이면 동선의 총합이 다음 마을 도착 전까지 같음
5. 마을의 위치가 같은 경우 어차피 해당 위치에서 반 이상이 넘어가나 해당 위치에서 걸리나 결과는 같다.
*/

#include <iostream>
#include <vector>
#include <utility>
#include <algorithm>

using namespace std;

bool ascend(pair<long long, long long> a, pair<long long, long long> b){
    return a.first < b.first;
}

int main(void){
    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    cin >> n;

    long long total = 0;
    vector< pair<long long, long long > > v(n); //위치, 사람 수
    for(int i =0; i < n; i++){
        cin >> v[i].first >> v[i].second;
        total += v[i].second;
    }
    sort(v.begin(), v.end(), ascend);

    int index = 0;
    long long sum = 0;
    // 누적 사람 수가 전체의 반 또는 초과여야 한다.
    if(total & 1) total++;
    total /= 2;
    while(1){
        if(sum >= total) break;
        sum += v[index++].second;
    }

    printf("%lld\n",v[index-1].first);
}