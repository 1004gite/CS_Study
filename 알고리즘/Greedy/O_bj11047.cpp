/*
N종류의 동전을 무한히 가지고 있다.
이때 동전 A(i)는 A(i-1)의 배수이다.
동전 가치의 합을 K로 만들때 최소한의 동전 개수를 사용하자.

풀이 1
1. Greedy하게 가장 값이 큰 동전순으로 가능한 많이 채워넣는다.
2. 이 문제에서 동전들은 배수 관계가 있기 때문에 큰 값과 작은 값 여러개는 항상 호환되고
    이는 큰 값을 먼저 넣어도 만들 수 있는 값의 종류가 달라지지 않음을 의미한다.
*/

#include <iostream>
#include <vector>

using namespace std;

int main(void){
    cin.tie(0);
    cin.sync_with_stdio(0);

    int n, k;
    cin >> n >> k;

    vector<int> coin;
    while(n--){
        int tmp;
        cin >> tmp;
        if(tmp <= k) coin.push_back(tmp);
    }

    int count = 0;
    for(int i = coin.size()-1; i >= 0; i--){
        count += k/coin[i];
        k %= coin[i];
        if(k == 0) break;
    }

    printf("%d\n",count);
}