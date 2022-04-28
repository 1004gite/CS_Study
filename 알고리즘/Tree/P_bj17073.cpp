/*

맞추긴 했으나 어렵게 풀었다.
node의 개수는 최대 50만개로 어차피 배열에 모든 node의 정보를 가지고 있을거면 처음부터 50만개짜리 배열을만들자


물을 자식 node로 랜덤하게 내려준다.
더이상 상태가 변하지 않을 때 물이 있는 것들의 기댓값의 평균을 구하는 문제

1. 모든 물이 leaf node에 있을 때 상태변화가 없다.
2. 기댓값을 구하는 문제이기 때문에 균등하게 물을 내린다고 생각할 수 있다.
3. 물은 밑으로만 내려가고 현재 node의 자식에게만 내려가기 때문에 1번씩 시뮬레이션 하지 않고 한번에 물을 나누어주어도 똑같다.

1번이 항상 root이다.
node의 번호를 1~n이라고 가정한다.

*/

#include <iostream>
#include <vector>
#include <queue>

using namespace std;

struct node{
    double w;
    int p;
    vector<int> children;
    vector<int> friends;
};

void getP(vector<node> &tree, int target){
    // printf("the Target is %d\n",target);
    // target이 부모를 얻으면 친구들을 모두 자식으로 만든다.
    while(!tree[target].friends.empty()){
        int lastfriend = tree[target].friends.back();
        if(tree[lastfriend].p == -1){
            // printf("%d-%d 부모-자식\n",target,lastfriend);
            tree[lastfriend].p = target; // 친구의 부모를 나로
            tree[target].children.push_back(lastfriend); // 친구를 자식에 추가
            getP(tree, lastfriend); // 친구의 변화도 체크
        }
        tree[target].friends.pop_back();
    }

}

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    double w;
    cin >> n;
    cin >> w;

    node tmp;
    tmp.w = 0.0;
    vector<int> tmpv;
    tmp.children = tmpv;
    tmp.friends = tmpv;
    tmp.p = -1;
    vector<node> tree(n+1, tmp);
    tree[0].p = 0;
    tree[1].p = 0;
    tree[1].w = w;

    while(--n){
        int a,b;
        cin >> a;
        cin >> b;
        // printf("a_p: %d, b_p:%d\n",tree[a].p, tree[b].p);

        if(tree[a].p == -1 && tree[b].p == -1){
            // 둘다 부모가 없으면 친구관계
            tree[a].friends.push_back(b);
            tree[b].friends.push_back(a);
        }
        else if(tree[a].p != -1){
            // a만 부모가 있으면 b가 a의 자식으로 들어감 (root인 경우 포함)
            tree[a].children.push_back(b);
            tree[b].p = a;
            getP(tree,b);
        }
        else if(tree[b].p != -1){
            // 반대 상황
            tree[b].children.push_back(a);
            tree[a].p = b;
            getP(tree,a);
        }
    }


    // 내려가면서 물 균등분배
    int count = 0, index = 1;
    double totalw = 0.0;
    queue<int> list; // queue에 들어있는 node들의 children들을 방문한다.
    list.push(1);
    while(!list.empty()){
        for(int i = 0; i < list.size(); i++){
            // i는 이번 타겟으로 i의 chidren을 방문한다.
            int childrensize = tree[list.front()].children.size();
            for(int j = 0; j < childrensize; j++){
                // child에게 물을 나눠주고 list에 추가한다
                int childindex = tree[list.front()].children[j];
                tree[ childindex ].w = tree[list.front()].w / (double)childrensize;
                // printf("%f\n",tree[list.front()].w);
                if(tree[childindex].children.empty()){
                    // leaf node이면 계산에 추가
                    totalw += tree[childindex].w;
                    count++;
                }
                else{
                    // 아니면 검색 리스트에 추가
                    list.push(childindex);
                }
            }
            list.pop();
        }
    }

    printf("%f\n",totalw/((double)count));
}