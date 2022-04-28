#include <iostream>
#include <string>

using namespace std;

int main(void){
    /*
    시간초과 문제를 해결하기 위함
    cin.tie(0)
    cin.sync_with_stdio(0)

    -> scanf,printf를 사용하는 방법도 가능
    */
    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    cin >> n;
    int Q[n];
    // front는 가장 처음 들어온 원소의 index를 가리킨다.
    // end는 새로 들어올 원소의 자리를 가리킨다.
    // end는 가장 최근에 들어온 원소의 index+1
    // 해당 문제에서는 queue가 넘칠 일이 없으므로 꽉차는 경우는 고려하지 않는다.
    int front = 0, end = 0;

    while(n--){
        string command;
        cin >> command;
        if(command == "push"){
            int x;
            cin >> x;
            Q[end++] = x;
        }
        else if(command == "pop"){
            if(front == end){
                printf("-1\n");
            }
            else {
                printf("%d\n",Q[front++]);
            }
        }
        else if(command == "size"){
            printf("%d\n",end-front);
        }
        else if(command == "empty"){
            if(end == front){
                printf("1\n");
            }
            else{
                printf("0\n");
            }
        }
        else if(command == "front"){
            if(front == end){
                printf("-1\n");
            }
            else {
                printf("%d\n",Q[front]);
            }
        }
        else if(command == "back"){
            if(front == end){
                printf("-1\n");
            }
            else {
                printf("%d\n",Q[end-1]);
            }
        }
    }
    return 0;
}