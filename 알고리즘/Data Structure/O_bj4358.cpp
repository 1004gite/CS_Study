#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

// struct Tree{
//     string name;
//     int num;

//     Tree(string name, int num) : name(name), num(num) {}
// };
// struct cmp{
//     bool operator()(Tree a, Tree b){
//         return a.name.compare(b.name);
//     }
// };

bool cmp(string a, string b){
    // 사전순으로 정렬
    if(a.compare(b) < 0){
        // a가 사전순으로 먼저 나온다면 a를 먼저 쓰겠다는 의미
        return true;
    }
    return false;
}

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    // priority_queue<Tree, vector<Tree>, cmp> pQ;

    vector<string> v;

    string tmp;
    while(1){
        getline(cin,tmp);
        if(tmp[0] == '\0'){
            break;
        }
        v.push_back(tmp);
    }

    // 사전순으로 우선 정렬
    sort(v.begin(), v.end(), &cmp);

    int total = v.size();
    int count = 0;
    for(int i = 0; i < total-1; i++){
        count++;
        if(v[i] != v[i+1]){
            // 같은 종류가 끝나는 지점
            double t,c,result;
            t = total;
            c = count;
            result = c/t;
            result = round(result*1000000); // 백분율로 나타낼 것이기 때문에 6자리까지 살려야함
            result /= 10000; // 4자리를 내려줌
            printf("%s %.4f\n",v[i].c_str(), result);
            count = 0;
        }
    }


    if(v[total-1] != v[total-2]){
        // 마지막 한개가 한개짜리 나무인지 확인
        double t,result;
        t = total;
        result = 1/t;
        result = round(result*1000000);
        result /= 10000;
        printf("%s %.4f",v[total-1].c_str(), result);
    }
    else{
        // 마지막까지 같은 나무일때 출력
        double t,c,result;
        t = total;
        c = ++count;
        result = c/t;
        result = round(result*1000000); // 백분율로 나타낼 것이기 때문에 6자리까지 살려야함
        result /= 10000; // 4자리를 내려줌
        printf("%s %.4f\n",v[total-1].c_str(), result);
    }

}