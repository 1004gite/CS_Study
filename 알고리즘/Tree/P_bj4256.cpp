/*
어떤 이진tree의 전위, 중위 순회의 결과가 주어진다.
후위 순회 결과를 출력하자.
이때 tree는 complete가 아니다.

풀이 1
1. 전위 순회에서 가장 먼저 나온 node는 root이다.
    같은 subtree라면 depth가 얕은 것이 먼저 나온다.
2. 중위 순회에서 root를 기준으로 각각 왼쪽/오른쪽의 subtree이다.
3. 중위 순회에서 왼쪽 목록 중 전위 순회에서 가장 먼저 나온 node는 subtree의 root이다.
    마찬가지로 오른쪽 목록 중 가장 먼저 나온 node는 오른쪽 subtree의 root이다.
4. 전체 tree나 subtree나 전위 순회에서 나오는 순서는 같다.

5. root를 찾아 시작점을 만든 후 subtree의 root를 찾아나간다.

시간초과 -> 전위, 중위를 따로 저장 후 각각에서 검사하지 않고 배열 하나에 중위, 전위의 정보를 저장한다.
    -> 전위는 나온 순서를 기록하여 수가 작으면 depth가 얕은 것으로 본다.
    -> 중위도 나온 순서를 기록하고 중위를 기준으로 sort한다 O( NlogN )
    => subtree에서 root를 찾는 작업이 O( N^2 )에서 O( N ) 으로 줄어든다
*/

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct node{
    int pre, mid, val;
};
bool cmp(node a, node b){
    // 중위 순회 기준 먼저 나온 node 정렬 -> 중위 순으로 정렬
    return a.mid < b.mid;
}

void makepost(vector<node> &tree,vector<int> &result, int start, int end, int rint,int count){
    if(start > end) return;

    node min;
    min.pre = 1001;
    int index = -1;
    for(int i = start; i <= end; i++){
        // 범위 내 가장 작은 pre값을 가진 node를 찾는다 -> 범위는 중위기준(같은 subtree임) -> 전위에서 가장 먼저나옴 -> depth가 가장 얕음
        // 이때 범위는 같은 subtree이므로 수평방향은 고려하지 않고 가장 얕은 depth가 겹칠 일도 없다.
        if(tree[i].pre < min.pre){
            min = tree[i];
            index = i;
        }
    }
    //subtree에서 root는 후위에서 subtree의 end에 위치한다.
    result[rint] = min.val;
    // printf("node: %d left: %d~%d, right: %d~%d -- rint: %d\n",min.val,start,index-1,index+1,end,rint);
    // printf("node %d, count: %d, rint: %d\n",min.val,count, rint);
    // 좌 우 subtree에 대해 반복한다.
    makepost(tree, result, start, index-1, index-1-count, count); // left subtree의 마지막 자리에 root를 넣는다.
                                                            // 왼쪽 subtree는 앞으로 밀리지 않는다.
    makepost(tree, result, index+1, end, end-count-1, count+1); // right subtree의 마지막 자리는 뒤에 배치한 node의 수만큼 앞으로 밀린다.
}

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int t;
    cin >> t;
    while(t--){

        int n;
        cin >> n;

        vector< node > tree(n+1);

        for(int i = 1; i <= n; i++){
            int tmp;
            cin >> tmp;
            tree[tmp].pre = i; // 먼저 나온 node는 값이 작다.
            tree[i].val = i;
        }
        for(int i = 1; i <= n; i++){
            int tmp;
            cin >> tmp;
            tree[tmp].mid = i;
        }
        // 중위 순으로 정렬
        sort(tree.begin(),tree.end(),cmp);
        vector<int> result(n+1);
        
        makepost(tree, result, 1, n, n, 0);

        for(int i = 1; i <= n; i++){
            printf("%d ",result[i]);
        }
        printf("\n");
    }
}