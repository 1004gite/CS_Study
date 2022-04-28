/*
난이도별 최소 최대값이 필요하다.
문제 번호로 원소를 제거할 일이 있다.

최소, 최대값이 필요하고 문제 번호를 찾아야 하기 때문에 sort가 먼저 생각났다.
하지만 그때그때 처리를 해야하기 때문에 O( N * NlogN )이 된다.

최하,최대 난이도를 위한 최소, 최대 힙을 사용한다.
삭제를 위해서는 문제 번호를 찾아야 하기 때문에 heap을 따로 구현한다.
삭제된 문제를 판단하기 위한 vector를 사용한다.
문제 삭제 시 문제 번호를 찾는 일은 따로 준비한 vector 에서 찾는다.
-> 문제 찾기 O( N*NlogN )  / 힙 정렬 O( NlogN )
// 문제번호를 찾는 것이 O( logN )이 걸려서 전체 시간복잡도가 증가하여 통과하지 못할 줄 알았는데
// 통과했다... 신기하다
*/

#include <iostream>
#include <queue>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

struct P{
    int Pnum, level, vaildnum;
    P(int a, int b, int c) : Pnum(a), level(b), vaildnum(c) {}
};
// 문제번호를 위한 최소힙
struct cmp{
    bool operator()(P a, P b){
        return a.Pnum > b.Pnum;
    }
};
struct levelasc{
    bool operator()(P a, P b){
        if(a.level == b.level){
            // 레벨이 같다면 문제 번호가 작은 것이 우선순위
            return a.Pnum > b.Pnum;
        }
        return a.level > b.level;
    }
};
struct leveldes{
    bool operator()(P a, P b){
        if(a.level == b.level){
            // 레벨이 같다면 문제 번호가 큰 것이 우선순위
            return a.Pnum < b.Pnum;
        }
        return a.level < b.level;
    }
};

void sync_vaild(
    priority_queue<P, vector<P>, leveldes> &levD,
    priority_queue<P, vector<P>, levelasc> &levA,
    vector<bool> &vaild){
    //top에 vaild가 유효한 것이 있을 때까지 뽑아냄
    while(!levD.empty() && !vaild[levD.top().vaildnum]){
        levD.pop();
    }
    while(!levA.empty() && !vaild[levA.top().vaildnum]){
        levA.pop();
    }
}



int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    priority_queue<P, vector<P>, leveldes> levD; // 레벨 최대힙
    priority_queue<P, vector<P>, levelasc> levA; // 레벨 최소힙
    vector<P> list;

    vector<bool> vaild;

    int n;
    cin >> n;
    // 기존에 있던 리스트 세팅
    while(n--){
        int pnum,level;
        cin >> pnum;
        cin >> level;
        P tmp(pnum,level,vaild.size());
        levD.push(tmp);
        levA.push(tmp);
        vaild.push_back(true);

        list.push_back(tmp);
    }
    cin >> n;
    while(n--){
        string order;
        cin >> order;
        if(order == "add"){
            int pnum,level;
            cin >> pnum;
            cin >> level;
            P tmp(pnum,level,vaild.size());
            levD.push(tmp);
            levA.push(tmp);
            vaild.push_back(true);

            list.push_back(tmp);
        }
        else if(order == "recommend"){
            int t;
            cin >> t;
            if(t == 1 && !levD.empty()){
                printf("%d\n",levD.top().Pnum);
            }
            else if(t == -1 && !levA.empty()){
                printf("%d\n",levA.top().Pnum);
            }
        }
        else{
            // solved인 경우
            // 해당 문제 찾아서 제거 및 vaild false로 수정
            int temp, index=0;
            cin >> temp;
            
            while(1){
                if(list[index++].Pnum == temp){
                    break;
                }
            }
            vaild[index-1] = false; 

            // 여기에만 삭제가 있기 때문에 여기서 동기화
            sync_vaild(levD,levA , vaild);
        }
    }
}