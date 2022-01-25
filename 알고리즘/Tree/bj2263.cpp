/*
tree의 node의 개수, inorder, postorder가 주어진다.
postorder를 출력하라

풀이 1
1. inorder는 왼쪽부터 오른쪽으로 출력한다.
2. postorder는 모든 node에 대해 left_subtree, right_subteee, node순으로 출력한다.
    -> 마지막에 나오는 node가 root이다. + depth가 깊은 것부터 나온다.
//  preorder를 출력하려면 depth가 얕은것부터, depth가 같다면 left부터 출력한다.

3. inorder에서 root를 기준으로 왼쪽/오른쪽을 각각의 subtree로 본다.
4. subtree의 node 중 postorder에서 늦게 나오는 node가 그 subtree의 root이다.
5. subtree의 root를 해당 subtree의 가장 앞에 위치시킨다.

*/

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct node{
    int post,in,val;
};
bool cmp(node a, node b){
    // inorder로 들어온 순서대로 정렬하기 위함.
    return a.in < b.in;
}
void makepre(vector<node> &tree,vector<int> &result, int start, int end, int count){
    // subtree는 start~end범위이다.
    // 해당 subtree의 root를 찾아 해당 subtree의 가장 앞으로 위치시킨다.
    // 이때 post값이 가장 큰 node가 (postorder에서 가장 늦게 들어온) root이다.
    int index;
    node max;
    max.post = -1;
    for(int i = start; i <= end; i++){
        if(max.post < tree[i].post){
            max = tree[i];
            index = i;
        }
    }

    result[start+count] = max.val; // 왼쪽 subtree일 경우 count를 늘려서 한칸 앞에 위치하게 한다.

    // left subtree
    makepre(tree,result,start,index-1,count+1);
    //right subtree
    makepre(tree,result,index+1,end,count); // 해당 subtree의 right트리일 경우 지금의 subtree가 밀린 만큼만 밀린다.
    
}
int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    cin >> n;

    vector< node > tree(n+1);

    for(int i = 1; i <= n; i++){
        // postorder로 들어온 순서 세팅 + 노트val 설정
        int tmp;
        cin >> tmp;
        tree[tmp].in = i;
        tree[i].val = i;
    }
    for(int i = 0; i <= n; i++){
        int tmp;
        cin >> tmp;
        tree[tmp].post = i;
    }  
    // inorder 순으로 정렬
    sort(tree.begin(),tree.end(),cmp);

    vector<int> result(n+1);
    makepre(tree,result,1,n,0);

    for(int i = 1; i <= n; i++){
        printf("%d ",tree[i]);
    }
    printf("\n");
}