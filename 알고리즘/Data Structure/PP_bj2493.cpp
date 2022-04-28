#include <iostream>
#include <stack>
#include <algorithm>

using namespace std;

/*
1. stack만을 이용해 넣었다가 뺴는 식으로만 했을 때는 시간초과가 발생했다.
2. deque를 사용해서 연산량을 줄여도 시간초과가 나왔다.
3. 직관적인 행위인 넣었다가 빼는 행위 자체를 없애자. -> 처음으로 60%이상 갔지만 여전히 시간초과
4. 배열에 전부 넣고 다시 비교하는 것이 아닌 값을 입력받으며 왼쪽의 탑부터 비교하게 했다. -> 똑같음
5. max값을 추가해 max보다 크면 비교 차체를 안한다. -> 확실히 빨라졌지만 같은 %에서 시간초과가 난다 -> 더 근본적인 해결이 필요함
6. 결국 입력받고 검사하는데 O(n^2)이 소모되는 것이 문제인 것 같다.

7. 결국 인터넷 검색을 참조했다. 답은 stack을 이용하는 것이고 대신 stack의 원소 입장에서
    자신 위에 더 높은 값이 있다면 그 원소는 비교할 필요가 없다는 점을 생각해야 한다.
*/

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    cin >> n;
    stack<int> highS;
    stack<int> indexS;
    int result[n];
    
    int high;
    cin >> high;
    highS.push(high);
    indexS.push(0);
    result[0] = 0;

    for(int i = 1; i < n; i ++){
        // high는 지금 판단하려는 탑의 높이이다.
        cin >> high;

        // 지금 stack에 들어있는 top의 높이가 판단하려는 탑의 high보다
        // 낮거나 같다면 앞으로는 비교할 필요가 없다.(판단하려는 탑에 먼저 부딫히기 때문)
        // 즉 stack에는 바닥기준으로 high가 높은 기둥들이 들어가있고 high가 같다면 index가 오른쪽인 기둥이 들어있게 된다. 
        while(!highS.empty() && high > highS.top()){
            highS.pop();
            indexS.pop();
        }

        if(highS.empty()){
            // 기존의 모든 탑을 빼냈다면 왼쪽에는 더 큰 탑이 없다는 의미이다.
            result[i] = 0;
        }
        else{
            // 남아있다면 남아있는 탑에 부딫힌 것이다.
            result[i] = indexS.top()+1;
        }

        highS.push(high);
        indexS.push(i);
    }

    printf("%d",result[0]);
    for(int i = 1; i < n; i++){
        printf(" %d",result[i]);
    }
    printf("\n");

}