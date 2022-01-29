/*
에라토스테네스의 체를 만든다.
2~N 의 수에 대해 고려하며 만드는 과정 중 k번째로 지우는 수를 구하라

풀이 1
1. 에라토스테네스의 체를 구현하여 시뮬레이션
*/

#include <iostream>
#include <vector>

using namespace std;

int main(void){

    int n,k;
    cin >> n >> k;

    vector<bool> table(n+1,true);

    int i = 2;
    while(1){
        if(table[i]){
            if(--k == 0){
                printf("%d\n",i);
                return 0;
            }
            int tmp = 2;
            while(i*tmp <= n){
                if(table[i*tmp]){
                    k--;
                }
                if(k == 0){
                    printf("%d\n",i*tmp);
                    return 0;
                }
                table[i*tmp] = false;
                tmp++;
            }
        }
        i++;
    }

}