/*
최대 100000개의 로프가 있다.
중량이 m인 물체를 들어올릴 때 각각의 로프에 동일한 하중이 실린다.
각 로프가 버틸 수 있는 하중은 다를 수 있을 때 들 수 있는 가장 큰 하중을 구하자
// 최대 총합은 1,000,000,000이다.

풀이 1
1. 강한 줄이 많아도 약한 줄이 존재한다면 (약한 줄 * N) 이상의 하중을 들 수 없다.
2. 줄을 강한 줄부터 내림차순으로 sort한다.
3. 줄을 강한 순으로 하나씩 추가해가며 최대 하중을 찾는다.
4. 다음 줄을 추가할 때 최대 기대값은 ( 현재 총량 + (다음 줄의 세기 * 쓰이지않은 줄의 수) )이다.
    최대 기대값을 고려하며 연산을 진행한다.
    (줄의 세기로 내림차순 정렬했고, 남은 줄의 세기가 같을 수도 더 작을 수도 있다.)
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
    int max = -1;
    cin >> n;

    vector<int> rope(n);

    for(int i = 0; i < n; i++){
        cin >> rope[i];
    }

    sort(rope.begin(),rope.end(),desc);

    for(int i = 0; i < n; i++){
        
        if(max >= rope[i]*n){
            // 이번에 대한 최대 기댓값으로 가능성 판단
            // 이번 줄이 나왔던 줄 중에서 가장 약하고 최선의 경우 다음 로프들이 이번 로프만큼 강함
            break;
        }
        int w = rope[i]*(i+1);
        if(max < w){
            max = w;
        }
    }

    printf("%d\n",max);

}