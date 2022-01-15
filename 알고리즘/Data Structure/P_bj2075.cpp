/*
입력이 N*N이다 M개의 입력이라고 하자.
sort하는 방법
-> O( M*MlogM )

각 줄마다 최대 heap을 만든다
만들어진 N개의 최대힙의 최대값끼리만 비교하여 N번째 큰 값을 얻는다.
-> 힙을 만드는데 O( M*logM )
-> 만들어진 힙에서 최대값을 빼는데 O( logM )
-> 최대값끼리의 비교에 O( logN )
=> 메모리 제한 발생

그냥 하나의 최소 힙을 쓰고 크기를 5로 제한한다.
이때 최소 힙의 top보다 큰 수가 들어올 때만 top을 없애고 이번 값을 넣는다
위에꺼에 비해 시간도 덜들고 공간도 덜쓴다. 하지만 세로기준 정렬되어있는 입력의 의미는 없어진다.
-> O( M*logN)
*/

#include <iostream>
#include <queue>
#include <vector>
#include <climits>

using namespace std;

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n, row, col;
    cin >> n;
    row = n;
    col = n;
    // n개의 최댓값 힙
    // priority queue의 비교연산자는 b가 기준이기 떄문에 최소힙을 위해 less를 써줌
    priority_queue<long, vector<long>, greater<long> > pq;

    // 처음 n개는 넣어두고 시작한다.
    col = n;
    while(col--){
        long val;
        cin >> val;
        pq.push(val);
    }
    while(--row){
        col = n;
        while(col--){
            long val;
            cin >> val;
            // 힙의 최소보다 최소를 빼고 크면 힙에 넣는다.
            if(pq.top() < val){
                pq.pop();
                pq.push(val);
            }
        }
    }

    // 5개의 원소는 가장 큰 5갸의 원소이고 top은 그중 최소이다.
    printf("%ld\n",pq.top());
}