/*

// tree를 공부중이라 맞췄지만 나중에 풀면?

이진트리에서 같은 depth에 있는 node는 같은 행에 있다.
subtree들은 각각 root의 오른쪽 혹은 왼쪽에 몰려있고 열이 겹치지 않는다.
이때 너비가 가장 넓은 레벨(depth)과 너비를 구하라. (같은 너비면 레벨이 작은 것이 우선)


1. 이진 트리이기 때문에 좌/우만 존재 가능성이 있다.
2. 어떤 node가 생기면 해당 node를 기준으로 다른 node들이 좌우로 1칸 밀린다.
// inorder로 좌우 판단가능

풀이 1
1. node들에 level정보를 준다.
2. inorder로 좌측부터 돌며 레벨별 가장 낮은 x와 높은 x 좌표를 표시한다.
3. 레벨별 계산
*/

#include <iostream>
#include <vector>
#include <utility>

using namespace std;
struct node{
    int left, right;
};

vector< node > tree(10001);
vector< pair<int,int> > m(10001, make_pair(10001, -1)); // (최소, 최대) level은 최대 n까지 있을 수 있다.
int x = 1;

void getM(int index, int level){
    //left
    if(tree[index].left != -1) getM(tree[index].left, level+1);

    //node
    // 최소, 최대 검사
    if(m[level].first > x) m[level].first = x;
    if(m[level].second < x) m[level].second = x;
    x++;

    //right
    if(tree[index].right != -1) getM(tree[index].right, level+1);
}

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n, tmp;
    cin >> n;
    tmp = n;

    vector<bool> isroot(n+1,true);
    while(tmp--){
        int no,l,r;
        cin >> no;
        cin >> l;
        cin >> r;
        tree[no].left = l;
        tree[no].right = r;

        if(l != -1)
            isroot[l] = false;
        if(r != -1)
            isroot[r] = false;
    }

    int root;
    for(int i =1; i <= n; i++ ){
        if(isroot[i]){
            root = i;
            break;
        }
    }

    getM(root,1);

    int lev, width = -1;
    for(int i = 1; i <= n; i++){
        if(m[i].second == -1) break;
        // printf("lev: %d, w : %d\n",i,m[i].second - m[i].first + 1);
        if(width < (m[i].second - m[i].first + 1)){
            // 같으면 이전 level 사용
            lev = i;
            width = m[i].second - m[i].first + 1;
        }
    }
    printf("%d %d\n",lev,width);
}