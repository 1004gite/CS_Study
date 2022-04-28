/*
천만 이하의 정수에 대한 소인수분해

풀이 1
1. 소인수분해를 위해서는 소수의 목록이 필요하다.
2. 배수를 소수에서 제거하는 방식으로 소수를 구한 뒤 소인수분해
*/

#include <iostream>
#include <vector>

using namespace std;

vector<bool> list(10000001, true);

int main(void){
    // 소수 리스트 생성
    // 4000^2은 10000000이상이다.
    // 4000이하의 수들에 대해 10000000 이넘어거기 전까지의 곱을 모두 고려헸기 떄문에 4000 이후에는 곱의 순서만 바뀌고 같은 결과
    for(int i = 2; i < 4000; i++){
        if(list[i]){
            int tmp = 2;
            while(i*tmp <= 10000000){
                list[i*tmp] = false;
                tmp ++;
            } 
        }
    }


    int n;
    cin >> n;

    int prime = 2;
    while(n != 1){
        if(n%prime != 0){
            while(1){
                prime++;
                // 소수이고 n을 나눌 수 있는 수를 찾는다.
                if(list[prime] && n%prime == 0) break;
            }
        }
        n /= prime;
        printf("%d\n",prime);
    }
}