#include <iostream>

using namespace std;

void printLine(int count){
    while(count--){
        printf("+-");
    }
    printf("+\n");
}

void printWall(int count){
    while(count--){
        printf("|.");
    }
    printf("|\n");
}

int main(void){
    int t;
    cin >> t;

    int rows[t+1];
    int cols[t+1];

    for(int i = 1; i <= t; i++){
        cin >> rows[i] >> cols[i];
    }

    for(int i = 1; i <=t; i++){
        int r= rows[i],c=cols[i];

        //제목
        printf("Case #%d:\n",i);

        // 첫줄
        printf("..");
        printLine(c-1);
        printf("..");
        printWall(c-1);

        //둘째줄부터
        while(--r){
            printLine(c);
            printWall(c);
        }

        printLine(c);
    }
}