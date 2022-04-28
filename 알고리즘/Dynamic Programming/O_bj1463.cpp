/*
어떤 정수를 3가지 연산을 사용해 1로 만들어야 한다.
나누어 떨어질 경우 2or3으로 나눌 수 있고 1을 빼는 것도 가능하다.
최소한의 연산 횟수로 1을 만들자

풀이 1
1. 수가 빨리 줄어들수록 유리해 보인다.
    /3 -> /2 -> -1 순으로 적용하는 것이 유리해 보인다.
    하지만 10의 경우 1을 먼저 빼면 9로 만들 수 있기 때문에 위 논리의 반례가 된다.
2. 어떤 수 x에 3종류의 연산을 모두 적용하고 쓰인 연산의 개수를 기록한다.
    이때 기존에 개수가 기록되어 있다면 더 적은 수를 선택한다.
    -> O(3*N)
2_1. 어떤 수 x를 만들기 위해서 x/3, x/2, x-1을 검사한다.
    이 중 가장 작은 연산 횟수를 가지는 수를 선택한다.
    -> O(3*N)
3. 2_1이 메모리에 쓰는 연산이 더 적고 dp측면에서 의미적으로 맞는 것 같다.
*/

#include <iostream>
#include <vector>

using namespace std;

int main(void){

    int n;
    cin >> n;

    if(n == 1){
        printf("0\n");
        return 0;
    }
    else if(n < 4){
        printf("1\n");
        return 0;
    }

    vector<int> dp(n+1,0);
    dp[2] = 1;
    dp[3] = 1;


    for(int i = 4; i <= n; i++){
        int min = dp[i-1];
        if(i % 2 == 0 && dp[i/2] < min) min = dp[i/2];
        if(i%3 == 0 && dp[i/3] < min) min = dp[i/3];

        dp[i] = min+1;
    }

    printf("%d\n",dp[n]);
}