#include <iostream>
#include <string>
#include <stack>
#include <vector>
#include <algorithm>

using namespace std;

/*
멱집합(부분집합)을 구하는 과정이 핵심이다.
각 원소는 포함되거나 포함되지 않을 수 있다.
원소별로 depth를 따저 모든 원소를 고려한다.
가장 낮은 depth까지 내려가면 하나의 배열이 완성된다.
개념적으로 dfs를 이용한다.
*/


void dfs(int depth, int size,vector<int> check , vector< vector<int> > &states){
    // depth는 0부터 시작한다.
    // depth가 끝까지 내려온 상태라면 현재 경우의 수를 적용한다.
    // check는 해당 index를 포함할 것인지를 나타내는 배열이다.

    if(depth == size -1 ){
        // check의 현재 상태에 0/1을 하나씩 고려하여 states에 저장

        // 전부 1인 경우는 고려하지 않는다.(모든 괄호를 빼지 않는 경우)
        int isThereZero = 0;
        for(int i = 0; i < size-1; i++){
            if(check[i] == 0){
                isThereZero = 1;
                break;
            }
        }
        check.push_back(0);
        states.push_back(check);
        
        if(isThereZero){
            check.pop_back();
            check.push_back(1);
            states.push_back(check);
        }
        
        return ;
    }

    check.push_back(0);
    dfs(depth+1, size, check, states);

    check.pop_back();
    check.push_back(1);
    dfs(depth+1, size, check, states);    
}


int main(void){

    char expression[201];
    vector<int> open;
    vector<int> close;

    stack<int> S;

    cin >> expression;

    for(int index = 0; expression[index] != '\0'; index++){
        char tmp = expression[index];

        // 괄호가 열리는 부분의 index를 stack에 저장하고 반대편 쌍을 기다림
        if(tmp == '('){
            S.push(index);
        }
        // 괄호가 닫히는 부분의 index는 close에 저장허고 맞는 짝은 stack에서 뽑아 open에 저장
        else if(tmp == ')'){
            close.push_back(index);
            open.push_back(S.top());
            S.pop();
        }
    }

    // 모든 부분집합을 구하여 result에 저장한다.
    vector<string> result;
    vector< vector<int> > states;
    vector<int> check;

    if(open.size() > 0 ){
        dfs(0, open.size(), check, states);
    }

    // 얻은 경우의 수만큼 고려한다.
    while(!states.empty()){
        string tmp = "";
        for(int index = 0; expression[index] != '\0'; index++){
            // 받은 식을 한바퀴 돈다.
            // index는 실제 표현식에서의 index이다.

            // 빼야하는 index인지 검사하는 부분
            int get = 1;
            for(int i = 0; i < open.size() ; i++){
                // i는 이번에 고려할 부분집합을 나타내는 배열 check의 index이다.
                // open, close, check 들의 index는 모두 같고 이는 i 이다.

                // 이번 index가 빼야할 index인지 states.back()을 통해 확인한다.
                if(states.back()[i] == 0){
                    // 이번 부분집합에서 빼야하는 부분이라면
                    // open,close의 i번째에 있는 실제 index와 지금 고려하는 실제 index를 비교한다.
                    if(index == open[i]){
                        get = 0;
                        break;
                    }
                    if(index == close[i]){
                        get = 0;
                        break;
                    }
                }
            }

            // 검사를 통과했다면 이번 char를 string에 이어 붙인다.
            if(get){
                tmp += expression[index];
            }
        }
        // 얻은 식 1개를 vector에 넣는다.
        result.push_back(tmp);
        // states의 마지막을 제거하여 다음 states를 고려한다.
        states.pop_back();
    }

    // 사전순으로 정렬한다.
    sort(result.begin(),result.end());
    // 중복을 제거한다.
    result.erase( unique(result.begin(),result.end()) , result.end());

    for (int i = 0; i < result.size(); i++){
        printf("%s\n",result[i].c_str());
    }

}