/*
인자를 2개 같은 최소 heap을 구현한다.

이때 정렬은 절대값을 기준으로 하며
절대값이 같을 경우에는 실제 값이 더 작은 값을 더 작다고 본다.
*/

#include <iostream>
#include <queue>
#include <cmath>

using namespace std;

/*
priority queue에서 기본형을 인자로 주지 않을 때는 아래처럼
인자로 들어갈 구조체와 그 구조체를 비교할 operator 함수를 위한 구조체를 이용해야 한다.
ex) priority_queue < Nums, vector<Nums>, cmp> pQ;
*/
struct Nums{

    int a; // priority queue 안에 어떤 인자가 들어가는지 정의
    bool check;

    // 구조체 이름(인자) : 테겟인자(인자) {}
    Nums(int a, bool c) : a(a), check(c) {} // 생성자 정의
};
struct cmp{
    // 비교연산자 정의 이떄 우선순위를 기준으로 생각한다. (앞뒤가 아닌 우선순위)
    // bool operator()(인자, 인자) {}
    bool operator()(int a, int b){
        // 절대값이 같다면 실제값이 작은 순으로 정렬
        if(abs(a) == abs(b)){
            return a > b;
        }
        // 절대값이 작은 순으로 정렬
        return abs(a) > abs(b);
    }
};

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    // 자료형, 구현체, 비교 연산자
    priority_queue< int, vector<int>, cmp> pQ;

    int n;
    cin >> n;

    while(n--){
        int input;
        cin >> input;

        if(input == 0){
            // 값을 출력 후 삭제
            if(pQ.size() == 0){
                printf("0\n");
            }
            else{
                printf("%d\n", pQ.top());
                pQ.pop();
            }
        }
        else{
            // 값을 입력
            pQ.push(input);
        }
    }

}