/*
들어있는 양이 다를 수 있는 포도주들이 일렬로 서있다.
연속해서 3잔을 마실 수 없을 때 가장 많은 양의 포도주를 마시는 경우를 구하라

풀이 1
1. 어떤 포도주를 마실지 말지 고려할 때 이전까지의 최적해를 사용할 수 없다.
    -> dp를 이용한다.
2. n번째를 선택한다면 2가지 경우가 생긴다.
    - n, n-1을 선택하고 n-2를 선택하지 않아 n-3이하부터 고려하는 경우
    - n을 선택하고 n-1을 선택하지 않아 n-2부터 고려하는 경우
    n번째를 선택하지 않는 경우이다.
    - n번째를 선택하지 않았을 경우 dp[n-1]과 같다.
    - n번째, n-1번째를 선택하지 않았을 경우 dp[n-2]와 같다.
    - 3개 연속 선택하지 않는 경우는 최적해가 아니다.
    dp[n-1]에서 dp[n-2]도 고려되기 때문에 선택하지 않는 경우는 항상 고려된다.
*/

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> list(10001,-1);
vector<int> dp(10001,0);

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    cin >> n;

    for(int i = 0; i < n; i++){
        cin >> list[i];
    }

    //2잔째까지는 모두 마시는 것이 이득
    dp[0] = list[0];
    dp[1] = dp[0] + list[1];
    dp[2] = max(list[0],list[1]);
    dp[2] += list[2];
    dp[2] = max(dp[2],dp[1]);

    for(int i = 3; i < n; i++){

        int case1 = list[i] + list[i-1] + dp[i-3];
        int case2 = list[i] + dp[i-2];
        int case3 = dp[i-1];

        int m = max(case1, case2);
        m = max(m, case3);

        dp[i] = m;
    }

    // printf("==debug==\n");
    // for(int i = 0; i < n; i++){
    //     printf("[%d]\n",dp[i]);
    // }
    printf("%d\n",dp[n-1]);
}