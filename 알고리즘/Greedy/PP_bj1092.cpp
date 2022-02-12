/*
화물들과 무게 제한이 있는 크레인 N대가 있다.
1분애 하나씩 박스를 배에 실을 수 있다.
모든 크레인은 동시에 움직인다.
모든 화물을 가장 적은 시간으로 옮기자.

풀이 1
1. 모든 크레인은 동시에 움직이기 때문에 한번 움직일 때 최대한 많은 화물을 옮기는 것이 유리하다.
2. 강한 크레인은 약한 크레인의 화물을 대신 옮겨줄 수 있다.
3. 가장 약한 크레인부터 화물을 가능한 무게까지 할당한다.
    이때 약한 크레인들은 최대한 많은 화물을 할당받게 된다.
4. 특정 크레인의 화물이 많다면 더 강한 크레인들에게 화물을 나눠준다.
    이때 약한 크레인부터 화물을 나눠주면 강한 크레인은 화물을 많이 할당받을 수 있기 때문에 강한 크레인부터 화물을 나눈다.
    또, 화물을 운반 가능한 크레인에 나눠줄 때 할당된 화물이 적은 크레인을 우선 선택한다고 생각한다.
    또, 강한 크레인부터 내려오면서 (크레인당 맡은 최대 화물 개수, 지금까지 맡은 누적 화물 개수)를 계산한다.
        현재 보고있는 크레인의 화물 개수가 최대 화물 개수보다 작으면 화물을 나누지 않는다.
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
    vector<int> crain(n);

    for(int i = 0; i < n; i++){
        cin >> crain[i];
    }
    sort(crain.begin(), crain.end(), desc);

    int g;
    cin >> g;
    vector<int> goods(g);
    for(int i = 0; i < g; i++){
        cin >> goods[i];
    }
    sort(goods.begin(), goods.end(), desc);

    if(crain[0] < goods[0]){
        // 가장 무거운 화물이 가장 강한 크레인보다 무거울 경우 불가능하다.
        printf("%d\n",-1);
        return 0;
    }

    vector<int> alloc(n,0);
    int index = n-1;
    for(int i =g-1; i >= 0; i--){
        while(goods[i] > crain[index]){
            index--;
        }
        alloc[index] ++;
    }

    vector<int> info(2); // 최대 적재 화물 개수, 누적 적재 화물 개수
    info[0] = alloc[0];
    info[1] = alloc[0];
    // printf("0's alloc is %d\n",alloc[0]);
    for(int i = 1; i < n; i++){
        // printf("%d's alloc is %d\n",i,alloc[i]);
        info[1] += alloc[i];
        // 지금까지의 누적 화물 개수가 (한개의 최대적재량 * 지금까지의 크레인 개수)보다 크면
        // 강한 크레인들이 더 많은 화물을 감당하고 있다는 뜻이다.
        while(info[0]*(i+1) < info[1]){
            info[0]++;
        }
    }

    printf("%d\n",info[0]);

}