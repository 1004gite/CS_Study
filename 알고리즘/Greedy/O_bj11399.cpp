/*
n개의 작업이 있고 각각의 소요시간이 있다.
한 작업이 끝나야 다음작업을 시작할 수 있다.
각 작업들의 대기시간을 포함하여 각 작업을 끝나치는데 걸리는 시간을 각각 더하자.
이때 가능한 합의 최소를 구하자

풀이 1
1. 각 작업의 총 시간은 (대기시간+작업시간)아고 작업시간은 절대적이다.
2. 대기시간은 앞의 작업시간들의 합이다.
3. 대기시간을 줄이기 위해 오름차순으로 작업한다.
*/

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

bool cmp(int a, int b){
    return a < b;
}

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    cin >> n;
    vector<int> p(n);

    for(int i =0; i < n; i++){
        cin >> p[i];
    }
    sort(p.begin(),p.end(),cmp);

    int total = 0;
    for(int i = 0; i < n; i++){
        // 본인 작업시간 + 본인 뒤가 본인을 기다리는 시간들
        total += p[i] * (n-i);
    }

    printf("%d\n",total);
}