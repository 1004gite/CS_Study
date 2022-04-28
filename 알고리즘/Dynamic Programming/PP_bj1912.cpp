/*
주어진 수열을 발췌할 수 있고 이때 발췌한 수영은 연속해야 한다.
발췌한 수열의 합이 최대가 되게 하라

풀이 1
1. two pointer를 사용한다.
2. end를 한칸씩 밀며 더한다.
3. 만약 음수를 만났다면 음수가 끝날때까지 더한다.
    이떄, 현재값이 0보다 크다면 start를 유지할 가치가 있다.
    아니라면 음수 바로다음 양수부터 다시 시작한다.
*/

#include <iostream>
#include <vector>
#include <climits>

using namespace std;

int main(void){
    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    cin >> n;

    vector<int> A(n);
    for(int i = 0; i < n; i++){
        cin >> A[i];
    }

    int start = 0, end = 0;
    int max = INT_MIN;
    int now = INT_MIN;

    while(1){
        if(start == n || end == n) break;

        else if(A[end] < 0){
            // 음수를 만났을 떄의 처리
            if(now == INT_MIN) now = A[end];
            else now += A[end];
            end++;
        }
        else{
            // 양수를 만났을 떄
            if(now == INT_MIN) now = A[end];
            else now += A[end];
            end++;
        }

        if(now > max) max = now;
        //최대값이 0보다 작은 경우를 고려하여 최대값 업데이트 후 가치판단
        if(now <= 0){
            // 가치가 없는 경우
            start = end;
            now = INT_MIN;
        }
    }

    printf("%d\n",max);
}