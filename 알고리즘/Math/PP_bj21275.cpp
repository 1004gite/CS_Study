/*

// 10진수 이하에서는 문자가 올 수 없음에 주의하자
// -> 10진수 뿐만 아니라 진수를 넘어가는 숫자가 들어오는 경우를 고려해야 한다.

수 X,A,B가 있다.
X는 십진법으로 표현되어있고 (0 <= X < 2^63)아다. -> long long부터 사용가능
A,B는 (2<= A,b <= 36)인 수이고 진법을 나타낸다.

A, B진법으로 표현한 X가 각각 주어지며 X,A,B를 유추한다.
유추 후 문제의 조건에 따라 출력한다.

풀이 1
1. 가능한 A, B의 모든 부분집합에 대해 X를 표현해보고 X값이 같은지 확인한다.
*/

#include <iostream>
#include <vector>
#include <string>

using namespace std;

struct pos{
    long long x;
    int a,b;
    pos(long long x, int a, int b) : x(x), a(a), b(b) {}
};

long long change(int m, string s){
    // m진법 s를 10진법으로 변환
    // 각 자리수는 m^x를 곱해준다 -> x는 0의자리수부터 시작
    long long result = 0;
    int x = 1;
    for(int i = s.size()-1; i >= 0; i--){
        long long tmp;
        if('a' <= s[i] && s[i] <= 'z'){
            tmp = s[i]-'a'+10;
        }
        else{
            tmp = s[i]-'0';
        }
        if(tmp >= (long long)m) return -1;
        if(result > 0x8000000000000000 - tmp*x) return -1; // x의 값이 2^63을 넘어가는 경우
        result += tmp * x;
        x *= m;
    }
    return result;
}

int main(void){

    string resultA, resultB;
    cin >> resultA >> resultB;

    vector< pos > possible; // X,A,B;

    for(int A = 2; A <= 36; A++){
        for(int B = 2; B <= 36; B++){
            if( A == B ) continue;
            long long tmpA, tmpB;
            tmpA = change(A,resultA); // A 변환
            tmpB = change(B, resultB);
            if(tmpA == -1 || tmpB == -1) continue;
            if( tmpA == tmpB){
                // A와 B의 X값이 같으면 possible목록에 추가
                possible.push_back(pos(tmpA,A,B));
            }
        }
    }

    if(possible.empty()){
        printf("Impossible\n");
    }
    else if(possible.size() == 1){
        printf("%lld %d %d\n",possible[0].x,possible[0].a,possible[0].b);
    }
    else{
        printf("Multiple\n");
    }

    // printf("\nDEBUG\n");
    // for(int i =0; i < possible.size(); i++){
    //     printf("X: %lld, A: %d, B: %d\n",possible[i].x,possible[i].a,possible[i].b);
    // }
}