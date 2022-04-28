/*
1. 트리의 지름이라는 개념을 잘못 이해해서 헤멘 문제
    - 트리의 지름은 두 node를 당겼을 때 원 안에 모든 node가 들어와야 하며 leaf가 아니면 그 하단에 node가 존재하기 때문에 지름의 끝점이 될 수 없다.
2. 트리의 지름을 구하는 공식이 있다.
    - 어떤 node에서 가장 밑까지 내려가는 길 중 가장 긴 길은 지름이 되는 점 2개중 1개이다.
    - 이를 이용히여 임의의 node에서 가장 긴 경로를 구한 후 그 점에서 또한번 가장 긴 경로를 구한다.
3. 처음 생각했던 것처럼 밑에서부터 비교히며 올라오려면 경로가 leaf에서 시작되었는지, leaf로 끝나는지 판단해야 한다.
*/

/*
트리의 지름을 구하는 문제이다.
최대 길이는 무조건 leaf에서 leaf로 가는 경로이다.
-> leaf에서 leaf로 가는것보다 큰 경로가 있을 수 있다.
    ex) root에서 1개의 node 에만 연결되어있고 root로 가는 경우

1. depth를 기준으로 검사한다.

2. 부모가 자식의 정보를 보며 판단해야 하기 때문에 자식의 정보가 필요하다.
    - node에 필요한 정보
    - 가중치 : 이 가중치는 부모와의 관계를 기준으로 갖는다.
    - 누적 가중치 : depth가 올라갈 때 가능한 최대가 되도록 가중치의 합으로 업데이트한다.
    - 자식의 정보

3. depth를 구할 떄는 root에서 출발하며 bfs 방식으로 depth별 queue에 넣는다.

4. depth를 기준으로 올라오면서 2가지 작업을 한다
    - 이번 node가 leaf라면 아무런 행동도 하지 않는다.
    - 이번 node까지 오는 경로 중 최장경로의 값을 보유한다.
    - 최대값의 후보는 leaf에서 leaf이므로 자식이 2개 이상인 경우에는 최대값과의 비교/갱신도 실시한다.
        - 노드가 2개인지 검사할 비용이면 차라리 누적값과 최대값을 항상 비교하자
        - 틀렸다. -> acc는 본인까지 오는 최장 경로로만 사용해야 하고 child가 2 이상일때만 경로 2개를 더해서 max를 찾아야 한다
            이때 child가 2개 이상이 아니면 더 길 수 없기 때문에 똑같이 크기비교하여 거른다.
            -> 최장경로는 길 2개의 합이고 accw는 길 1개의 최장거리이기 때문이다.
    - top에 도착할 떄까지 반복한다.
*/

#include <iostream>
#include <vector>
#include <queue>
#include <utility>

using namespace std;

// index는 node의 번호를 의미한다.
// 인자는 순서대로 인접한 node의 번호와 가중치를 의미한다.
vector< pair<int, int> > tmp;
vector< vector < pair<int, int> > > nodes(10001, tmp);
vector< bool> visited;
pair<int, int> long1(0,0); //최장거리, 최장점
pair<int, int> long2(0,0);

void dfs(int index, int acc, pair<int,int> & l){
    visited[index] = true; // 방문했음을 표시
    int size = nodes[index].size(); // 이번 node의 인접node 개수

    if(acc > l.first){
        l.first = acc;
        l.second = index;
    }

    for(int i = 0; i < size; i++){
        if(visited[nodes[index][i].first]){
            continue; //방문했다면 다음 차례로
        }
        dfs(nodes[index][i].first, acc + nodes[index][i].second, l); // 인접 node 방문
    }
}

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    cin >> n;

    int x = n;
    while(--x){
        int p,c,w;
        cin >> p;
        cin >> c;
        cin >> w;
        // p에 정보 넣기
        nodes[p].push_back(make_pair(c,w));

        // c에 정보 넣기
        nodes[c].push_back(make_pair(p,w));
    }

    // bfs로 내려가면서 탐색하면 끝날 때까지 무엇이 최장경로로 가는 길인지 모르기 때문에 값을 계속 보유해야 한다.
    // dfs로 leaf까지 내려가며 끝점에서 비교
    visited = vector<bool>(10001,false);
    dfs(1,0,long1);
    visited = vector<bool>(10001,false);
    dfs(long1.second,0,long2);

    printf("%d\n",long2.first);
}
