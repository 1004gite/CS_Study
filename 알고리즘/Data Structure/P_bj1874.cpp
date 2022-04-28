#include <iostream>
#include <stack>
#include <vector>

using namespace std;

/*
1. 최대값이 필요할때는 <climits>를 include하자
2. 동적 할당이 필요할 때는 vector를 사용하자 (vector.size()는 원소의 개수이다.)
3. queue, stack 등의 자료구조를 참조할 때는 .empty()로 비어있는지 먼저 확인하자
4. segment에러가 뜨면 배열의 범위보다는 논리적 결함을 먼저 생각하자
*/

int main(void){
    cin.tie(0);
    cin.sync_with_stdio(0);
    
    int n;
    stack<int> s;
    // tmp는 8이 가장 밑으로 오는 stack이며
    // tmp를 이용해서 s에 들어갈 수 있는 숫자와 순서를 관리한다.
    stack<int> tmp;
    cin >> n;
    int x = n;

    //불가능한 경우 중간에 출력을 주면 안되기 때문
    vector<int> v;

    while(x){
        tmp.push(x--);
    }

    while(n--){
        int num;
        cin >> num;

        // '-'한번마다 '+'가 0번이상 선행된다.
        int Pcount = 0;

        // 가능한 경우 tmp의 top이 num일 때까지 s에 넣는다.
        // 불가능할 경우는 이미 s의 아래에 해당 값이 있는 경우이므로 while문을 바로 빠져나간다.
        // s에 들어있는 수가 마지막 하나일 경우 tmp가 비어있을 수 있다.
        while(!tmp.empty() && tmp.top() < num){
            s.push(tmp.top());
            tmp.pop();
            Pcount++;
        }

        // 이때 s의 top또는 tmp의 top은 num이여만 한다.
        if(!s.empty() && s.top() == num){
            s.pop();
        }
        else if(!tmp.empty() && tmp.top() == num){
            tmp.pop();
            // tmp에서 제거했다면 s에 넣었다가 뺀 것이다.
            Pcount++;
        }
        else{
            printf("NO\n");
            return 0;
        }

        v.push_back(Pcount);
    
    }
    for(int i = 0; i < v.size(); i++){
        int tmp = v[i];
        while(tmp--){
            printf("+\n");
        }
        printf("-\n");
    }
}
