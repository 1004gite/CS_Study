#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n,m;
    cin >> n;
    cin >> m;

    vector<string> s(n);

    int tmp = n;
    while(tmp--){
        cin >> s[tmp];
    }
    sort(s.begin(), s.end());

    int result = 0;
    while(m--){
        string target;
        cin >> target;
        int start=0, end = n-1;
        while(end >= start){
            int index = (end-start)/2 + start;
            int cmp = target.compare(s[index]);
            if(cmp == 0){
                result++;
                break;
            }
            else if(cmp < 0){
                end = index-1;
            }
            else if(cmp > 0){
                start = index +1;
            }

        }
    }

    printf("%d\n",result);

}