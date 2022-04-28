#include <iostream>
#include <queue>

using namespace std;

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n,card =1;
    cin >> n;

    queue<int> q;

    while(n--){
        q.push(card++);
    }

    while(q.size() > 1){
        q.pop();
        q.push(q.front());
        q.pop();
    }
    printf("%d\n",q.front());
}