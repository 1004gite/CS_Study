#include <iostream>
#include <stack>
#include <cmath>
#include <cstring>

using namespace std;

/*
stack을 두개 만들어 대괄호 소괄호를 관리한다.
어떤 괄호가 바로 닫힐때만 값이 발생하므로 그때를 기준으로 감싸고 있는 괄호가 몇개인지 파악 후 계산한다.

올바르지 않은 괄호라면 0을 출력한다.
*/

int main(void){

    char s[31];
    cin >> s;

    stack<int> small,big;

    long result = 0;

    int index = 0;
    while(1){
        char tmp;
        tmp = s[index];

        if(tmp == '\0'){
            break;
        }
        // 괄호가 생기는 경우 해당 stack에 추가
        // 인접하여 닫히는 괄호인지도 확인
        else if(tmp == '('){
            //인접하여 닫히는 괄호라면
            if(s[index+1] == ')'){
                long tmpresult = 2;
                for(int i =0; i < small.size();i++){
                    tmpresult *= 2;
                }
                for(int i =0; i < big.size();i++){
                    tmpresult *= 3;
                }
                
                result += tmpresult;
                //다음원소를 검사할 필요가 없기 때문
                index ++;
            }
            else{
                small.push(1);
            }
        }
        else if(tmp == '['){
            //인접하여 닫히는 괄호라면
            if(s[index+1] == ']'){
                long tmpresult = 3;
                for(int i =0; i < small.size();i++){
                    tmpresult *= 2;
                }
                for(int i =0; i < big.size();i++){
                    tmpresult *= 3;
                }

                result += tmpresult;
                //다음원소를 검사할 필요가 없기 때문
                index ++;
            }
            else{
                big.push(1);
            }
        }
        // 괄호가 닫히는 경우 점수를 더함 (올바르지 않은 괄호에 대한 검사를 수반)
        else if(tmp == ')'){
            if(small.empty()){
                printf("0\n");
                return 0;
            }
            small.pop();
        }
        else if(tmp == ']'){
            if(big.empty()){
                printf("0\n");
                return 0;
            }
            big.pop();
        }
        index++;
    }

    if(!small.empty() || !big.empty()){
        printf("0\n");
        return 0;
    }

    printf("%ld\n",result);
}