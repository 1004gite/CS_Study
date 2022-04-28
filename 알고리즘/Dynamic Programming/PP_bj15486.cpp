/*
(상담을 하는 날짜, 걸리는 일수, 보수) 3가지 정보의 상담들이 주어진다.
n일동안 상담을 진행할 때 받을 수 있는 최대 보수는 얼마인지 구하자

풀이 1
1. 1일에서 3일이 걸리는 100원짜리 일을 한다면
    100원을 보유한 채로 4일부터 다른 일을 고려할 수 있다.
2. 마지막날의 입장에서 볼 때의 최대 수익 = 이전 날들까지의 최대 수익 중 최대값(+ 마지막날에 1일짜리 상담이 있으면 마지막날 수입)
3. dp와 2번 논리를 이용하여 x일 이전까지의 최대 수익을 저장한다.
4. 이전까지의 최대 수익은 maxHeap을 이용한다.

풀이 2 -> O(N^2)으로 시간초과
1. 풀이 1에서 x일을 고려할 때 x-3일에서 3일짜리 일을 할 수 있는 경우를 고려할 수 없다.
2. dp에는 x일 이전까지의 최대값을 저장한다.
3. 최대값 계산시에는 저장된 이전 dp들의 최대값 + (그 날에서 시작해 x일 이전에 끝낼 수 있다면) 그날에 받을 일
    을 고려한다.
4. 3번을 고려 시 x일에 끝나는 일까지 고려한다면 x일에 일을 시작하는 것에 차질이 생길 수 있다.

풀이 3
1. 상담이 걸리는 일수는 최대 50일 이므로 이전 50일까지만 검사하면 된다.
    n-51일 이전의 날들은 해당 날에 들어온 상담이 이전 n-1일에 고려된다.
    만약 n-51일 이후 아무 상담도 받지 않고 n일에 상담을 받는 것이 최대라면 이는 n-50 ~ n-1일에 고려된다.

풀이 4
1. 다른 사람들의 답이 효율이 더 좋아 새로 풀이를 작성한다.
2. x일에서 일을 할지 말지 고려한다.
    dp에 해당 날짜 이전까지의 누적합의 최대값을 저장할 때
    일을 할 경우 x+(걸리는 일수) 날짜에 x일이전까지의 누적합+x일에서 한 일의 보수 만큼의 값을 가지게 된다.
    일을 하지 않을 경우 x+1 날짜에 x일 이전까지의 누적합을 가지게 된다.
        이미 누적값이 있다면 더 큰 값으로 대체한다.
*/

#include <iostream>
#include <vector>

using namespace std;

vector<int> T(1500001); // 걸리는 일수
vector<int> P(1500001); // 보수
vector<int> dp(1500001,0);

int main(void){
    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    cin >> n;

    for(int i = 0; i < n; i++){
        cin >> T[i] >> P[i];
    }

    for(int i = 0; i < n+1; i++){
        // 일을 안하는 경우
        if(dp[i+1] < dp[i]) dp[i+1] = dp[i];

        // 일을 하는 경우
        int endDate = i + T[i] -1;
        if( endDate > n-1) continue; //상담 불가능
        if( dp[endDate+1] < dp[i]+P[i]) dp[endDate+1] = dp[i]+P[i];
    }

    printf("%d\n",dp[n]);
}



// #include <iostream>
// #include <vector>
// #include <queue>

// using namespace std;

// // 걸리는 날짜, 보수
// vector< vector<int> > list(2, vector<int>(1500001,0));
// vector<int> dp(1500001,0);

// int main(void){
//     cin.tie(0);
//     cin.sync_with_stdio(0);

//     int n;
//     cin >> n;

//     for(int i = 0; i < n; i++){
//         cin >> list[0][i] >> list[1][i];
//     }


//     for(int i = 1; i < n+1; i++){
//         int max = 0;
//         for(int j = i-1; j >= 0; j--){
//             if( i - j > 50) break;
//             int acc = dp[j];
//             // j일에 시작하여 x일이 걸리면 그 일은 x+(j-1)일에 끝남
//             if(list[0][j] + j-1 < i) acc += list[1][j];
//             if (max < acc) max = acc;
//         }
//         dp[i] = max;
//     }

//     printf("%d\n",dp[n]);
// }