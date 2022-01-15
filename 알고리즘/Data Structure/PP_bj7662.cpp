/*
vector를 sort하는 방법 이용
-> 정렬 O( N*NlogN) -> 시간초과

최대 heap을 시용하되 최솟값을 찾을 때는 leafnode에서 찾은 후 재정렬한다.
-> heap 정렬 O( N*logN )
-> 하지만 삭제 시에 최소값을 찾는 과정에서 O(N/2) 즉 O(N*N)이 소요된다.

priority queue를 두개 사용하자
이때, 동기화를 위해 이미 쓴 원소인지 확인하는 vector를 하나 둔다.
-> O( N*logN )
*/

#include <iostream>
#include <vector>
#include <queue>

using namespace std;

struct Node{
    int val, num;
    Node(int a,int b) : val(a), num(b) {}
};

// priority queue의 비교연산자를 만들 때는 sort와는 반대로 b를 기준으로 하자
struct cmpde{ // for max heap
    bool operator()(Node a, Node b){
        return a.val < b.val;
    }
};
struct cmpa{ // for min heap
    bool operator()(Node a, Node b){
        return a.val > b.val;
    }
};

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int t;
    cin >> t;
    while(t--){
        int k;
        cin >> k;

        priority_queue<Node, vector<Node>, cmpa> ascending;
        priority_queue<Node, vector<Node>, cmpde> descending;
        vector<bool> vaild;

        while(k--){
            char order;
            int value;

            cin >> order;
            cin >> value;

            if(order == 'I'){
                // 추가하는 경우
                // 오름, 내림에 둘다 추가
                Node node(value,vaild.size());
                ascending.push(node);
                descending.push(node);
                vaild.push_back(true);
            }
            else{
                // 제거하는 경우
                if(value == -1){
                    //최솟값 제거
                    // 이미 제거된 값들 제거
                    while(!ascending.empty() && !vaild[ascending.top().num]){
                        ascending.pop();
                    }
                    if(ascending.empty()){
                        continue;
                    }
                    // vaild 표시 후 제거
                    vaild[ascending.top().num] = false;
                    ascending.pop();
                }
                else{
                    // 최대값 제거
                    // 이미 제거된 값들 제거
                    while(!descending.empty() && !vaild[descending.top().num]){
                        descending.pop();
                    }
                    if(descending.empty()){
                        continue;
                    }
                    // vaild 표시 후 제거
                    vaild[descending.top().num] = false;
                    descending.pop();
                }
            }
        }
        // 이미 제거된 값들 제거
        while(!ascending.empty() && !vaild[ascending.top().num]){
            ascending.pop();
        }
        while(!descending.empty() && !vaild[descending.top().num]){
            descending.pop();
        }
        if(descending.empty()){
            printf("EMPTY\n");
        }
        else{
            printf("%d %d\n",descending.top().val, ascending.top().val);
        }
    }

}