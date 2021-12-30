#include <iostream>
#include <stack>
#include <algorithm>
#include <cstring>
#include <vector>

using namespace std;

int main(void){

    char ex[201];
    cin >> ex;
    stack<int> openStack;
    vector<int> open, close;

    // 괄호가 열고 닫히는 구간의 index를 저장
    int index = 0;
    while(ex[index] != '\0'){
        if(ex[index] == '('){
            openStack.push(index);
        }
        else if(ex[index] == ')'){
            close.push_back(index);
            open.push_back(openStack.top());
            openStack.pop();
        }
        index++;
    }
    
    vector<string> result;
    
    // 괄호를 없앨 수 있는 모든 경우의 수를 크기별 부분집합으로 고려한다.
    for(int num = 1; num <= open.size(); num++){
        // 원소가 num개인 부분집합을 모두 고려한다.
        // sub(index)부터 num-1개의 원소를 고정으로 두고 1개씩 바꿔가며 추가한다.
        for(int sub = 0; sub+num-1 < open.size()  ;sub++){
            vector<int> rmindex;
            // 고정할 index를 넣어놓는다.
            for(int fix = sub+num-2; fix >= sub; fix--){
                rmindex.push_back(fix);
            }
            // 그 후부터 하나식 바꿔감
            for(int va = sub+num-1;va < open.size();va++){
                rmindex.push_back(va);
                // 하나의 부분집합에 대해서 경우의 수를 만든다.
                string tmp = "";
                for(int j = 0; ex[j]!='\0';j++){
                    // rmindex에 있는 값인지 비교 후 아니라면 결과값에 더함
                    int tmpindex = num;
                    int get = 1;
                    while(tmpindex--){
                        if(open[rmindex[tmpindex]] == j || close[rmindex[tmpindex]] == j){
                            get = 0;
                            break;
                        }
                    }
                    if(get){
                        tmp += ex[j];
                    }
                }
                result.push_back(tmp);
                rmindex.pop_back();
            }
        }
    }

    //부분집합으로 고려하였음으로 중복을 제거한다. + 사전정렬
    sort(result.begin(),result.end());
    result.erase(unique(result.begin(),result.end()),result.end());
    for(int i = 0; i < result.size(); i++){
        printf("%s\n",result[i].c_str());
    }
}