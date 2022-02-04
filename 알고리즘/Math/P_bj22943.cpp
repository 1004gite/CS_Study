/*
0~9 까지의 수를 최대 1번씩 사용하여 이어붙여 k자리의 수를 만든다.
1. 이때 이 수는 다른 두개의 소수의 합으로 나타낼 수 있어야 하고
2. 주어진 M으로 나머지연산한 값이 두 소수(같아도 됨)의 곱이여야 한다.

풀이 1
1. 0부터 9끼지의 수를 한번씩 이용하여 k자리의 수를 만드는 가지수는 맨앞에 0이 올 수 없기 때문에 (9 * 9 * 8 * ... * (10-k+1))개이다.
(최대 9*9*8*7*6*5*4)
2. 만들 수 있는 가장 큰 수는 98765 이다. 즉, 98765이하의 소수에서 모든 판단이 가능하다.

3. 만든 값을 M으로 나머지연산 후 소수로 나눠서 몫이 소수인지 확인한다. -> 문제 잘못읽음
(해당 수의 제곱근이하까지의 소수에만 검사한다.)
==> 문제에서는 정확히 M으로 더이상 나누어 떨어지지 않을 때까지 나누라고 되어있다. (마음대로 해석하지 말자...)

4. 3번 조건을 통과했다면 서로 다른 소수의 합으로 나타낼 수 있는지 확인한다.
(가장 작은 소수부터 시작하여 해당 값에서 소수를 뺀 값이 다른 소수인지 판단한다.)
(이떄 검사는 해당 값의 절반미만까지 한다.)
*/

#include <iostream>
#include <vector>

using namespace std;

vector<bool> table(98765+1,true);
vector<int> primes;

bool check(int number){
    vector<int> nums;
    while(number > 0){
        nums.push_back(number%10); // 일의자리 넣음
        number /= 10; // 한칸 땡김
    }

    // 최대 5자리임으로 brute force하게 비교
    for(int i = 0; i < nums.size(); i++){
        for(int j = i; j < nums.size();j++){
            if(i == j) continue;
            if(nums[i] == nums[j]) return false;
        }
    }
    return true;
}

bool check1(int number, int m){
    // m으로 나머지연산한 결과가 두 소수의 곱인지 판단.
    while(number%m == 0 || number == 0){
        number /= m;
    }
    for(int i = 0; i < primes.size(); i++){
        if(primes[i]*primes[i] > number) break; //제곱근을 넘어가면 그만 검사
        if(number%primes[i] == 0){
            //나누어 떨어지면 몫이 소수인지 검사
            if(table[number/primes[i]]){
                return true;
            }
        }
    }
    return false;
}

bool check2(int number){
    // 서로다른 두개의 소수의 합으로 나타낼 수 있는지 판단
    for(int i = 0; i < primes.size(); i++){
        if(primes[i]*2 >= number) break; // 절반까지 왔다면 이미 탈락
        if(table[number-primes[i]]) return true;
    }
    return false;
}

int main(void){

    //소수 테이블 생성
    table[0] = false;
    table[1] = false;
    for(int i = 2; i < 400; i++){ // 98765 < 400*400
        if(table[i]){
            for(int mul = 2; mul*i <= 98765; mul++){
                table[mul*i] = false;
            }
        }
    }
    for(int i = 2; i <= 98765; i++){
        if(table[i]) primes.push_back(i);
    }

    int k, m;
    cin >> k >> m;

    int number = 0;
    int checknum = 1;
    for(int i = 0; i < k; i++){
        // number를 k자리의 99... 으로 만듦
        number *= 10;
        number += 9-i;
        checknum *= 10;
    }
    checknum /= 10;

    int count = 0;

    while(1){
        // 일의 자리의 숫자를 하나씩 감소시키며 수를 조합한다.
        // 이때 다른 자리의 숫자와 겹칠 수 없다.
        // 0이면 다음자리의 숫자를 겹치지 않게 내리고 본인을 9로 만든다. (이때 9가 겹치면 겹치지 않을 때까지 내린다.)

        // 종료조건
        if(checknum > number) break;

        // 현재 수 판단
        if(check1(number,m) && check2(number)){
            count ++;
            // printf("the num is %d\n",number);
        }

        // 가능한 가장 큰 수 만들기
        while(1){
            number--;
            if(check(number)) break;
        }

    }

    printf("%d\n", count);

}