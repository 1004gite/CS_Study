/*

풀긴 했지만 2번 풀이가 훨씬 효과적이다.

이진 검색 트리란 왼쪽 node는 부모보다 작고 오른쪽 node는 부모보다 큰 성질을 띄는 tree이다.

이진 검색 tree의 전위 순회한 결과가 주어진다.
후위 순회한 결과를 출력하자.

node의 개수는 최대 10000개이고 worstcase에서의 depth는 10000이다.
배열로 tree를 구현할 때 index로 관계를 정의하면 worstcase에 2^10000 -1 의 크기가 필요하기 때문에 구조체를 사용한다.

1. 전위 순회한 결과를 이용해 tree를 만든다.
    - 이번에 들어온 값이 이전에 들어온 값보다 작으면 왼쪽 node로 간다.
    - 이번에 들어온 값이 이전에 들어온 값보다 더 크면 부모 node의 오른쪽 node로 간다.

2. 그냥 처음 들어오는 원소가 root이기 때문에 고정하고 나머지는 들어오는 순서대로 배치한다.
    - 전위 순위로 들어오기 때문에 본인이 먼저 들어와서 들어오는 순서대로 판단해도 문제없다.

같은 구조체를 생성할 때 반복문 안에서 할당하지 않으면서 생성하면 같은 주소값으로 만들어진다.
*/

#include <iostream>
#include <vector>

using namespace std;

struct node {
    int value, num;
    node * parent;
    node * left;
    node * right;
    node(int v, int num, node * p, node * l, node * r) : value(v),num(num) , parent(p), left(l), right(r) {}
};

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int tmp, num = 0;
    cin >> tmp;
    // 첫번째 입력은 항상 top이다.
    node top(tmp, num++, nullptr, nullptr, nullptr);

    node * prenode = &top;
    node * nownode;

    // 트리 생성
    while(cin >> tmp){
        if(tmp == -1){
            break;
        }
        node * nownode = (node *) malloc(sizeof(node));
        nownode->num = num++;
        nownode->value = tmp;
        nownode->left = nullptr;
        nownode->parent = nullptr;
        nownode->right = nullptr;

        if(nownode->value < prenode->value){
            //지금 node가 이전 node의 왼쪽이다.
            // (왼쪽이 먼저 들어오기 때문에 더 작은 값이 들어왔다면 바로 왼쪽 node로 붙일 수 있다.)
            prenode->left = nownode;
            nownode->parent = prenode;
        }
        else if(nownode->value > prenode->value){
            // 이전에 봤던 node의 자식이 아닐 수 있기 때문에 자리를 찾는다.
            // 1. 본인보다 큰 값을 만나기 직전 또는 top까지 올라간다.
            // 2. 오른쪽 node가 없으면 오른쪽 node가 되고 있으면 빈자리가 있을 때까지 값에 맞게 움직인다.
            node * p = prenode;
            while(1){
                if(p == &top){
                    break;
                }
                if((p->parent)->value > nownode->value){
                    break;
                }
                p = p->parent;
            }
            while(1){
                if(p->value < nownode->value){
                    // 더 크면 비었는지 확인 후 오른쪽으로 이동
                    if(p->right == nullptr){
                        p->right = nownode;
                        nownode->parent = p;
                        break;
                    }
                    p = p->right;
                }
                else{
                    if(p->left == nullptr){
                        p->left = nownode;
                        nownode->parent = p;
                        break;
                    }
                    p = p->left;
                }
            }
        }
        // printf("now=%d, parent=%d\n",nownode->value,(nownode->parent)->value);
        // 지금 설정한 node를 prenode로 둔다.
        prenode = nownode;
    }

    // 방문 기록
    vector<bool> visted(num, false);

    nownode = &top;
    // 후위 순회 (좌, 우, 본인)
    while(1){
        // printf("the nownode info: val: %d, leftval: %d, rightval: %d\n",
        // nownode->value, (nownode->left == nullptr)? -1 : (nownode->left)->value,(nownode->right ==nullptr) ? -1 : (nownode->right)->value);

        bool leftvisted, rightvisted;
        if(nownode -> left == nullptr){
            leftvisted = true;
            // printf("none left\n");
        }
        else{
            leftvisted = visted[(nownode->left)->num];
        }
        if(nownode -> right == nullptr){
            rightvisted = true;
            // printf("none right\n");
        }
        else{
            rightvisted = visted[(nownode->right)->num];
        }
        // break;
        
        // 종료조건
        if(nownode == &top && leftvisted && rightvisted){
            printf("%d\n",nownode->value);
            break;
        }

        if(leftvisted == false){
            // printf("now=%d, go left\n",nownode->value);
            // 왼쪽을 방문하지 않았다면 방문
            nownode = nownode->left;
        }
        else if(rightvisted == false){
            // printf("now=%d, go right\n",nownode->value);
            // 왼쪽을 방문했는데 오른쪽을 방문하지 않았다면 방문
            nownode = nownode->right;
        }
        else{
            // 오른쪽, 왼쪽을 모두 방문했다면 본인을 출력 후(방문표시) now를 부모로 바꾼다.
            // leaf node인 경우에도 여기에 해당된다.
            printf("%d\n",nownode->value);
            visted[nownode->num] = true;
            nownode = nownode->parent;
        }
    }

}