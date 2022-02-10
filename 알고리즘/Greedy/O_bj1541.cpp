/*
어떤 식이 주어진다.
식은 양수와 +,- 만 존재한다.
괄호를 이용해 식의 결과가 최소가 되게 하자.

풀이 1
1. 덧셈, 뺄셈, 양수만 존재하기 떄문에 뺄셈을 최대한 크게 묶어 값을 음수로 만드는 것이 좋다.
2. 덧셈들을 괄호로 묶을 것이기 때문에 덧셈을 들어올때마다 처리한다.
3. 뺄셈이 들어오면 Queue에 이전에 더해지던 값을 넣고 뒤에 나오는 덧셈들을 계산한다.
4. Queue에 있는 모든 수를 뺀다.
5. 종합적으로 생각하면 '-' 뒤에있는 모든 수는 음수로 바꿀 수 있음을 의미한다.
    -> -가 나오기 전까지 모두 더하다가 -가 나온 이후부터는 전부 뺀다.
*/

#include <iostream>
#include <string>

using namespace std;

int main(void){

    string expression;
    cin >> expression;

    string tmp = "";
    bool check = false;
    int val = 0;
    for(int i = 0; i < expression.size(); i++){
        // 0으로 시작하는 숫자인 경우 넘김
        if(expression[i] == 0 && tmp == "") continue;
        if(expression[i] == '-'){
            // 이번 기호가 처음나온 '-' 일 수 있다.
            if(check) val -= stoi(tmp);
            else val += stoi(tmp);

            check = true;
            tmp = "";
        }
        else if(expression[i] == '+'){
            // 앞에 - 가 나왔으면 빼고 아니면 더한다.
            if(check) val -= stoi(tmp);
            else val += stoi(tmp);
            tmp = "";
        }
        else {
            // tmp에 숫자를 더함
            tmp += expression[i];
        }
    }
    // 마지막 기호 없는부분
    if(check) val -= stoi(tmp);
    else val += stoi(tmp);

    printf("%d\n",val);
}