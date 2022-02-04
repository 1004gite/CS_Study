/*
'X'와 '.'으로 이루어진 판이 있다. ex) XXX.XX
이 판의 X부분만 'AAAA'와 'BB'로 덮을 수 있는 경우를 시전순으로 출력하자

풀이 1
1. 연속된 X의 개수를 세고 저장한다.
    이때 .은 0개로 표시한다.
2. 홀수개인 개수가 있으면 -1
3. 모두 짝수개라면 AAAA가 가능한만큼 출력 후 BB를 출력한다. 
*/

#include <iostream>
#include <vector>
#include <string>

using namespace std;

int main(void){

    string board;
    cin >> board;

    vector<int> n;

    int count = 0;
    for(int i =0; i < board.size(); i++){
        if(board[i] == 'X') count++;
        else{
            // 지금까지 연속된 x의 개수
            if(count != 0) n.push_back(count);
            n.push_back(0); //이번에 나온 점
            count = 0;
        }
    }
    if(board.back() == 'X'){
        n.push_back(count);
    }

    string result = "";
    for(int i = 0; i < n.size(); i++){
        if(n[i] % 2 != 0){
            printf("-1\n");
            return 0;
        }
        if(n[i] == 0){
            result += ".";
        }
        else{
            while(n[i] >= 4){
                result += "AAAA";
                n[i] -= 4;
            }
            while(n[i] >= 2){
                result += "BB";
                n[i] -= 2;
            }
        }
    }

    printf("%s\n",result.c_str());
}