/*
각 사람들은 줄 팁의 금액을 정해놓고 있다.
팁을 줄 때 (주는 순서-1)만큼 감소된 금액을 준다. ex) 5원을 3번째로 줌 -> 5-(3-1) = 3원
사람들이 줄을 서서 팁을 줄 때 받을 수 있는 최대 금액을 구하자

풀이 1
1. 적은 팁을 주는 사람은 맨 뒤로 보내도 손실이 적다.
    사람 수보다 많은 팁을 주는 사람은 뒤로 보내는만큼 손실이 난다.
2. 내림차순으로 정렬 후 팁이 1원 이상일 때까지 더한다.
*/

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

bool desc(int a, int b){
    return a > b;
}

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    cin >> n;

    vector<int> tips(n);
    for(int i = 0 ; i < n; i++){
        cin >> tips[i];
    }

    sort(tips.begin(), tips.end(), desc);

    long long total = 0;

    for(int i = 0; i < n; i++){
        long long tip = tips[i]-i;
        // printf("%d's tip is %lld\n",i+1,tip);
        if(tip <= 0) break;
        total += tip;
    }

    printf("%lld\n",total);
}