/*
문제는 문제번호/난이도/그룹의 정보가 있다
문제 번호를 찾아 문제를 삭제하는 작업이 있다 - sort
전체 중 난이도 기준 최상 최하를 뽑는 작업 - heap
그룹 중 난이도 기준 최상 최하를 뽑는 작업 - heap을 그룹별로 만들어야 함
전체 중 특정 난이도 바로 위 아래를 뽑는 작업 - sort

난이도나 문제 번호를 찾는 것은 O( N )으로 brute force하게 하는것이 나아보임
heap은 그룹별로 만들고 전체 최상 최하를 비교할 때는 각 그룹의 최상 최하끼리 비교하자
-> heap O( NlogN ), 찾기 O( N * N )

recommend3 명령어는 전체 문제에서 특정 난이도 L을 기준으로 L에 가장 가까운 크거나 작은 문제를 찾아야 한다.
이때 brute force하게 찾게 되면 시간 초과가 뜬다.

난이도와 그룹은 0~100 이므로 크지 않은 수인 것을 고려하자... 
-> 각 난이도별로 해당 index에 원소를 모아 난이도를 찾으면 sort없이 바로 접근 가능하다.

구조체를 쓸 때는 비어있는지 항상 먼저 확인하자!
*/

#include <iostream>
#include <queue>
#include <vector>
#include <climits>
#include <string>

using namespace std;

struct P{
    int pnum, level, group, vaildnum;
    P(int pnum, int level, int g, int vnum) : pnum(pnum), level(level), group(g), vaildnum(vnum) {}
};
struct Descending{ // 레벨 기준 maxheap
    bool operator()(P a, P b){
        if(a.level == b.level){
            return a.pnum < b.pnum;
        }
        return a.level < b.level;
    }
};
struct Ascending{ // 레벨 기준 minheap
    bool operator()(P a, P b){
        if(a.level == b.level){
            return a.pnum > b.pnum;
        }
        return a.level > b.level;
    }
};

void addP(
    vector<bool> &vaild,
    vector<P> &list,
    vector< priority_queue<P,vector<P>, Descending> > &Dvec,
    vector< priority_queue<P,vector<P>, Ascending> > &Avec,
    vector< vector<P> > &L)
    {
        int a,b,c;
        cin >> a; // 문제 번호
        cin >> b; // 난이도
        cin >> c; // 그룹
        P tmp(a,b,c, vaild.size());
        list.push_back(tmp);
        vaild.push_back(true);
        L[b].push_back(tmp);

        // 같은 그룹이 존재하는지 보고 있으면 같은 그룹에 넣는다.
        bool check = true;
        for(int i = 0; i < Dvec.size(); i++){
            if(Dvec[i].top().group == tmp.group){
                // 같은 그룹이면 체크 해제 후 그 힙에 넣음
                Dvec[i].push(tmp);
                check = false;
                break;
            }
        }
        if(check){
            // 없으면 새로운그룹 생성
            priority_queue<P,vector<P>, Descending> tmppq;
            tmppq.push(tmp);
            Dvec.push_back(tmppq);
        }

        check = true;
        for(int i = 0; i < Avec.size(); i++){
            if(Avec[i].top().group == tmp.group){
                // 같은 그룹이면 체크 해제 후 그 힙에 넣음
                Avec[i].push(tmp);
                check = false;
                break;
            }
        }
        if(check){
            // 없으면 새로운그룹 생성
            priority_queue<P,vector<P>, Ascending> tmppqa;
            tmppqa.push(tmp);
            Avec.push_back(tmppqa);
        }
}

