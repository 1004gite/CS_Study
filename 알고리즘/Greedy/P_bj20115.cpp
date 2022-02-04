/*
에너지 드링크 n개가 주어진다.
n개 중 2개를 합치는 것을 반복해 1개의 드링크로 만든다.
이때 둘 중 하나의 드링크는 절반이 없어진다.
1개의 드링크가 되었을 때 드링크의 양이 최대가 되게 하자

풀이 1
1. 가장 적은 양의 드링크를 반씩 흘리는 것이 최선이다.
2. 가장 많은 드링크에 가장 적은 드링크를 넣는것을 반복한다.
    이때, 가장 많은 드링크는 항상 같게 된다.
3. 다르게 보면 가장 큰 드링크를 제외하고 모두 절반이 된다.
    -> 입력시 max값을 찾아 max값만 온전히 더하고 나머지는 반만 더한다.
*/

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    double total =0, max=-1;
    int n;
    cin >> n;
    while(n--){
        double tmp;
        cin >> tmp;
        if(tmp > max) max = tmp;
        total += tmp;
    }

    // 전체 양을 반으로 나눈 후 가장 큰 드링크는 온전히 넣어줌을 고려한다.
    total = total/2 + max/2;

    printf("%f\n",total);
}