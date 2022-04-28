#include <iostream>
#include <queue>

using namespace std;

/*
요세푸스 순열 문제
queue를 이용하여 문제의 논리 그대로를 구현하여 해결
", "와 "\n"을 고려해야함
*/

int main(void){
    int n,k;
    queue<int> Q;
    cin >> n >> k;
    
    for(int i =1; i <= n;i++){
        Q.push(i);
    }

    printf("<");
    while(1){
        int index = k-1;
        while(index){
            Q.push(Q.front());
            Q.pop();
            index --;
        }
        printf("%d",Q.front());
        Q.pop();
        if(Q.empty()){
            break;
        }
        printf(", ");
    }
    printf(">\n");
}