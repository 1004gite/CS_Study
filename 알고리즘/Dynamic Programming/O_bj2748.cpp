/*
피보나치 수 구하기
*/


#include <iostream>

using namespace std;

int main(void){

    int n;
    cin >> n;

    if(n <= 1) printf("%d\n",n);
    else{
        long long pp =0, p =1;
        n -=1;
        while(n--){
            long long tmp = p;
            p = p+pp;
            pp = tmp;
        }
        printf("%lld\n",p);
    }
}