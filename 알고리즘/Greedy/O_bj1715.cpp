/*
카드뭉치들이 여러개 있다.
카드뭉치 2개를 1개로 합치는 과정을 반복하여 1개의 뭉치로 만든다.
합치는 가정에서 뭉치의 합만큼의 비용이 든다.
최소비용으로 뭉치를 합치자.

풀이 1
1. 큰 카드뭉치를 먼저 합치면 나중에 또 합쳐야 하기 때문에 작은 뭉치를우선으로 합친다.
*/

#include <iostream>
#include <queue>
#include <vector>

using namespace std;

struct cmp{
    bool operator()(int a, int b){
        return a > b;
    }
};

int main(void){
    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    cin >> n;

    priority_queue<int, vector<int>, cmp> mh;
    while(n--){
        int tmp;
        cin >> tmp;
        mh.push(tmp);
    }

    int total = 0;
    while(mh.size() > 1){
        int a, b;
        a = mh.top();
        mh.pop();
        b = mh.top();
        mh.pop();
        total += a+b;
        mh.push(a+b);
    }
    printf("%d\n",total);
}