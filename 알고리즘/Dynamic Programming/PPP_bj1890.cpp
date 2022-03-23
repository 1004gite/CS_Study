/*


====================================================================================
====== 귀찮다고 대충 넘어가지 말고 제발 제발 제발 제발 자료형 범위좀 잘 찾아보자 2시간이나 버렸다..=======
====================================================================================

n*n크기의 게임판이 있고 각각의 칸에는 숫자가 적혀있다.
각 칸에서는 쓰여있는 숫자만큼 오른쪽/아래 중 하나를 선택하여 이동할 수 있다.
게임의 목적은 가장 왼쪽 위에서 가장 오른쪽 아래로 도착하는 것이다.
이때, 목적지까지 도착할 수 있는 경우의 수를 구하자.

풀이 1
1. 도착점에 도달할 수 있는 경우의 수는
    한번의 이동으로 도착점에 갈 수 있는 점들까지 가는 경우의 수들의 합이다.
2. 가능한 움직임은 2가지 종류이고 점 하나마다 순차적으로 값을 더한다면
    경로를 더한 후 이미 고려된 경로가 업데이트되는 경우가 생길 수 있다.
3. 움직임은 왼쪽 혹은 아래로만 가능하므로 같은 열 또는 행에서는 한가지 움직임만 가능하다.
     첫 시작점의 값을 1로 주고 0열만 초기화한다.

풀이 2 -> 맞왜틀 -> 자료형이 문제였다.
1. 어떤 점까지 가는 경로를 고려할 때 그 점의 왼쪽과 위쪽 구역만 고려해도 된다.
    (고려하는 점이 오른쪽 끝점이 되는 느낌)
2. 해당 구역에서 바로 고려하는 점으로 넘어올수 있는 경우 그 점들까지 오는 경로를 더한다.
3. dp를 이용하여 저장하며 진행한다
    이떄, 한행씩 오른쪽으로 진행하면 바로 올 수 있는 왼쪽/위쪽 부분이 업데이트되어있는 상태이다.

풀이 3
1. 이동 방향은 오른쪽/아래쪽 만가능하기 때문에 0행만 고려할 때 왼쪽에서부터 갈 수 있는 곳을 업데이트하면 문제가 없다.
2. 0행에서 내려올 수 있는 곳을 업데이트 후 1행도 왼쪽부터 업데이트한다.
    이때, 1행에서 내려올 수 있는 곳이 업데이트 되어있으므로 위쪽 방향은 고려된다.
3. 모든 행에 순차적으로 적용한다.
4. 이동 가능 범위가 0인경우를 예외처리하여 자기 자신을 더하지 못하도록 한다.
*/

#include <iostream>
#include <vector>

using namespace std;

vector< vector<unsigned long long> > board(100, vector<unsigned long long>(100,0));
vector< vector<unsigned long long> > dp(100, vector<unsigned long long>(100,0));

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    cin >> n;

    for(int i = 0; i < n; i++){
        for(int j = 0; j < n ; j++){
            cin >> board[i][j];
        }
    }

    dp[0][0] = 1;
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n ; j++){
            if(board[i][j] == 0) continue;
            // i행 오른쪽 적용
            int col = j + board[i][j];
            if(col < n) dp[i][col] += dp[i][j];
            // i행 아래로 적용
            int row = i + board[i][j];
            if(row < n) dp[row][j] += dp[i][j];
        }
    }

    printf("%llu\n",dp[n-1][n-1]);


    // printf("==debug==\n");
    // for(int i = 0; i < n; i++){
    //     for(int j = 0; j < n ; j++){
    //          printf("%llu ",dp[i][j]);
    //     }
    //     printf("\n");
    // }
}








// #include <iostream>
// #include <vector>

// using namespace std;

// vector< vector<unsigned long long> > board(100, vector<unsigned long long>(100,0));
// vector< vector<unsigned long long> > dp(100, vector<unsigned long long>(100,0));

// unsigned long long getCount(int i, int j){
//     if( i == 0 && j == 0) return 1;

//     unsigned long long count = 0;
//     // i행중 j열이하의 원소
//     for(int col = j-1; col >=0 ; col--){
//         if( j == (col + board[i][col]) ) count += dp[i][col];
//     }
//     // j열중 i행 이하의 원소
//     for(int row = i-1; row >= 0; row--){
//         if(i == row+board[row][j]) count += dp[row][j];
//     }
//     return count;
// }

// int main(void){

//     cin.tie(0);
//     cin.sync_with_stdio(0);

//     int n;
//     cin >> n;

//     for(int i = 0; i < n; i++){
//         for(int j = 0; j < n ; j++){
//             cin >> board[i][j];
//         }
//     }

//     dp[0][0] = 1;
//     for(int i = 0; i < n; i++){
//         for(int j = 0; j < n ; j++){
//             dp[i][j] = getCount(i,j);
//         }
//     }

//     printf("%llu\n",dp[n-1][n-1]);


//     // printf("==debug==\n");
//     // for(int i = 0; i < n; i++){
//     //     for(int j = 0; j < n ; j++){
//     //          printf("%llu ",dp[i][j]);
//     //     }
//     //     printf("\n");
//     // }
// }