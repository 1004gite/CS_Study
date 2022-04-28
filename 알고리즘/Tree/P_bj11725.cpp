/*
첫째 줄에 node의 개수가 주어진다.
둘째 줄부터는 연결되어있는 node의 관계가 하나식 주어진다.
각 숫자는 node의 번호이다.
root node는 1 이다.
2진 tree라는 언급은 없다.
연결된 두 node 중 어느 node가 부모인지를 파악해야 한다

1번 방법으로 해결함

1. 부모, 친구 관계를 만든다.
-> 두 node의 부모가 모두 없으면 친구 관계가 되고 친구 관계에서 둘 중 하나의 부모가 생기면 친구를 자식 node로 바꾼다.
-> 부모는 1개이고 친구는 여러개 있을 수 있다.
==> 재귀를 사용해서 메모리 초과 발생
==> 재귀를 while문으로 바꿔서 메모리 초과 해결

2. 크기가 n+1인 배열을 만든다.
-> 각 index는 node의 번호를 의미하고 각 원소는 부모의 번호를 의미한다.
-> 부모가 없을 때는 -1의 값을 가진다.
-> 입력이 들어왔을 때 부 node모두 부모가 없을 경우 임시저장 후 변화가 생길 때 다시 확인한다.
==> 시간초과 발생
*/

#include <iostream>
#include <queue>
#include <vector>

using namespace std;

struct node{
    int parent;
    vector< int > friends;
    node(int parent, vector<int> friends) : parent(parent), friends(friends) {}
};

// void getParent(vector<node> & result, int a, int b){
//     // a는 부모가 있고 b는 부모가 없는 경우 b의 부모를 a로 한다.
//     // 재귀로 반복한다.

//     result[b].parent = a;
//     for(int i = 0; i < result[b].friends.size(); i++){
//         // 친구들의 부모를 b로 바꾼다.
//         getParent(result, b, result[b].friends[i]);
//     }
//     // 친구관계를 없앤다.
//     result[b].friends.clear();
// }

void getParentWhile(vector<node> & result, int a, int b){
    // b의 부모를 a로 만든다.
    // b의 친구들의 부모를 b로 만든다.
    // 반복한다.

    int target = b;
    result[b].parent = a;

    while(1){
        // 친구가 있는 경우 밑으로 내려가며 친구의 부모를 본인으로 설정한다.
        // 친구가 없는 경우 부모 노드로 올라간다.
        // 이번 타겟이 b이고 더이상 b의 친구노드가 없으면 종료한다.
        if(target == b && result[b].friends.empty()){
            break;
        }

        // 만약 친구의 부모가 존재한다면 친구 목록에서 지운다.
        while(result[result[target].friends.back()].parent != -1){ //친구 index를 보았을 때 부모가 있다면
            result[target].friends.pop_back(); // 친구 목록에서 지움
        }
        // 이번 타겟의 부모를 설정
        int lastindex = result[target].friends.size()-1;
        if(lastindex == -1){
            // 친구가 없으면 부모 노드로 돌아감
            target = result[target].parent;
        }
        else{
            // 친구의 부모를 나로 만든 후 친구 목록에서 지운다.
            // 후에 친구를 타겟으로 만든다.
            int friendindex = result[target].friends[lastindex];
            result[friendindex].parent = target;
            result[target].friends.pop_back();
            target = friendindex;
        }
    }
}

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    cin >> n;

    // 각 index는 node번호를 의미하고
    // node에는 부모의 정보와 친구의 정보가 있다.
    vector<int> tmpfriends;
    node tmpnode(-1,tmpfriends);

    vector< node > result(n+1, tmpnode);

    // 1번 node를 top으로 만든다.
    result[1].parent = 0;

    while(--n){
        int a, b;
        cin >> a;
        cin >> b;

        if(result[a].parent == -1 && result[b].parent == -1){
            // 둘 다 부모가 없는 경우 서로 친구관계가 된다.
            result[a].friends.push_back(b);
            result[b].friends.push_back(a);
        }
        else if(result[a].parent != -1 && result[b].parent == -1){
            // a만 부모가 있는 경우 b의 부모는 a가 된다.
            // 이때 b의 친구들은 b를 부모로 삼는다.
            getParentWhile(result,a,b);
        }
        else if(result[a].parent == -1 && result[b].parent != -1){
            // 반대의 경우
            getParentWhile(result,b,a);
        }
        // 둘 다 부모가 있는 경우 아무런 행동도 하지 않는다.
    }

    for(int i = 2; i < result.size(); i++){
        printf("%d\n",result[i].parent);
    }
}