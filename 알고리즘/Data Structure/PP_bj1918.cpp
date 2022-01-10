/*
문제를 꼼꼼히 읽자.
조금 아는 문제라고 편하게 풀려고 했다가 오히려 한참 돌아갔다.

1. 연산자가 들어왔을 때 이전에 우선순위가 같거나 높은 연산자가 있다면 모두 적용한다.

2. 연산자 stack에 괄호를 넣어 고려한다.
*/

#include <iostream>
#include <string>
#include <stack>

using namespace std;

int main(void){

    char expression[101];

    cin >> expression;

    stack<char> s;
    string result = "";

    for(int i = 0; expression[i] != '\0'; i++){

        if('A' <= expression[i] && expression[i] <= 'Z'){
            result += expression[i];
        }
        else if(expression[i] == '('){
            s.push(expression[i]);
        }
        else if(expression[i] == '*' || expression[i] == '/'){
            // 우선 순위가 높거나 같은 연산자를 적용
            // ( 가 나오거나 stack이 빌 때까지

            while(!s.empty()){
                if(s.top() == '('){
                    break;
                }
                if(s.top() == '+' || s.top() == '-'){
                    // +,- 는 우선순위가 낮음으로 적용하지 않음
                    break;
                }
                result += s.top();
                s.pop();
            }
            s.push(expression[i]);
        }
        else if(expression[i] == '+' || expression[i] == '-'){
            // 우선 순위가 높거나 같은 연산자를 적용
            // ( 가 나오거나 stack이 빌 때까지

            while(!s.empty()){
                if(s.top() == '('){
                    break;
                }
                result += s.top();
                s.pop();
            }
            s.push(expression[i]);
        }
        else if( expression[i] == ')'){
            // 괄호 1개에 대한 처리
            while(!s.empty()){
                if(s.top() == '('){
                    s.pop();
                    break;
                }
                result += s.top();
                s.pop();
            }
        }
    }

    // s에 남은 식들 처리
    while(!s.empty()){
        result += s.top();
        s.pop();
    }

    printf("%s\n",result.c_str());
}