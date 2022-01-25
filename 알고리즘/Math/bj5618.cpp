/*
자연수 n개가 주어질 떄 공약수를 모두 구하라

풀이 1
1. 1부터 가장 작은 수의 절반까지 모든 수의 나머지를 구한다.
    가장 작은 수의 절반을 초과하면 가장 작은 수를 나눌 수 없다.
*/

#include <iostream>
#include <vector>
#include <climits>

using namespace std;

int main(void){

    int n, tmpn;;
    cin >> n;
    tmpn = n;

    int min = INT_MAX;
    vector<int> list;

    while(tmpn--){
        int tmp;
        cin >> tmp;
        if(min > tmp){
            min = tmp;
        }
        list.push_back(tmp);
    }

    int half = min/2;
    for(int i = 1; i <= half; i++){
        tmpn = n;
        bool c = true;
        for(int j = 0; j < tmpn; j++){
            if(list[j]%i > 0){
                c = false;
                break;
            }
        }
        if(c) printf("%d ",i);
    }
    printf("\n");

}