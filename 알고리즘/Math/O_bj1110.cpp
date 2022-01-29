/*
각 자리를 더한 후 -> (기존 수의 1의자리 )(더한 수의 1의자리) 조합의 2자리 수를 만든다.
10보다 작은 수 -> 0을 붙여 2자리로
몇번을 돌아야 원래 수로 돌아오는가?

풀이 1
1. 10보다 작은 수 x가 올 경우 결과는 xx
2. 10보다 큰 경우 시뮬레이션
3. 반복하여 돌아가는 횟수를 시뮬레이션
*/

#include <iostream>

using namespace std;

int main(void){

    int n, origin;
    cin >> n;

    origin = n;
    int count = 0;
    while(1){
        count++;
        if(n < 10){
            n = n*10 + n;
        }
        else{
            int sum;
            sum = n/10 + n%10;
            n = (n%10)*10 + sum%10;
        }
        if(n == origin) break;
    }
    printf("%d\n",count);
}