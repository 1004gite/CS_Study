/*
다리를 n개 건설하려 한다.
서쪽에는 n개의 자리가 있고 동쪽에는 n개보다 많은 (m개의)자리가 있다.
다리끼리는 서로 겹칠 수 없을 때 가능한 경우의 수를 구하자

풀이 1
1. 다리가 서로 겹칠 수 없기 때문에 순서를 유지해야 한다.
2. m개중에서 어느 다리를 뽑든 반대편과 이어지는 곳은 순서대로 정해지므로
    m개 중 n개를 뽑는 경우의 수를 구한다.
    -> mCn
3. 30!은 수가 많이 크기 떄문에 brute force하게 factorial을 구하면 범위를 넘어갈 수 있다.
4. n+1Cr+1 = nCr + nCr+1을 이용한다.
    또, 조합의 경우 절반을 기준으로 대칭이다.
*/

#include <iostream>
#include <vector>

using namespace std;

int main(void){
    
    int t;
    cin >> t;

    while(t--){
        int n, m;
        cin >> n >> m;
        if(n %2 == 0 && n > m/2){
            n = m/2-(n-m/2);
        }
        else if(n %2 != 0 && n > (m+1)/2){
            n = (m+1)/2-(n-(m+1)/2);
        }


        vector< vector<long long> > mn(m+1,vector<long long>(n+1,1));
        // mCn에서 n이 0이면 1, n이 m이면 m의 값을 갖는다.
        for(int i = 1;i<=m;i++){
            mn[i][1] = i;
        }
        for(int i = 2; i <= m; i++){
            for(int j = 1; j <= n;j++){
                if(j >= i) break;
                mn[i][j] = mn[i-1][j-1] + mn[i-1][j];
                // printf("%d C %d = %lld\n",i,j,mn[i][j]);
            }
        }
        printf("%lld\n",mn[m][n]);

    }
}