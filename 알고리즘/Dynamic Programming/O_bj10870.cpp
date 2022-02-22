/*
피보나치 수열을 구하는 문제

풀이 1
1. 이전 2개의 값을 보유한다.
*/

#include <iostream>

using namespace std;

int main(void){

    int n;
    cin >> n;

    long long pp = 0, p = 1; // 전전, 전

    if(n == 0) printf("%lld\n",pp);
    else if(n == 1) printf("%lld\n",p);
    else{
        n -= 1;
        while(n--){
            long long tmp = p;
            p = pp + p;
            pp = tmp;
        }
        printf("%lld\n",p);
    }
}