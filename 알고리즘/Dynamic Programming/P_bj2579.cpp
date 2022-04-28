/*
규칙에 따라 계단을 오르고 이때 밟은 계단의 점수를 얻게 된다.
1칸을 가거나/ 1칸을 뛰어넘어 2칸을 갈 수 있다.
이때 연속해서 3개의 계단을 갈 수 없다.
얻을 수 있는 최대 점수를 구하자

풀이 1
1. 어떤 계단에 오는 방법은 이전 계단에서 1칸을 오르거나
    2칸 전에서 2칸을 오르는 방법이다.
2. 위 방법대로 고려하면 1칸을 3번 오르는 것을 파악하기 어렵다.
3. 어떤 계단을 오르는 방법을 고려할 때 3칸 밑에서 올라오는 경우를 고려한다.
    1칸+2칸/2칸+1칸 의 조합으로 올라온다면 어떤 경우에도 1칸씩 3번 올라오지 않게 된다.
4. 3번을 위해 3칸 전까지는 누적된 최대값을 이용하고 2칸전, 1칸전은 기본값을 이용해야 한다.

풀이 2
1. 풀이 1의 3번은 틀렸다.
    3개의 계단을 연속해서 밟는 경우는 1칸씩 두번 가는 경우이다.
2. dp에 2개의 정보를 저장한다.
    첫번쨰 정보는 한칸 전에서 왔을 때의 값이고 다른 하나는 2칸 전 계단에서 왔을 떄의 값이다.
    한칸 전 계단에서 오는 경우에는 이전 계단의 첫번째 정보를 이용하고
    두칸 전 계단에서 오는 경우는 두칸 전 계단의 정보 중 큰 것을 이용한다.
*/

#include <iostream>
#include <vector>
#include <utility>

using namespace std;

int main(void){
    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    cin >> n;

    vector<int> stair(301,0);
    for(int i =1; i <= n; i++){
        cin >> stair[i];
    }

    vector< pair<int, int> > dp(301, make_pair<int,int> (0,0));

    dp[1].first = stair[1];
    dp[2].first = stair[1]+stair[2];
    dp[2].second = stair[2];

    for(int i = 3; i <= n; i++){
        dp[i].first = dp[i-1].second+stair[i];
        int big = dp[i-2].first > dp[i-2].second ? dp[i-2].first : dp[i-2].second;
        dp[i].second = big + stair[i];
    }

    printf("%d\n",dp[n].first > dp[n].second ? dp[n].first : dp[n].second);

    // //debug
    // for(int i = 1; i <= n; i++){
    //     printf("[%d] %d\n",i,dp[i]);
    // }

}