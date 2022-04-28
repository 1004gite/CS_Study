/*

포인터, malloc 관련 꼬이는 것 주의하기

배열을 이용한 tree가 아닌 주소값을 이용한 tree를 만들어보려 시도했다.
corrupted list라는 런타임 오류가 떴는데 이는 동적 메모리 할당에 있어 해제를 해주지 않았기 때문이다.
(프로그램이 끝나지 않으면서 계속 할당을 해서 꼬인 것 같다.)
메모리 할당을 해제하려면 어차피 구조체의 주소값을 전부 보유해야 하기 때문에 차라리 배열로 tree를 구현하는 것이 합리적이다.

수열이 들어오면 특정 규칙을 통해 트리를 만든다.

규칙은 다음과 같다.
0. 처음 들어오는 수는 root이고 다음부터 들어오는 수는 항상 root+1 보다 크다.
1. 수가 연속해서 1씩 증가하게 들어온다면 그만큼은 한 집합이다.
2. 한 집합은 자식이 없는 node의 자식이 된다 (수 작은순)
3. 수열은 오름차순으로 들어온다.

이 트리에서 특정 수가 주어졌을 때 형제 node의 수를 구하자
- 형제 node란 부모가 다르며 부모끼리는 같은 부모를 갖는 node 이다.

풀이 1
1. node에 부모정보와 depth정보를 저장한다.
2. 부모를 적절히 할당해 tree를 완성하며 k의 index를 기록한다.
3. 같은 depth를 가지는 node들 중 부모는 다르고 부모의 부모는 같은 node의 개수를 센디
    - 이때 n은 1000보다 작으므로 최대 999의 시간 복잡도가 된다. O(1)
*/

#include <iostream>
#include <vector>

using namespace std;

struct node{
    int val, parent, depth;
    node(int v, int p, int d) : val(v), parent(p), depth(d) {}
};

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    while(1){
        int n, k;
        cin >> n;
        cin >> k;
        if(n == 0) break;

        vector<node> tree(n, node(-1,-1,-1));

        // root와 2번쨰 인자는 따로 설정
        cin >> tree[0].val;
        tree[0].depth = 0;

        int nochild = 0; // child가 없는 가장 작은 node의 index // 1씩 증가
        int kindex = -1;

        for(int i =1; i < n; i++){
            cin >> tree[i].val;
            if(tree[i].val == k){
                kindex = i;
            }

            if(tree[i].val == tree[i-1].val+1){
                // 연속된 숫자가 들어온 경우 같은 부모를 가진다.
                tree[i].parent = tree[i-1].parent;
                tree[i].depth = tree[i-1].depth;
            }
            else{
                // 연속되지 않을 경우 자식이 없는 가장 작은 node의 자식이 된다.
                tree[i].parent = nochild;
                tree[i].depth = tree[nochild].depth+1;
                nochild++;
            }
        }
        int count = 0;
        for(int i = 0; i < n; i++){
            // printf("val: %d, depth: %d, parent: %d\n",tree[i].val,tree[i].depth,tree[tree[i].parent].val);
            if(tree[i].depth != tree[kindex].depth) continue;
            if(tree[i].parent == tree[kindex].parent) continue;
            if(tree[tree[i].parent].parent == tree[tree[kindex].parent].parent) count++;
        }

        printf("%d\n",count);
    }
}