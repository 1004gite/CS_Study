/*
x좌표에 두 점이 주어진다.
두 점은 특정한 점을 향해 한칸씩 나아간다.
나아가는 길에 있는 값만큼 꿀을 딸 수 있다.
이때, 누군가의 시작점에서는 꿀을 딸 수 없고 목적지에서는 꿀을 딴다.
꿀의 양이 적힌 좌표계가 주어졌을 때 {두 점, 목표점}을 설정해 가장 많은 꿀을 따게 하자

풀이 1
1. 값이 큰 꿀은 두 벌이 모두 지나가면 좋다.
2. 끝에서 시작하고 반대편 끝이 목적지일 때 가장 많은 경로를 지난다.
3. 목적지는 두 벌이 모두 지나므로 양 끝점 중 꿀이 더 많은 곳으로 하고 반대편 끝에 벌들을 놓는다.
4. 벌 한마리는 무조건 가장 뒤에 두고 남은 한마리는 그 바로 앞에 둔다
    이때 벌을 앞으로 한칸씩 옮기며 앞벌이 못먹게 되는 꿀의 양이 뒷벌이 먹게되는 꿀의 양보다 적거나 같으면 한칸씩 앞으로 보낸다.
    앞벌이 못먹게 되는 양 = 다음 자리의 꿀의 양
    뒷벌이 더 먹게되는 양 = 앞벌이 있던 자리 - 앞벌이 들어간 자리 (한칸 앞으로 보냈을 때 보는 이득)
    // 뒷벌은 어차피 1칸치의 이득만 볼 수 있고 앞벌은 앞으로 나갈때마다 손해보기 떄문에 앞벌은 누적으로 계산
    // 손해와 이득이 같으면 한칸 더 가본다.
==> 틀렸다. 출발지와 목적지를 양 끝으로 확정 것에 논리적 오류가 있는 것 같다.

풀이 2
1. 목적지가 양 끝단에 있는 경우 외에 목적지가 가운데 있는 경우도 고려한다.
    목적지가 가운데 있으면서 벌들이 같은 방향에 있을 경우 목적지가 끝에 있는것보다 무조건 작기 때문에 고려하지 않는다.
    목적지가 가운데 있다면 벌들은 양 끝단에 위치해야 최대가 된다.
2. 각 케이스의 최대값을 구하기 위해 누적 합을 미리 구해놓고 시용한다.
-> 목적지와 출발지가 같을 수 있다는 생각을 풀었는데 그렇게 풀거면 그 경우에 대한 식은 따로 만들어야 한다.
*/

#include <iostream>
#include <vector>

using namespace std;

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    cin >> n;

    vector< long long > honey(n);
    vector< long long > acc(n,0);

    cin >> honey[0];
    acc[0] = honey[0];
    for(int i = 1; i < n; i++){
        cin >> honey[i];
        acc[i] = acc[i-1] + honey[i];
    }

    long long max = 0;
    // 목적지가 가장 오른쪽에 있고 벌 한마리는 가장 왼쪽에 있는 경우
    // 벌 1 = acc[n-1] - honey[0] - honey[i], 벌2 = acc[n-1] - acc[i]
    for(int i = 1; i < n-1; i++){
        long long tmp = acc[n-1] - honey[0] - honey[i] + acc[n-1] - acc[i];
        if(max < tmp) max = tmp;
    }

    // 목적지가 가장 왼쪽에 있고 벌 한마리는 가장 오른쪽에 있는 경우
    // 벌 1 = acc[n-1] - honey[n-1] - honey[i], 벌 2 = acc[i-1]
    for(int i = n-2; i >= 1; i--){
        long long tmp = acc[n-2] - honey[i] + acc[i-1];
        if(max < tmp) max = tmp;
    }

    // 목적지가 가운데 있고 벌들이 양 끝에 있는 경우
    // 벌1 + 벌2 = acc[n-1] - honey[0] - honey[n-1] + honey[i];
    for(int i = 1; i < n-1; i++){
        long long tmp = acc[n-2] - honey[0] + honey[i];
        if(max < tmp) max = tmp;
    }

    // 목적지 == 출발지인 경우는 따로 판단한다. (case1, case2, case3 모두 같은 값)
    if(max < acc[n-1]-honey[0]-honey[n-1]) max = acc[n-1]-honey[0]-honey[n-1]; // 목적지 == 출발지

    printf("%lld\n",max);

}