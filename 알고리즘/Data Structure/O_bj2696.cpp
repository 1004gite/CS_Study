/*
수열을 입력받고 홀수번째 입력받을 때마다 중앙값을 출력하는 문제

홀수번째마다 판단하기 때문에 중앙값보다 큰값, 작은값의 개수가 같다.
어떤 중앙값이 있는 상태에서 다음 판단은 값이 2개 더 들어올 때이다.

이떄 2개의 case로 나눌 수 있다.
기존의 중앙값보다 큰 수,작은 수가 1개씩 있는 경우/ 큰 수 또는 작은 수만 2개인 경우
전자의 경우 기존의 중앙값을 출력한다
후자의 경우 작은 수만 2개일 경우를 예를 들면, 기존의 중앙값을 큰 수에 넣고 받은 수 2개를 작은 수에 넣은 후
작은 수에서 가장 큰 수를 중앙값으로 삼는다.
-> 후자의 경우 작은 수/ 큰 수의 집합들에서 최대, 최소값을 유지해야 하기 때문에 heap을 사용한다.
-> 중앙값에서 멀리 떨어진 수가 나올 수 있기 때문에 최근에 받은 값 2개만으로는 판단하면 안된다.
*/

#include <iostream>
#include <queue>
#include <vector>

using namespace std;

struct Ascend{
    bool operator()(int a, int b){
        return a > b;
    }
};
struct Descend{
    bool operator()(int a, int b){
        return a < b;
    }
};

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int t;
    cin >> t;

    while(t--){

        priority_queue<int, vector<int>, Ascend> big; // 중앙값보다 큰 수들은 최솟값이 top이 되게 관리한다.
        priority_queue<int, vector<int>, Descend> small;

        int n;
        cin >> n;
        if(n%2 == 0){
            // n이 짝수면 n-1번까지 받아서 연산하는 것과 같다.
            n--;
        }
        // 출력할 중앙값의 개수를 출력 후 (n/2 + 1)
        // 한줄에 10개씩 홀수번쨰 상황에서의 중앙값을 출력한다.

        int count = 0;
        printf("%d\n",n/2+1);

        // 첫번째는 무조건 중앙값
        int median, t1, t2;
        cin >> median;
        printf("%d ",median);
        count++;
        // n/2번동안 2번씩 받아 처리한다.
        for(int i = 0; i < n/2; i++){
            cin >> t1;
            cin >> t2;

            if(t1 <= median && median <= t2){
                // 중앙값이 또 중앙값인 경우
                small.push(t1);
                big.push(t2);
            }
            else if(t2 <= median && median <= t1){
                // 중앙값이 또 중앙값인 경우2
                small.push(t2);
                big.push(t1);
            }
            else if(t1 <= median && t2 <= median){
                // 두 값이 모두 작을 경우
                small.push(t1);
                small.push(t2);
                big.push(median);
                median = small.top();
                small.pop();
            }
            else if(t1 >= median && t2 >= median){
                // 두 값이 모두 클 경우
                big.push(t1);
                big.push(t2);
                small.push(median);
                median = big.top();
                big.pop();
            }
            printf("%d ",median);


            if(++count == 10){
                printf("\n");
                count = 0;
            }
        }
        printf("\n");

    }

}