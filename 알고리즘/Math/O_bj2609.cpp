/*
두 자연수의 최대공약수 최소공배수 출력

풀이 1
1. 최대공약수 -> gcd // 소인수분해시 두수가 공통으로 가지는 소수들을 최대한 많이
2. 최소공배수 -> lcm (두 수의 곱 / 최대공약수) 
// 두 수를 곱한 값을 소수의 곱으로 봤을 때 공통으로 쓰는 공배수에서 소수를 제거하려면 공통된 액수만 제거해야함
// 그래야 두 자연수 모두 곱해서 공배수를만들 수 있음
*/

#include <iostream>

using namespace std;

int gcd(int a, int b){
    if(a % b == 0) return b;
    return gcd(b, a%b);
}

int main(void){

    int a,b;
    cin >> a >> b;

    int x = a > b ? gcd(a,b) : gcd(b, a);
    int y = a/x*b; // x는 어차피 공약수이기 때문에 혹시 모를 overflow방지를 위해 나누기 먼저

    printf("%d\n%d\n",x,y);

}