/*
n개의 문제가 있을 때 맞은 것은 파란색 틀린 것은 빨간색으로 표시한다.
표시하는 과정에서 연속된 숫자범위를 한번에 같은색으로 칠할 수 있다.
가장 적은 횟수로 칠할 때 그 횟수를 구하자.

풀이 1.
1. 한가지 색으로 전체를 칠하면 나머지 하나의 색만 고려해도 된다.
2. 순서대로 칠한다고 가정할 때 칠하는 횟수가 많은 색을 전체에 먼저 칠한다.
*/

#include <iostream>

using namespace std;

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    cin >> n;

    char pre = 'A';
    int b=0, r=0;
    while(n--){
        char tmp;
        cin >> tmp;
        if(pre == 'B' && tmp == 'R'){
            // 파란색 횟수추가 후 pre갱신
            b++;
            pre = tmp;
        }
        else if(pre == 'R' && tmp == 'B'){
            r++;
            pre = tmp;
        }
        else if(pre == 'A'){
            // 첫 시작일 경우
            pre = tmp;
        }
    }
    // 마지막에 대한 처리
    if(pre == 'B') b++;
    else r++;

    printf("%d\n", b > r ? r+1 : b+1);
}