/*
정수 n을 "1,2,3" 세 숫자의 합으로 나타내는 방법의 수를 구하자
(순서가 상관이 있다.)

풀이 1
1. n을 나타내려면 n-1, n-2, n-3에 각각 1,2,3을 더하면 된다.
    즉, n을 나타내는 방법의 수는 n-1,n-2,n-3을 나타내는 방법의 수의 합이다.
*/

#include <iostream>
#include <vector>

using namespace std;

vector<int> dp(11,0);

int main(void){

    dp[1] = 1;
    dp[2] = 2;
    dp[3] = 4;
    for(int i = 4; i < 11; i++){
        dp[i] += dp[i-1]+dp[i-2]+dp[i-3];
    }

    int t;
    cin >> t;
    while(t--){
        int n;
        cin >> n;

        printf("%d\n",dp[n]);
    }
}