void solved(vector<P> &list, vector<bool> &vaild,
    vector< priority_queue<P,vector<P>, Descending> > &Dvec,
    vector< priority_queue<P,vector<P>, Ascending> > &Avec)
    {
    // n번문제를 풀었을 때 그 문제의 vaild를 false로 만든다.
    int n;
    cin >> n;
    for(int i = 0; i < list.size();i++){
        // 같은 번호의 문제를 푼 후 다시 넣는 경우를 고려하여 문제 번호도 같고 vaild한 목록을 찾는다.
        if((list[i].pnum == n) && vaild[list[i].vaildnum]){
            vaild[list[i].vaildnum] = false;
            break;
        }
    }
    // 삭제연산은 solved가 전부이기 때문에 여기서 동기화까지 처리한다.
    for(int i = 0; i < Dvec.size(); i++){
        while(!Dvec[i].empty() && !vaild[Dvec[i].top().vaildnum]){
            Dvec[i].pop();
        }
    }
    for(int i = 0; i < Avec.size(); i++){
        while(!Avec[i].empty() && !vaild[Avec[i].top().vaildnum]){
            Avec[i].pop();
        }
    }
}

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    vector<bool> vaild; // 삭제되었는지 존재하는지 점사하기 위함
    vector<P> list; // 검색을 위함
    vector< priority_queue<P,vector<P>, Descending> > Dvec; // maxheap모음
    vector< priority_queue<P,vector<P>, Ascending> > Avec; // minheap 모음
    vector< vector<P> > L(101); // 레벨이 1부터 100까지이기 때문에 레벨 기준으로 원소들을 모아놓는다.


    int n;
    cin >> n;
    // 기존 문제리스트 받기
    while(n--){
        addP(vaild,list,Dvec,Avec,L);
    }

    cin >> n;
    while(n--){

        string order;
        cin >> order;

        if(order == "add"){
            addP(vaild,list,Dvec,Avec,L);
        }
        else if(order == "solved"){
            // 푼 문제를 찾아서 유효성을 false로 만든다.
            solved(list,vaild,Dvec,Avec);
        }
        else if(order == "recommend"){
            // 주어진 그룹에서 뽑는다.
            int g, x;
            cin >> g;
            cin >> x;
            if(x == -1){
                for(int i = 0; i < Avec.size(); i++){
                    if(Avec[i].empty()){
                        continue;
                    }
                    if(Avec[i].top().group == g){
                        printf("%d\n",Avec[i].top().pnum);
                        break;
                    }
                }
            }
            else if(x == 1){
                for(int i = 0; i < Dvec.size(); i++){
                    if(Dvec[i].empty()){
                        continue;
                    }
                    if(Dvec[i].top().group == g){
                        printf("%d\n",Dvec[i].top().pnum);
                        break;
                    }
                }    
            }
        }
        else if(order == "recommend2"){
            // 그룹 상관없이 가장 높거나 낮은 난이도.
            int x;
            cin >> x;
            if(x == -1){
                P tmp(INT32_MAX,INT32_MAX,0,0);
                for(int i = 0; i < Avec.size(); i++){
                    if(Avec[i].empty()){
                        continue;
                    }
                    if(tmp.level > Avec[i].top().level){
                        tmp = Avec[i].top();
                    }
                    else if(tmp.level == Avec[i].top().level && (tmp.pnum > Avec[i].top().pnum)){
                        tmp = Avec[i].top();
                    }
                }
                printf("%d\n",tmp.pnum);
            }
            else if(x == 1){
                P tmp(INT32_MIN,INT32_MIN,0,0);
                for(int i = 0; i < Dvec.size(); i++){
                    if(Dvec[i].empty()){
                        continue;
                    }
                    if(tmp.level < Dvec[i].top().level){
                        tmp = Dvec[i].top();
                    }
                    else if(tmp.level == Dvec[i].top().level && (tmp.pnum < Dvec[i].top().pnum)){
                        tmp = Dvec[i].top();
                    }
                }
                printf("%d\n",tmp.pnum); 
            }
        }
        else if(order == "recommend3"){
            int x, l;
            cin >> x;
            cin >> l;
            P tmp(0,0,0,0);
            if(x == -1){
                // L 보다 난이도가 작은 문제 중 가장 난이도가 큰 문제
                // 문제 난이도가 같다면 문제 번호가 큰 것을 선택한다
                // 적당한 문제가 없으면 -1을 출력한다.
                tmp = P(INT32_MIN,INT32_MIN,0,0);
                l--;
                while(l > 0){
                    for(int i = 0; i < L[l].size();i++){
                        if(vaild[L[l][i].vaildnum] && tmp.pnum < L[l][i].pnum){
                            tmp = L[l][i];
                        }
                    }
                    if(tmp.group != 0){
                        break;
                    }
                    l--;
                }
            }
            else if(x == 1){
                // L보다 문제 난이도가 크거나 같은 문제 중 난이도가 가장 작은 문제를 고른다.
                // 문제 난이도가 같다면 문제 번호가 작은 것을 선택한다.
                // 적당한 문제가 없다면 -1을 출력한다.
                tmp = P(INT32_MAX,INT32_MAX,0,0);
                while(l <= 100){
                    for(int i = 0; i < L[l].size();i++){
                        if(vaild[L[l][i].vaildnum] && tmp.pnum > L[l][i].pnum){
                            tmp = L[l][i];
                        }
                    }
                    if(tmp.group != 0){
                        break;
                    }
                    l++;
                }
            }

            if(tmp.group == 0){
                printf("-1\n");
            }
            else{
                printf("%d\n",tmp.pnum);
            }
        }
    }
}