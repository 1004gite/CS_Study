#include <iostream>
#include <cstring>

using namespace std;

int main(void){
    int t;
    cin >> t;

    for(int i = 1; i <= t; i++){
        
        // 프린터들의 잉크색별 최솟값 저장
        int min[4];
        memset(&min[0],1000000,sizeof(int)*4);

        int tmp;
        for(int printer = 3; printer > 0; printer--){
            for(int x = 0; x < 4; x++){
                cin >> tmp;
                if(min[x] > tmp) min[x] = tmp;
            }
        }

        int acc = 0;
        for(int x = 0; x < 4; x++){
            if((acc+min[x]) >= 1000000){
                // x번째 색은 딱 맞추고 그 이후는 0으로
                min[x] = 1000000 - acc;
            }

            acc += min[x];
        }

        if(acc < 1000000){
            printf("Case #%d: IMPOSSIBLE\n",i);
        }
        else{
            printf("Case #%d:",i);
            for(int x = 0; x < 4; x++){
                printf(" %d",min[x]);
            }
            printf("\n");
        }

    }
}