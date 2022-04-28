/*
// 1은 소수가 아니다...

범위 내에서 소수를 찾는 문제

풀이 1
1. 최대 1~10000이므로 배열을 이용해 10000 이하의 소수를 모두 찾는다.
(배수들에 대해 소수가 아님을 표시하는 방법)
*/

#include <iostream>
#include <vector>

using namespace std;

int main(void){

    int m,n; // m ~ n
    cin >> m >> n;

    vector<bool> table(n+1, true);
    table[1] = false; // 1에 대한 처리

    for(int i = 2; i <= n/2; i++){ // n/2 부터는 아래의 소수에서 처리되었음 -> x*y == y*x
                                    // 루트n 이하만 하는것이 더 정확
        if(table[i]){
            // 소수인 경우 배수 제거
            // 소수가 아니면 해당 수의 배수는 이미 액수에 의해 제거됨
            int tmp = 2;
            while(i*tmp <= n){
                table[i*tmp] = false;
                tmp++;
            }
        }
    }

    int min = n+1, sum = 0;
    for(int i = n; i >= m; i--){
        if(table[i]){
            min = i;
            sum += i;
        }
    }

    if(min == n+1){
        printf("-1\n");
    }
    else{
        printf("%d\n%d\n",sum,min);
    }

}