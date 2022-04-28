/*
한시간 단위로 선택
일을 한다 -> 피로 A만큼 축적, 일 B만큼 해결
일을 쉰다 -> 피로 C만큼 감소, 일 0만큼 처리 (피로도는 최소 0)

하루 24시간 동안 피로도 M을 넘기지 않고 할 수 있는 최대 일의 양은?

풀이 1
1. 계산 횟수는 24번이므로 시뮬레이션한다.
*/

#include <iostream>

using namespace std;

int main(void){

    int a,b,c,m;
    cin >> a; cin >> b; cin >> c; cin >> m;

    int condition = 0, work = 0;

    for(int i = 0; i < 24;i++){

        if((condition + a) > m){
            //휴식
            condition -= c;
            if(condition < 0 ) condition = 0;
        }
        else{
            //일
            condition += a;
            work += b;
        }
    }

    printf("%d\n",work);
}