/*
모든 자연수는 4개 이하의 수들의 제곱으로 표현할 수 있다.
가장 작은 개수의 자연수로 표현할 때 몇개의 자연수가 필요한가

====
처음에는 큰 수부터 더하려 했지만 큰 수부터 더하는 것이 제곱숭릐 개수를 최소로 만든다느 확신이 들지 않아 dp를 사용
===

풀이 1
1. 작은 수부터 더하면 4개로는 부족할 수 있다.
2. 큰 수부터 더하는것이 유리해보인다
    큰 수부터 더했을 때 오히려 작은 수 여러개를 더해야하는 경우가 생길수도 있을 것 같다.
3. n은 (n-i) + i 로 나태낼 수 있고 n-i와,i를 이루는 제곱수들을 사용하게 된다.
    (n-i) + i는 가운데를 기준으로 대칭이다. (가운데 = (n+1)/2)
    // n이 제곱수면 1개로 만들 수 있다.
4. 2부터 가능한 경우 중 가장 작은 제곱수의 개수를 채택한다.

풀이 2
1. 풀이 1처럼 풀면 O(N/2 * N) 이되어 0.5초를 넘어갈 것 같다.
2. 제곱수는 하나의 제곱수로만 고성할 수 있으므로 1을 기록한다.
3. 어떤 수에 제곱수를 하나 더해서 수를 구성할 수 있다.
    이때, 어떤 수를 수성하는 제곱수의 개수 +1 만큼 제곱수를 사용하게 된다.
4. 수를 구성한 후 더 작은 개수를 선택한다.

풀이 3
1. 어떤 수를 만들 떄 어떤 수에 제곱수를 더해서 만들 수 있다.
    즉, 어떤 수에 제곱수를 뺀 수와 해당 제곱수를 더해서 만들 수 있다.
    따라서 어떤 수에서 제곱수를 뺀 수들 중 가장 작은 개수로 이루어져 있는 수를 선택한다.
*/

#include <iostream>
#include <vector>

using namespace std;

int main(void){
    
    int n;
    cin >> n;

    vector<int> dp(n+1, 100);
    vector<int> squares;

    // 제곱수들
    for(int i = 1; i*i <= n; i++){
        dp[i*i] = 1;
        squares.push_back(i*i);
    }

    int count = 4;
    while(count--){
        // 4개의 제곱수면 모든 수를 표현 가능하기 때문에 4번을 돌리며 최소 개수를 찾는다.
        for(int i = 1; i <= n; i++){
            for(int j = 0; j < squares.size(); j++){
                int now = i+squares[j]; // 기존 수에 제곱수를 더함
                if(now > n) break; // 범위를 넘어가는 경우
                // i에 제곱수를 하나 더한 것이 기존의 개수보다 작다면 업데이트
                if(dp[now] > dp[i]+1) dp[now] = dp[i]+1;
            }
        }
    }

    printf("%d\n",dp[n]);

}