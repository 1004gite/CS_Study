/*
두 자연수의 최소공배수를 구하자

풀이 1
1. gcd를 구한 후 두 수의 곱에서 나눈다.
2. 이때 수의 범위는 백만으로 큰 자료형을 쓴다.
*/

#include <iostream>

using namespace std;

long long gcd(long long a, long long b){
    if(a%b == 0) return b;
    return gcd(b,a%b);
}

int main(void){

    int t;
    cin >> t;
    while(t--){
        long long a,b;
        cin >> a >> b;
        long long x = (a > b)?gcd(a,b):gcd(b,a);
        printf("%lld\n",a/x*b);
    }
}

