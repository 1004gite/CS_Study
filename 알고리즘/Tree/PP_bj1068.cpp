/*
최대 node 개수가 50인 tree가 주어짐
각 node는 부모 정보만 가지고 있음

1. node의 갸수가 많지 않으므로 배열을 이용한다.
    leaf node의 개수를 출력하는 문제이고
    node를 지워 leaf node가 될 수도 있으므로 자신을 부모로 삼는 node의 개수 정보를 관리한다.
-- int가 2개 들어있는 구조체 50개를 사용했더니 128MB이상의 메모리를 사용했다며 메모리 초과가 발생했다.
-- int 1개에 4B, 100개에 400B 인데 왜인지 모르겠다. (char로 변경해도 똑같이 메모리 초과 발생)

==> vector를 사용할시 index를 잘못 참조해서 일어난 결과였다.
    배열의 경우 잘못된 index를 참조하면 에러가 발생하지만 vector는 다른 주소로 가버려 무한루프에 빠질 수 있다.
    해당 문제의 경우 무한루프에 빠졌는데 실행시간보다 메모리 제한이 먼저 걸렸다.
*/

#include <iostream>
#include <vector>

using namespace std;

struct node{
    int parent, leafcount;
    node(int p, int lc) : parent(p), leafcount(lc) {}
};

int main(void){

    int n,tmp;
    cin >> n;

    vector<node> tree(n, node(-1,0));

    for(int i = 0; i < n; i++){
        cin >> tmp;
        if(tmp == -1){
            // root node면 변경할 정보 없음
            continue;
        }
        tree[i].parent = tmp; // 부모 설정
        tree[tmp].leafcount += 1; //부모의 leaf 추가
    }

    // 몇번 node를 떨어뜨리는지
    cin >> tmp;

    // root leaf를 제거하면 바로 0 출력
    if(tree[tmp].parent == -1){
        printf("0\n");
        return 0;
    }

    // 부모의 leaf 하나를 없앤다.
    tree[tree[tmp].parent].leafcount -= 1;

    int count = 0;

    for(int i = 0; i < n; i++){
        if(tree[i].leafcount > 0) continue; // leaf가 아니면 다음 node 검사

        int index = i;
        // leaf 후보라면 조상 중에 없어진 node(tmp)가 있는지 검사
        while(index != -1){
            if( index == tmp ){
                // 없어진 node 이기 때문에 중단
                break;
            }
            index = tree[index].parent;
        }
        if(index == -1){
            // root 까지 올라갔다면 유효한 leaf이다.
            count++;
        }
    }
    printf("%d\n",count);
}