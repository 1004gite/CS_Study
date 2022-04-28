/*
어떤 수 N보다 크거나 같고 소수이면서 팰린드롬인 가장 작은 수를 찾아라

풀이 1
1. N보다 크거나 같은 수를 하나씩 판단한다.
(소수는 평균적으로 logN마다 나오고 하나를 검사하는데 약 O(N)이므로 그냥 하나씩 검사한다.)
*/

#include <iostream>
#include <string>

using namespace std;

bool checkPrime(long long n){
    if(n == 1) return false;
    // 루트n이 i보다 조금 커도 어차피 i까지 검사
    for(long long i = 2; i <= n/i; i++){
        if(n%i == 0) return false;
    }
    return true;
}

bool checkPalindrom(long long n){
    string s;
    while(n > 0){
        s.push_back((char)('0'+n%10));
        n /= 10;
    }
    int start =0, end = s.size()-1;
    while(end > start){
        // end == start이먄 한개짜리로 팰린드롬임
        if(s[start++] != s[end--]) return false;
    }
    return true;
}

int main(void){

    long long n;
    cin >> n;

    while(1){
        // 팰린드롬 검사가 더 적은 시간을 소요하므로 먼저 검사
        if(checkPalindrom(n) && checkPrime(n)) break;
        n++;
    }
    printf("%lld\n",n);
}