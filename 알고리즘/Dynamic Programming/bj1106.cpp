/*
홍보비용, 홍보 효과(늘어나는 고객수)가 각 도시마다 주어진다.
홍보비용,홍보효과는 정수배로 적용 가능하다 (나눈기는 안된다.)
각 도시의 잠재적 고객은 무한하다.
고객을 최소 c명 늘이기 위해 투자해야 하는 돈의 쵯소값은?

풀이 1
0. 도시의 개수는 20개로 매우 작다. 하지만 도시 하나당 선택 가능한 경우의 수는 최대 1000개이다.
1. 최소 c명이기 때문에 c명이 넘어도 된다.
2. 정수배로만 곱할 수 있기 때문에 효율이 좋은 도시만 타게팅해도최적의 해가 아닐수 있다.
3. 구해야 하는 값은 돈이고 이때 최솟값을 구해야 한다.
    고객이 c명 이상이라면 투자할 돈을 더 줄일 수 있다.

풀이 2
1. 명수(c)를 기준으로 dp 진행
    index는 사람수, value는 최소비용
2. dp[i- info[x].사람수 ].비용 + dp[사람수].비용으로 검사한다.

*/

#include <iostream>
#include <vector>
#include <utility>

using namespace std;

vector<int> dp(2000, 100001);

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int c,n;
    cin >> c >> n;

    // 고객수 - 비용
    vector< pair<int,int> > info(n);
    for(int i = 0; i < n; i++){
        cin >> info[i].second >> info[i].first;
    }

    int min = 100001;
    dp[0] = 0;
    for(int i = 1; i < 2000; i++){

        for (int x = 0; x < n; x++){
            if( (i - info[x].first) < 0) continue;
            int tmp = dp[i - info[x].first] + info[x].second;
            if( dp[i] > tmp ) dp[i] = tmp;
        }
        if( i >= c && dp[i] < min) min = dp[i];

    }

    printf("%d\n",min);
}