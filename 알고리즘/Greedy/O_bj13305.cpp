/*
각 도시는 수평으로 일직선상에 있다.
각 도시마다의 기름값은 다를 수 있다.
각 도시 사이의 거리도 다를 수 있다.
가장 왼쪽 도시에서 출발하여 가장 오른쪽 도시로 갈 때의 최소 기름값은?

도시의 개수는 최대 100,000 이고, 각 거리는 최대 1,000,000,000, 리터당 가격은 최대 1,000,000,000 이므로 long long 사용

풀이 1
1. 도시간 이동 중 이후의 도시들의 기름값이 더 크다면 그 도시에서 기름을 모두 충전하고 나머지 도시를 이동한다.
2. 기름값순으로 도시를 정렬한다.
3. 도시정보에는 기름값, 종착 도시까지의 거리정보를 넣는다.
4. 종점까지의 거리정보가 더 크면 더 앞에 있는 도시이다.
5. 기름값 = {(이번 도시정보에서의 남은 길이 - 이미 할당된 뒷부분) * 기름값}, 0보다 작으면 0으로 한다.


풀이 2 -> 풀이 1의 경우 정렬에서 O(NlogN)을 가진다.

1. 첫 도시에서 출발할 때 다음 도시까지의 거리만 갈 수 있게 충전한다.
2. 다음 도시의 기름값이 더 싸다면 다음 도시에서 충전한다.
    만약 마지막으로 충전한 도시의 기름값이 더 싸다면 그 도시에서 충전하고 온 것으로 치고 값을 계산한다.
*/

#include <iostream>
#include <vector>

using namespace std;

struct city{
    long long len, val;
    city(long long len, long long val) : len(len), val(val) {}
};

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    cin >> n;

    vector<city> cities(n, city(0,0));
    for(int i = 0 ; i < (n-1); i++){
        cin >> cities[i].len; // 다음 도시까지의 거리
    }
    for(int i = 0; i < n; i++){
        cin >> cities[i].val; // 해당 도시의 기름값
    }

    long long nowval = cities[0].val;
    long long total = 0;
    
    for(int i = 0; i < (n-1); i++){
        // 각 도시를 지날때마다 기름값을 확인 후 지나온 가장 싼 값으로 가름을 산 것으로 친다.

        if(nowval > cities[i].val) nowval = cities[i].val; // 더 싼 기름값으로 갱신

        total += nowval*cities[i].len;
    }

    printf("%lld\n",total);

}