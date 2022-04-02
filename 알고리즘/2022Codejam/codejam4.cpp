#include <iostream>
#include <vector>
#include <queue>

using namespace std;

struct node {
    int funFact;
    unsigned long long point;
    vector<int> pointMe;
    bool vaild;
    node(int f, unsigned long long p, vector<int> pm, bool v) : funFact(f), point(p), pointMe(pm), vaild(v) {}
};

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int t;
    cin >> t;

    for(int T = 1; T <= t; T++){
        int n;
        cin >> n;

        vector<node> list(n+1, node(0,0,vector<int>(),true));
        for(int i =1; i <= n; i++){
            cin >> list[i].funFact;
        }
        for(int i =1; i <= n; i++){
            cin >> list[i].point;
            list[list[i].point].pointMe.push_back(i);
        }


        // 고려할(마지막 노드들)의 index
        queue<int> indexs;
        // 시작점인 것들에서 출발하여 길이 1개면 거기까지 업데이트
        for(int i = 1; i <= n; i++){
            if(list[i].vaild == false) continue;
            // 시작점이 아니면 무시
            if(list[i].pointMe.size() > 0) continue;
            int tmp = i;
            while(1){
                // 마지막까지 갔으면 멈춘다.
                if(list[tmp].point == 0) break;
                // 길이 1개 이상이 되는 지점까지 간 경우
                if(list[list[tmp].point].pointMe.size() > 1) break;

                // 큰걸로 업데이트
                list[tmp].vaild = false;
                list[list[tmp].point].funFact
                    = list[list[tmp].point].funFact > list[tmp].funFact ? list[list[tmp].point].funFact : list[tmp].funFact;

                tmp = list[tmp].point;
            }
            // 시작점 추가
            indexs.push(tmp);
        }

        unsigned long long result = 0;
        while(!indexs.empty()){
            while(!indexs.empty() && list[indexs.front()].vaild == false){
                indexs.pop();
            }
            if(indexs.empty()) break;
            int i = indexs.front();
            indexs.pop();
            unsigned long long max = 0;

            int tmp = i;
            while(1){
                // 더이상 갈데가 없으면 지금의 최대값 사용
                if(list[tmp].point == 0) break;
                if(list[list[tmp].point].vaild == false) break;

                // 다음 노드에 오는 값들 중 작은 값이 가는 것이 유리
                unsigned long long min = 1000000000;
                int minIndex = 0;
                node next = list[list[tmp].point];
                for(int x = 0; x < next.pointMe.size(); x++){
                    if(min > list[next.pointMe[x]].funFact){
                        min = list[next.pointMe[x]].funFact;
                        minIndex = next.pointMe[x];
                    }
                }
                // 적절한 node에서 가는것으로 확정
                list[minIndex].vaild = false;
                list[list[tmp].point].funFact = list[list[tmp].point].funFact > list[minIndex].funFact ? list[list[tmp].point].funFact : list[minIndex].funFact;
                tmp = list[tmp].point;
            }
            result += list[tmp].funFact;
            list[tmp].vaild = false;

            // 본인이 아닌 다른 시작점이 선택됐을 경우를 대비하여 나중에 한번 더 확인
            indexs.push(i);

            // printf("debug| tmp=%d\n",tmp);
        }

        printf("Case #%d: %llu\n",T,result);
    }
}