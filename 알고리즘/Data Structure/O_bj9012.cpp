#include <iostream>
#include <stack>

using namespace std;

/*
어려운 문제는 아니었지만 테스트 케이스를 입력할 때 scanf를 사용하면 엔터가 들어와야한다.
6줄짜리 테스트 케이스라면 5줄까지는 엔터가 입력되어있지만 마지막 줄은 엔터가 없을 수 있으니
당황하지 말고 엔터를 쳐보자
추가적으로 입력과 출력시간이 겹쳐 출력이 뭉개져도 당황하지 말자
*/

int main(void){
    int n;
    cin >> n;
    while(n--){
        stack<int> S;
        char input[51];
        scanf("%s",input);
        int index = 0;
        while(1){
            char x = input[index++];
            if(x == '\0')
                break;
            if(x == '('){
                S.push(1);
            }
            if(x == ')'){
                if(S.empty()){
                    S.push(0);
                    break;
                }
                else{
                    S.pop();
                }
            }
        }
        if(S.empty()){
            printf("YES\n");
        }
        else{
            printf("NO\n");
        }
    }
}