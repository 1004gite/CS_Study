/*
양의 정수 n개가 주어질 떄 가능한 모든 쌍의 GCD의 합을 구하라
// 최대 100개이고 각 정수는 1,000,000을 넘지 않는다. 가능한 모든 조합은 nC2로 최대 100*99/2 개가 가능하다.
-> 합이 int의 범위를 넘을 수 있다.

풀이 1
1. gcd를 구하여 모두 더한다.
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

    int t;
    cin >> t;
    while(t--){
        int n;
        cin >> n;

        vector<int> list(n);
        for(int i = 0; i < n; i++){
            cin >> list[i];
        }

        long long sum = 0;
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                sum += (long long)gcd(list[i],list[j]);
            }
        }

        printf("%lld\n",sum);
    }
}