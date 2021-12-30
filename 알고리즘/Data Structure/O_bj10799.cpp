#include <iostream>
#include <stack>

using namespace std;

/*
괄호가 열리고 바로 닫히는 경우 레이저다.
괄호가 열리고 바로 닫히지 않는 경우 새로운 쇠막대기가 시작됐다고 판단한다.
쇠막대기는 매칭되는 괄호가 닫힐 때 끝난다.
레이저가 나올 때 해당 경로에 있는 쇠막대기 수는 stack에 있는(아직 닫히지 않은 괄호의) 원소의 개수이다.
즉 레이제가 나올 때마다 stack의 원소의 개수만큼 쇠막대기가 증가한다.(기존 쇠막대기는 따로 더햐준다.)
*/

int main(void){

    stack<int> s;

    long baseCount = 0;
    long addCount = 0;

    char input[100001];
    cin >> input;

    int index = 0;
    while(input[index] != '\0'){
        // 쇠막대기의 시작 혹은 레이저
        if(input[index] == '('){
            // 레이저일 경우
            if(input[index+1] == ')'){
                addCount += s.size();
                s.push(1);
            }
            //쇠막대기의 시작일 경우
            else{
                baseCount++;
                s.push(1);
            }
        }
        // 쇠막대기의 끝
        else{
            s.pop();
        }
        index++;
    }
    printf("%ld\n",baseCount+addCount);
}