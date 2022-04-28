/*
주어지는 n개의 수 중에서 소수는 몇개인가
수들은 1000이하의 자연수다

풀이 1
1. 에라토네스의 체 를 이용하여 1000이하의 소수를 구해놓는다.
*/

#include <iostream>
#include <vector>

using namespace std;

vector<bool> table(1001,true);

int main(void){

    table[1] = false;
    for(int i = 2; i <= 400; i++){
        if(table[i]){
            int tmp = 2;
            while(i*tmp <= 1000){
                table[i*tmp] = false;
                tmp++;
            }
        }
    }

    int n;
    cin >> n;

    int count = 0;

    while(n--){
        int tmp;
        cin >> tmp;
        if(table[tmp]) count++;
    }

    printf("%d\n",count);
}