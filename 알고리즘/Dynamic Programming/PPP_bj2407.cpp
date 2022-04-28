/*
nCm 출력

풀이 1
1. factorial 개념이 들어가기 때문에 수가 매우 커질 수 있다.
2. nCm = n-1Cm-1 + n-1Cm 공식을 이용한다.
    nCm은 어떤 원소 x를 꼭 뽑는 case와 x를 무조건 뽑지 않는 case로 나눌 수 있다.
    n-1Cm-1은 어떤 원소를 이미 뽑아놓고 나머지 n-1개에서 m-1개를 뽑는 것을 의미하고 -> x를 무조건 뽑음
    n-1Cm은 어떤 원소를 절때 뽑지 않는 상황에서 n-1개 중 m개를 뽑는 것을 의미한다. -> x를 무조건 뽑지 않음
3. nCm에서 m은 n의 절반을 기준으로 대칭이다.
4. 100C50의 경우 long long의 범위도 훤씬 넘어가기 때문에 string으로 관리하자
*/

#include <iostream>
#include <vector>
#include <string>
using namespace std;

string strAdd(string a, string b){
    if(a.size() < b.size()){
        string tmp = a;
        a = b;
        b = tmp;
    }

    int add = 0;
    int iA =a.size()-1, iB =b.size()-1;
    while(iB >= 0){
        int t = (int)(a[iA]-'0') + (int)(b[iB]-'0') + add;
        if ( t >= 10){
            a[iA] = (char)(t-10+'0');
            add = 1;
        }
        else{
            a[iA] = (char)(t+'0');
            add = 0;
        }
        iA--;
        iB--;
    }
    while(add != 0){
        if(iA < 0){
            a = "1" + a;
            break;
        } 
        int t = (int)(a[iA]-'0') + add;
        if ( t >= 10){
            a[iA] = (char)(t-10+'0');
            add = 1;
        }
        else{
            a[iA] = (char)(t+'0');
            add = 0;
        }
        iA--;
    }
    return a;
}

int main(void){

    int n,m;
    cin >> n >> m;
    if(n%2 == 0){
        if(m > n/2){
            m = n/2 - (m-n/2);
        }
    }
    else{
        if( m > n/2){
            m = n/2 - (m-n/2) + 1;
        }
    }

    vector< vector<string> > dp(n+1, vector<string> (m+1,"1"));
    for(int i = 1; i <= n; i++){
        dp[i][1] = to_string(i);
    }

    for(int i = 1; i <= n; i++){
        for(int j = 2; j <= m; j++){
            // xCx의 경우 미리 1으로 세팅되어있어 할 필요가 없고
            // 범위를 넘어가는 값도 1로 설정되어있어 해서는 안됨
            if( j >= i) break;
            dp[i][j] = strAdd(dp[i-1][j-1], dp[i-1][j]);
            // printf("%dC%d = %llu\n",i,j,dp[i][j]);
        }
    }

    printf("%s\n",dp[n][m].c_str());
}