/*
트리에서 점 또는 선을 제거했을 떄 2개 이상의 subtree(로 나누어지는지 판단한는 문제이다.

점을 제거할 때의 경우 2개 이상의 점과 연결되어 있다면(2개 이상의 간선이 있다면) 참이다.(leaf node가 아니라면)
선을 제거할 경우 1개짜리도 tree이기 때문에 무조건 참이다.

node의 개수가 주어지고 1번부터 n-1번까지의 번호를 가지기 때문에 배열에 할당한다.
*/

#include <iostream>
#include <vector>

using namespace std;

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    cin >> n;
    vector <int> nodes(n+1,0); // index = 노드번호, 원소 = 자신과 연결된 node의 개수

    int i = n*2 -2;
    while(i--){
        int a;
        cin >> a;
        nodes[a]++;
    }
    cin >> n;
    while(n--){
        int a,b;
        cin >> a;
        cin >> b;

        if(a == 2){
            printf("yes\n");
        }
        else{
            if(nodes[b] > 1){
                printf("yes\n");
            }
            else{
                printf("no\n");
            }
        }
    }

}