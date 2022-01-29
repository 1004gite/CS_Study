/*
수열 A에서 X와 서로소인 것들의 평균을 출력하자

풀이 1
1. 최대공약수가 1이면 서로소이다.
2. 서로소가 되는 수들의 합이 최대 500,000,000,000 이므로 long long 사용
*/

#include <iostream>
#include <vector>

using namespace std;

int gcd(int a, int b){
    if(a%b == 0) return b;
    return gcd(b,a%b);
}

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    cin >> n;
    
    vector<int> list(n);

    while(n--){
        cin >> list[n];
    }

    int x;
    cin >> x;

    long long sum = 0;
    long long count = 0;

    for(int i = 0; i < list.size();i++){
        int a, b;
        if( x > list[i]){
            a = x;
            b = list[i];
        }
        else{
            a = list[i];
            b = x;
        }

        if(gcd(a,b) == 0){
            // 서로소이면
            sum += (long)list[i];
            count++;
        }
    }

    if(sum%count == 0){
        printf("%ld\n",sum/count);
    }
    else{
        // 오차는 10^(-6)까지 허용한다.
        // 즉, 10^(-5)까지는 값이 정확해야 한다.
        sum *= 100000;
        sum /= count;
        long double result = (long double) sum;
        result /= 100000;
        printf("%lf\n",result);
    }

}