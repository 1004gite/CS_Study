/*
수열이 주어진다.
수열중 일부를 뽑을 수 있고 이때 뽑힌 수열은 증가하는 수열이어야 한다.
뽑을 수 있는 수열들 중 합이 최대인 합을 구하자.

풀이 1
1. 시작점을 start로 하고 한칸씩 뒤로 밀며 조건에 맞으면 합을 업데이트한다.
2. 업데이트시 이전에 지나온 값들 중 지금 값보다 작은 값들만 고려할 수 있다.
3. 업데이트를 하며 지나왔기 때문에 이전 값들은 가능한 최대로 업데이트되어있고 이중 최대값을 뽑는다.
4. start를 밀어가며 반복한다.
5. 이떄 입력이 1000개이므로 O(N^2)으로 그냥 진행한다.
*/

#include <iostream>
#include <vector>

using namespace std;

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    cin >> n;
    vector<int> A(n);
    vector<int> dp(n);
    
    for(int i = 0; i < n; i++){
        cin >> A[i];
        dp[i] = A[i];
    }

    int result = 0;

    for(int i = 0; i < n; i++){
        int max = 0;
        for(int j = 0; j < i; j++){
            if(A[i] > A[j] && max < dp[j]) max = dp[j];
        }
        dp[i] += max;
        if(dp[i] > result) result = dp[i];
    }

    printf("%d\n",result);

}