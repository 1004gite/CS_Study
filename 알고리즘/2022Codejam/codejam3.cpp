#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

bool cmp(int a, int b){
    return a < b;
}

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int t;
    cin >> t;

    for(int T=1; T <= t; T++){

        int n;
        cin >> n;
        vector<int> dices(n);
        for(int i = 0; i < n; i++){
            cin >> dices[i];
        }

        sort(dices.begin(), dices.end(),cmp);

        int num = 0;
        for(int i = 0; i < n; i++){
            if(dices[i] <= num) continue;
            num++;
        }
        printf("Case #%d: %d\n",T,num);
    }
}