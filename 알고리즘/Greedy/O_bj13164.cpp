/*
사람들을 키순서대로 줄세운다.
세운 순서대로 임의로 잘라 k개의 팀을 만든다.
각 팀의 팀복을 맞추는 비용은 (가장 큰 팀원의 키 - 가장 작은 팀원의 키) 이다.
팀복의 비용을 최소화하자.

풀이 1
1. 바로 인접한 팀원틀의 키만큼 추가비용이 발생한다.
2. 모든 사람이 한 팀인 상황에서 팀을 2개로 나누면 경계선에 있는 두 사람의 키차이만큼 비용이 감소한다.
3. 키차이가 가장 큰 구간 k-1개를 구하여 모두가 한 팀인 상황의 비용에서 비용을 뺀다.
*/

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

bool cmp(long long a, long long b){
    return a > b;
}

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n,k;
    cin >> n;
    cin >> k;

    vector<long long> diff;
    long long small,big, pre, tmp;
    // 맨앞
    cin  >> small;
    pre = small;
    while(n > 2){
        cin >> tmp;
        diff.push_back(tmp-pre);
        pre = tmp;
        n--;
    }
    //마지막 사람
    cin >> big;
    diff.push_back(big-pre);

    sort(diff.begin(), diff.end(), cmp);

    long long total = big - small;
    for(int i = 0; i < k-1; i++){
        total -= diff[i];
    }

    printf("%lld\n",total);

}