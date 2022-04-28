/*
수열이 주어질 때 값이 점점 증가하게 부분수열을 만들 수 있다.
이때 수열의 길이가 가장 길도록 만들지

풀이 1
1. 모든 부분수열을 모두 검사하는 것은 모요가 크다.
2. 수열은 끝까지 검사해야 총 길이를 알 수 있기 때문에 한 시작점에 대해 끝까지 고려해야 한다.
3. i번째 수는 (0~i-1)번째 수들 중 i번쨰 수보다 작은 수 중에 가장 큰 길이 다음에 나오는 것이 유리하다.
*/

#include <iostream>
#include <vector>

using namespace std;

vector<int> A(1000);
vector<int> dp(1000,1);
int n;

int getMaxIndex(int index){
    // i번쨰 이전의 수들 중 더 작은 값이 있다면 그 중 최대 dp를 선택한다.
    int result = -1;
    int max = -1;
    for(int i = 0; i < index; i++){
        if(A[i] >= A[index]) continue;
        if(dp[i] > max){
            max = dp[i];
            result = i;
        }
    }
    return result;
}

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    cin >> n;

    for(int i = 0; i < n; i++){
        cin >> A[i];
    }

    for(int i = 1; i < n; i++){
        int index = getMaxIndex(i);
        if(index == -1) continue;
        dp[i] = dp[index] + 1;
    }

    int max = -1;
    for(int i = 0; i<n;i++){
        if(dp[i] > max) max = dp[i];
    }

    // if(max == 1) max--;
    printf("%d\n",max);
    
}