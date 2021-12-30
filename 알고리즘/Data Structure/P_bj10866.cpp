#include <iostream>
#include <string>

using namespace std;

/*
만약 배열의 크기에 제한이 있다면 front의 index가 가장 앞에 있을 때
끝으로 돌아가는 식으로 구현하자

front는 현재 원소가 들어있는 index를
end는 다음에 원소가 들어갈 자리의 index를 가리키고 있다는거
생각하면서 실수하지 말자

cin.tie(0);
cin.sync_with_stdio(0);
사용해서 timeout 안나게 하자
아니면 scanf를 쓰자
*/

int main(void){
    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    cin >> n;

    int deque[2*n];
    int front =n-1, end =n-1;

    while(n--){
        string command;
        cin >> command;
        if(command == "push_front"){
            int x;
            cin >> x;
            deque[--front] = x;
        }
        else if(command == "push_back"){
            int x;
            cin >> x;
            deque[end++] = x;
        }
        else if(command == "pop_front"){
            if(end == front){
                printf("-1\n");
            }
            else{
                printf("%d\n",deque[front++]);
            }
        }
        else if(command == "pop_back"){
            if(end == front){
                printf("-1\n");
            }
            else{
                printf("%d\n",deque[--end]);
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
            if(end == front){
                printf("-1\n");
            }
            else{
                printf("%d\n",deque[front]);
            }
        }
        else if(command == "back"){
            if(end == front){
                printf("-1\n");
            }
            else{
                printf("%d\n",deque[end-1]);
            }
        }
    }

}