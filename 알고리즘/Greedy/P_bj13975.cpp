/*
연속된 파일들이 있다.
이 파일들은 각각 용량값이 있고 두개의 파일을 합칠 때 두 파일의 합만큼 비용이 든다.
한번에 2개씩 파일을 합칠 수 있을 때 비용이 최소가 되게 하자

풀이 1 -> 매 순간 가장 적은 cost를 구하는데 O(N)이 들고 N번을 합쳐야 해서 시간이 오래걸린다
        -> 심지어 답이 틀렸다.
0. 1억개(100,000,000)의 계산에 보통 1초
    -> 최대 챕터 수 = 1,000,000
    -> 2초면 O(N^2)을 넘어서는 안된다.
1. 큰 파일을 먼저 합치게 되면 큰 파일의 비용이 계속 더해지므로 작은 파일을 먼저 합치는 것이 좋다.
2. 현재 상황에서 합칠 수 있는 가장 greedy한 방법으로 진행한다.
3. 파일을 합치며 그때그때 가장 작은 파일 2개를 업데이트 해야한다.
    또, 파일을 합칠 때 파일의 개수가 줄어들고 주변의 비용의 합이 증가한다는 점을 고려한다.

풀이 2
1. 어떤 파일을 합치면 파일의 총 개수가 1개 줄어들고 총 n-1번을 합쳐야 한다.
2. 파일을 합칠 수 있는 경우는 파일의 좌,우 뿐이며 모두 오른쪽과만 합친다고 생각하면 모두 고려할 수 있다.
3. 어떤 파일을 합치면 합쳐진 파일 왼쪽의 파일은 합칠 때 비용이 달라진다.
    즉, 어떤 상황에서 다음에 어떤 파일을 합칠지 고려할 때 바뀌는 비용도 고려해야 한다.
4. 어떤 파일을 합칠지 고르는 과정은 다음과 같다
    우선 (합칠때의 비용 + 합쳤을 때 변하는 양쪽의 값)을 구한다
    heap에 넣어 logN의 비용으로 최솟값을 구한다.

풀이 3
1. 소설을 이어붙이는 문제라 당연히 연속되어야 하는 줄 알았지만 연속되게 이어붙이지 않아도 되는 문제였다...
    -> heap사용...

*/

#include <iostream>
#include <vector>
#include <queue>

using namespace std;

struct cmp{
    bool operator()(long long a, long long b){
        return a > b;
    }
};

int main(void){
    cin.tie(0);
    cin.sync_with_stdio(0);

    int k;
    cin >> k;

    while(k--){
        int n;
        cin >> n;
        
        priority_queue<long long, vector<long long>, cmp> minheap;
        for(int i = 0; i < n; i++){
            long long tmp;
            cin >> tmp;
            minheap.push(tmp);
        }

        long long total = 0;
        while(minheap.size() > 1){
            long long a, b;
            a = minheap.top();
            minheap.pop();
            b = minheap.top();
            minheap.pop();

            total += a+b;
            minheap.push(a+b);
        }

        printf("%lld\n",total);
    }
}