/*
운동기구가 n개 있다.
한번에 2개의 운동기구를 사용하며 1개가 남았을 때만 1개를 사용한다.
각 운동기구를 사용할 떄 근손실이 난다.
각 세트마다 근손실이 최소가 되게 하자.

풀이 1
1. 기구가 홀수인 경우 근손실이 0인 기구로 친다
2. 근손실이 큰 기구끼리 뭉쳐있으면 근손실의 합이 커진다
3. 근손실을 기준으로 sort하여 반대편에 있는 기구끼리 짝을 짓는다.
4. 각 합의 최소를 구한다.
*/

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

bool cmp(long long a, long long b){
    return a < b;
}

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    cin >> n;

    vector<long long> g(n);
    for(int i = 0; i < n; i++){
        cin >> g[i];
    }
    if(n%2 != 0){
        g.push_back(0);
        n++;
    }

    sort(g.begin(), g.end(), cmp);

    long long max = -1;
    for(int i = 0; i < n/2; i++){
        if(max < g[i]+g[n-1-i]) max = g[i]+g[n-1-i];
    }

    printf("%lld\n",max);
}