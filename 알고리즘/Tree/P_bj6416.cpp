/*

====
논리는 제대로 짰으나 좀 복잡해지니까 잔실수가 많아져서 오래 걸렸다.
tree를 공부하던 중에는 바로 풀렸으나 나중에 풀면 어려울 수도 있다.
====


음수 2개를 받으면 모든 입력이 끝난다.
0 두개를 받으면 한 테스트에 대한 입력이 끝난다.
수 2개는 순서대로 u,v이고 u->v 를 의미한다.

tree일 조건
0. 크기가 0이면 트리이다.
1. 들어오는 간선이 없는 root가 존재
2. root를 제외한 모든 node는 들어오는 선이 존재
3. root에서 다른 node로 가는 길은 반드시 딱 1개 존재

풀이 1
node의 번호가 순서대로 나온다는 조건이 없기 때문에 배열의 index를 node번호로 사용할 수 없다.
u,v의 관계를 배열에 저장한 후 sort하여 tree를 구성한다. (배열애서의 v들은 정상적인 경우 root를 제외한 모든 node이다.)

- v를 기준으로 sort 한 후 자신을 가르키는 u를 값 대신 index로 바꾼다. (sort된 순서대로 들어가있기 때문에 쉽게 찾는다.)
    이때, 연속해서 나오는 node가 있으면 들어오는 간선이 여러개로 tree가 아니다.(2번조건 확인)
- root가 딱 1개인지 확인한다.

== 위 두 조건을 만족하면 아래의 조건도 자동으로 만족한다.
- 모든 목록에 대해 하나의 node로 향하는지 확인한다.
    하나의 node로 향하지 않는다면 root가 유일하지 않거나 중간에 간선이 끊긴 경우이다. (1, 3번 조건)
    (관계만 주어지므로 한개의 node만 동떨어져 있는 경우는 없다.)
*/

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct arrow{
    int u,v; // v가 주인공, u는 parent였다가 parentidnex로 바뀜
    arrow(int u, int v) : u(u), v(v) {}
};

bool cmpArrow(arrow a, arrow b){
    return a.v < b.v;
}

int findIndex(vector<arrow> &list, int u){
    // 2분탐색 (u->v 에서 u의 index를 찾는다.)
    // 목록에 없으면 나오는 간선은 있지만 들어오는 간선은 없다는 뜻으로 root이다.
    int index;
    int start = 0, end = list.size()-1;
    while(1){
        index = (end-start)/2 + start;
        if(list[index].v == u){
            return index;
        }
        else if(list[index].v < u){
            start = index +1;
        }
        else if(list[index].v > u){
            end = index -1;
        }

        if(start > end){
            // 찾는 원소가 없음 (root임)
            return -1;
        }
    }
}

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int k = 1;

    while(1){
        // 테스트케이스 반복
        bool check = true;
        vector< arrow > list;
        int u,v;
        int root = -1;
        while(1){
            // 테스트케이스 입력
            cin >> u; // node
            cin >> v; // parent
            if(u <= 0){
                break;
            }
            arrow tmp(u,v);
            list.push_back(tmp);
        }
        if(u <= -1){
            break;
        }
        if(list.size() == 0){
            printf("Case %d is a tree.\n",k++);
            continue;
        }
        sort(list.begin(), list.end(), cmpArrow);
        // ////////////
        // printf("\n======\n");
        // for(int i = 0; i < list.size(); i++){
        //     printf("(%d,%d) %d (u,v)index\n",list[i].u,list[i].v,i);
        // }
        // ///////////
        for(int i = 0; i < list.size(); i++){
            if(i > 0 && list[i].v == list[i-1].v){
                // 들어오는 간선이 2개 이상인 상황
                check = false;
                break;
            }
            // u는 부모 역할이고 부모 값을 가지는 node의 index를 찾는다.
            int index = findIndex(list, list[i].u);
            // u값을 index로 바꾼다.
            // v값이 node의 주인이 되고 u값은 부모의 index가 된다.
            if(index != -1){
                list[i].u = index;
            }
            else{
                // printf("Found the root!\n");
                if(root == -1){
                    // root 설정
                    // printf("Set the root!\n");
                    root = list[i].u;
                }
                else if(root != list[i].u){
                    // printf("The root already exist!\n");
                    //root가 이미 있는데 또 나온 상황
                    check = false;
                    break;
                }
            }
        }
        if(root == -1){
            // root가 없는 상황
            check = false;
        }
        // 들어오는 간선이 1개이고 root가 1개이면 무조건 root에서 모든 node로 단 하나의 길로 갈 수 있다.

        if(check){
            printf("Case %d is a tree.\n",k++);
        }
        else{
            printf("Case %d is not a tree.\n",k++);
        }
    }

}