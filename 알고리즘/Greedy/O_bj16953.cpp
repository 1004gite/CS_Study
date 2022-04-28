/*
정수 A를 B로 바꾼다.
가능한 연산은 {1. 가장 오른쪽에 1을 추가한다, 2. 2를 곱한다} 2가지이다.
최소 횟수를 구하라

풀이 1
1. 1번 연산은 10을 곱한 후 1을 더하는 것과 같다.
    또, 연산 후 첫째 자리가 1이 된다는 특징이 있다.
2. B에서 출발하여 반대로 연산한다.
    - B의 일의자리가 1인 경우 1번연산을 거꾸로 적용한다.
    - B의 1의자리가 짝수인 경우 1의자리가 1이 될때까지 2로 나눈다.(2번연산)
    - B가 A보다 작아지거나 B의 1의자리에 1이아닌 홀수가 나오면 불가능하다.
*/

#include <iostream>

using namespace std;

int main(void){
    long long a,b;
    cin >> a >> b;

    int count = 0;
    while(1){
        if(a == b){
            printf("%d\n",++count);
            return 0;
        }
        if(b%10 != 1 && b%2 != 0) break; // 1이아닌 홀수가 일의자리에 있는 경우
        if(b < a) break;
        
        if(b%10 == 1) b /= 10;
        else b /= 2;
        count++;
    }

    printf("-1\n");
}