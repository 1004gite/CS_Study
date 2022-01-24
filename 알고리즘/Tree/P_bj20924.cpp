/*
// 논리 설계에 있어 root같은 특수한 경우는 항상 고려하자

트리의 간선 정보가 주어진다.
이때 루트 node에서 출발하여 한 방향으로만 가는 구간의 길이를 줄기라 한다.
줄기의 길이와 줄기가 끝나는 곳에서부터 가장 긴 가지의 길이를 구하자

풀이 1.
1. 상하관계 없이 인접 node에 대한 정보를 node에 저장한다.
2. root에서 출발하여 인접 node가 2개 이하인 node는 줄기이다.
3. 줄기가 끝나는 곳 기준 가장 긴 가지를 찾는다.

틀렸습니다
1. 0이나 없는 경우 생각
2. vector의 범위를 벗어난 경우 생각
3. 논리구조 검토 -> root의 경우 예외처리를 해줘야했음
*/

#include <iostream>
#include <vector>
#include <utility>

using namespace std;

vector< vector < pair<int,int> > > tree(200001, vector< pair<int,int> >() );
vector< bool > visited(200001,false);

int dfs(int index, int len){
    int max = len;
    visited[index] = true;
    for(int i = 0; i < tree[index].size(); i++){
        if(!visited[ tree[index][i].first ]){
            int tmp = dfs(tree[index][i].first, len + tree[index][i].second);
            if(tmp > max) max = tmp;
        }
    }
    return max;
}


int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n, root;
    cin >> n;
    cin >> root;

    while(--n){
        int a,b,d;
        cin >> a;
        cin >> b;
        cin >> d;

        tree[a].push_back(make_pair(b,d));
        tree[b].push_back(make_pair(a,d));
    }


    int nindex = root;
    int stampsize = 0;
    int branchsize = 0;

    if(tree[nindex].empty()){
        // root만 있는 경우
        printf("0 0\n");
        return 0;
    };
    // root의 경우 줄기라면 무조건 1개의 node와만 연결되어야 한다.
    // root의 줄기가 여러개인 경우 바로 dfs 함수로 넘긴다.
    if( tree[nindex].size() == 1 ){

        while(tree[nindex].size() <= 2){
            // 갈 수 있는 방향이 2개 이하면 줄기이다.
            visited[nindex] = true;
            bool out = true;
            for(int i = 0; i < tree[nindex].size(); i++){
                if(!visited[ tree[nindex][i].first ]){
                    stampsize += tree[nindex][i].second;
                    nindex = tree[nindex][i].first;
                    out = false;
                    break;
                }
            }
            if(out) break;
        }
    }
    // 현재 nindex는 줄기의 마지막 부분(가지의 root)를 가리킨다.
    // dfs 탐색 (메모리가 1024MB로 넉넉 -> 재귀)
    branchsize = dfs(nindex, 0);

    printf("%d %d\n",stampsize,branchsize);
}