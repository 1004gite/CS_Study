/*

총정리 
- 소수는 평균적으로 log N 마다 나온다 -> O( 루트N * log N )
    - 에라스토테네스의 체로 소수를 구하고 시작하면 소수를 구하는데만 O(N log N)정도 걸린다.
    ==> 하나의 소수만 다룰 때는 하나씩 판단하는 것이 효과적이다.
- cin.tie, sync_with_stdio를 잊지 말자


정수 n이 주어졌을 때 n보다 크거나 같은 소수 중 가장 작은 소수 출력
n은 최대 4*10^9 이다. -> int로 담을 수 없다.

풀이 1 -> 시간 초과
1. 소수 리스트를 기지고 시작한다.
2. 처음에는 2만 들어있고 3부터 소수 리스트에 있는 소수들과 나누어 떨어지는지 확인하며 전진한다
    이때 나누어 떨어지지 않으면 소수 리스트에 추가한다.

풀이 2 -> 시간초과
1. n까지의 소수를 모두 구한 후 n 이후부터 풀이 1로 검사한다.

풀이 3 -> 메모리 초괴
1. 소수는 평균적으로 logN 마다 한번씩 나온다.
2. long long의 범위 안에 소수가 나온다고 가정하고 에라스토테네스의 체를 이용한다.

풀이 4 -> 시간초과
1. 2번 풀이에서 소수의 크기가 루트n 이상이면 검사할 필요가 없다.

풀이 5
1. 그냥 n부터 brute force하게 하되 검사범위에 신경써서
*/

#include <iostream>
#include <vector>

using namespace std;

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int t;
    cin >> t;
    while(t--){
        unsigned long long n;
        cin >> n;

        if(n <= 2){
            printf("2\n");
            continue;
        }
        
        while(1){
            bool check = true;
            for(unsigned long long i = 2; i*i <= n; i++){
                if(n % i == 0){
                    check = false;
                    break;
                }
            }
            if(check){
                printf("%llu\n",n);
                break;
            }
            n++;
        }
    }
}