/*
이진 트리이기 때문에 배열로 트리를 구현한다.
노드의 개수는 최대 26개이기 떄문에 26칸의 배열을 만들고 한칸한칸을
알파벳 순서대로의 node로 본다.

최대 26개의 node만 있기 때문에 재귀로 구현해도 가능하다고 생각한다.
-> 부모 node의 정보를 가지고 있을 필요가 없다.
*/

#include <iostream>
#include <vector>

using namespace std;

struct node{
    int left, right;
    node(int l, int r) : left(l), right(r) {}
};

void front(vector<node> &tree, int index){
    // 전위
    printf("%c", 'A'+index);
    if(tree[index].left != -1){
        front(tree,tree[index].left);
    }
    if(tree[index].right != -1){
        front(tree, tree[index].right);
    }
}
void middle(vector<node> &tree, int index){
    // 중위
    if(tree[index].left != -1){
        middle(tree,tree[index].left);
    }
    printf("%c", 'A'+index);
    if(tree[index].right != -1){
        middle(tree, tree[index].right);
    }
}
void back(vector<node> &tree, int index){
    // 후위
    if(tree[index].left != -1){
        back(tree,tree[index].left);
    }
    if(tree[index].right != -1){
        back(tree, tree[index].right);
    }
    printf("%c", 'A'+index);
}

int main(void){

    int n;
    cin >> n;

    // 0~25 -> A~Z ('알파벳' - 'A')
    vector<node> tree(n, node(-1,-1));

    // tree 구축
    while(n--){
        char t, left, right;
        cin >> t;
        cin >> left;
        cin >> right;

        int target;
        target = t - 'A';

        if(left != '.'){
            tree[target].left = left - 'A';
        }
        if(right != '.'){
            tree[target].right = right - 'A';
        }
    }

    front(tree, 0);
    printf("\n");
    middle(tree, 0);
    printf("\n");
    back(tree, 0);
    printf("\n");
}