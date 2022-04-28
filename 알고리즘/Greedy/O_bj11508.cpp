/*
n개의 상품을 구매할 때 3개를 묶으면 가장 싼 하나의 가격이 빠진다.
n개의 상품과 각 가격이 주어질 때 가장 싸게 살 수 있는 가격을 구하자.

풀이 1
1. 비싼 제품이 흩어져있으면 싼 제품만 할인받을 수 있게 된다.
2. 가격순으로 내림차순 정렬 후 3번째마다 각격을 더하지 않으며 가격을 합산한다.
*/

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

bool cmp(int a, int b){
    return a > b;
}

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    cin >> n;

    vector<int> prices(n);
    for(int i = 0; i < n; i++){
        cin >> prices[i];
    }
    sort(prices.begin(), prices.end(),cmp);

    long long total = 0;
    for(int i = 1; i <= n; i++){
        if(i%3 == 0) continue;

        total += (long long)prices[i-1];
    }

    printf("%lld\n",total);

}