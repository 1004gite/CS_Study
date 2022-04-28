/*
3.5 짜리 가방이 있다.
이 봉지로 정확히 N키로그램을 만든다.
이때, 최소한의 봉지만 사용하라

풀이 1
1. Greedy하게 접근한다
2. 5키로짜리로 n을 넘지 않을때까지 할당한다.
3. 3키로짜리도 남은 용량애 대해 n을 넘지 않을 떄까지 할당한다.
4. 5를 하나 빼고 3을 두개 넣으면 1이 증가한다.
5. 5를 먼저 채웠으므로 증가시켜야 하는 부분은 1~4이다.
6. 5를 하나 뺴야 총 1을 증가시킬 수 있으므로 5짜리가 충분히 있는지 확인한다.

풀이 2
1. DP로 접근
2. ikg의 봉지를 만드려면 i-3kg에 3짜리 봉지를 쓰거나
    i-5에 5짜리 봉지를 쓰는 방법이 있다.
3. 이 두가지 중 가장 작은 봉지 수를 쓰는 방법을 선택해 나가자

*/

#include <iostream>
#include <vector>

using namespace std;

int main(void){

    int n;
    cin >> n;

    vector<int> dp(n+1,5001); // 불가능한 경우 최솟값이 될 수 없게 한다.
    dp[3] = 1;
    dp[5] = 1;

    for(int i = 6; i < n+1; i++){
        int min = (dp[i-3] < dp[i-5]) ? dp[i-3] : dp[i-5];
        dp[i] = min+1;
    }

    if(dp[n] > 5000) printf("-1\n");
    else printf("%d\n",dp[n]);
}

// #include <iostream>

// using namespace std;

// int main(void){

//     int n;
//     cin >> n;

//     int five, three;

//     five = n/5;
//     n %= 5;
//     three = n/3;
//     n %= 3;

//     if(five < n) printf("-1\n");
//     else{
//         //five -= n;
//         //three += n*2;
//         printf("%d\n",five+three+n);
//     }
// }