#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n,n_t;
    
    cin >> n;
    cin >> n_t;

    // 포켓몬 번호가 들어오면 바로 index로 출력하기 위함
    vector<string> forindex(n);
    // 포켓몬 이름이 들어오면 사전순으로 sort된 fornum을 이용해서 O(logN)으로 출력
    vector< vector<string> > fornum(n);

    for(int i = 0; i < n; i++){
        cin >> forindex[i];
        vector<string> tmp(2);
        tmp[0] = forindex[i]; // 포켓몬 이름
        tmp[1] = to_string(i+1); //포켓몬 번호
        fornum[i] = tmp;
    }

    sort(fornum.begin(),fornum.end());

    while(n_t--){
        char tmp[21];
        cin >> tmp;
        string conv;
        conv = tmp;
        if( '0' <= tmp[0] && tmp[0] <= '9'){
            // 번호인 경우 포켓몬 이름을 출력
            int index = stoi(conv) - 1;
            printf("%s\n",forindex[index].c_str());
        }
        else{
            // 포켓몬 이름인 경우 번호를 출력
            int start = 0, end = fornum.size()-1;
            while(start <= end){
                int index = (end-start)/2 + start;
                int cmp = conv.compare(fornum[index][0]);
                if(cmp == 0){ // 찾은 경우
                    printf("%s\n",fornum[index][1].c_str());
                    break;
                }
                else if(cmp < 0){ // 사전적으로 더 앞에있는 경우
                    end = index-1;
                }
                else if(cmp > 0){ // 사전적으로 더 뒤에 있는 경우
                    start = index+1;
                }
            }
        }
    }

}
