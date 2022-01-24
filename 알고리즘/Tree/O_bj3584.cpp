/*
루트가 있는 트리가 주어짐
두 정점이 주어졌을 때 두 노드의 가장 가까운 조상을 찾는 문제

조상이라는 개념이 들어가기 때문에 상하관계가 있다.
(최단 경로 중 가장 depth가 얕은 node를 찾는 것과 같다.)

풀이 1
depth 정보를 가진다면 depth를 맞춰 올라가면서 처음 만나는 곳을 찾는다.

풀이 2
한 node를 먼저 root까지 보내고 경로를 기록한다.
다음 node를 올리면서 경로가 처음 겹차는 곳을 찾는다.
*/

#include <iostream>
#include <vector>

using namespace std;

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int t;
    cin >> t;

    while(t--){

        int n;
        cin >> n;

        // 거슬러 올라가는 작업만 하기 때문에 부모 정보만 저장한다.
        // index를 node번호로 보고 원소를 부모번호로 본다.
        vector< int > tree(n+1, -1);
        vector< bool > visited(n+1, false); // 방문여부

        while(--n){
            int p, c;
            cin >> p;
            cin >> c;

            tree[c] = p;
        }

        int a, b;
        cin >> a;
        cin >> b;
        
        // a를 먼저 올린다
        while(1){
            if(a == -1){
                break;
            }
            visited[a] = true;
            a = tree[a];
        }
        // b가 올라가며 찾는다.
        while(1){
            if(visited[b]){
                printf("%d\n",b);
                break;
            }
            b = tree[b];
        }
    }

}