#include <iostream>
#include <queue>
#include <algorithm>
#include <cstring>

using namespace std;

// sort(배열,크기,greater<int>()); -> 내림차순 정렬
// memset 함수는 <cstring>헤더파일에서 불러오며 byte단위로 설정 및 동작한다.

struct node{
    int periory, target;
};

int main(void){
    cin.tie(0);
    cin.sync_with_stdio(0);

    int testnum;
    cin >> testnum;
    
    while(testnum--){
        int n,index, count=0;
        cin >> n;
        cin >> index;

        // 프린터에 들어가있는 순서대로 중요도를 queue에 저장
        queue<node> q;
        // 중요도별 개수를 저장하여 어떤 중요도가 나올 차례인지를 판단
        int periory[10];
        int perioryIndex = 9;
        memset(periory,0, sizeof(int)*10);
        //각 중요도가 몇개인지, queue에 들어간 순서를 세팅
        for(int i = 0; i < n; i++){
            int tmp;
            cin >> tmp;
            // 해당 중요도의 개수 증가
            periory[tmp] += 1;
            // queue에 삽입
            node tnode;
            tnode.periory = tmp;
            if(index == i) tnode.target = 1;
            else tnode.target = 0;
            q.push(tnode);
        }

        // 시뮬레이션
        while(1){
            // perioryIndex는(현재 남아있는 periory중 가장 높은 것을 의미)
            while(!periory[perioryIndex]){
                perioryIndex--;
            }
            // q의 앞부분에 지금 프린트 가능한 원소가 들어있으면 프린트(pop)하고 해당 periority 1감소
            if(q.front().periory == perioryIndex){
                // 구하고자 하는 타겟이라면 count에 1을 더해 줄력 후 while문 종료
                if(q.front().target){
                    printf("%d\n",count+1);
                    break;
                }
                q.pop();
                periory[perioryIndex] -= 1;
                count++;
            }
            // 지금 프린트해야하는 periority 보다 작다면 맨 뒤로 보낸다.
            else{
                q.push(q.front());
                q.pop();
            }
        }
    }
}