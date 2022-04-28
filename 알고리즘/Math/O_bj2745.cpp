/*
n진법을 10진법으로 바꾸는 문제

풀이 1
1. 알파벳이 들어오면 char - char를 이용해 숫자로 변환한 뒤 10을 더한다.
2. 각 자리수에는 (진법^자리수)를 곱해준다.
*/

#include <iostream>
#include <string>

using namespace std;

int main(void){

    string x;
    int n;
    cin >> x >> n;

    int result = 0;
    int mul = 1;

    for(int i = x.size()-1; i >= 0; i--){
        int num;
        if('A' <= x[i] && x[i] <= 'Z'){
            num = (int)(x[i]-'A') + 10;
        }
        else{
            num = (int)(x[i]-'0');
        }
        result += num*mul;
        mul *= n;
    }
    printf("%d\n",result);
}