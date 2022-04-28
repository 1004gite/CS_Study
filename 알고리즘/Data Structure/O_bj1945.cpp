#include <iostream>
#include <stack>

using namespace std;

/*
실수형의 범위나 출력에 대해 알아야 하는 문제
*/

int main(void){
    cin.tie(0);
    cin.sync_with_stdio(0);
    
    int n;
    cin >> n;
    //expression에 식 전체를 넣는다.
    char expression[101];
    cin >> expression;

    // A부터 n개의 값을 value에 저장한다.
    // 값에 접근할 때는  (int)'변수' -(int)'A' 를 index로 사용한다.
    double value[n];
    for(int i =0; i < n;i++){
        cin >> value[i];
    }

    // 식이 나오기 전까지의 값들은 stack에 저장한다.
    stack<double> valueS;

    // expression의 끝까지
    int index = 0;
    while(expression[index] != '\0'){
        char now = expression[index];
        int cint = ((int)now-(int)'A');
        // 알파벳이면 대응되는 값 저장
        if( 0 <= cint && cint <= 25){
            valueS.push(value[cint]);
        }
        // 식이면
        else{
            double y = valueS.top();
            valueS.pop();
            double x = valueS.top();
            valueS.pop();

            if(now == '+'){
                valueS.push(x+y);
            }
            else if(now == '-'){
                valueS.push(x-y);
            }
            else if(now == '*'){
                valueS.push(x*y);
            }
            else if(now == '/'){
                valueS.push(x/y);
            }
        }
        index++;
    }
    printf("%.2f",valueS.top());
}