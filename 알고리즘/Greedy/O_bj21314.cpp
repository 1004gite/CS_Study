/*
새로운 수 체계가 있다.
M,K로 이루어진 이 체계에서 M의 개수는 몇자리 수인지를 나타내고 (MM = 10)
K는 수의 가장 큰자리가 5임을 나타낸다.(K는 수의 가장 끝에만 올 수 있다.)
또, 이 수체계로 여러가지 수를 이어붙였을 경우 문자열처럼 이어붙일 수 있다.
어떤 수가 주어졌을 때 가능한 최대/최소값을 구하자

풀이 1
0. K는 항상 어떤 수의 끝을 의미한다.
1. M과 K가 붙어있는 경우 하나의 수로 취급하면 5가 가장 큰자리고 가기 때문에 수가 커진다.
2. M만 여러개 붙어있는 경우 각각 하나의 수로 보면 수가 더 커진다.
3. 즉, 최대값을 만들기 위해서는 k가 나온 곳까지 하나의 수로 보고 k가 끝까지 나오지 않으면 각각의 수로 본다.
    최솟값을 만들기 위해서는 K가 나오기 전까지 하나의 수로 보고 k는 각각 하나의 수로 본다.
*/

#include <iostream>
#include <string>

using namespace std;

string trans(string ex){
    if(ex == "") return "";
    
    string result = "";
    if(ex.back() == 'K') result += "5";
    else result += "1";

    int tmp = ex.size()-1;
    while(tmp-- > 0) result += "0";

    return result;
}

int main(void){
    string expression;
    cin >> expression;

    string max = "", tmp = "";
    for(int i =0; i < expression.size(); i++){
        // max 처리
        if(expression[i] == 'K'){
            tmp += 'K';
            max += trans(tmp);
            tmp = "";
        }
        else {
            tmp += 'M';
        }
    }

    // 마지막에 대한 처리
    if(tmp != ""){
        // 전부 M인 경우가 남았다.
        // 모든 M을 각각 1로 한다.
        int size = tmp.size();
        while(size-- > 0){
            max += "1";
        }
    }

    string min = "";
    tmp = "";
    for(int i =0; i < expression.size(); i++){
        // min 처리
        if(expression[i] == 'K'){
            // K이전 M들을 처리 후 K를 따로 처리
            min += trans(tmp);
            tmp = "";
            min += "5";
        }
        else {
            tmp += 'M';
        }
    }
    // 마지막에 대한 처리
    if(tmp != ""){
        // 전부 M인 경우
        min += trans(tmp);
    }

    printf("%s\n%s\n",max.c_str(), min.c_str());
}