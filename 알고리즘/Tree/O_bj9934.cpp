/*
완전 이진 트리에서의 중위 순회 순서가 주어졌을 때 트리의 구조를 예측하는 문제이다.
배열로 tree를 구현할 때, 부모 = (index-1)/2, 좌측 = index*2+1, 우측 = index*2+2

k는 최대 10이고 (2^10 -1) 의 node는 10의 depth를 갖는다.

1. 트리의 크기는 주어진다 -> 중위 순회하며 받은 입력만큼 값을 넣어 트리를 채운다.
-> depth가 최대 10이므로 재귀로 구현한다.
*/

#include <iostream>
#include <vector>

using namespace std;

void middle(vector<int> &tree, int index){
    // 중위탐색으로 돌며 값을 넣는다.
    int left = index*2+1, right = index*2+2;
    if(left < tree.size()){
        middle(tree, left);
    }
    cin >> tree[index];
    if(right < tree.size()){
        middle(tree, right);
    }
}

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    // k가 최대 10이므로 하드코딩
    int power[] = {1,2,4,8,16,32,64,128,256,512,1024};

    int k;
    cin >> k;

    vector<int> tree(power[k]-1);

    middle(tree, 0);

    // depth가 d이면 해당 depth에는 2^(d-1)개의 node가 있다.
    int count = 0, d = 0;
    for(int i = 0; i < tree.size(); i++){
        printf("%d ",tree[i]);
        if(++count == power[d]){
            printf("\n");
            count = 0;
            d++;
        }
    }

}