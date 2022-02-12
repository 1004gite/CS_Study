/*
N자리수의 숫자가 주어진다.
여기서 숫자 k개를 지워야 할 때 얻을 수 있는 가장 큰 수를 구하라.
최대 500,000 자리가지 가능하기 때문에 string으로 수를 관리한다.

풀이 1
1. 가장 작은 수를 지우면 손실이 적다
2. 어떤 수를 지울 때 그 수의 앞부분은 자릿수를 손해보고 뒷자리는 변화가 없다.
3. 어떤 수를 지울 때 손실은 해당 수의 (앞자리들의90% + 해당 수)이다.
4. 가장 작은 수를 우선해서 뺀다
    같은 수가 있다면 최대한 앞에 있는 수를 빼서 다른 더 큰 숫자들의 손해를 최소화한다.
*/

#include <iostream>
#include <string>
#include <algorithm>
#include <vector>

using namespace std;

struct number{
    int num, index;
};

bool desc(number a, number b){
    if(a.num == b.num) return a.index < b.index;
    return a.num < b.index;
}

int main(void){
    int n,k;
    string num;
    cin >> n >> k >> num;

    vector<number> v(n);
    for(int i = 0; i < n; i++){
        v[i].index = i;
        v[i].num = num[i];
    }

    sort(v.begin(), v.end(), desc);
    // sort된 v의 앞에서 k개는 출력하지 않는다.
    for(int i = 0; i < k; i++){
        num[v[i].index] = 'x';
    }

    for(int i =0; i < n; i++){
        if(num[i] == 'x') continue;
        printf("%c",num[i]);
    }
    printf("\n");

}
